package nic.elastic.login.utility;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
public class RandomSaltGeneration 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(RandomSaltGeneration.class);
	public static String randCode(HttpServletRequest request, HttpSession session)
	{
			int n=32;
	        // length is bounded by 256 Character 
	        byte[] array = new byte[256]; 
	        new SecureRandom().nextBytes(array); 
	        String randomString = new String(array, Charset.forName("UTF-8")); 
	        // Create a StringBuffer to store the result 
	        StringBuffer r = new StringBuffer(); 
	        // remove all special char 
	        String  AlphaNumericString = randomString .replaceAll("[^A-Za-z0-9]", ""); 
	        // Append first 32 alphanumeric characters 
	        // from the generated random String into the result 
	        for (int k = 0; k < AlphaNumericString.length(); k++) 
	        {
	            if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0) || Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) 
	            { 
	                r.append(AlphaNumericString.charAt(k)); 
	                n--; 
	            } 
	        } 
	        // return the resultant string by encoding with base64
	        String originalInput =  r.toString();
	        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
	        System.out.println(originalInput);
	        System.out.println(encodedString);
	        if(request.getSession(true) != null)
			{
			session.setAttribute("genRandNo", originalInput);
			LOGGER.info("RAND VALUE IN SESSION - "+session.getAttribute("genRandNo"));
			}
	        return encodedString;
	        } 
}
