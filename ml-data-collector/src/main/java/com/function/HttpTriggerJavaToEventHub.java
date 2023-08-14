package com.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerJavaToEventHub {
    /**
     * This function listens at endpoint "/api/HttpTriggerJavaToEventHub". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerJava1
     * 2. curl {your host}/api/HttpTriggerJava1?name=HTTP%20Query
     */
    @FunctionName("HttpTriggerJavaToEventHub")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @EventHubOutput(name = "eventMessage", eventHubName = "test-topic", connection = "AzureEventHubConnection") OutputBinding<String> eventMessage,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger for event hub processed a request: " + request.getBody());

        // Parse query parameter
        Optional<String> optMessage = request.getBody();
        if (optMessage.isPresent()) {
            eventMessage.setValue(optMessage.get());
            return request.createResponseBuilder(HttpStatus.OK).body("Message: " + optMessage.get()).build();
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Include body in the request to be sent as Event queue message").build();
        }
    }
}
