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

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class LikeOperationsITests extends FacebookITest implements ITestCredentials {

	private static final String SPRING_SOCIAL_PAGE_ID = "197280340404056";

	private static final Logger logger = Logger.getLogger(LikeOperationsITests.class.getName());
	
	private TestUser testUser1;
	private LikeOperations likeOps1;

	public LikeOperationsITests() {
		super("162886103757745", "fa8a9825f555a7a8949ec48fb93bda58");
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Alice Arensen");
		likeOps1 = new FacebookTemplate(testUser1.getAccessToken()).likeOperations();
		logger.info("CREATED TEST USERS: " + testUser1.getId());
	}

	@Test
	public void likeTests() throws Exception {
		// NOTE: Only able to do surface tests...unable to actually "like" something.
		assertNotNull(likeOps1.getLikes(SPRING_SOCIAL_PAGE_ID));
		assertEquals(0, likeOps1.getBooks().size());
		assertEquals(0, likeOps1.getGames().size());
		assertEquals(0, likeOps1.getMovies().size());
		assertEquals(0, likeOps1.getMusic().size());
		assertEquals(0, likeOps1.getPagesLiked().size());
		assertEquals(0, likeOps1.getTelevision().size());
	}

}
