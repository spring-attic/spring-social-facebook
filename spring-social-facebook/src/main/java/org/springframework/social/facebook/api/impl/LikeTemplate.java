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

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Reference;
import org.springframework.util.LinkedMultiValueMap;

class LikeTemplate implements LikeOperations {

	private final GraphApi graphApi;

	public LikeTemplate(GraphApi graphApi) {
		this.graphApi = graphApi;
	}

	public void like(String objectId) {
		graphApi.post(objectId, "likes", new LinkedMultiValueMap<String, Object>());
	}

	public void unlike(String objectId) {
		graphApi.delete(objectId, "likes");
	}

	public PagedList<Reference> getLikes(String objectId) {
		return graphApi.fetchConnections(objectId, "likes", Reference.class);
	}

	public PagedList<Reference> getLikes(String objectId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(objectId, "likes", Reference.class, pagingParameters.toMap());
	}

	public PagedList<Page> getPagesLiked() {
		return getPagesLiked("me");
	}

	public PagedList<Page> getPagesLiked(PagingParameters pagingParameters) {
		return getPagesLiked("me", pagingParameters);
	}

	public PagedList<Page> getPagesLiked(String userId) {
		return graphApi.fetchConnections(userId, "likes", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getPagesLiked(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "likes", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getBooks() {
		return getBooks("me");
	}

	public PagedList<Page> getBooks(PagingParameters pagingParameters) {
		return getBooks("me", pagingParameters);
	}

	public PagedList<Page> getBooks(String userId) {
		return graphApi.fetchConnections(userId, "books", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getBooks(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "books", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getMovies() {
		return getMovies("me");
	}

	public PagedList<Page> getMovies(PagingParameters pagingParameters) {
		return getMovies("me", pagingParameters);
	}

	public PagedList<Page> getMovies(String userId) {
		return graphApi.fetchConnections(userId, "movies", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getMovies(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "movies", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getMusic() {
		return getMusic("me");
	}

	public PagedList<Page> getMusic(PagingParameters pagingParameters) {
		return getMusic("me", pagingParameters);
	}

	public PagedList<Page> getMusic(String userId) {
		return graphApi.fetchConnections(userId, "music", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getMusic(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "music", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getTelevision() {
		return getTelevision("me");
	}

	public PagedList<Page> getTelevision(PagingParameters pagingParameters) {
		return getTelevision("me", pagingParameters);
	}

	public PagedList<Page> getTelevision(String userId) {
		return graphApi.fetchConnections(userId, "television", Page.class, PAGE_FIELDS);
	}
	
	public PagedList<Page> getTelevision(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "television", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}
	
	public PagedList<Page> getGames() {
		return getGames("me");
	}

	public PagedList<Page> getGames(PagingParameters pagingParameters) {
		return getGames("me", pagingParameters);
	}

	public PagedList<Page> getGames(String userId) {
		return graphApi.fetchConnections(userId, "games", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getGames(String userId, PagingParameters pagingParameters) {
		return graphApi.fetchConnections(userId, "games", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	private static final String PAGE_FIELDS = "id,name,category,description,location,website,picture,phone,affiliation,company_overview,likes,checkins,cover";
}
