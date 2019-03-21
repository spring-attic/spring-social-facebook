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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PageAdministrationException;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.PageUpdate;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class PageTemplate implements PageOperations {

	private final GraphApi graphApi;
	
	public PageTemplate(GraphApi graphApi) {
		this.graphApi = graphApi;
	}

	public Page getPage(String pageId) {
		return graphApi.fetchObject(pageId, Page.class);
	}

	public void updatePage(PageUpdate pageUpdate) {
		String pageId = pageUpdate.getPageId();
		String pageAccessToken = getAccessToken(pageId);
		MultiValueMap<String, Object> map = pageUpdate.toRequestParameters();
		map.add("access_token", pageAccessToken);
		graphApi.post(pageId, map);
	}
	
	public boolean isPageAdmin(String pageId) {
		return getAccount(pageId) != null;
	}
	
	public PagedList<Account> getAccounts() {
		return graphApi.fetchConnections("me", "accounts", Account.class);
	}

	public String post(String pageId, String message) {
		return post(new PagePostData(pageId).message(message));
	}
	
	public String post(String pageId, String message, FacebookLink link) {
		PagePostData postData = new PagePostData(pageId)
				.message(message)
				.link(link.getLink(), link.getPicture(), link.getName(), link.getCaption(), link.getDescription());
		return post(postData);
	}
	
	public String post(PagePostData post) {
		String pageId = post.getPageId();
		String pageAccessToken = getAccessToken(pageId);
		MultiValueMap<String, Object> map = post.toRequestParameters();
		map.set("access_token", pageAccessToken);
		return graphApi.publish(pageId, "feed", map);
	}

	public String postPhoto(String pageId, String albumId, Resource photo) {
		return postPhoto(pageId, albumId, photo, null);
	}
	
	public String postPhoto(String pageId, String albumId, Resource photo, String caption) {
		String pageAccessToken = getAccessToken(pageId);
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		if(caption != null) {
			parts.set("message", caption);
		}
		parts.set("access_token", pageAccessToken);
		return graphApi.publish(albumId, "photos", parts);
	}
	
	public PagedList<Page> search(String query) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "page");
		return graphApi.fetchConnections("search", null, Page.class, queryMap);
	}
	
	public PagedList<Page> searchPlaces(String query, double latitude, double longitude, long distance) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "place");
		queryMap.add("center", latitude + "," + longitude);
		queryMap.add("distance", String.valueOf(distance));
		return graphApi.fetchConnections("search", null, Page.class, queryMap);
	}

	public String getAccessToken(String pageId) {
		Account account = getAccount(pageId);
		if(account == null) {
			throw new PageAdministrationException(pageId);
		}
		return account.getAccessToken();
	}

	public Account getAccount(String pageId) {
		if(!accountCache.containsKey(pageId)) {
			// only bother fetching the account data in the event of a cache miss
			List<Account> accounts = getAccounts();
			for (Account account : accounts) {
				accountCache.put(account.getId(), account);
			}
		}
		return accountCache.get(pageId);
	}
	
	public Facebook facebookOperations(String pageId) {
		return new FacebookTemplate(getAccessToken(pageId));
	}

	// private helper methods
	
	private Map<String, Account> accountCache = new HashMap<String, Account>();
	
}
