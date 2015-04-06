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
package org.springframework.social.facebook.connect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserOperations;

public class FacebookAdapterTest {

	private FacebookAdapter apiAdapter = new FacebookAdapter();
	
	private Facebook facebook = Mockito.mock(Facebook.class);
	
	@Test
	public void fetchProfile() {		
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		Mockito.when(facebook.userOperations()).thenReturn(userOperations);
		Mockito.when(userOperations.getUserProfile()).thenReturn(new User("12345678", "Craig Walls", "Craig", "Walls", null, null));
		UserProfile profile = apiAdapter.fetchUserProfile(facebook);
		assertEquals("Craig Walls", profile.getName());
		assertEquals("Craig", profile.getFirstName());
		assertEquals("Walls", profile.getLastName());
		assertNull(profile.getEmail());
		assertNull(profile.getUsername());
	}

	@Test
	public void setConnectionValues() {		
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		Mockito.when(facebook.userOperations()).thenReturn(userOperations);
		Mockito.when(userOperations.getUserProfile()).thenReturn(new User("12345678", "Craig Walls", "Craig", "Walls", null, null));
		TestConnectionValues connectionValues = new TestConnectionValues();
		apiAdapter.setConnectionValues(facebook, connectionValues);
		assertEquals("Craig Walls", connectionValues.getDisplayName());
		assertEquals("https://graph.facebook.com/12345678/picture", connectionValues.getImageUrl());
		assertEquals("https://www.facebook.com/app_scoped_user_id/12345678/", connectionValues.getProfileUrl());
		assertEquals("12345678", connectionValues.getProviderUserId());
	}

	private static class TestConnectionValues implements ConnectionValues {

		private String displayName;
		private String imageUrl;
		private String profileUrl;
		private String providerUserId;

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getProfileUrl() {
			return profileUrl;
		}

		public void setProfileUrl(String profileUrl) {
			this.profileUrl = profileUrl;
		}

		public String getProviderUserId() {
			return providerUserId;
		}

		public void setProviderUserId(String providerUserId) {
			this.providerUserId = providerUserId;
		}
		
	}
}
