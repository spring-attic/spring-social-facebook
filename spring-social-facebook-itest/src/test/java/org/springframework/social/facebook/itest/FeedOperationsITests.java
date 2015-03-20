/*
 * Copyright 2015 the original author or authors.
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
package org.springframework.social.facebook.itest;

import java.util.List;

import org.junit.Test;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class FeedOperationsITests extends FacebookITest implements ITestCredentials {

	public FeedOperationsITests() {
		super(APP_ID, APP_SECRET);
	}
	
	@Test
	public void testFeedOperations() throws Exception {
		// perform all tests in one method to avoid cost of creating new test users for each test method.
		
		TestUser testUser1 = createTestUser(true, "publish_actions", "Alice Arensen");
//		TestUser testUser2 = createTestUser(true, "manage_friendlists", "Joshamee Gibbs");

		FeedOperations feedOps1 = new FacebookTemplate(testUser1.getAccessToken()).feedOperations();
		
		List<String> userPermissions = new FacebookTemplate(testUser1.getAccessToken()).userOperations().getUserPermissions();
		System.out.println(userPermissions);
		
		String postId = feedOps1.updateStatus("Hello");
		System.out.println(postId);
	}
	
}
