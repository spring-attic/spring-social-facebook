/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads.impl;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.FacebookAds;
import org.springframework.social.facebook.api.ads.SearchOperations;
import org.springframework.social.facebook.api.ads.impl.json.FacebookAdsModule;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

/**
 * @author Karthick Sankarachary
 */
public class FacebookAdsTemplate extends FacebookTemplate implements
		FacebookAds {
	private AccountOperations accountOperations;
	private AccountGroupOperations accountGroupOperations;
	private CampaignOperations campaignOperations;
	private CreativeOperations creativeOperations;
	private AdGroupOperations adGroupOperations;
	private SearchOperations searchOperations;

	public FacebookAdsTemplate() {
		super();
		initialize();
	}

	public FacebookAdsTemplate(String accessToken) {
		super(accessToken);
		initialize();
	}

	private void initialize() {
		initSubApis();
	}

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, getRestTemplate(),
				isAuthorized());
		accountGroupOperations = new AccountGroupTemplate(this, isAuthorized());
		campaignOperations = new CampaignTemplate(this, isAuthorized());
		creativeOperations = new CreativeTemplate(this, isAuthorized());
		adGroupOperations = new AdGroupTemplate(this, getRestTemplate(),
				isAuthorized());
		searchOperations = new SearchTemplate(this, objectMapper, isAuthorized());
	}

	public AccountOperations accountOperations() {
		return accountOperations;
	}

	public AccountGroupOperations accountGroupOperations() {
		return accountGroupOperations;
	}

	public CampaignOperations campaignOperations() {
		return campaignOperations;
	}

	public CreativeOperations creativeOperations() {
		return creativeOperations;
	}

	public AdGroupOperations adGroupOperations() {
		return adGroupOperations;
	}

	@Override
	public SearchOperations searchOperations() {
		return searchOperations;
	}

	@Override
	protected MappingJacksonHttpMessageConverter getJsonMessageConverter() {
		MappingJacksonHttpMessageConverter converter = super
				.getJsonMessageConverter();
		objectMapper.registerModule(new FacebookAdsModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}
}
