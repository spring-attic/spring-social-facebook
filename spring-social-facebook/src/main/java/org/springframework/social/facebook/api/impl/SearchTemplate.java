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

import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ResultSet;
import org.springframework.social.facebook.api.SearchOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Karthick Sankarachary
 * 
 */
public class SearchTemplate extends AbstractFacebookOperations implements
		SearchOperations {
	private final GraphApi graphApi;

	public SearchTemplate(GraphApi graphApi, boolean isAuthorized) {
		super(isAuthorized);
		this.graphApi = graphApi;
	}

	public <T> List<T> search(String type, String query, Class<T> resultType) {
		return search(type, query, null, resultType);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> search(String type, String query,
			MultiValueMap<String, String> vars, Class<T> resultType) {
		if (vars == null) {
			vars = new LinkedMultiValueMap<String, String>();
		}
		vars.set("type", type);
		vars.set("q", query);
		ResultSet<T> resultSet = graphApi.fetchObject("search",
				ResultSet.class, vars);
		return resultSet.getData();
	}

}
