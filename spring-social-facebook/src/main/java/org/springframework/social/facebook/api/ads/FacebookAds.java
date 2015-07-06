package org.springframework.social.facebook.api.ads;

import org.springframework.social.facebook.api.ads.impl.FacebookAdsTemplate;

/**
 * Interface specifying a basic set of operations for interacting with Facebook Marketing API.
 * Implemented by {@link FacebookAdsTemplate}.
 *
 * @author Sebastian Górecki
 */
public interface FacebookAds {
	/**
	 * API for working with Facebook Ad account.
	 *
	 * @return {@link AccountOperations}
	 */
	AccountOperations accountOperations();

	/**
	 * API for working with Facebook Ad campaign.
	 *
	 * @return {@link CampaignOperations}
	 */
	CampaignOperations campaignOperations();

	/**
	 * API for working with Facebook AdSet.
	 *
	 * @return {@link AdSetOperations}
	 */
	AdSetOperations adSetOperations();

	/**
	 * API for working with Facebook creative.
	 *
	 * @return {@link CreativeOperations}
	 */
	CreativeOperations creativeOperations();

	/**
	 * API for working with Facebook ad.
	 *
	 * @return {@link AdOperations}
	 */
	AdOperations adOperations();
}
