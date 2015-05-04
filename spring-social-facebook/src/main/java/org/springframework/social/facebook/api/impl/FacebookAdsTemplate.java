package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.AccountOperations;
import org.springframework.social.facebook.api.CampaignOperations;
import org.springframework.social.facebook.api.FacebookAds;

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
