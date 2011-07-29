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
import org.springframework.social.MissingAuthorizationException;


/**
 * Defines operations for working with a user's likes and interests.
 * @author Craig Walls
 */
public interface LikeOperations {

	/**
	 * Retrieves a list of references to users who have liked the specified object.
	 * @param objectId the object ID (an Album, Checkin, Comment, Note, Photo, Post, or Video).
	 * @return a list of {@link Reference} objects for the users who have liked the object.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getLikes(String objectId);
	
	/**
	 * Retrieves a list of pages that the authenticated user has liked.
	 * Requires "user_likes" permission. Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getPagesLiked();

	/**
	 * Retrieves a list of pages that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" or "friends_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getPagesLiked(String userId);

	/**
	 * Like an object on behalf of the authenticated user.
	 * The type of object to be liked is limited to Album, Checkin, Comment, Note, Photo, Post, or Video.
	 * You cannot like a Facebook Page through this API.
	 * Requires "publish_stream" permission and permission to access the object being liked.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission or if the user does not have permission to access the object.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void like(String objectId);

	/**
	 * Unlike an object on behalf of the authenticated user.
	 * The type of object to be liked is limited to Album, Checkin, Comment, Note, Photo, Post, or Video.
	 * You cannot unlike a Facebook Page through this API.
	 * Requires "publish_stream" permission and permission to access the object being liked.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission or if the user does not have permission to access the object.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void unlike(String objectId);

	/**
	 * Retrieves a list of books that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getBooks();

	/**
	 * Retrieves a list of books that the given user has liked. Requires
	 * "user_likes" permission for the authenticated user and "friends_likes"
	 * for the authenticated user's friends. Returns an empty list if permission
	 * isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getBooks(String userId);

	/**
	 * Retrieves a list of movies that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getMovies();

	/**
	 * Retrieves a list of movies that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getMovies(String userId);

	/**
	 * Retrieves a list of music that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getMusic();

	/**
	 * Retrieves a list of music that the given user has liked. 
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getMusic(String userId);

	/**
	 * Retrieves a list of television shows that the authenticated user likes.
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getTelevision();

	/**
	 * Retrieves a list of television shows that the given user has liked.
	 * Requires "user_likes" permission for the authenticated user and "friends_likes" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" or "friends_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getTelevision(String userId);

	/**
	 * Retrieves a list of activities that the authenticated user likes.
	 * Requires "user_activities" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_activities" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getActivities();

	/**
	 * Retrieves a list of activities that the given user likes. 
	 * Requires "user_activities" permission for the authenticated user and "friends_activities" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_activities" or "friends_activities" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getActivities(String userId);

	/**
	 * Retrieves a list of interests that the authenticated user likes. 
	 * Requires "user_interests" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_interests" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getInterests();

	/**
	 * Retrieves a list of interests that the given user likes. 
	 * Requires "user_interests" permission for the authenticated user and "friends_interests" for the authenticated user's friends. 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_interests" or "friends_interests" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Page> getInterests(String userId);

}
