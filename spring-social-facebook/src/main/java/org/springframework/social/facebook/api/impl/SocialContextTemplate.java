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

import org.springframework.social.facebook.api.CountedList;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.SocialContextOperations;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

class SocialContextTemplate implements SocialContextOperations {

	private static final int LIMIT = 25;
	
	private final RestOperations rest;

	private GraphApi graphApi;
	
	/**
	 * @deprecated Construct with GraphApi and RestOperations instead. Kept for backward compatibility only.
	 * @param rest a RestOperations
	 */
	@Deprecated
	public SocialContextTemplate(RestOperations rest) {
		this.rest = rest;
	}

	public SocialContextTemplate(GraphApi graphApi, RestOperations rest) {
		this.graphApi = graphApi;
		this.rest = rest;
	}

	public CountedList<Reference> getMutualFriends(String userId) {
		return getSocialContext(userId, "mutual_friends", LIMIT);
	}

	public CountedList<Reference> getMutualFriends(String userId, int limit) {
		return getSocialContext(userId, "mutual_friends", limit);
	}

	public CountedList<Reference> getAllMutualFriends(String userId) {
		return getSocialContext(userId, "all_mutual_friends", LIMIT);
	}

	public CountedList<Reference> getAllMutualFriends(String userId, int limit) {
		return getSocialContext(userId, "all_mutual_friends", limit);
	}

	public CountedList<Reference> getMutualLikes(String userId) {
		return getSocialContext(userId, "mutual_likes", LIMIT);
	}

	public CountedList<Reference> getMutualLikes(String userId, int limit) {
		return getSocialContext(userId, "mutual_likes", limit);
	}

	public CountedList<Reference> getFriendsUsingApp(String appId) {
		return getFriendsUsingApp(appId, LIMIT);
	}
	
	public CountedList<Reference> getFriendsUsingApp(String appId, int limit) {
		return getSocialContext(appId, "friends_using_app", limit);
	}

	public CountedList<Reference> getFriendsWhoLike(String objectId) {
		return getFriendsWhoLike(objectId, LIMIT);
	}
	
	public CountedList<Reference> getFriendsWhoLike(String objectId, int limit) {
		return getSocialContext(objectId, "friends_who_like", limit);
	}

	public CountedList<Reference> getFriendsWhoWatched(String videoId) {
		return getFriendsWhoWatched(videoId, LIMIT);
	}
	
	public CountedList<Reference> getFriendsWhoWatched(String videoId, int limit) {
		return getSocialContext(videoId, "video_watch_friends", limit);
	}

	public CountedList<Reference> getFriendsWhoListenTo(String objectId) {
		return getFriendsWhoListenTo(objectId, LIMIT);
	}
	
	public CountedList<Reference> getFriendsWhoListenTo(String objectId, int limit) {
		return getSocialContext(objectId, "music_listen_friends", limit);
	}
	
	public CountedList<Reference> getFriendsTaggedAt(String placeId) {
		return getFriendsTaggedAt(placeId, LIMIT);
	}
	
	public CountedList<Reference> getFriendsTaggedAt(String placeId, int limit) {
		return getSocialContext(placeId, "friends_tagged_at", limit);
	}
	
	private CountedList<Reference> getSocialContext(String userId, String context, int limit) {
		URIBuilder uriBuilder = URIBuilder
				.fromUri(graphApi.getBaseGraphApiUrl() + userId)
				.queryParam("fields", "context.fields(" + context + ".limit(" + limit + "))");
		JsonNode responseNode = rest.getForObject(uriBuilder.build(), JsonNode.class);
		JsonNode contextRootNode = responseNode.get("context");

		if (contextRootNode == null) {
			return new CountedList<Reference>(new ArrayList<Reference>(0), 0);
		}

		JsonNode contextNode = contextRootNode.get(context);
		if (contextNode == null) {
			return new CountedList<Reference>(new ArrayList<Reference>(0), 0);
		}

		ArrayNode dataNode = (ArrayNode) contextNode.get("data");
		ArrayList<Reference> results = new ArrayList<Reference>(dataNode.size());
		for (JsonNode itemNode : dataNode) {
			results.add(new Reference(itemNode.get("id").textValue(), itemNode.get("name").textValue()));
		}
		
		Integer totalCount = (contextNode.has("summary") && contextNode.get("summary").has("total_count")) ?
				contextNode.get("summary").get("total_count").intValue() : null;
		
		return new CountedList<Reference>(results, totalCount);
	}
}
