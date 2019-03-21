/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

import java.util.List;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;



public interface UserOperations {
	
	/**
	 * Retrieves the profile for the authenticated user.
	 * @return the user's profile information.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	User getUserProfile();
	
	/**
	 * Retrieves the profile for the specified user.
	 * @param userId the Facebook user ID to retrieve profile data for.
	 * @return the user's profile information.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	User getUserProfile(String userId);

	/**
	 * Retrieves the user's profile image. Returns the image in Facebook's "normal" type.
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	byte[] getUserProfileImage();

	/**
	 * Retrieves the user's profile image. Returns the image in Facebook's "normal" type.
	 * @param userId the Facebook user ID.
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getUserProfileImage(String userId);

	/**
	 * Retrieves the user's profile image.
	 * @param imageType the image type (eg., small, normal, large. square)
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	byte[] getUserProfileImage(ImageType imageType);

	/**
	 * Retrieves the user's profile image.
	 * @param userId the Facebook user ID.
	 * @param imageType the image type (eg., small, normal, large. square)
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getUserProfileImage(String userId, ImageType imageType);

	/**
	 * Retrieves the user's profile image. When height and width are both used,
	 * the image will be scaled as close to the dimensions as possible and then
	 * cropped down.
	 * @param width the desired image width
	 * @param height the desired image height
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getUserProfileImage(Integer width, Integer height);

	/**
	 * Retrieves the user's profile image. When height and width are both used,
	 * the image will be scaled as close to the dimensions as possible and then
	 * cropped down.
	 * @param userId the Facebook user ID.
	 * @param width the desired image width
	 * @param height the desired image height
	 * @return an array of bytes containing the user's profile image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getUserProfileImage(String userId, Integer width, Integer height);

	/**
	 * Retrieves a list of permissions that the application has been granted for the authenticated user.
	 * @return the permissions granted for the user.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Permission> getUserPermissions();
	
	/**
	 * Fetches IDs that the user has on any applications associated with the calling application via Facebook's Business Mapping API.
	 * @return a list of ID-to-application mapping that the user has on related applications.
	 */
	List<UserIdForApp> getIdsForBusiness();
	
	/**
	 * Fetches a list of places that the user has checked into or has been tagged at.
	 * @return a list of place tags for the user.
	 */
	List<PlaceTag> getTaggedPlaces();
	
	/**
	 * Searches for users.
	 * @param query the search query (e.g., "Michael Scott")
	 * @return a list of {@link Reference}s, each representing a user who matched the given query.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Reference> search(String query);
	
	static final String[] PROFILE_FIELDS = {
		"id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", 
		"favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type",
		"is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", 
		"political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", 
		"sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", 
		"website", "work"
	};

}
