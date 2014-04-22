/*
 * Copyright 2014 the original author or authors.
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

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Facebook ServiceProvider implementation.
 * @author Keith Donald
 * @author Craig Walls
 */
public class FacebookServiceProvider extends AbstractOAuth2ServiceProvider<Facebook> {

	private String appNamespace;

	/**
	 * Creates a FacebookServiceProvider for the given application ID, secret, and namespace.
	 * @param appId The application's App ID as assigned by Facebook 
	 * @param appSecret The application's App Secret as assigned by Facebook
	 * @param appNamespace The application's App Namespace as configured with Facebook. Enables use of Open Graph operations.
	 */
	public FacebookServiceProvider(String appId, String appSecret, String appNamespace) {
		super(new FacebookOAuth2Template(appId, appSecret));
		this.appNamespace = appNamespace;
	}

	public Facebook getApi(String accessToken) {
		return new FacebookTemplate(accessToken, appNamespace);
	}
	
}
