package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.AccountOperations;
import org.springframework.social.facebook.api.FacebookAds;

/**
 * This is the central class for interacting with Facebook Marketing API.
 *
 * @author Sebastian Górecki
 */
public class FacebookAdsTemplate extends FacebookTemplate implements FacebookAds {

	private AccountOperations accountOperations;

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

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, getRestTemplate(), isAuthorized());
	}
}
