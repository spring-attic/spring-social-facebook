package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.InvalidCampaignStatusException;
import org.springframework.social.facebook.api.PagedList;

/**
 * Defines operations for working with Facebook Ad Campaign object.
 *
 * @author Sebastian Górecki
 */
public interface CampaignOperations {

	static final String[] AD_CAMPAIGN_FIELDS = {
			"id", "account_id", "buying_type", "campaign_group_status", "name", "objective", "spend_cap"
	};

	static final String[] AD_CAMPAIGN_INSIGHT_FIELDS = {
			"account_id", "account_name", "date_start", "date_stop", "actions_per_impression", "clicks", "unique_clicks",
			"cost_per_result", "cost_per_total_action", "cpc", "cost_per_unique_click", "cpm", "cpp", "ctr", "unique_ctr",
			"frequency", "impressions", "unique_impressions", "objective", "reach", "result_rate", "results", "roas",
			"social_clicks", "unique_social_clicks", "social_impressions", "unique_social_impressions", "social_reach",
			"spend", "today_spend", "total_action_value", "total_actions", "total_unique_actions", "actions",
			"unique_actions", "cost_per_action_type", "video_start_actions"
	};

	/**
	 * Gets all ad campaigns belonging to user.
	 *
	 * @param accountId the ID of the ad account (account_id)
	 * @return the list of {@link AdCampaign} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdCampaign> getAdCampaigns(String accountId);

	/**
	 * Get the campaign by given id.
	 *
	 * @param id the id of the campaign
	 * @return the {@link AdCampaign} object.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdCampaign getAdCampaign(String id);

	/**
	 * Get all ad sets from one ad campaign.
	 *
	 * @param campaignId the id of the campaign
	 * @return the list of {@link AdSet} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdSet> getAdCampaignSets(String campaignId);

	/**
	 * Get the insight of the ad campaign.
	 *
	 * @param campaignId the id of the campaign
	 * @return the {@link AdInsight} object
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdInsight getAdCampaignInsight(String campaignId);

	/**
	 * Creates new campaign based on adCampaign object.
	 *
	 * @param accountId  the ID of the ad account (account_id)
	 * @param adCampaign the ad campaign object
	 * @return the id of the created ad campaign
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InvalidCampaignStatusException  if you provided wrong status for the new campaign
	 */
	String createAdCampaign(String accountId, AdCampaign adCampaign);

	/**
	 * Updates the ad campaign with information in adCampaign object.
	 *
	 * @param campaignId the ID of the ad campaign to update
	 * @param adCampaign the ad campaign object
	 * @return true if update was successful
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	boolean updateAdCampaign(String campaignId, AdCampaign adCampaign);

	/**
	 * Deletes the ad campaign given by id.
	 *
	 * @param campaignId the id of the campaign to delete
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void deleteAdCampaign(String campaignId);
}
