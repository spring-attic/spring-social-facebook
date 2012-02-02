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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.FamilyMember;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class FriendTemplate extends AbstractFacebookOperations implements FriendOperations {
	
	private final GraphApi graphApi;

	private final RestTemplate restTemplate;

	public FriendTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}
	
	public List<Reference> getFriendLists() {
		return getFriendLists("me");
	}

	public List<Reference> getFriendLists(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "friendlists", Reference.class);
	}
	
	public Reference getFriendList(String friendListId) {
		requireAuthorization();
		return graphApi.fetchObject(friendListId, Reference.class);
	}
	
	public List<Reference> getFriendListMembers(String friendListId) {
		requireAuthorization();
		return graphApi.fetchConnections(friendListId, "members", Reference.class);
	}

	public String createFriendList(String name) {
		return createFriendList("me", name);
	}
	
	public String createFriendList(String userId, String name) {
		requireAuthorization();
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<String, Object>();
		request.set("name", name);
		return graphApi.publish(userId, "friendlists", request);
	}
	
	public void deleteFriendList(String friendListId) {
		requireAuthorization();
		graphApi.delete(friendListId);
	}

	public void addToFriendList(String friendListId, String friendId) {
		requireAuthorization();
		graphApi.post(friendListId, "members/" + friendId, new LinkedMultiValueMap<String, String>());
	}
	
	public void removeFromFriendList(String friendListId, String friendId) {
		requireAuthorization();
		URI uri = URIBuilder.fromUri(GraphApi.GRAPH_API_URL + friendListId + "/members/" + friendId).build();
		restTemplate.delete(uri);
	}
	
	public List<Reference> getFriends() {
		return getFriends("me");
	}
	
	public List<String> getFriendIds() {
		return getFriendIds("me");
	}
	
	public List<FacebookProfile> getFriendProfiles() {
		return getFriendProfiles("me", 0, 100);
	}

	public List<FacebookProfile> getFriendProfiles(int offset, int limit) {
		return getFriendProfiles("me", offset, limit);
	}
	
	public List<Reference> getFriends(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "friends", Reference.class);
	}
	
	public List<String> getFriendIds(String userId) {
		requireAuthorization();		
		URI uri = URIBuilder.fromUri("https://graph.facebook.com/" + userId + "/friends").queryParam("fields", "id").build();
		@SuppressWarnings("unchecked")
		Map<String,List<Map<String, String>>> response = restTemplate.getForObject(uri, Map.class);
		List<Map<String,String>> entryList = response.get("data");
		List<String> idList = new ArrayList<String>(entryList.size());
		for (Map<String, String> entry : entryList) {
			idList.add(entry.get("id"));
		}	
		return idList;
	}
	
	public List<FacebookProfile> getFriendProfiles(String userId) {
		return getFriendProfiles(userId, 0, 100);
	}

	public List<FacebookProfile> getFriendProfiles(String userId, int offset, int limit) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("offset", String.valueOf(offset));
		parameters.set("limit", String.valueOf(limit));
		parameters.set("fields", FULL_PROFILE_FIELDS);
		return graphApi.fetchConnections(userId, "friends", FacebookProfile.class, parameters);
	}

	public List<FamilyMember> getFamily() {
		requireAuthorization();
		return graphApi.fetchConnections("me", "family", FamilyMember.class);
	}

	public List<FamilyMember> getFamily(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "family", FamilyMember.class);
	}

	private static final String FULL_PROFILE_FIELDS = "id,username,name,first_name,last_name,gender,locale,education,work,email,third_party_id,link,timezone,updated_time,verified,about,bio,birthday,location,hometown,interested_in,religion,political,quotes,relationship_status,significant_other,website";

}
