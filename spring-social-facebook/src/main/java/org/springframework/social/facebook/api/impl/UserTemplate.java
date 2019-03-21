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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Permission;
import org.springframework.social.facebook.api.PlaceTag;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserIdForApp;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

class UserTemplate implements UserOperations {

	private final GraphApi graphApi;
	
	private final RestTemplate restTemplate;

	public UserTemplate(GraphApi graphApi, RestTemplate restTemplate) {
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public User getUserProfile() {
		return getUserProfile("me");
	}

	public User getUserProfile(String facebookId) {
		return graphApi.fetchObject(facebookId, User.class, PROFILE_FIELDS);
	}
	
	public byte[] getUserProfileImage() {
		return getUserProfileImage("me", ImageType.NORMAL);
	}
	
	public byte[] getUserProfileImage(String userId) {
		return getUserProfileImage(userId, ImageType.NORMAL);
	}

	public byte[] getUserProfileImage(ImageType imageType) {
		return getUserProfileImage("me", imageType);
	}
	
	public byte[] getUserProfileImage(String userId, ImageType imageType) {
		return graphApi.fetchImage(userId, "picture", imageType);
	}

	public byte[] getUserProfileImage(Integer width, Integer height) {
		return getUserProfileImage("me", width, height);
	}

	public byte[] getUserProfileImage(String userId, Integer width, Integer height) {
		return graphApi.fetchImage(userId, "picture", width, height);
	}

	public List<Permission> getUserPermissions() {
		JsonNode responseNode = restTemplate.getForObject(graphApi.getBaseGraphApiUrl() + "me/permissions", JsonNode.class);
		return deserializePermissionsNodeToList(responseNode);
	}
	
	public List<UserIdForApp> getIdsForBusiness() {
		return graphApi.fetchConnections("me", "ids_for_business", UserIdForApp.class);
	}
	
	public List<PlaceTag> getTaggedPlaces() {
		return graphApi.fetchConnections("me", "tagged_places", PlaceTag.class);
	}

	public PagedList<Reference> search(String query) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "user");
		return graphApi.fetchConnections("search", null, Reference.class, queryMap);
	}

	private List<Permission> deserializePermissionsNodeToList(JsonNode jsonNode) {
		JsonNode dataNode = jsonNode.get("data");			
		List<Permission> permissions = new ArrayList<Permission>();
		for (Iterator<JsonNode> elementIt = dataNode.elements(); elementIt.hasNext(); ) {
			JsonNode permissionsElement = elementIt.next();
			String name = permissionsElement.get("permission").asText();
			String status = permissionsElement.get("status").asText();
			permissions.add(new Permission(name, status));
		}
		return permissions;
	}

}
