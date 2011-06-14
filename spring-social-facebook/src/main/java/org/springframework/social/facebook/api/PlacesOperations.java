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
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingCredentialsException;


/**
 * Defines the operations for interacting with a user's Facebook checkins.
 * @author Craig Walls
 */
public interface PlacesOperations {
	
	/**
	 * Retrieves a list of checkins for the authenticated user.
	 * Requires "user_checkins" or "friends_checkins" permission.
	 * @return a list {@link Checkin}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_checkins" or "friends_checkins" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<Checkin> getCheckins();

	/**
	 * Retrieves a list of checkins for the specified object.
	 * If the object is a user, this returns checkins for places the user has checked into.
	 * If the object is a page, then this returns checkins that the user's friends has made to the location that the page represents.
	 * Requires "user_checkins" or "friends_checkins" permission.
	 * @param objectId either a Facebook user ID or page ID
	 * @return a list {@link Checkin}s, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_checkins" or "friends_checkins" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<Checkin> getCheckins(String objectId);

	/**
	 * Retrieves details for a single checkin.
	 * @param checkinId the checkin ID
	 * @return a {@link Checkin}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	Checkin getCheckin(String checkinId);
	
	/**
	 * Checks the authenticated user into the specified location.
	 * Requires "publish_checkins" permission.
	 * @param placeId the ID of the place to check into.
	 * @param latitude the latitude of the place.
	 * @param longitude the longitude of the place.
	 * @return the ID of the checkin.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_checkins" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	String checkin(String placeId, double latitude, double longitude);

	/**
	 * Checks the authenticated user into the specified location.
	 * Requires "publish_checkins" permission.
	 * @param placeId the ID of the place to check into.
	 * @param latitude the latitude of the place.
	 * @param longitude the longitude of the place.
	 * @param message a message to post along with the checkin.
	 * @param tags a varargs list of user IDs to tag on the checkin.
	 * @return the ID of the checkin.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_checkins" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	String checkin(String placeId, double latitude, double longitude, String message, String... tags);
	
	/**
	 * Searches for places near a given coordinate.
	 * @param query the search query (e.g., "Burritos")
	 * @param latitude the latitude of the point to search near
	 * @param longitude the longitude of the point to search near
	 * @param distance the radius to search within (in feet)
	 * @return a list of {@link Page}s matching the search
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<Page> search(String query, double latitude, double longitude, long distance);
}
