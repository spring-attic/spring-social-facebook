package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.PagedList;

/**
 * @author Sebastian Górecki
 */
public interface AdOperations {
	static final String[] AD_FIELDS = {
			"id", "account_id", "adgroup_status", "bid_type", "bid_info", "campaign_id", "campaign_group_id", "created_time",
			"creative", "name", "targeting", "updated_time"
	};

	static final String[] AD_INSIGHT_FIELDS = {
			"account_id", "account_name", "date_start", "date_stop", "actions_per_impression", "clicks", "unique_clicks",
			"cost_per_result", "cost_per_total_action", "cpc", "cost_per_unique_click", "cpm", "cpp", "ctr", "unique_ctr",
			"frequency", "impressions", "unique_impressions", "objective", "reach", "result_rate", "results", "roas",
			"social_clicks", "unique_social_clicks", "social_impressions", "unique_social_impressions", "social_reach",
			"spend", "today_spend", "total_action_value", "total_actions", "total_unique_actions", "actions",
			"unique_actions", "cost_per_action_type", "video_start_actions"
	};

	/**
	 * Get all ads from one ad account.
	 *
	 * @param accountId the ID of the ad account
	 * @return the list of the {@link Ad} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<Ad> getAccountAds(String accountId);

	/**
	 * Get all ads from one ad campaign.
	 *
	 * @param campaignId the id of the ad campaign.
	 * @return the list of the {@link Ad} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<Ad> getCampaignAds(String campaignId);

	/**
	 * Get all ads from one ad set.
	 *
	 * @param adSetId the of of the ad set.
	 * @return the list of the {@link Ad} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<Ad> getAdSetAds(String adSetId);

	/**
	 * Get details about an ad.
	 *
	 * @param adId the id of an ad.
	 * @return the {@link Ad} object.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	Ad getAd(String adId);

	/**
	 * Get the insights from ad object.
	 *
	 * @param adId the id of an ad.
	 * @return the {@link AdInsight} object.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdInsight getAdInsight(String adId);

	/**
	 * Synchronously creates one ad.
	 *
	 * @param accountId the ID of the ad account
	 * @param ad        the {@link Ad} object.
	 * @return the id of created ad.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	String createAd(String accountId, Ad ad);

	/**
	 * Updates certain fields in an ad.
	 *
	 * @param adId the if of an ad.
	 * @param ad   the {@link Ad} object.
	 * @return true if successful.
	 */
	boolean updateAd(String adId, Ad ad);

	/**
	 * Deletes an ad object.
	 *
	 * @param adId the if of an ad.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void deleteAd(String adId);
}
