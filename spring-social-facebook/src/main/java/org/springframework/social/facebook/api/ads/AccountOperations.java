package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.AdUser.AdUserRole;

/**
 * Defines operations for working with Facebook Marketing API Ad Account object.
 *
 * @author Sebastian Górecki
 */
public interface AccountOperations {

	static final String[] AD_ACCOUNT_FIELDS = {
			"id", "account_id", "account_status", "age", "amount_spent", "balance", "business_city", "business_country_code",
			"business_name", "business_state", "business_street", "business_street2", "business_zip", "capabilities",
			"created_time", "currency", "daily_spend_limit", "end_advertiser", "funding_source", "funding_source_details",
			"is_personal", "media_agency", "name", "offsite_pixels_tos_accepted", "partner", "spend_cap", "timezone_id",
			"timezone_name", "timezone_offset_hours_utc", "users", "tax_id_status"
	};

	static final String[] AD_ACCOUNT_INSIGHT_FIELDS = {
			"account_id", "account_name", "date_start", "date_stop", "actions_per_impression", "clicks", "unique_clicks",
			"cost_per_result", "cost_per_total_action", "cpc", "cost_per_unique_click", "cpm", "cpp", "ctr", "unique_ctr",
			"frequency", "impressions", "unique_impressions", "objective", "reach", "result_rate", "results", "roas",
			"social_clicks", "unique_social_clicks", "social_impressions", "unique_social_impressions", "social_reach",
			"spend", "today_spend", "total_action_value", "total_actions", "total_unique_actions", "actions",
			"unique_actions", "cost_per_action_type", "video_start_actions"
	};

	/**
	 * Get all ad accounts for given user.
	 *
	 * @param userId the id of an user
	 * @return the list of {@link AdAccount} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdAccount> getAdAccounts(String userId);

	/**
	 * Get the ad account by given id.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @return the {@link AdAccount} object
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdAccount getAdAccount(String accountId);

	/**
	 * Get all ad campaigns of an ad account.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @return the list of {@link AdCampaign} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdCampaign> getAdAccountCampaigns(String accountId);

	/**
	 * Get all users of the ad account.
	 *
	 * @param accountId the ID of the ad account, the string act_{ad_account_id}
	 * @return the list of {@link AdUser} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdUser> getAdAccountUsers(String accountId);

	/**
	 * Add the user to the ad account.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @param userId    the id of an user (App Scoped User ID)
	 * @param role      the role for the new user in ad account
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void addUserToAdAccount(String accountId, String userId, AdUserRole role);

	/**
	 * Remove user's access to an ad account.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @param userId    the id of an user (App Scoped User ID)
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void deleteUserFromAdAccount(String accountId, String userId);

	/**
	 * Get the insight for the ad account in aggregate.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @return the {@link AdInsight} object
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 */
	AdInsight getAdAccountInsight(String accountId);

	/**
	 * Updates the ad account with information given in adAccount object.
	 * Currently only updates on ad account name and spend_cap are supported.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @param adAccount the ad account object containing updated account information
	 * @return true if the update succeeded
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 */
	boolean updateAdAccount(String accountId, AdAccount adAccount);
}
