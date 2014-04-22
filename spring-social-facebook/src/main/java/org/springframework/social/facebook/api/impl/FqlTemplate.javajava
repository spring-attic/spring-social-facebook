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
package org.springframework.social.facebook.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.FqlOperations;
import org.springframework.social.facebook.api.FqlResult;
import org.springframework.social.facebook.api.FqlResultMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class FqlTemplate extends AbstractFacebookOperations implements FqlOperations {

	private final GraphApi graphApi;

	public FqlTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	
	public <T> List<T> query(String fql, FqlResultMapper<T> mapper) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("q", fql);
		@SuppressWarnings("unchecked")
		Map<String, Object> resultSet = (Map<String, Object>) graphApi.fetchObject("fql", Map.class, parameters);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> resultSetData = (List<Map<String, Object>>) resultSet.get("data");
		List<T> response = new ArrayList<T>();
		for (Map<String, Object> dataItem : resultSetData) {
			response.add(mapper.mapObject(new FqlResult(dataItem)));
		}		
		return response;
	}
	
}
