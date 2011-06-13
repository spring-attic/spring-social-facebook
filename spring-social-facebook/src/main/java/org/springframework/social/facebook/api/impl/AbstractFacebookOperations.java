/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.facebook.api.impl;

import org.springframework.social.AuthorizationRequiredException;

class AbstractFacebookOperations {
	
	private final boolean isAuthorizedForUser;

	public AbstractFacebookOperations(boolean isAuthorizedForUser) {
		this.isAuthorizedForUser = isAuthorizedForUser;
	}
	
	protected void requireUserAuthorization() {
		if(!isAuthorizedForUser) {
			throw new AuthorizationRequiredException("User authorization required: FacebookTemplate must be created with an access token to perform this operation.");
		}
	}
	
}
