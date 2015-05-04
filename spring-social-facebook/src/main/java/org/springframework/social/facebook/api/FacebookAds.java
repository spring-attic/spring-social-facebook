package org.springframework.social.facebook.api;

import org.springframework.social.facebook.api.impl.FacebookAdsTemplate;

/**
 * Interface specifying a basic set of operations for interacting with Facebook Marketing API.
 * Implemented by {@link FacebookAdsTemplate}.
 *
 * @author Sebastian Górecki
 */
public interface FacebookAds {
	AccountOperations accountOperations();
	CampaignOperations campaignOperations();
}
