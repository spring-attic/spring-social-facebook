package org.springframework.social.facebook.api.ads.impl;

import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.FacebookAds;
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

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, getRestTemplate(), isAuthorized());
		campaignOperations = new CampaignTemplate(this, getRestTemplate(), isAuthorized());
	}
}
