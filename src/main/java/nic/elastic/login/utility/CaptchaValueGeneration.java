package nic.elastic.login.utility;
public class CaptchaValueGeneration 
{
	public static String generateCaptchaTextMethod(int captchaLength)   
	{
		 String captchaChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	     StringBuffer captchaStrBuffer = new StringBuffer();
	     java.util.Random rnd = new java.util.Random();
	     // build a random captchaLength 
	     while (captchaStrBuffer.length() < captchaLength)
	     {
	    	 int index = (int) (rnd.nextFloat() * captchaChars.length());
	    	 captchaStrBuffer.append(captchaChars.substring(index, index+1));
	     }
	     return captchaStrBuffer.toString();
	 
	}
}
