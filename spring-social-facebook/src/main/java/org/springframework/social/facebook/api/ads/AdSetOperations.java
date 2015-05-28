package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;

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
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdSet getAdSet(String id);

	/**
	 * Creates an ad set in the given account
	 *
	 * @param accountId the account id
	 * @param adSet     the ad set object
	 * @return the id of the new ad set.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	String createAdSet(String accountId, AdSet adSet);

	/**
	 * Updates the ad set.
	 *
	 * @param adSetId    the id of the ad set
	 * @param adSet the ad set object
	 * @return true if update was successful
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	boolean updateAdSet(String adSetId, AdSet adSet);

	/**
	 * Deletes ad set given by id.
	 *
	 * @param adSetId the id of the ad set
	 */
	void deleteAdSet(String adSetId);
}
