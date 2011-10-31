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
package org.springframework.social.facebook.api.ads;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ads.impl.FacebookAdsTemplate;

/**
 * Interface specifying a basic set of operations for interacting with ads on
 * Facebook.
 * 
 * @see {@link FacebookAdsTemplate}.
 * @author Karthick Sankarachary
 */
public interface FacebookAds extends Facebook {
	/**
	 * API for performing operations on Facebook ads accounts.
	 */
	public AccountOperations accountOperations();

	/**
	 * API for performing operations on Facebook ads account groups.
	 */
	public AccountGroupOperations accountGroupOperations();

	/**
	 * API for performing operations on Facebook ads campaigns.
	 */
	public CampaignOperations campaignOperations();

	/**
	 * API for performing operations on Facebook ads creatives.
	 */
	public CreativeOperations creativeOperations();

	/**
	 * API for performing operations on Facebook ads groups.
	 */
	public AdGroupOperations adGroupOperations();

}
