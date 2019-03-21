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
import org.springframework.social.facebook.api.FitnessActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.util.MultiValueMap;

public class FitnessActionsTemplate implements FitnessActions {

	private OpenGraphOperations openGraphOperations;

	public FitnessActionsTemplate(OpenGraphOperations openGraphOperations) {
		this.openGraphOperations = openGraphOperations;
	}

	public String runs(String courseUrl) {
		return runs(courseUrl, EMPTY_ACTION_METADATA);
	}
	
	public String runs(String courseUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("course", courseUrl);
		return openGraphOperations.publishAction("fitness.runs", parameters, true);
	}

	public String walks(String courseUrl) {
		return walks(courseUrl, EMPTY_ACTION_METADATA);
	}
	
	public String walks(String courseUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("course", courseUrl);
		return openGraphOperations.publishAction("fitness.walks", parameters, true);
	}

	public String bikes(String courseUrl) {
		return bikes(courseUrl, EMPTY_ACTION_METADATA);
	}
	
	public String bikes(String courseUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("course", courseUrl);
		return openGraphOperations.publishAction("fitness.bikes", parameters, true);
	}

}
