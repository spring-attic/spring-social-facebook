package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.InvalidCampaignStatusException;

/**
 * Defines operations for working with Facebook Ad Campaign object.
 *
 * @author Sebastian Górecki
 */
public interface CampaignOperations {

	static final String[] AD_CAMPAIGN_FIELDS = {
			"id", "account_id", "buying_type", "campaign_group_status", "name", "objective", "spend_cap"
	};

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
	 * Creates new campaign with given name and status.
	 *
	 * @param adAccountId the ID of the ad account, the string act_{ad_account_id}
	 * @param name        the name of new campaign
	 * @param status      the status of the new campaign (ACTIVE or PAUSED)
	 * @return the id of the ad campaign created
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InvalidCampaignStatusException  if you provided wrong status for the new campaign
	 */
	String createAdCampaign(String adAccountId, String name, CampaignStatus status);

	/**
	 * Creates new campaign with given name, status and objective.
	 *
	 * @param adAccountId the ID of the ad account, the string act_{ad_account_id}
	 * @param name        the name of the new campaign
	 * @param status      the status of the new campaign (ACTIVE or PAUSED)
	 * @param objective   the objective of this ad campaign
	 * @param spendCap    the spend cap for the campaign defined as value of cents in your currency, set to null to allow unlimited spend. A minimum value is $100 USD (or approximate local equivalent).
	 * @return the id of the ad campaign created
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InvalidCampaignStatusException  if you provided wrong status for the new campaign
	 */
	String createAdCampaign(String adAccountId, String name, CampaignStatus status, CampaignObjective objective, String spendCap);

	/**
	 * Creates new campaign with given name, status, objective and spend cap.
	 *
	 * @param adAccountId  the ID of the ad account, the string act_{ad_account_id}
	 * @param name       the name of the new campaign
	 * @param status     the status of the new campaign (ACTIVE or PAUSED)
	 * @param objective  the objective of this ad campaign
	 * @param spendCap   the spend cap for the campaign defined as value of cents in your currency, set to null to allow unlimited spend. A minimum value is $100 USD (or approximate local equivalent).
	 * @param buyingType buying type - this field will help Facebook make future optimizations to delivery, pricing, and limits
	 * @return the id of the ad campaign created
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 * @throws InvalidCampaignStatusException  if you provided wrong status for the new campaign
	 */
	String createAdCampaign(String adAccountId, String name, CampaignStatus status, CampaignObjective objective, String spendCap, BuyingType buyingType);

	/**
	 * Updates the name of the ad campaign.
	 *
	 * @param campaignId the id of ad campaign
	 * @param name       new name of the campaign
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void updateAdCampaignName(String campaignId, String name);

	/**
	 * Updates the status of the ad campaign.
	 *
	 * @param campaignId the id of ad campaign
	 * @param status     new status of the campaign
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void updateAdCampaignStatus(String campaignId, CampaignStatus status);

	/**
	 * Updates the objective of the ad campaign.
	 *
	 * @param campaignId the id of ad campaign
	 * @param objective  new objective of the campaign
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void updateAdCampaignObjective(String campaignId, CampaignObjective objective);

	/**
	 * Updates spend cap of the ad campaign.
	 *
	 * @param campaignId the id of ad campaign
	 * @param spendCap   new spend cap
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void updateAdCampaignSpendCap(String campaignId, int spendCap);

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
