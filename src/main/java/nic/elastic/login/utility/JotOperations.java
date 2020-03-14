package nic.elastic.login.utility;
import java.util.Date;
import java.util.UUID;

import static nic.elastic.login.config.LoginConstants.SECRET_KEY;

import java.security.Key;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
public class JotOperations 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(JotOperations.class);
	private static String jwt;
	static Key signingKey;
	//Sample method to construct a JWT
	public static String createJWT(String userName, String role)
	{
		String id = UUID.randomUUID().toString().replace("-", "");
		String issuer = "IVFRT";
		Date exp = new Date(System.currentTimeMillis() + (1800000)); //1800000 milliseconds - 30 mins
		Date now = new Date(System.currentTimeMillis());
		String subject = "sform_login";
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		//Let's set the JWT Claims
		String token = Jwts.builder()
				.setId(id)
				.setSubject(subject)
				.setHeaderParam("typ", "jwt")
				.claim("userName", userName)
				.claim("role", role)
				.setIssuer(issuer)
				.setIssuedAt(now)
				.setNotBefore(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, signingKey )
				.compact();
		jwt = token;
		return "bearer "+token;
	}
	public static String splitJWT(String bearerJwt)
	{
		String[] splitJwt = bearerJwt.split(" ");
		String Bearer = splitJwt[0]; 
		String jwt = splitJwt[1]; 
		LOGGER.info(Bearer);
		LOGGER.info(jwt);
		return jwt;
	}
	public static String verifyJWT(String jwt) 
	{
		LOGGER.info("-------------------------- IN METHOD --- JWT--"+jwt);
		JwtParser jwtparser;
		Claims token = null;
		Jwt jwtVerifiedValue = null;
		String userName, role;
		//This line will throw an exception if it is not a signed JWS (as expected)
		jwtparser = Jwts.parser();
		Jws <Claims> claims = null;
		try
		{
			jwtVerifiedValue = jwtparser.setSigningKey(signingKey).parse(jwt);
			jwtparser.parseClaimsJws(jwt);
			Jws <Claims> jwtCLAIM = jwtparser.parseClaimsJws(jwt);
			claims = jwtCLAIM;
			token = jwtCLAIM.getBody();
		}
		catch(Exception e)
		{
			LOGGER.info("JWT Invalid");
			return "IV_JWT";
		}
		if(token!=null)
		{
			//the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC. 
			Date nowDate = new Date(System.currentTimeMillis());
			Date expiredDate = token.getExpiration();
			userName = token.get("userName").toString();
			role = token.get("role").toString();
			//the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this date. 
			long nowMillSec = nowDate.getTime() - expiredDate.getTime();
			if(nowMillSec<=300000)//5 minutes - 300,000 milliseconds
			{
				String generatedNewToken = JotOperations.createJWT(userName, role);
				String splitToken = JotOperations.splitJWT(generatedNewToken);
				String tokenValue = JotOperations.verifyJWT(splitToken);
				LOGGER.info("value"+ tokenValue);
				return "bearer "+tokenValue;
			}
			else 
			{
				return "bearer "+jwtVerifiedValue;
			}
		}
		else
		{
			return "NF_JWT";
		}
	}
	public static String destroyJWT(String bearerjwt)
	{
		return bearerjwt;
	}

}
