/*
 * Copyright 2012 the original author or authors.
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

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.MissingNamespaceException;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class OpenGraphTemplate extends AbstractFacebookOperations implements OpenGraphOperations {
	
	private GraphApi graphApi;
	
	public OpenGraphTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public String publishAction(String action, String objectType, String objectUrl) {
		requireAuthorization();
		requireApplicationNamespace();
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set(objectType, objectUrl);
		return graphApi.publish("me", graphApi.getApplicationNamespace() + ":" + action, parameters);
	}

	private void requireApplicationNamespace() {
		String applicationNamespace = graphApi.getApplicationNamespace();
		if (applicationNamespace == null || applicationNamespace.isEmpty()) {
			throw new MissingNamespaceException();
		}
	}

}
