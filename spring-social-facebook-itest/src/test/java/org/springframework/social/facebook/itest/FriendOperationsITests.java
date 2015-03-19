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

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class FriendOperationsITests extends FacebookITest implements ITestCredentials {

	public FriendOperationsITests() {
		super(APP_ID, APP_SECRET);
	}
	
	@Test
	public void testFriendOperations() throws Exception {
		TestUser testUser1 = createTestUser(true, "", "Jack Sparrow");
		TestUser testUser2 = createTestUser(true, "", "Joshamee Gibbs");

		FriendOperations friendOps1 = new FacebookTemplate(testUser1.getAccessToken()).friendOperations();
		FriendOperations friendOps2 = new FacebookTemplate(testUser2.getAccessToken()).friendOperations();
		
		assertEquals(0, friendOps1.getFriendIds().size());
		assertEquals(0, friendOps2.getFriendIds().size());

		clientFacebook.testUserOperations().sendConfirmFriends(testUser1, testUser2);
		clientFacebook.testUserOperations().sendConfirmFriends(testUser2, testUser1);
		
		List<String> testUser1FriendIds = friendOps1.getFriendIds();
		assertEquals(1, testUser1FriendIds.size());
		assertEquals(testUser2.getId(), testUser1FriendIds.get(0));

		List<Reference> testUser1Friends = friendOps1.getFriends();
		assertEquals(1, testUser1Friends.size());
		assertEquals(testUser2.getId(), testUser1Friends.get(0).getId());

		PagedList<FacebookProfile> testUser1FriendProfiles = friendOps1.getFriendProfiles();
		assertEquals(1, testUser1FriendProfiles.size());
		assertEquals(testUser2.getId(), testUser1FriendProfiles.get(0).getId());

		List<String> testUser2FriendIds = friendOps2.getFriendIds();
		assertEquals(1, friendOps2.getFriendIds().size());
		assertEquals(testUser1.getId(), testUser2FriendIds.get(0));

		List<Reference> testUser2Friends = friendOps2.getFriends();
		assertEquals(1, testUser2Friends.size());
		assertEquals(testUser1.getId(), testUser2Friends.get(0).getId());
		
		PagedList<FacebookProfile> testUser2FriendProfiles = friendOps2.getFriendProfiles();
		assertEquals(1, testUser2FriendProfiles.size());
		assertEquals(testUser1.getId(), testUser2FriendProfiles.get(0).getId());
	
	}
	
}
