context.setProperty("GetEmail", new GetEmail());

class GetEmail{

	def GetEmailAddress()
	{
		def index=GetRandomNumber(1,9999999);
		def str=GetRandomLetter();
		def email ="CoreGameTest."+index+str+"@test.com"
	}

	def GetRandomNumber(min,max)
    	{
    	 def n=Math.abs(new Random().nextInt() % (max-min)) + min;
    	 return n;
    	}

	def GetRandomLetter()
    	{
    		def charArr=['test', 'lotto', 'hello', 'nz','auckland' ];
    		def k=GetRandomNumber(0,5);
    		def charS=charArr.getAt(k).toString();
    		
    		return charS
    	}
}
def email=context.GetEmail.GetEmailAddress();

log.info email
testRunner.testCase.setPropertyValue( 'emailAddr',email);

def guidVal = "${java.util.UUID.randomUUID()}";
def deviceId=guidVal.toString();
testRunner.testCase.setPropertyValue( 'deviceId',deviceId);
log.info "deviceId:"+deviceId