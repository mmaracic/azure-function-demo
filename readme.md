# Instructions

For error in Visual Studio code when running locally (F5):  
* Cannot be loaded because running scripts is disabled on this system  
Use second answer from here:  
https://stackoverflow.com/questions/56199111/visual-studio-code-cmd-error-cannot-be-loaded-because-running-scripts-is-disabl/67420296#67420296

* Set security level in function java file  as authLevel = AuthorizationLevel.ANONYMOUS to avoid using security key  
* Security key if not using anonymous is generated on Azure and used as https://<APP_NAME>.azurewebsites.net/api/<FUNCTION_NAME>?clientid=<KEY_NAME>  
* To deploy from VS Code select "Azure Functions" -> "Deploy to function app", and select a function to deploy to.
THIS DEPLOYS THE FUNCTION WRONGLY, use command line as below  
* To deploy from command line set modify the azurefunctions configuration of build.gradle to match resource group, function name and region as listed in Azure and then call
`gradle azureFunctionsDeploy`

# Tutorials
## Function apps    
Function version and extensions (for bindings and triggers) are listed in host.json file of the function project  
### Creation
https://learn.microsoft.com/en-us/azure/azure-functions/functions-create-first-java-gradle  
### Timeout and scaling
Scaling depends on Hosting plan
The timeout duration for functions in a function app is defined by the functionTimeout property in the host.json project file  
https://learn.microsoft.com/en-us/azure/azure-functions/functions-scale
### Triggers and bindings
Triggers cause a function to run. A trigger defines how a function is invoked and a function must have exactly one trigger. Triggers have associated data, which is often provided as the payload of the function.

Binding to a function is a way of declaratively connecting another resource to the function; bindings may be connected as input bindings, output bindings, or both. Data from bindings is provided to the function as parameters.

You can mix and match different bindings to suit your needs. Bindings are optional and a function might have one or multiple input and/or output bindings.

Triggers and bindings let you avoid hardcoding access to other services. Your function receives data (for example, the content of a queue message) in function parameters. You send data (for example, to create a queue message) by using the return value of the function.  
https://learn.microsoft.com/en-us/azure/azure-functions/functions-triggers-bindings?tabs=java

## Messaging and events
Azure offers three services that assist with delivering events or messages throughout a solution. These services are:  
* Azure Event Grid
* Azure Event Hubs
* Azure Service Bus  
https://learn.microsoft.com/en-us/azure/service-bus-messaging/compare-messaging-services


### Event hubs  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-features   

Sending and receiving data:  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-java-get-started-send?tabs=passwordless%2Croles-azure-portal  

## Azure Stream Analytics
https://learn.microsoft.com/en-us/azure/stream-analytics/stream-analytics-introduction


