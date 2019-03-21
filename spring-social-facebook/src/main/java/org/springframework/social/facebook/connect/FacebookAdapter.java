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
package org.springframework.social.facebook.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;

/**
 * Facebook ApiAdapter implementation.
 * @author Keith Donald
 */
public class FacebookAdapter implements ApiAdapter<Facebook> {

	public boolean test(Facebook facebook) {
		try {
			facebook.userOperations().getUserProfile();
			return true;
		} catch (ApiException e) {
			return false;
		}
	}

	public void setConnectionValues(Facebook facebook, ConnectionValues values) {
		User profile = facebook.fetchObject("me", User.class, "id", "name", "link");
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getName());
		values.setProfileUrl(profile.getLink());
		values.setImageUrl(facebook.getBaseGraphApiUrl() + profile.getId() + "/picture");
	}

	public UserProfile fetchUserProfile(Facebook facebook) {
		User profile = facebook.userOperations().getUserProfile();
		return new UserProfileBuilder().setId(profile.getId()).setName(profile.getName()).setFirstName(profile.getFirstName()).setLastName(profile.getLastName()).
			setEmail(profile.getEmail()).build();
	}
	
	public void updateStatus(Facebook facebook, String message) {
		facebook.feedOperations().updateStatus(message);
	}

}
