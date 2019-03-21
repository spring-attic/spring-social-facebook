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

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class ErrorsITests extends FacebookITest implements ITestCredentials {

	private static final Logger logger = Logger.getLogger(ErrorsITests.class.getName());
	
	private TestUser testUser1;
	private Facebook fb;

	public ErrorsITests() {
		super("162886103757745", "fa8a9825f555a7a8949ec48fb93bda58");
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Alice Arensen");
		fb = new FacebookTemplate(testUser1.getAccessToken());
		logger.info("CREATED TEST USERS: " + testUser1.getId());
	}

	@Test
	public void errorTests() throws Exception {
		
		String postId = fb.feedOperations().updateStatus("Hello");
		
		String postId2 = fb.feedOperations().updateStatus("Hello");
		
	}

}
