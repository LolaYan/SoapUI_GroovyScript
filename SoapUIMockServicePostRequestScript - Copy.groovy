import java.util.Date;
import java.text.SimpleDateFormat;

def groovyUtils = new com.eviware.soapui.support.GroovyUtils(context)
def currentDate = new Date().format("yyyy-MM-dd hh:mm:ss");
def projectDir = groovyUtils.projectPath
//Create Path
def resultDir = new File(projectDir+"/data");
if(!resultDir.exists()) 
{
	resultDir.mkdirs();
}
//Create Folder/File
def resultsFile = new File(resultDir,'MockSeriveMonitor_All.csv')
def resultsFile_Phone = new File(resultDir,'MockSeriveMonitor_Phone.csv')
if(!resultsFile.exists() ) {
	resultsFile.createNewFile();
	resultsFile.write("");
}
if(!resultsFile_Phone.exists() ) {
	resultsFile_Phone.createNewFile();
	resultsFile_Phone.write("");
}
//Collect Time Data
Date today = new Date();
String todayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);
log.info todayTime;

//Collect Request and Response Data
request = mockResult.getMockRequest() 
response = mockResult.getMockResponse() 
requestHeader = request.getHttpRequest() 
requestBody = request.getRequestContent() 
//"Request Method Info : "+
requestMethod = request.getMethod()
//"Request Path Info : "+
requestpath = request.getPath()
requestQueryString= request.getRequest().getQueryString()
//"Response Header Info: "+
responseHeader = request.getHttpResponse() 
//"Response Content Info: "+
responseBody =  mockResult.getResponseContent() 
tag = "-----@ "+todayTime+"----------------------"
log.info tag
log.info "Request Header Info : "+requestHeader
log.info "Request Content Info : "+requestBody
log.info "Response Header Info: "+responseHeader
log.info "Response Content Info: "+responseBody

//Record all data
resultsFile.append(tag+'\r\n');
resultsFile.append("Request Header Info : "+requestHeader+'\r\n');
resultsFile.append("Request Content Info : "+requestBody+'\r\n');
resultsFile.append("Response Header Info: "+responseHeader+'\r\n');
resultsFile.append("Response Content Info: "+responseBody+'\r\n');

//Record certain data
def phone = requestQueryString.replaceFirst( /.*to=(\w+).*/, '$1' )
log.info "phone:"+phone
resultsFile_Phone.append("@"+todayTime+" "+requestMethod+" "+requestpath+' Phone:'+phone+'\r\n');
