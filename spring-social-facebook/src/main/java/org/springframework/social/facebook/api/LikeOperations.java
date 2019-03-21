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
	 * Limited to 25 references. 
	 * Pass the {@link PagingParameters} returned from {@link PagedList#getNextPage()} into {@link #getLikes(String, PagingParameters)} to get the next page.
	 * @param objectId the object ID (an Album, Checkin, Comment, Note, Photo, Post, or Video).
	 * @return a list of {@link Reference} objects for the users who have liked the object.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Reference> getLikes(String objectId);

	/**
	 * Retrieves a page of references to users who have liked the specified object.
	 * @param objectId the object ID (an Album, Checkin, Comment, Note, Photo, Post, or Video).
	 * @param pagingParameters the paging parameters for fetching a specific page of references.
	 * @return a list of {@link Reference} objects for the users who have liked the object.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Reference> getLikes(String objectId, PagingParameters pagingParameters);

	/**
	 * Retrieves a list of pages that the authenticated user has liked.
	 * Requires "user_likes" permission. Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getPagesLiked();

	/**
	 * Retrieves a list of pages that the authenticated user has liked.
	 * Requires "user_likes" permission. Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getPagesLiked(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of pages that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getPagesLiked(String userId);

	/**
	 * Retrieves a list of pages that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getPagesLiked(String userId, PagingParameters pagingParameters);

	/**
	 * Like an object on behalf of the authenticated user.
	 * The type of object to be liked is limited to Album, Checkin, Comment, Note, Photo, Post, or Video.
	 * You cannot like a Facebook Page through this API.
	 * Requires "publish_actions" permission and permission to access the object being liked.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission or if the user does not have permission to access the object.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void like(String objectId);

	/**
	 * Unlike an object on behalf of the authenticated user.
	 * The type of object to be liked is limited to Album, Checkin, Comment, Note, Photo, Post, or Video.
	 * You cannot unlike a Facebook Page through this API.
	 * Requires "publish_actions" permission and permission to access the object being liked.
	 * @param objectId the object ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission or if the user does not have permission to access the object.
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
	PagedList<Page> getBooks();

	/**
	 * Retrieves a list of books that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getBooks(String userId);

	/**
	 * Retrieves a list of books that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getBooks(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of books that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getBooks(String userId, PagingParameters pagingParameters);

	/**
	 * Retrieves a list of movies that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMovies();

	/**
	 * Retrieves a list of movies that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMovies(String userId);

	/**
	 * Retrieves a list of movies that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMovies(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of movies that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMovies(String userId, PagingParameters pagingParameters);

	/**
	 * Retrieves a list of music that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMusic();

	/**
	 * Retrieves a list of music that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMusic(String userId);

	/**
	 * Retrieves a list of music that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMusic(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of music that the given user has liked. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getMusic(String userId, PagingParameters pagingParameters);

	/**
	 * Retrieves a list of television shows that the authenticated user likes.
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getTelevision();

	/**
	 * Retrieves a list of television shows that the given user has liked.
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getTelevision(String userId);

	/**
	 * Retrieves a list of television shows that the authenticated user likes.
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getTelevision(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of television shows that the given user has liked.
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getTelevision(String userId, PagingParameters pagingParameters);

	/**
	 * Retrieves a list of games that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getGames();

	/**
	 * Retrieves a list of games that the given user likes. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getGames(String userId);

	/**
	 * Retrieves a list of games that the authenticated user likes. 
	 * Requires "user_likes" permission. 
	 * Returns an empty list if permission isn't granted.
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getGames(PagingParameters pagingParameters);

	/**
	 * Retrieves a list of games that the given user likes. 
	 * Requires "user_likes" permission 
	 * Returns an empty list if permission isn't granted.
	 * @param userId the ID of the user
	 * @param pagingParameters the paging parameters for fetching a given range of pages.
	 * @return a list of {@link Page} objects
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_likes" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Page> getGames(String userId, PagingParameters pagingParameters);

}
