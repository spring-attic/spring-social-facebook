package org.springframework.social.facebook.api.ads;

/**
 * Defines operations for working with Facebook Ad set object.
 *
 * @author Sebastian Górecki
 */
public interface AdSetOperations {
	static final String[] AD_SET_FIELDS = {
			"account_id", "bid_info", "bid_type", "budget_remaining", "campaign_group_id", "campaign_status", "created_time",
			"creative_sequence", "daily_budget", "end_time", "id", "is_autobid", "lifetime_budget", "name", "promoted_object",
			"start_time", "targeting", "updated_time"
	};

	/**
	 * Gets ad set by given id.
	 *
	 * @param id the id of the ad set
	 * @return the {@link AdSet} object
	 */
	AdSet getAdSet(String id);

	/**
	 * Creates an ad set in the given account
	 *
	 * @param accountId the account id
	 * @param adSet     the ad set object
	 * @return the id of the new ad set.
	 */
	String createAdSet(String accountId, AdSet adSet);
}
