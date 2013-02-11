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
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagedListParameters;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class UserTemplate extends AbstractFacebookOperations implements UserOperations {

	private final GraphApi graphApi;
	
	private final RestTemplate restTemplate;

	public UserTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public FacebookProfile getUserProfile() {
		requireAuthorization();
		return getUserProfile("me");
	}

	public FacebookProfile getUserProfile(String facebookId) {
		return graphApi.fetchObject(facebookId, FacebookProfile.class);
	}
	
	public byte[] getUserProfileImage() {
		requireAuthorization();
		return getUserProfileImage("me", ImageType.NORMAL);
	}
	
	public byte[] getUserProfileImage(String userId) {
		return getUserProfileImage(userId, ImageType.NORMAL);
	}

	public byte[] getUserProfileImage(ImageType imageType) {
		requireAuthorization();
		return getUserProfileImage("me", imageType);
	}
	
	public byte[] getUserProfileImage(String userId, ImageType imageType) {
		return graphApi.fetchImage(userId, "picture", imageType);
	}

	public PagedList<String> getUserPermissions() {
		requireAuthorization();
		JsonNode responseNode = restTemplate.getForObject("https://graph.facebook.com/me/permissions", JsonNode.class);		
		return deserializePermissionsNodeToList(responseNode);
	}

	public PagedList<Reference> search(String query) {
		requireAuthorization();
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "user");
		return graphApi.fetchConnections("search", null, Reference.class, queryMap);
	}

	private PagedList<String> deserializePermissionsNodeToList(JsonNode jsonNode) {
		JsonNode dataNode = jsonNode.get("data");			
		List<String> permissions = new ArrayList<String>();
		for (Iterator<JsonNode> elementIt = dataNode.getElements(); elementIt.hasNext(); ) {
			JsonNode permissionsElement = elementIt.next();
			for (Iterator<String> fieldNamesIt = permissionsElement.getFieldNames(); fieldNamesIt.hasNext(); ) {
				permissions.add(fieldNamesIt.next());
			}
		}			
		
		JsonNode pagingNode = jsonNode.get("paging");
		if (pagingNode != null) {
			PagedListParameters previousPage = PagedListUtils.getPagedListParameters(pagingNode, "previous");
			PagedListParameters nextPage = PagedListUtils.getPagedListParameters(pagingNode, "next");
			return new PagedList<String>(permissions, previousPage, nextPage);
		}
		
		return new PagedList<String>(permissions, null, null);
	}
}
