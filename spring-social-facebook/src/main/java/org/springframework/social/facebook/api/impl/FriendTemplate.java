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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.FamilyMember;
import org.springframework.social.facebook.api.FriendList;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserInvitableFriend;
import org.springframework.social.facebook.api.UserTaggableFriend;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

class FriendTemplate implements FriendOperations {
	
	private final GraphApi graphApi;

	private final RestTemplate restTemplate;

	public FriendTemplate(GraphApi graphApi, RestTemplate restTemplate) {
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}
	
	public PagedList<FriendList> getFriendLists() {
		return graphApi.fetchConnections("me", "friendlists", FriendList.class);
	}

	public FriendList getFriendList(String friendListId) {
		return graphApi.fetchObject(friendListId, FriendList.class);
	}
	
	public PagedList<Reference> getFriends() {
		return getFriends("me");
	}
	
	public PagedList<String> getFriendIds() {
		return getFriendIds("me");
	}
	
	public PagedList<User> getFriendProfiles() {
		return getFriendProfiles("me");
	}

	public PagedList<User> getFriendProfiles(PagingParameters pagedListParameters) {
		return getFriendProfiles("me", pagedListParameters);
	}
	
	public PagedList<Reference> getFriends(String userId) {
		return graphApi.fetchConnections(userId, "friends", Reference.class);
	}
	
	public PagedList<String> getFriendIds(String userId) {
		URI uri = URIBuilder.fromUri(graphApi.getBaseGraphApiUrl() + userId + "/friends").queryParam("fields", "id").build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		ArrayNode dataNode = (ArrayNode) responseNode.get("data");
		List<String> idList = new ArrayList<String>(dataNode.size());
		for (JsonNode entryNode : dataNode) {
			idList.add(entryNode.get("id").textValue());
		}
		
		Integer totalCount = responseNode.has("summary") && responseNode.get("summary").has("total_count") ?
				responseNode.get("summary").get("total_count").asInt() : null;
		return new PagedList<String>(idList, null, null, totalCount);
	}
	
	public PagedList<User> getFriendProfiles(String userId) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("fields", FULL_PROFILE_FIELDS);
		return graphApi.fetchConnections(userId, "friends", User.class, parameters);
	}

	public PagedList<User> getFriendProfiles(String userId, PagingParameters pagedListParameters) {
		MultiValueMap<String, String> parameters = PagedListUtils.getPagingParameters(pagedListParameters);
		parameters.set("fields", FULL_PROFILE_FIELDS);
		return graphApi.fetchConnections(userId, "friends", User.class, parameters);
	}
	
	public PagedList<FamilyMember> getFamily() {
		return graphApi.fetchConnections("me", "family", FamilyMember.class);
	}

	public PagedList<FamilyMember> getFamily(String userId) {
		return graphApi.fetchConnections(userId, "family", FamilyMember.class);
	}

	public PagedList<UserInvitableFriend> getInvitableFriends() {
		return graphApi.fetchConnections("me", "invitable_friends", UserInvitableFriend.class, 
				"id", "name" ,"first_name", "last_name", "middle_name");
	}

	public PagedList<UserTaggableFriend> getTaggableFriends() {
		return graphApi.fetchConnections("me", "taggable_friends", UserTaggableFriend.class, 
				"id", "name" ,"picture", "first_name", "last_name", "middle_name");  
	}
	
	private static final String FULL_PROFILE_FIELDS = "id,name,first_name,last_name,gender,locale,education,work,email,third_party_id,link,timezone,updated_time,verified,about,birthday,location,hometown,interested_in,religion,political,quotes,relationship_status,significant_other,website";

}
