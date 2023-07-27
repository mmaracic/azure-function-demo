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