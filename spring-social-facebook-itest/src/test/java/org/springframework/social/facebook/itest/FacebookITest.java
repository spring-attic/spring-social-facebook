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
package org.springframework.social.facebook.itest;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookServiceProvider;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;

public abstract class FacebookITest {

	protected FacebookTemplate clientFacebook;

	private String appId; 
	private String appSecret;
	private List<String> testUserIds = new ArrayList<String>();
	

	public FacebookITest(String appId, String appSecret) {
		this.appId = appId;
		this.appSecret = appSecret;
	}
	
	@Before
	public void setup() {
		OAuth2Operations oauth = new FacebookServiceProvider(appId, appSecret, null).getOAuthOperations();
		AccessGrant clientGrant = oauth.authenticateClient();
		clientFacebook = new FacebookTemplate(clientGrant.getAccessToken(), "", appId);
	}

	public TestUser createTestUser(boolean installed, String permissions, String name) {
		TestUser testUser = clientFacebook.testUserOperations().createTestUser(installed, permissions, name);
		testUserIds.add(testUser.getId()); // keep track of user so we can remove it after the test
		return testUser;
	}
	
	@After
	public void teardown() {
		for (String testUserId : testUserIds) {
			clientFacebook.testUserOperations().deleteTestUser(testUserId);
		}
	}

}
