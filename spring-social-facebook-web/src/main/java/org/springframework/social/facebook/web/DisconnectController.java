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
package org.springframework.social.facebook.web;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Spring MVC controller for handling deauthorization callbacks from Facebook as described at https://developers.facebook.com/docs/authentication/.
 * Handles a POST request to /disconnect/facebook with a single signed_request parameter, extracts the Facebook user ID from the signed_request,
 * then removes all connections for the given Facebook user.
 * @author habuma
 */
@Controller
@RequestMapping("/disconnect/facebook")
public class DisconnectController {

	private final static Log logger = LogFactory.getLog(DisconnectController.class);

	private final SignedRequestDecoder signedRequestDecoder;

	private final UsersConnectionRepository usersConnectionRepository;

	/**
	 * Constructs a DisconnectController.
	 * @param usersConnectionRepository the current user's {@link UsersConnectionRepository} needed to persist connections; must be a proxy to a request-scoped bean
	 * @param applicationSecret the application's secret as assigned by Facebook at registration time. Used to validate signed_request.
	 */
	public DisconnectController(UsersConnectionRepository usersConnectionRepository, String applicationSecret) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.signedRequestDecoder = new SignedRequestDecoder(applicationSecret);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> disconnect(@RequestParam("signed_request") String signedRequest) {
		try {
			String userId = getUserId(signedRequest);
			logger.info("Deauthorization request received for Facebook User ID: " + userId + "; Removing connections.");
			Set<String> providerUserIds = new HashSet<String>();
			providerUserIds.add(userId);
			Set<String> localUserIds = usersConnectionRepository.findUserIdsConnectedTo("facebook", providerUserIds);
			for (String localUserId : localUserIds) {
				ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(localUserId);
				logger.info("  Removing Facebook connection for local user '" + localUserId + "'");
				connectionRepository.removeConnection(new ConnectionKey("facebook", userId));
			}		
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (SignedRequestException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	private String getUserId(String signedRequest) throws SignedRequestException {
		return (String) signedRequestDecoder.decodeSignedRequest(signedRequest, Map.class).get("user_id");		
	}

}
