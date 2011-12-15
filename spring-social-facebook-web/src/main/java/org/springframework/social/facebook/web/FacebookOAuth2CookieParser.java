package org.springframework.social.facebook.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;

import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.util.json.JSONObject;
import com.google.gdata.util.common.util.Base64;
import com.google.gdata.util.common.util.Base64DecoderException;

/**
 * utilities class to parse an oauth2 facebook cookie
 * @author matthewreid
 */
public class FacebookOAuth2CookieParser{
	private static ObjectMapper objectMapper = new ObjectMapper();

	// return the fb user in the cookie.
	public static Map<String,String> parseFacebookCookie(Cookie[] cookies, String appKey, String appSecret) {
	    Cookie fbCookie = getFBCookie(cookies,appKey);

	    if (fbCookie == null){
	        return Collections.emptyMap();
	    }

	    // gets cookie value
	    String fbCookieValue = fbCookie.getValue();

	    // splits it.
	    String[] stringArgs = fbCookieValue.split("\\.");
	    String encodedPayload = stringArgs[1];

	    String payload = base64UrlDecode(encodedPayload);
	    
	    try {
			return objectMapper.readValue(payload, HashMap.class);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static boolean ValidateFBCookie(Cookie fbCookie, String appSecret)throws Exception {

	    // gets cookie information
	    String fbCookieValue = fbCookie.getValue();

	    String[] stringArgs = fbCookieValue.split("\\.");
	    String encodedSignature = stringArgs[0];
	    String encodedPayload = stringArgs[1];

	    //decode
	    String sig = base64UrlDecode(encodedSignature);
	    String payload = base64UrlDecode(encodedPayload);

	    // gets the js object from the cookie
	    JSONObject data = new JSONObject(payload);

	    if (data.getString("algorithm") != "HMAC-SHA256") {
	        return false;
	    }

	    SecretKey key = new SecretKeySpec(appSecret.getBytes(),"hmacSHA256");

	    Mac hmacSha256 = Mac.getInstance("hmacSHA256");
	    hmacSha256.init(key);
	    // decode the info.
	    byte[] mac = hmacSha256.doFinal(encodedPayload.getBytes());

	    String expectedSig = new String(mac);

	    // compare if the spected sig is the same than in the cookie.
	    return expectedSig.equals(sig);

	}

	/**
	 * bas64 decode an input string
	 * @param input
	 * @return
	 */
	private static String base64UrlDecode(String input) {
	    String result = null;
	    byte[] decodedBytes=null;
		try {
			decodedBytes = Base64.decode(input);
			result = new String(decodedBytes);
		} catch (Base64DecoderException shouldntHappen) {}
	    return result;
	}

	/**
	 * get a facebook cookie object
	 * @param cookies
	 * @param apiKey
	 * @return
	 */
	private static Cookie getFBCookie(Cookie[] cookies, String apiKey) {
		Cookie fbCookie = null;
		if(cookies!=null){
			for (Cookie c : cookies) {
		        if (c.getName().equals("fbsr_" + apiKey)) {
		            fbCookie = c;
		        }
		    }	
		}
	    return fbCookie;
	}
}
