/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
	FacebookProfile getUserProfile();
	
	/**
	 * Retrieves the profile for the specified user.
	 * @param userId the Facebook user ID to retrieve profile data for.
	 * @return the user's profile information.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	FacebookProfile getUserProfile(String userId);

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
	 * Retrieves a list of permissions that the application has been granted for the authenticated user.
	 * @return the permissions granted for the user.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<String> getUserPermissions();
	
	/**
	 * Searches for users.
	 * @param query the search query (e.g., "Michael Scott")
	 * @return a list of {@link Reference}s, each representing a user who matched the given query.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> search(String query);
	
	/**
	 * Posts a short free-form message to the user's notification list 
	 * NOTE: This will only work with an APP access token (not a user access token)
	 * SEE: https://developers.facebook.com/docs/app_notifications/
	 * 
	 * @param userId the Facebook user ID
	 * @param href The relative path/GET params of the target (for example, "index.html?gift_id=123", 
	 * or "?gift_id=123"). Then we will construct proper target URL based on your app settings. The 
	 * logic is that, on web, if Canvas setting exists, we always show “Canvas URL + href”. If not, 
	 * we show nothing. In the future (not in this version), we will also use existing URL 
	 * re-writing logic to support mobile canvas and native mobile apps. We also append some special 
	 * tracking params (fb_source, notif_id, notif_t) to the target URL for developers to track at
	 *  their side. One example of target URL displayed in the jewel is: 
	 *  https://apps.facebook.com/bzhang_og/?fb_source=notification&notif_id=notif_514699839_145756436&ref=notif&notif_t=app_notification
	 * @param template The customized text of the notification.
	 * @return
	 */
	boolean postNotifcation(String userId, String href, String template);
}
