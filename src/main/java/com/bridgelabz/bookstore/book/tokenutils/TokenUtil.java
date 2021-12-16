package com.bridgelabz.bookstore.book.tokenutils;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
@Slf4j
public class TokenUtil 
{
	
	private static final String TOKEN_SECRET = "Shreyas";

	public String createToken(long id)   
	{
	       try {
	        //to set algorithm
	    	   System.out.println("print"+id);
	    	log.debug("Token creation");
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        String token = JWT.create()
	        .withClaim("user_id", id)
	        .sign(algorithm);
	        return token;
	        } catch (JWTCreationException exception) {
	        exception.printStackTrace();
	           //log Token Signing Failed
	        } catch (IllegalArgumentException e) {
	// TODO Auto-generated catch block
	        		e.printStackTrace();
	        }
	       return null;
	 	}
	
	public long decodeToken(String token)
		 {
		 	long userid;
		           //for verification algorithm
	    	log.debug("Token verification");
		    Verification verification = null;
			try {
				verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
			} 
			catch (IllegalArgumentException  e) {
				e.printStackTrace();
			}
		     JWTVerifier jwtverifier=verification.build();
		           //to decode token
		     DecodedJWT decodedjwt=jwtverifier.verify(token);
	
		     Claim claim=decodedjwt.
		     getClaim("user_id");
		     userid=claim.asLong();    
		     return userid;
		     
		 }

}
