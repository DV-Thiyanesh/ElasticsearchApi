package nic.elastic.login.utility;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashingValue 
{
	public static String hash(String value) 
    { 
        try 
        { 
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(value.getBytes()); 
            // Convert byte array into signum representation (+ve = -1, -ve = -1, 0 = 0)
            BigInteger no = new BigInteger(1, messageDigest); 
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 64) 
            { 
                hashtext = "0" + hashtext; 
            } 
            // return the HashText 
            return hashtext; 
        } 
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) 
        { 
            throw new RuntimeException(e); 
        } 
    } 
}
