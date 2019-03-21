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
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.TestUserOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class TestUserTemplate extends AbstractFacebookOperations implements TestUserOperations {

	private String appId;
	
	private RestTemplate restTemplate;

	private GraphApi graphApi;

	/**
	 * @deprecated Construct with a GraphApi, RestTemplate, and appId instead.
	 * @param restTemplate the RestTemplate
	 * @param appId the app ID
	 */
	@Deprecated
	public TestUserTemplate(RestTemplate restTemplate, String appId) {
		this(null, restTemplate, appId);
	}
	
	public TestUserTemplate(GraphApi graphApi, RestTemplate restTemplate, String appId) {
		super(false);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
		this.appId = appId;
	}

	public TestUser createTestUser(boolean installed, String permissions) {
		return createTestUser(installed, permissions, null);
	}
	
	public TestUser createTestUser(boolean installed, String permissions, String name) {
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<String, Object>();
		request.set("installed", "" + installed);
		if (name != null) {
			request.set("name", name);
		}
		
		if (permissions != null) {
			request.set("permissions", permissions);
		}

		return restTemplate.postForObject(graphApi.getBaseGraphApiUrl() + "{appId}/accounts/test-users", request, TestUser.class, appId);
	}
	
	public void sendConfirmFriends(TestUser testUser1, TestUser testUser2) {
		RestOperations userRest = new FacebookTemplate(testUser1.getAccessToken()).restOperations();
		
		userRest.postForObject(graphApi.getBaseGraphApiUrl() + "{testUserId1}/friends/{testUserId2}", "", String.class, testUser1.getId(), testUser2.getId());
	}
	
	public void deleteTestUser(String testUserId) {
		restTemplate.delete(graphApi.getBaseGraphApiUrl() + "{testUserId}", testUserId);
	}
	
}
