package org.springframework.social.facebook.api.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.PagedList;

/**
 * Defines operations for working with Facebook Creative object.
 *
 * @author Sebastian Górecki
 */
public interface CreativeOperations {

	static final String[] AD_CREATIVE_FIELDS = {
			"actor_id", "body", "image_hash", "image_url", "link_url", "name", "object_id", "object_story_id",
			"object_url", "run_status", "title", "url_tags", "thumbnail_url", "object_type", "id"
	};

	/**
	 * Retrieve an account's ad creatives.
	 *
	 * @param accountId the ID of the ad account.
	 * @return the list of {@link AdCreative} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdCreative> getAccountCreatives(String accountId);

	/**
	 * Retrieve an ad set's ad creatives.
	 *
	 * @param adSetId the id of the ad set.
	 * @return the list of {@link AdCreative} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdCreative> getAdSetCreatives(String adSetId);

	/**
	 * Get information about an ad creative.
	 *
	 * @param creativeId the id of the ad creative
	 * @return the {@link AdCreative} object.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdCreative getAdCreative(String creativeId);

	/**
	 * Create an ad creative in the given account.
	 *
	 * @param accountId the ID of the ad account.
	 * @param creative  the {@link AdInsight} object.
	 * @return the id of the ad creative
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	String createAdCreative(String accountId, AdCreative creative);

	/**
	 * Rename an ad creative in creative library.
	 *
	 * @param creativeId the id of the ad creative.
	 * @param name       new name of the ad creative
	 * @return true if rename was successful
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	boolean renameAdCreative(String creativeId, String name);

	/**
	 * Delete given ad creative.
	 *
	 * @param creativeId the id of the ad creative
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void deleteAdCreative(String creativeId);
}
