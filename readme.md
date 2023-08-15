# Function Instructions

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

Make sure to have AZURE CLI:  
Install with:  
```
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash
```
https://learn.microsoft.com/en-us/cli/azure/install-azure-cli-linux?pivots=apt  
Test with:
```
az login
```
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
* To deploy from command line set modify the azurefunctions configuration of build.gradle to match resource group, function name and region as listed in Azure and then call
`gradle azureFunctionsDeploy`  
If the deployment fails due to some form of unauthorised use "az login" as above (requires AZURE CLI)

# EventHub

Publishing to eventHub directly:  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-java-get-started-send?tabs=passwordless%2Croles-azure-portal  
Publishing through bindings:  
https://learn.microsoft.com/en-us/azure/azure-functions/functions-bindings-event-hubs-output?tabs=python-v2%2Cin-process%2Cfunctionsv2%2Cextensionv5&pivots=programming-language-java
Annotation can go on function as in the sample above (then it refers to return value) or to function parameter as in HttpTriggerJavaToEventHub.
How to obtain a connection string:  
https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string
