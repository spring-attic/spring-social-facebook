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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.LinkPost;
import org.springframework.social.facebook.api.NotePost;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.StatusPost;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class FeedTemplate extends AbstractFacebookOperations implements FeedOperations {

	private final GraphApi graphApi;
	
	private ObjectMapper objectMapper;
	
	private final RestTemplate restTemplate;

	public FeedTemplate(GraphApi graphApi, RestTemplate restTemplate, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public List<Post> getFeed() {
		return getFeed("me");
	}

	public List<Post> getFeed(String ownerId) {
		requireAuthorization();
		return graphApi.fetchConnections(ownerId, "feed", Post.class);
	}

	public List<Post> getHomeFeed() {
		requireAuthorization();
		return graphApi.fetchConnections("me", "home", Post.class);
	}

	public List<StatusPost> getStatuses() {
		return getStatuses("me");
	}
	
	public List<StatusPost> getStatuses(String userId) {
		requireAuthorization();
		JsonNode responseNode = restTemplate.getForObject("https://graph.facebook.com/" + userId + "/statuses", JsonNode.class);
		return deserializeList(responseNode, "status", StatusPost.class);
	}
	
	public List<LinkPost> getLinks() {
		return getLinks("me");
	}
	
	public List<LinkPost> getLinks(String ownerId) {
		requireAuthorization();
		JsonNode responseNode = restTemplate.getForObject("https://graph.facebook.com/" + ownerId + "/links", JsonNode.class);
		return deserializeList(responseNode, "link", LinkPost.class);
	}

	public List<NotePost> getNotes() {
		return getNotes("me");
	}
	
	public List<NotePost> getNotes(String ownerId) {
		requireAuthorization();
		JsonNode responseNode = restTemplate.getForObject("https://graph.facebook.com/" + ownerId + "/notes", JsonNode.class);
		return deserializeList(responseNode, "note", NotePost.class);
	}
	
	public List<Post> getPosts() {
		return getPosts("me");
	}
	
	public List<Post> getPosts(String ownerId) {
		requireAuthorization();
		return graphApi.fetchConnections(ownerId, "posts", Post.class);
	}
	
	public Post getFeedEntry(String entryId) {
		requireAuthorization();
		return graphApi.fetchObject(entryId, Post.class);
	}

	public String updateStatus(String message) {
		return post("me", message);
	}

	public String postLink(String message, FacebookLink link) {
		return postLink("me", message, link);
	}
	
	public String postLink(String ownerId, String message, FacebookLink link) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("link", link.getLink());
		map.set("name", link.getName());
		map.set("caption", link.getCaption());
		map.set("description", link.getDescription());
		map.set("message", message);
		return graphApi.publish(ownerId, "feed", map);
	}
	
	public String post(String ownerId, String message) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("message", message);
		return graphApi.publish(ownerId, "feed", map);
	}

	public void deleteFeedEntry(String id) {
		requireAuthorization();
		graphApi.delete(id);
	}

	public List<Post> searchPublicFeed(String query) {
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		queryParameters.add("q", query);
		queryParameters.add("type", "post");
		return graphApi.fetchConnections("search", null, Post.class, queryParameters);
	}
	
	public List<Post> searchHomeFeed(String query) {
		requireAuthorization();
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		queryParameters.add("q", query);
		return graphApi.fetchConnections("me", "home", Post.class, queryParameters);
	}
	
	public List<Post> searchUserFeed(String query) {
		requireAuthorization();
		return searchUserFeed("me", query);
	}
	
	public List<Post> searchUserFeed(String userId, String query) {
		requireAuthorization();
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		queryParameters.add("q", query);
		return graphApi.fetchConnections(userId, "feed", Post.class, queryParameters);
	}
	
	private <T> List<T> deserializeList(JsonNode jsonNode, String nodeType, Class<T> type) {
		try {
			JsonNode dataNode = jsonNode.get("data");
			List<T> posts = new ArrayList<T>();
			for(Iterator<JsonNode> iterator = dataNode.iterator(); iterator.hasNext();) {
				ObjectNode node = (ObjectNode) iterator.next();
				node.put("type", nodeType);	
				posts.add(objectMapper.readValue(node, type));
			}
			return posts;
		} catch (IOException shouldntHappen) {
			throw new UncategorizedApiException("Error deserializing list of " + nodeType + " posts", shouldntHappen);
		}
	}

}
