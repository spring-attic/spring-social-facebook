/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.facebook.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private final static Log logger = LogFactory.getLog(RealTimeUpdateController.class);

	private Map<String, String> tokens;
	
	private List<UpdateHandler> updateHandlers;

	/**
	 * Constructs a RealTimeUpdateController.
	 * @param tokens A map of subscription names to verification tokens.
	 * @param updateHandlers A list of {@link UpdateHandler} implementations to handle incoming updates.
	 */
	public RealTimeUpdateController(Map<String, String> tokens, List<UpdateHandler> updateHandlers) {
		this.tokens = tokens;
		this.updateHandlers = updateHandlers;
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
	 * @param update The update details.
	 */
	@RequestMapping(value="/{subscription}", method=POST)
	public @ResponseBody String receiveUpdate(@PathVariable("subscription") String subscription, @RequestBody RealTimeUpdate update) {
		logger.debug("Received " + update.getObject() + " update for '" + subscription + "'.");
		for (UpdateHandler handler : updateHandlers) {
			handler.handleUpdate(subscription, update);
		}
		return "";
	}

}
