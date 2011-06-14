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
 * Defines operations for working with a user's likes and interests.
 * @author Craig Walls
 */
public interface LikeOperations {

	/**
	 * Retrieves a list of things that the authenticated user has liked.
	 * Requires "user_likes" permission. Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getLikes();

	/**
	 * Retrieves a list of things that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" or "friends_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getLikes(String userId);

	/**
	 * Like an object on behalf of the authenticated user.
	 * Requires "publish_stream" permission.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	void like(String objectId);

	/**
	 * Unlike an object on behalf of the authenticated user.
	 * Requires "publish_stream" permission.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	void unlike(String objectId);

	/**
	 * Retrieves a list of books that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getBooks();

	/**
	 * Retrieves a list of books that the given user has liked. Requires
	 * "user_likes" permission for the authenticated user and "friends_likes"
	 * for the authenticated user's friends. Returns an empty list if permission
	 * isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getBooks(String userId);

	/**
	 * Retrieves a list of movies that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getMovies();

	/**
	 * Retrieves a list of movies that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getMovies(String userId);

	/**
	 * Retrieves a list of music that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getMusic();

	/**
	 * Retrieves a list of music that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getMusic(String userId);

	/**
	 * Retrieves a list of television shows that the authenticated user likes.
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getTelevision();

	/**
	 * Retrieves a list of television shows that the given user has liked.
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" or "friends_likes" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getTelevision(String userId);

	/**
	 * Retrieves a list of activities that the authenticated user likes.
	 * Requires "user_activities" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_activities" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getActivities();

	/**
	 * Retrieves a list of activities that the given user likes. 
	 * Requires "user_activities" permission for the authenticated user and "friends_activities" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_activities" or "friends_activities" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getActivities(String userId);

	/**
	 * Retrieves a list of interests that the authenticated user likes. 
	 * Requires "user_interests" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_interests" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getInterests();

	/**
	 * Retrieves a list of interests that the given user likes. 
	 * Requires "user_interests" permission for the authenticated user and "friends_interests" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link UserLike} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_interests" or "friends_interests" permission.
	 * @throws MissingCredentialsException if FacebookTemplate was not created with an access token.
	 */
	List<UserLike> getInterests(String userId);

}
