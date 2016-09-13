import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.simple.JSONObject;

def GetRandomNumber(min,max)
{
	def n=Math.abs(new Random().nextInt() % (max-min)) + min;
	return n;
}


def GetRandomString( int length)
{
	String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numberChar = "0123456789";

	StringBuffer sb = new StringBuffer();
	Random random = new Random();

	for (int i = 0; i < length; i++) 
	{
		sb.append(allChar.charAt(random.nextInt(allChar.length())));
	}
	return sb;
}

def uid=GetRandomNumber(10000000,99999999).toString();
log.info uid;
context.setProperty("userId",uid);

def sessionId = GetRandomString(30).toString();
log.info sessionId;
context.setProperty("SessionId",sessionId);

Date today = new Date();
String todayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);
log.info todayTime;
context.setProperty("sendTime",todayTime);

JSONObject obj = new JSONObject();
obj.put("Result", "Login Successfully");
obj.put("UserId", uid.toInteger());
obj.put("LoginTime", todayTime);
def response = obj.toString();
context.setProperty("responseMsg",response);
log.info response;