/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.DuplicateStatusException;
import org.springframework.social.facebook.api.NotAFriendException;
import org.springframework.social.facebook.api.ResourceOwnershipException;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * Subclass of {@link DefaultResponseErrorHandler} that handles errors from Facebook's
 * Graph API, interpreting them into appropriate exceptions.
 * @author Craig Walls
 */
class FacebookErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		Map<String, String> errorDetails = extractErrorDetailsFromResponse(response);
		if (errorDetails == null) {
			handleUncategorizedError(response, errorDetails);
		}

		handleFacebookError(response.getStatusCode(), errorDetails);
		
		// if not otherwise handled, do default handling and wrap with UncategorizedApiException
		handleUncategorizedError(response, errorDetails);			
	}
	
	@Override 
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if(super.hasError(response)) {
			return true;
		}
		// only bother checking the body for errors if we get past the default error check
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
		return reader.ready() && reader.readLine().startsWith("{\"error\":");
	}

	/**
	 * Examines the error data returned from Facebook and throws the most applicable exception.
	 * @param errorDetails a Map containing a "type" and a "message" corresponding to the Graph API's error response structure.
	 */
	void handleFacebookError(HttpStatus statusCode, Map<String, String> errorDetails) {
		// Can't trust the type to be useful. It's often OAuthException, even for things not OAuth-related. 
		// Can rely only on the message (which itself isn't very consistent).
		String message = errorDetails.get("message");

		if (statusCode == HttpStatus.OK) {
			if (message.contains("Some of the aliases you requested do not exist")) {
				throw new ResourceNotFoundException(message);
			}
		} else if (statusCode == HttpStatus.BAD_REQUEST) {
			if (message.contains("Unknown path components")) {
				throw new ResourceNotFoundException(message);
			} else if (message.equals("An access token is required to request this resource.")) {
				throw new MissingAuthorizationException();
			} else if (message.equals("An active access token must be used to query information about the current user.")) {
				throw new MissingAuthorizationException();				
			} else if (message.startsWith("Error validating access token")) {
				handleInvalidAccessToken(message);
			} else if (message.equals("Error validating application.")) { // Access token with incorrect app ID
				throw new InvalidAuthorizationException(message);
			} else if (message.equals("Invalid access token signature.")) { // Access token that fails signature validation
				throw new InvalidAuthorizationException(message);				
			} else if (message.contains("Application does not have the capability to make this API call.") || message.contains("App must be on whitelist")) {
				throw new OperationNotPermittedException(message);
			} else if (message.contains("Invalid fbid") || message.contains("The parameter url is required")) { 
				throw new OperationNotPermittedException("Invalid object for this operation");
			} else if (message.contains("Duplicate status message") ) {
				throw new DuplicateStatusException(message);
			}
		} else if (statusCode == HttpStatus.UNAUTHORIZED) {
			if (message.startsWith("Error validating access token")) {
				handleInvalidAccessToken(message);
			}
			throw new NotAuthorizedException(message);
		} else if (statusCode == HttpStatus.FORBIDDEN) {
			if (message.contains("Requires extended permission")) {
				String requiredPermission = message.split(": ")[1];
				throw new InsufficientPermissionException(requiredPermission);
			} else if (message.contains("Permissions error")) {
				throw new InsufficientPermissionException();
			} else {
				throw new OperationNotPermittedException(message);
			}
		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
			if (message.equals("User must be an owner of the friendlist")) { // watch for pattern in similar message in other resources
				throw new ResourceOwnershipException(message);
			} else if (message.equals("The member must be a friend of the current user.")) {
				throw new NotAFriendException(message);
			} else {
				throw new InternalServerErrorException(message);
			}
		}
	}

	private void handleInvalidAccessToken(String message) {
		if (message.contains("Session has expired at unix time")) {
			throw new ExpiredAuthorizationException();
		} else if (message.contains("The session has been invalidated because the user has changed the password.")) {
			throw new RevokedAuthorizationException();
		} else if (message.contains("The session is invalid because the user logged out.")) {
			throw new RevokedAuthorizationException();
		} else if (message.contains("has not authorized application")) {
			// Per https://developers.facebook.com/blog/post/500/, this could be in the message when the user removes the application.
			// In reality, "The session has been invalidated because the user has changed the password." is what you get in that case.
			// Leaving this check in place in case there FB does return this message (could be a bug in FB?)
			throw new RevokedAuthorizationException();
		} else {
			throw new InvalidAuthorizationException(message);				
		}
	}

	private void handleUncategorizedError(ClientHttpResponse response, Map<String, String> errorDetails) {
		try {
			super.handleError(response);
		} catch (Exception e) {
			if (errorDetails != null) {
				throw new UncategorizedApiException(errorDetails.get("message"), e);
			} else {
				throw new UncategorizedApiException("No error details from Facebook", e);
			}
		}
	}

	/*
	 * Attempts to extract Facebook error details from the response.
	 * Returns null if the response doesn't match the expected JSON error response.
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());		
		try {
		    Map<String, Object> responseMap = mapper.<Map<String, Object>>readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
		    if (responseMap.containsKey("error")) {
		    	return (Map<String, String>) responseMap.get("error");
		    }
		} catch (JsonParseException e) {
			return null;
		}
	    return null;
	}
}
