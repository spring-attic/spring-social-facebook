/*
 * Copyright 2014 the original author or authors.
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

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

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

	public List<String> getUserPermissions() {
		requireAuthorization();
		JsonNode responseNode = restTemplate.getForObject(GraphApi.GRAPH_API_URL + "me/permissions", JsonNode.class);
		return deserializePermissionsNodeToList(responseNode);
	}

	public PagedList<Reference> search(String query) {
		requireAuthorization();
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "user");
		return graphApi.fetchConnections("search", null, Reference.class, queryMap);
	}

	private List<String> deserializePermissionsNodeToList(JsonNode jsonNode) {
		JsonNode dataNode = jsonNode.get("data");			
		List<String> permissions = new ArrayList<String>();
		for (Iterator<JsonNode> elementIt = dataNode.elements(); elementIt.hasNext(); ) {
			JsonNode permissionsElement = elementIt.next();
			String name = permissionsElement.get("permission").asText();
			String status = permissionsElement.get("status").asText();
			if ("granted".equals(status)) {
				permissions.add(name);
			}
		}
		return permissions;
	}
}
