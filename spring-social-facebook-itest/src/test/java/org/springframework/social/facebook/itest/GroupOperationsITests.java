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
import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.GroupOperations;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class GroupOperationsITests extends FacebookITest implements ITestCredentials {

	private static final Logger logger = Logger.getLogger(GroupOperationsITests.class.getName());
	
	private TestUser testUser1;
	private GroupOperations groupOps1;

	private static final String groupId = "179807812093847"; // F8 Hack group for testing

	public GroupOperationsITests() {
		super(APP_ID, APP_SECRET);
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Alice Arensen");
		groupOps1 = new FacebookTemplate(testUser1.getAccessToken()).groupOperations();
		logger.info("CREATED TEST USERS: " + testUser1.getId());
	}

	@Test
	public  void groupTests() {
	
		// NOTE: These tests are surface tests only. In other words, they only test to make sure that the endpoints
		//       won't fail. Although it's possible to create a group for testing purposes, it seems impossible to
		//       delete it because you can't remove all members from the group. Instead, using a fixed group for
		//       surface testing purposes.
		//
		// TODO: Figure out how to create and delete a group.
		
		Group group = groupOps1.getGroup(groupId);
		assertEquals(groupId, group.getId());
		assertEquals("f8 Hack", group.getName());
		
		assertNotNull(groupOps1.getMembers(groupId));
		assertNotNull(groupOps1.getMemberProfiles(groupId));
		assertNotNull(groupOps1.getMemberships());
		
		byte[] groupImage = groupOps1.getGroupImage(groupId);
		assertTrue(groupImage.length > 0);
		
		groupImage = groupOps1.getGroupImage(groupId, ImageType.LARGE);
		assertTrue(groupImage.length > 0);
	}

}
