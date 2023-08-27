# Function Instructions

## Setup

Make sure that JAVA_HOME and GRADLE_HOME are set up (in ~/.profile):
```
export GRADLE_HOME="/opt/gradle/gradle-8.2.1/bin"
export JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64/bin"
```
Make sure that .NET SDK is installed  (needed for EventHub extensions and possibly generally when "Step 8 of 8: Installing function extensions if needed" is failing)
```
sudo apt-get install -y dotnet-sdk-7.0
```
https://learn.microsoft.com/en-us/dotnet/core/install/linux-ubuntu-2204 

For windows download .NET SDK from https://dotnet.microsoft.com/en-us/download and install.

Make sure to have AZURE CLI:  
Install with:  
```
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash
```
https://learn.microsoft.com/en-us/cli/azure/install-azure-cli-linux?pivots=apt  
For Windows:   
https://learn.microsoft.com/en-us/cli/azure/install-azure-cli-windows?tabs=azure-cli  
Test with:
```
az login
```
Login is also possible with:
```
az login --use-device-code
```
which enables login via any device or browser (not necessarily default) via url by using temporary code as intermediary.

Upgrade azure CLI with:
```
az upgrade
```
On Windows this will download MSI installer of newer cli version and install it. Terminal restart is necessary to make the update visible.

## Run
Functions can be run locally by pressing F5 or running:  
```
gradle azureFunctionsRun
```
For error in Visual Studio code when running locally (F5):  
* Cannot be loaded because running scripts is disabled on this system  
Use second answer from here:  
https://stackoverflow.com/questions/56199111/visual-studio-code-cmd-error-cannot-be-loaded-because-running-scripts-is-disabl/67420296#67420296

For error:  
* "Failed to get Azure Functions Core Tools version locally"  
Try to run function code when running locally (F5) and exten extensions will offer to install core tools. If the automatic install fails because of missing permissions cope the command and add sudo i.e. run
    ```
    sudo npm install -g azure-functions-core-tools@4
    ```
* Set security level in function java file  as authLevel = AuthorizationLevel.ANONYMOUS to avoid using security key  
* Security key if not using anonymous is generated on Azure and used as https://<APP_NAME>.azurewebsites.net/api/<FUNCTION_NAME>?clientid=<KEY_NAME>  
* To deploy from VS Code select "Azure Functions" -> "Deploy to function app", and select a function to deploy to.
THIS DEPLOYS THE FUNCTION WRONGLY, use command line as below  
* To deploy from command line set modify the azurefunctions configuration of build.gradle to match resource group, function name and region as listed in Azure and then call`gradle azureFunctionsDeploy`

If the deployment fails due to some form of unauthorised use "az login" as above (requires AZURE CLI)

## Stop
To stop function there is the stop button on the tollbar top center.  
Problem:   
Usually stopping the function still leaves JVM running so the JVM process needs to be killed manually.

# EventHub

Publishing to eventHub directly:  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-java-get-started-send?tabs=passwordless%2Croles-azure-portal  
Publishing through bindings:  
https://learn.microsoft.com/en-us/azure/azure-functions/functions-bindings-event-hubs-output?tabs=python-v2%2Cin-process%2Cfunctionsv2%2Cextensionv5&pivots=programming-language-java
Annotation can go on function as in the sample above (then it refers to return value) or to function parameter as in HttpTriggerJavaToEventHub.
How to obtain a connection string:  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string

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


