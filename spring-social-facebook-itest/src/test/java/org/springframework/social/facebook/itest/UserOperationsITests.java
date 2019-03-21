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

import java.util.List;

import org.junit.Test;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Permission;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class UserOperationsITests extends FacebookITest implements ITestCredentials {

	public UserOperationsITests() {
		super(APP_ID, APP_SECRET);
	}
	
	@Test
	public void testUserOperations() throws Exception {
		// perform all tests in one method to avoid cost of creating new test users for each test method.

		TestUser testUser = createTestUser(true, "", "Jack Sparrow");
		
		Facebook fb = new FacebookTemplate(testUser.getAccessToken());
		
		List<Permission> userPermissions = fb.userOperations().getUserPermissions();
		assertEquals(2, userPermissions.size());
		
		User profile = fb.userOperations().getUserProfile();
		assertEquals(testUser.getId(), profile.getId());
		
		profile = fb.userOperations().getUserProfile(testUser.getId());
		assertEquals(testUser.getId(), profile.getId());

		assertNonEmpty(fb.userOperations().getUserProfileImage());
		assertNonEmpty(fb.userOperations().getUserProfileImage(ImageType.NORMAL));
		assertNonEmpty(fb.userOperations().getUserProfileImage(ImageType.SQUARE));
		assertNonEmpty(fb.userOperations().getUserProfileImage(ImageType.SMALL));
		assertNonEmpty(fb.userOperations().getUserProfileImage(ImageType.LARGE));
		
		assertNonEmpty(fb.userOperations().getUserProfileImage(testUser.getId()));
		assertNonEmpty(fb.userOperations().getUserProfileImage(testUser.getId(), ImageType.NORMAL));
		assertNonEmpty(fb.userOperations().getUserProfileImage(testUser.getId(), ImageType.SQUARE));
		assertNonEmpty(fb.userOperations().getUserProfileImage(testUser.getId(), ImageType.SMALL));
		assertNonEmpty(fb.userOperations().getUserProfileImage(testUser.getId(), ImageType.LARGE));
		
		// Tests that the request works, but (oddly) searching as a test user doesn't return any results.
		PagedList<Reference> search = fb.userOperations().search("Bill");
		assertNotNull(search);
		
	}
	
	private void assertNonEmpty(byte[] bytes) {
		assertTrue(bytes.length > 0);
	}
}
