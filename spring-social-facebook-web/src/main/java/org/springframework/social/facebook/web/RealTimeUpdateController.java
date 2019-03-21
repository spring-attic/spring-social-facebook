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

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A Spring MVC controller that handles callbacks from Facebook's Real-Time Update API.
 * Handles both the initial subscription verification request as well as individual updates:
 * The following requests are handled:
 * <ul>
 * <li>GET /realtime/facebook/{subscription}  - Verifies subscription.</li>
 * <li>POST /realtime/facebook/{subscription} - Receives an update.</li>
 * </ul>
 * 
 * Note that these requests are performed by Facebook and are not typically linked to or otherwise called in a web application.
 * @author Craig Walls
 */
@Controller
@RequestMapping("/realtime/facebook")
public class RealTimeUpdateController {

	private Map<String, String> tokens;
	
	private List<UpdateHandler> updateHandlers;

	private String applicationSecret;

	/**
	 * Constructs a RealTimeUpdateController.
	 * @param tokens A map of subscription names to verification tokens.
	 * @param updateHandlers A list of {@link UpdateHandler} implementations to handle incoming updates.
	 * @param applicationSecret the application's Facebook App Secret
	 */
	@Inject
	public RealTimeUpdateController(Map<String, String> tokens, List<UpdateHandler> updateHandlers, String applicationSecret) {
		this.tokens = tokens;
		this.updateHandlers = updateHandlers;
		this.applicationSecret = applicationSecret;
	}

	/**
	 * Handles subscription verification callback from Facebook.
	 * @param subscription The subscription name.
	 * @param challenge A challenge that Facebook expects to be returned.
	 * @param verifyToken A verification token that must match with the subscription's token given when the controller was created.
	 * @return The challenge if the verification token matches; blank string otherwise. 
	 */
	@RequestMapping(value="/{subscription}", method=GET, params="hub.mode=subscribe")
	public @ResponseBody String verifySubscription(
			@PathVariable("subscription") String subscription,
			@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String verifyToken) {
		logger.debug("Received subscription verification request for '" + subscription + "'.");
		return tokens.containsKey(subscription) && tokens.get(subscription).equals(verifyToken) ? challenge : "";
	}
	
	/**
	 * Receives an update from Facebook's real-time API.
	 * @param subscription The subscription name.
	 * @param payload The request body payload.
	 * @param signature The SHA1 signature of the request.
	 * @return a String with the response back to Facebook
	 * @throws Exception an Exception if anything goes wrong while processing the update
	 */
	@RequestMapping(value="/{subscription}", method=POST)
	public @ResponseBody String receiveUpdate(
			@PathVariable("subscription") String subscription,
			@RequestBody String payload,
			@RequestHeader(X_HUB_SIGNATURE) String signature) throws Exception {

		// Can only read body once and we need it as a raw String to calculate the signature.
		// Therefore, use Jackson ObjectMapper to give us a RealTimeUpdate object from that raw String.
		RealTimeUpdate update = new ObjectMapper().readValue(payload, RealTimeUpdate.class);		
		if (verifySignature(payload, signature)) {
			logger.debug("Received " + update.getObject() + " update for '" + subscription + "'.");
			for (UpdateHandler handler : updateHandlers) {
				handler.handleUpdate(subscription, update);
			}
		} else {
			logger.warn("Received an update, but signature was invalid. Not delegating to handlers.");
		}
		return "";
	}

	private boolean verifySignature(String payload, String signature) throws Exception {
		if (!signature.startsWith("sha1=")) {
			return false;
		}
		String expected = signature.substring(5);		
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		SecretKeySpec signingKey = new SecretKeySpec(applicationSecret.getBytes(), HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(payload.getBytes());
		String actual = new String(Hex.encode(rawHmac));
		return expected.equals(actual);
	}

	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	private static final String X_HUB_SIGNATURE = "X-Hub-Signature";

	private final static Log logger = LogFactory.getLog(RealTimeUpdateController.class);

}
