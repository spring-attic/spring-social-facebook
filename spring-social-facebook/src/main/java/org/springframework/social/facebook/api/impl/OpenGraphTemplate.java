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

import org.springframework.social.facebook.api.ActionMetadata;
import org.springframework.social.facebook.api.BookActions;
import org.springframework.social.facebook.api.FitnessActions;
import org.springframework.social.facebook.api.GeneralActions;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.MissingNamespaceException;
import org.springframework.social.facebook.api.MusicActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.social.facebook.api.VideoActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class OpenGraphTemplate extends AbstractFacebookOperations implements OpenGraphOperations {
	
	private static final ActionMetadata EMPTY_ACTION_METADATA = new ActionMetadata();
	
	private GraphApi graphApi;
	
	private GeneralActions generalActions;
	
	private MusicActions musicActions;
	
	private BookActions bookActions;
	
	private VideoActions videoActions;
	
	private FitnessActions fitnessActions;

	private String appId;

	private String appSecret;

	public OpenGraphTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.generalActions = new GeneralActionsTemplate(this);
		this.bookActions = new BookActionsTemplate(this);
		this.musicActions = new MusicActionsTemplate(this);
		this.videoActions = new VideoActionsTemplate(this);
		this.fitnessActions = new FitnessActionsTemplate(this);
	} 
	
	public OpenGraphTemplate(FacebookTemplate facebookTemplate, String appId,
			String appSecret, boolean authorized) {
		this(facebookTemplate, authorized);
		this.appId = appId;
		this.appSecret = appSecret;
	}

	public GeneralActions generalActions() {
		return generalActions;
	}
	
	public MusicActions musicActions() {
		return musicActions;
	}

	public BookActions bookActions() {
		return bookActions;
	}

	public VideoActions videoActions() {
		return videoActions;
	}
	
	public FitnessActions fitnessActions() {
		return fitnessActions;
	}
	
	public void deleteAction(String actionId) {
		graphApi.delete(actionId);
	}
	
	public String publishAction(String action, String objectType, String objectUrl) {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set(objectType, objectUrl);
		return publishAction(graphApi.getApplicationNamespace() + ":" + action, parameters, false);
	}
	
	public String publishAction(String action, MultiValueMap<String, Object> parameters, boolean builtInAction) {
		requireAuthorization();
		if (!builtInAction) {
			requireApplicationNamespace();
		}
		return graphApi.publish("me", action, parameters);
	}
	
	public void sendAppNotification(String userId, String href, String template) {
		requireAuthorization();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		map.set("access_token", appId + "|" + appSecret);
		map.set("href", href);
		map.set("template", template);
		
		graphApi.post(userId, "notifications", map);
	}

	private void requireApplicationNamespace() {
		String applicationNamespace = graphApi.getApplicationNamespace();
		if (applicationNamespace == null || applicationNamespace.isEmpty()) {
			throw new MissingNamespaceException();
		}
	}
	
}
