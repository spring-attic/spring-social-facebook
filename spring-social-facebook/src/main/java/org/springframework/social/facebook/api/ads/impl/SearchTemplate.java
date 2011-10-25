/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ads.SearchOperations;
import org.springframework.social.facebook.api.ads.ValidKeyword;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Karthick Sankarachary
 */
class SearchTemplate extends
		org.springframework.social.facebook.api.impl.SearchTemplate implements
		SearchOperations {

	public SearchTemplate(GraphApi graphApi, ObjectMapper objectMapper,
			boolean isAuthorizedForUser) {
		super(graphApi, objectMapper, isAuthorizedForUser);
	}

	public Identifier getIdByUrl(String url) {
		List<Identifier> ids = super.search("adobjectbyurl", url,
				Identifier.class);
		return ids != null && ids.size() == 1 ? ids.get(0) : null;
	}

	public List<Identifier> getKeywordAutocomplete(String keyword) {
		return super.search("adkeyword", keyword, null, Identifier.class);
	}

	public List<Identifier> getKeywordSuggestions(String... keywords) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.set("keyword_list", join(keywords));
		return super
				.search("adkeywordsuggestion", null, vars, Identifier.class);
	}

	public List<ValidKeyword> getValidKeywords(String... keywords) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.set("keyword_list", join(keywords));
		return super.search("adkeywordvalid", null, vars, ValidKeyword.class);
	}

	private String join(String[] keywords) {
		List<String> list = new ArrayList<String>();
		for (String keyword : keywords) {
			list.add(keyword);
		}
		return super.join(list);
	}

}
