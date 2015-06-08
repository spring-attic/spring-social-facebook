package org.springframework.social.facebook.api.ads.impl;

import org.springframework.social.facebook.api.ads.*;
import org.springframework.social.facebook.api.ads.impl.AccountTemplate;
import org.springframework.social.facebook.api.ads.impl.CampaignTemplate;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

/**
 * This is the central class for interacting with Facebook Marketing API.
 *
 * @author Sebastian Górecki
 */
public class FacebookAdsTemplate extends FacebookTemplate implements FacebookAds {

	private AccountOperations accountOperations;
	private CampaignOperations campaignOperations;
	private AdSetOperations adSetOperations;

	public FacebookAdsTemplate() {
		super(null);
		initSubApis();
	}

	public FacebookAdsTemplate(String accessToken) {
		super(accessToken);
		initSubApis();
	}

	public AccountOperations accountOperations() {
		return accountOperations;
	}

	public CampaignOperations campaignOperations() {
		return campaignOperations;
	}

	public AdSetOperations adSetOperations() { return adSetOperations; }

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, getRestTemplate(), isAuthorized());
		campaignOperations = new CampaignTemplate(this, getRestTemplate(), isAuthorized());
		adSetOperations = new AdSetTemplate(this, getRestTemplate(), getJsonMessageConverter().getObjectMapper(), isAuthorized());
	}
}
