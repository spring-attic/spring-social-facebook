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

import static org.springframework.social.facebook.api.impl.PagedListUtils.*;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.LinkPost;
import org.springframework.social.facebook.api.NotePost;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Post.PostType;
import org.springframework.social.facebook.api.StatusPost;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class FeedTemplate extends AbstractFacebookOperations implements FeedOperations {

	private static final PagingParameters FIRST_PAGE = new PagingParameters(25, null, null, null);

	private final GraphApi graphApi;
	
	private ObjectMapper objectMapper;
	
	private final RestTemplate restTemplate;

	public FeedTemplate(GraphApi graphApi, RestTemplate restTemplate, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public PagedList<Post> getFeed() {
		return getFeed("me", FIRST_PAGE);
	}

	public PagedList<Post> getFeed(int offset, int limit) {
		return getFeed("me", offset, limit);
	}

	public PagedList<Post> getFeed(PagingParameters pagedListParameters) {
		return getFeed("me", pagedListParameters);
	}
	
	public PagedList<Post> getFeed(String ownerId) {
		return getFeed(ownerId, FIRST_PAGE);
	}
		
	public PagedList<Post> getFeed(String ownerId, int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/feed", offset, limit);
		return deserializeList(responseNode, null, Post.class);
	}

	public PagedList<Post> getFeed(String ownerId, PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/feed", pagedListParameters);
		return deserializeList(responseNode, null, Post.class);
	}

	public PagedList<Post> getHomeFeed() {
		return getHomeFeed(FIRST_PAGE);
	}
	
	public PagedList<Post> getHomeFeed(int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/me/home", offset, limit);
		return deserializeList(responseNode, null, Post.class);
	}

	public PagedList<Post> getHomeFeed(PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/me/home", pagedListParameters);
		return deserializeList(responseNode, null, Post.class);
	}

	public PagedList<StatusPost> getStatuses() {
		return getStatuses("me", FIRST_PAGE);
	}
	
	public PagedList<StatusPost> getStatuses(int offset, int limit) {
		return getStatuses("me", offset, limit);
	}

	public PagedList<StatusPost> getStatuses(PagingParameters pagedListParameters) {
		return getStatuses("me", pagedListParameters);
	}

	public PagedList<StatusPost> getStatuses(String userId) {
		return getStatuses(userId, FIRST_PAGE);
	}
	
	public PagedList<StatusPost> getStatuses(String userId, int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + userId + "/statuses", offset, limit);
		return deserializeList(responseNode, "status", StatusPost.class);
	}

	public PagedList<StatusPost> getStatuses(String userId, PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + userId + "/statuses", pagedListParameters);
		return deserializeList(responseNode, "status", StatusPost.class);
	}

	public PagedList<LinkPost> getLinks() {
		return getLinks("me", FIRST_PAGE);
	}

	public PagedList<LinkPost> getLinks(int offset, int limit) {
		return getLinks("me", offset, limit);
	}

	public PagedList<LinkPost> getLinks(PagingParameters pagedListParameters) {
		return getLinks("me", pagedListParameters);
	}

	public PagedList<LinkPost> getLinks(String ownerId) {
		return getLinks(ownerId, FIRST_PAGE);
	}
	
	public PagedList<LinkPost> getLinks(String ownerId, int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/links", offset, limit);
		return deserializeList(responseNode, "link", LinkPost.class);
	}

	public PagedList<LinkPost> getLinks(String ownerId, PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/links", pagedListParameters);
		return deserializeList(responseNode, "link", LinkPost.class);
	}

	public PagedList<NotePost> getNotes() {
		return getNotes("me", FIRST_PAGE);
	}

	public PagedList<NotePost> getNotes(int offset, int limit) {
		return getNotes("me", offset, limit);
	}

	public PagedList<NotePost> getNotes(PagingParameters pagedListParameters) {
		return getNotes("me", pagedListParameters);
	}

	public PagedList<NotePost> getNotes(String ownerId) {
		return getNotes(ownerId, FIRST_PAGE);
	}
	
	public PagedList<NotePost> getNotes(String ownerId, int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/notes", offset, limit);
		return deserializeList(responseNode, "note", NotePost.class);
	}
	
	public PagedList<NotePost> getNotes(String ownerId, PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/notes", pagedListParameters);
		return deserializeList(responseNode, "note", NotePost.class);
	}
	
	public PagedList<Post> getPosts() {
		return getPosts("me", FIRST_PAGE);
	}

	public PagedList<Post> getPosts(int offset, int limit) {
		return getPosts("me", offset, limit);
	}

	public PagedList<Post> getPosts(PagingParameters pagedListParameters) {
		return getPosts("me", pagedListParameters);
	}

	public PagedList<Post> getPosts(String ownerId) {
		return getPosts(ownerId, FIRST_PAGE);
	}
	
	public PagedList<Post> getPosts(String ownerId, int offset, int limit) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/posts", offset, limit);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public PagedList<Post> getPosts(String ownerId, PagingParameters pagedListParameters) {
		requireAuthorization();
		JsonNode responseNode = fetchConnectionList("https://graph.facebook.com/" + ownerId + "/posts", pagedListParameters);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public Post getPost(String entryId) {
		requireAuthorization();
		ObjectNode responseNode = (ObjectNode) restTemplate.getForObject("https://graph.facebook.com/" + entryId, JsonNode.class);
		return deserializePost(null, Post.class, responseNode);
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

	public void deletePost(String id) {
		requireAuthorization();
		graphApi.delete(id);
	}

	public PagedList<Post> searchPublicFeed(String query) {
		return searchPublicFeed(query, FIRST_PAGE);
	}
	
	public PagedList<Post> searchPublicFeed(String query, int offset, int limit) {
		JsonNode responseNode = restTemplate.getForObject("https://graph.facebook.com/search?q={query}&type=post&offset={offset}&limit={limit}", JsonNode.class, query, offset, limit);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public PagedList<Post> searchPublicFeed(String query, PagingParameters pagedListParameters) {
		String url = "https://graph.facebook.com/search?q={query}&type=post";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("query", query);
		if (pagedListParameters.getLimit() != null) {
			url += "&limit={limit}";
			params.put("limit", pagedListParameters.getLimit());
		}
		if (pagedListParameters.getSince() != null) {
			url += "&since={since}";
			params.put("since", pagedListParameters.getSince());
		}
		if (pagedListParameters.getUntil() != null) {
			url += "&until={until}";
			params.put("until", pagedListParameters.getUntil());
		}
		JsonNode responseNode = restTemplate.getForObject(url, JsonNode.class, params);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public PagedList<Post> searchHomeFeed(String query) {
		return searchHomeFeed(query, FIRST_PAGE);
	}
	
	public PagedList<Post> searchHomeFeed(String query, int offset, int limit) {
		requireAuthorization();
		URI uri = URIBuilder.fromUri("https://graph.facebook.com/me/home")
				.queryParam("q", query)
				.queryParam("offset", String.valueOf(offset))
				.queryParam("limit", String.valueOf(limit))
				.build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public PagedList<Post> searchHomeFeed(String query, PagingParameters pagedListParameters) {
		requireAuthorization();
		URIBuilder uriBuilder = URIBuilder.fromUri("https://graph.facebook.com/me/home").queryParam("q", query);
		uriBuilder = appendPagedListParameters(pagedListParameters, uriBuilder);
		URI uri = uriBuilder.build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return deserializeList(responseNode, null, Post.class);
	}

	public PagedList<Post> searchUserFeed(String query) {
		return searchUserFeed("me", query, FIRST_PAGE);
	}

	public PagedList<Post> searchUserFeed(String query, int offset, int limit) {
		return searchUserFeed("me", query, offset, limit);
	}

	public PagedList<Post> searchUserFeed(String query, PagingParameters pagedListParameters) {
		return searchUserFeed("me", query, pagedListParameters);
	}

	public PagedList<Post> searchUserFeed(String userId, String query) {
		return searchUserFeed(userId, query, FIRST_PAGE);
	}
	
	public PagedList<Post> searchUserFeed(String userId, String query, int offset, int limit) {
		requireAuthorization();
		URI uri = URIBuilder.fromUri("https://graph.facebook.com/" + userId + "/feed")
				.queryParam("q", query)
				.queryParam("offset", String.valueOf(offset))
				.queryParam("limit", String.valueOf(limit))
				.build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return deserializeList(responseNode, null, Post.class);
	}
	
	public PagedList<Post> searchUserFeed(String userId, String query, PagingParameters pagedListParameters) {
		requireAuthorization();
		URIBuilder uriBuilder = URIBuilder.fromUri("https://graph.facebook.com/" + userId + "/feed").queryParam("q", query);
		uriBuilder = appendPagedListParameters(pagedListParameters, uriBuilder);		
		URI uri = uriBuilder.build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return deserializeList(responseNode, null, Post.class);
	}
	
	// private helpers
	
	private JsonNode fetchConnectionList(String baseUri, int offset, int limit) {
		URI uri = URIBuilder.fromUri(baseUri)
				.queryParam("offset", String.valueOf(offset))
				.queryParam("limit", String.valueOf(limit)).build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return responseNode;
	}

	private JsonNode fetchConnectionList(String baseUri, PagingParameters pagedListParameters) {
		URIBuilder uriBuilder = URIBuilder.fromUri(baseUri);
		uriBuilder = appendPagedListParameters(pagedListParameters, uriBuilder);
		URI uri = uriBuilder.build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return responseNode;
	}

	private <T> PagedList<T> deserializeList(JsonNode jsonNode, String postType, Class<T> type) {
		JsonNode dataNode = jsonNode.get("data");
		List<T> posts = new ArrayList<T>();
		for (Iterator<JsonNode> iterator = dataNode.iterator(); iterator.hasNext();) {
			posts.add(deserializePost(postType, type, (ObjectNode) iterator.next()));
		}
		if (jsonNode.has("paging")) {
			JsonNode pagingNode = jsonNode.get("paging");
			PagingParameters previousPage = getPagedListParameters(pagingNode, "previous");
			PagingParameters nextPage = getPagedListParameters(pagingNode, "next");
			return new PagedList<T>(posts, previousPage, nextPage);
		}
		
		return new PagedList<T>(posts, null, null);
	}

	private <T> T deserializePost(String postType, Class<T> type, ObjectNode node) {
		try {
			if (postType == null) {
				postType = determinePostType(node);
			}
			// Must have separate postType field for polymorphic deserialization. If we key off of the "type" field, then it will
			// be null when trying to deserialize the type property.
			node.put("postType", postType); // used for polymorphic deserialization
			node.put("type", postType); // used to set Post's type property
			return objectMapper.readValue(node, type);
		} catch (IOException shouldntHappen) {
			throw new UncategorizedApiException("facebook", "Error deserializing " + postType + " post", shouldntHappen);
		}
	}

	private String determinePostType(ObjectNode node) {
		if (node.has("type")) {
			try {
				String type = node.get("type").getTextValue();
				PostType.valueOf(type.toUpperCase());
				return type;
			} catch (IllegalArgumentException e) {
				return "post";
			}
		}
		return "post";
	}
	
	private URIBuilder appendPagedListParameters(PagingParameters pagedListParameters,
			URIBuilder uriBuilder) {
		if (pagedListParameters.getLimit() != null) {
			uriBuilder = uriBuilder.queryParam("limit", String.valueOf(pagedListParameters.getLimit()));
		}
		if (pagedListParameters.getSince() != null) {
			uriBuilder = uriBuilder.queryParam("since", String.valueOf(pagedListParameters.getSince()));
		}
		if (pagedListParameters.getUntil() != null) {
			uriBuilder = uriBuilder.queryParam("until", String.valueOf(pagedListParameters.getUntil()));
		}
		return uriBuilder;
	}

}
