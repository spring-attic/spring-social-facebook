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

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Reference;
import org.springframework.util.LinkedMultiValueMap;

class LikeTemplate extends AbstractFacebookOperations implements LikeOperations {

	private final GraphApi graphApi;

	public LikeTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public void like(String objectId) {
		requireAuthorization();
		graphApi.post(objectId, "likes", new LinkedMultiValueMap<String, String>());
	}

	public void unlike(String objectId) {
		requireAuthorization();
		graphApi.delete(objectId, "likes");
	}

	public PagedList<Reference> getLikes(String objectId) {
		requireAuthorization();
		return graphApi.fetchConnections(objectId, "likes", Reference.class);
	}

	public PagedList<Reference> getLikes(String objectId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(objectId, "likes", Reference.class, pagingParameters.toMap());
	}

	public PagedList<Page> getPagesLiked() {
		return getPagesLiked("me");
	}

	public PagedList<Page> getPagesLiked(PagingParameters pagingParameters) {
		return getPagesLiked("me", pagingParameters);
	}

	public PagedList<Page> getPagesLiked(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "likes", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getPagesLiked(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "likes", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getBooks() {
		return getBooks("me");
	}

	public PagedList<Page> getBooks(PagingParameters pagingParameters) {
		return getBooks("me", pagingParameters);
	}

	public PagedList<Page> getBooks(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "books", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getBooks(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "books", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getMovies() {
		return getMovies("me");
	}

	public PagedList<Page> getMovies(PagingParameters pagingParameters) {
		return getMovies("me", pagingParameters);
	}

	public PagedList<Page> getMovies(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "movies", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getMovies(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "movies", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getMusic() {
		return getMusic("me");
	}

	public PagedList<Page> getMusic(PagingParameters pagingParameters) {
		return getMusic("me", pagingParameters);
	}

	public PagedList<Page> getMusic(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "music", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getMusic(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "music", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getTelevision() {
		return getTelevision("me");
	}

	public PagedList<Page> getTelevision(PagingParameters pagingParameters) {
		return getTelevision("me", pagingParameters);
	}

	public PagedList<Page> getTelevision(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "television", Page.class, PAGE_FIELDS);
	}
	
	public PagedList<Page> getTelevision(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "television", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}
	
	public PagedList<Page> getActivities() {
		return getActivities("me");
	}

	public PagedList<Page> getActivities(PagingParameters pagingParameters) {
		return getActivities("me", pagingParameters);
	}

	public PagedList<Page> getActivities(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "activities", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getActivities(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "activities", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getInterests() {
		return getInterests("me");
	}

	public PagedList<Page> getInterests(PagingParameters pagingParameters) {
		return getInterests("me", pagingParameters);
	}

	public PagedList<Page> getInterests(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "interests", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getInterests(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "interests", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	public PagedList<Page> getGames() {
		return getGames("me");
	}

	public PagedList<Page> getGames(PagingParameters pagingParameters) {
		return getGames("me", pagingParameters);
	}

	public PagedList<Page> getGames(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "games", Page.class, PAGE_FIELDS);
	}

	public PagedList<Page> getGames(String userId, PagingParameters pagingParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "games", Page.class, pagingParameters.toMap(), PAGE_FIELDS);
	}

	private static final String PAGE_FIELDS = "id,name,category,description,location,website,picture,phone,affiliation,company_overview,likes,checkins";
}
