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

import static org.springframework.social.facebook.api.OpenGraphOperations.*;

import org.springframework.social.facebook.api.ActionMetadata;
import org.springframework.social.facebook.api.GeneralActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.util.MultiValueMap;

public class GeneralActionsTemplate implements GeneralActions {

	private OpenGraphOperations openGraphOperations;

	public GeneralActionsTemplate(OpenGraphOperations openGraphOperations) {
		this.openGraphOperations = openGraphOperations;
	}

	public String like(String object) {
		return like(object, EMPTY_ACTION_METADATA);
	}
	
	public String like(String object, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("object", object);
		return openGraphOperations.publishAction("og.likes", parameters, true);
	}

	public String follow(String profile) {
		return follow(profile, EMPTY_ACTION_METADATA);
	}

	public String follow(String profileUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("profile", profileUrl);
		return openGraphOperations.publishAction("og.follows", parameters, true);
	}

}
