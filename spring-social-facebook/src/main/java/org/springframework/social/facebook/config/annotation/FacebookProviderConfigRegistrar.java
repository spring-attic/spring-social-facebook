/*
 * Copyright 2012 the original author or authors.
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
package org.springframework.social.facebook.config.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * {@link ImportBeanDefinitionRegistrar} for configuring a {@link FacebookConnectionFactory} bean and a request-scoped {@link Facebook} bean.
 * @author Craig Walls
 */
public class FacebookProviderConfigRegistrar extends AbstractProviderConfigRegistrarSupport {

	public FacebookProviderConfigRegistrar() {
		super(EnableFacebook.class, FacebookConnectionFactory.class, FacebookApiHelper.class);
		try {
			setAuthenticationServiceClass("org.springframework.social.facebook.security.FacebookAuthenticationService");
		} catch (ClassNotFoundException shouldntHappen) {
			// shouldn't happen unless the class name or package are refactored
		}
	}
	
	static class FacebookApiHelper implements ApiHelper<Facebook> {
		
		private final UsersConnectionRepository usersConnectionRepository;

		private final UserIdSource userIdSource;

		private FacebookApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
			this.usersConnectionRepository = usersConnectionRepository;
			this.userIdSource = userIdSource;		
		}

		public Facebook getApi() {
			if (logger.isDebugEnabled()) {
				logger.debug("Getting API binding instance for Facebook provider");
			}
					
			Connection<Facebook> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(Facebook.class);
			if (logger.isDebugEnabled() && connection == null) {
				logger.debug("No current connection; Returning default FacebookTemplate instance.");
			}
			return connection != null ? connection.getApi() : new FacebookTemplate();
		}
		
		private final static Log logger = LogFactory.getLog(FacebookApiHelper.class);

	}

}
