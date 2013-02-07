/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.facebook.config.xml;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * Implementation of {@link AbstractConnectionFactoryBeanDefinitionParser} that creates a {@link FacebookConnectionFactory}.
 * @author Craig Walls
 */
class FacebookConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

	public FacebookConfigBeanDefinitionParser() {
		super(FacebookConnectionFactory.class, FacebookApiHelper.class);
	}
	
	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return FacebookAuthenticationService.class;
	}
	
	@Override
	protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FacebookConnectionFactory.class).addConstructorArgValue(appId).addConstructorArgValue(appSecret);
		if (allAttributes.containsKey("app-namespace")) {
			builder.addConstructorArgValue(allAttributes.get("app-namespace"));			
		}
		return builder.getBeanDefinition();
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
				logger.debug("Getting API binding instance for Facebook");
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
