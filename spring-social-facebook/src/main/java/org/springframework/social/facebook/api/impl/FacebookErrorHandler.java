/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api.impl;

import static org.springframework.social.facebook.api.FacebookErrors.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.ServerException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.FacebookError;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Subclass of {@link DefaultResponseErrorHandler} that handles errors from Facebook's
 * Graph API, interpreting them into appropriate exceptions.
 * @author Craig Walls
 */
class FacebookErrorHandler extends DefaultResponseErrorHandler {

	private static final String FACEBOOK_PROVIDER_ID = "facebook";
	private final static Log logger = LogFactory.getLog(FacebookErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		FacebookError error = extractErrorFromResponse(response);
		handleFacebookError(response.getStatusCode(), error);
	}
	
	/**
	 * Examines the error data returned from Facebook and throws the most applicable exception.
	 * @param errorDetails a Map containing a "type" and a "message" corresponding to the Graph API's error response structure.
	 */
	void handleFacebookError(HttpStatus statusCode, FacebookError error) {
		if (error != null && error.getCode() != null) {
			int code = error.getCode();
			
			if (code == UNKNOWN) {
				throw new UncategorizedApiException(FACEBOOK_PROVIDER_ID, error.getMessage(), null);
			} else if (code == SERVICE) {
				throw new ServerException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else if (code == TOO_MANY_CALLS || code == USER_TOO_MANY_CALLS || code == EDIT_FEED_TOO_MANY_USER_CALLS || code == EDIT_FEED_TOO_MANY_USER_ACTION_CALLS) {
				throw new RateLimitExceededException(FACEBOOK_PROVIDER_ID);
			} else if (code == PERMISSION_DENIED || isUserPermissionError(code)) {
				throw new InsufficientPermissionException(FACEBOOK_PROVIDER_ID);
			} else if (code == PARAM_SESSION_KEY || code == PARAM_SIGNATURE) {
				throw new InvalidAuthorizationException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else if (code == PARAM_ACCESS_TOKEN && error.getSubcode() == null) {
				throw new InvalidAuthorizationException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else if (code == PARAM_ACCESS_TOKEN && error.getSubcode() == 463) {
				throw new ExpiredAuthorizationException(FACEBOOK_PROVIDER_ID);
			} else if (code == PARAM_ACCESS_TOKEN) {
				throw new RevokedAuthorizationException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else if (code == MESG_DUPLICATE) { 
				throw new DuplicateStatusException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else if (code == DATA_OBJECT_NOT_FOUND || code == PATH_UNKNOWN) {
				throw new ResourceNotFoundException(FACEBOOK_PROVIDER_ID, error.getMessage());
			} else {
				throw new UncategorizedApiException(FACEBOOK_PROVIDER_ID, error.getMessage(), null);
			}
		}

	}
	
	private FacebookError extractErrorFromResponse(ClientHttpResponse response) throws IOException {
		String json = readResponseJson(response);
		try {
			ObjectMapper mapper = new ObjectMapper(new JsonFactory());
			JsonNode jsonNode = mapper.readValue(json, JsonNode.class);
			if (jsonNode.has("error")) {
				JsonNode errorNode = jsonNode.get("error");
				Integer code = errorNode.has("code") ? errorNode.get("code").intValue() : null;
				String type = errorNode.has("type") ? errorNode.get("type").asText() : null;
				String message = errorNode.has("message") ? errorNode.get("message").asText() : null;
				Integer subcode = errorNode.has("error_subcode") ? errorNode.get("error_subcode").intValue() : null;
				String userMessage = errorNode.has("error_user_msg") ? errorNode.get("error_user_msg").asText() : null;
				String userTitle = errorNode.has("error_user_title") ? errorNode.get("error_user_title").asText() : null;
				
				FacebookError error = new FacebookError(code, type, message, subcode, userMessage, userTitle);
				if (logger.isDebugEnabled()) {
					logger.debug("Facebook error: ");
					logger.debug("   CODE        : " + error.getCode());
					logger.debug("   TYPE        : " + error.getType());
					logger.debug("   SUBCODE     : " + error.getSubcode());
					logger.debug("   MESSAGE     : " + error.getMessage());
					logger.debug("   USER TITLE  : " + error.getUserTitle());
					logger.debug("   USER MESSAGE: " + error.getUserMessage());
				}
				return error;
			}
		} catch (JsonParseException e) {
			return null;
		}
		return null;
	}

	private String readResponseJson(ClientHttpResponse response) throws IOException {
		String json = readFully(response.getBody());
		if (logger.isDebugEnabled()) {
			logger.debug("Error from Facebook: " + json);
		}
		return json;
	}
	
	private String readFully(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		while (reader.ready()) {
			sb.append(reader.readLine());
		}
		return sb.toString();
	}
}
