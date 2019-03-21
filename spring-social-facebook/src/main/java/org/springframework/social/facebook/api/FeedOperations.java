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
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.RateLimitExceededException;

/**
 * Interface defining operations that can be performed on a Facebook feed.
 * @author Craig Walls
 */
public interface FeedOperations {

	/**
	 * Retrieves recent posts for the authenticated user.
	 * Requires "read_stream" permission to read non-public posts. 
	 * Returns up to the most recent 25 posts.
	 * @return a list of {@link Post}s for the authenticated user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getFeed();

	/**
	 * Retrieves recent posts for the authenticated user.
	 * Requires "read_stream" permission to read non-public posts. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s for the authenticated user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getFeed(PagingParameters pagedListParameters);
	
	/**
	 * Retrieves recent feed entries for a given user. 
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission to read non-public posts. 
	 * @param ownerId the Facebook ID or alias for the owner (user, group, event, page, etc) of the feed.
	 * @return a list of {@link Post}s for the specified user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getFeed(String ownerId);

	/**
	 * Retrieves recent feed entries for a given user. 
	 * Requires "read_stream" permission to read non-public posts.
	 * Returns up to the most recent 25 posts.
	 * @param ownerId the Facebook ID or alias for the owner (user, group, event, page, etc) of the feed.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s for the specified user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getFeed(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Retrieves the user's home feed. This includes entries from the user's friends.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of {@link Post}s from the authenticated user's home feed.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getHomeFeed();

	/**
	 * Retrieves the user's home feed. This includes entries from the user's friends.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s from the authenticated user's home feed.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getHomeFeed(PagingParameters pagedListParameters);

	/**
	 * Retrieves a single post.
	 * @param entryId the entry ID
	 * @return the requested {@link Post}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	Post getPost(String entryId);
	
	/**
	 * Retrieves the status entries from the authenticated user's feed.
	 * Returns up to the most recent 25 posts.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getStatuses();

	/**
	 * Retrieves the status entries from the authenticated user's feed.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getStatuses(PagingParameters pagedListParameters);


	/**
	 * Retrieves the status entries from the specified user's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @param userId the user's ID
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getStatuses(String userId);

	/**
	 * Retrieves the status entries from the specified user's feed.
	 * Requires "read_stream" permission. 
	 * @param userId the user's ID
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getStatuses(String userId, PagingParameters pagedListParameters);
	
	/**
	 * Retrieves the link entries from the authenticated user's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getLinks();

	/**
	 * Retrieves the link entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getLinks(PagingParameters pagedListParameters);	
	
	/**
	 * Retrieves the link entries from the specified owner's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getLinks(String ownerId);

	/**
	 * Retrieves the link entries from the specified owner's feed.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getLinks(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Retrieves the post entries from the authenticated user's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getPosts();

	/**
	 * Retrieves the post entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getPosts(PagingParameters pagedListParameters);

	/**
	 * Retrieves the post entries from the specified owner's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getPosts(String ownerId);

	/**
	 * Retrieves the post entries from the specified owner's feed.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getPosts(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Retrieves the post entries that the authenticated user was tagged in.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getTagged();

	/**
	 * Retrieves the post entries that the authenticated user was tagged in.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getTagged(PagingParameters pagedListParameters);

	/**
	 * Retrieves the post entries that the specified user was tagged in.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getTagged(String ownerId);

	/**
	 * Retrieves the post entries that the specified user was tagged in.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getTagged(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Posts a status update to the authenticated user's feed.
	 * Requires "publish_actions" permission.
	 * @param message the message to post.
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the status message duplicates a previously posted status.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String updateStatus(String message);

	/**
	 * Posts a link to the authenticated user's feed.
	 * Requires "publish_actions" permission.
	 * @param message a message to send with the link.
	 * @param link the {@link FacebookLink} object to post
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postLink(String message, FacebookLink link);

	/**
	 * Posts a message to a feed.
	 * Requires "publish_actions" permission.
	 * @param ownerId the feed owner ID. Could be a user ID or a page ID.
	 * @param message the message to post.
	 * @return the id of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String post(String ownerId, String message);
	
	/**
	 * Adds a Post to a feed.
	 * @param post the new post
	 * @return the id of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String post(PostData post);
	
	/**
	 * Posts a link to a feed.
	 * Requires "publish_actions" permission.
	 * @param ownerId the feed owner ID. Could be a user ID or a page ID.
	 * @param message a message to send with the link.
	 * @param link the {@link FacebookLink} object to post
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postLink(String ownerId, String message, FacebookLink link);

	/**
	 * Deletes a post.
	 * Requires "publish_actions" permission and the post must have been created by the same application.
	 * @param id the feed entry ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void deletePost(String id);

	/**
	 * Retrieves a list of up to 25 recent checkins for the authenticated user.
	 * Requires "read_stream" or "user_posts" permission.
	 * @return a list {@link Post}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_checkins" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getCheckins();

	/**
	 * Retrieves a list of checkins for the authenticated user.
	 * Requires "read_stream" or "user_posts" permission.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link Post}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_checkins" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> getCheckins(PagingParameters pagedListParameters);

	/**
	 * Retrieves details for a single checkin.
	 * @param checkinId the checkin ID
	 * @return a {@link Post}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	Post getCheckin(String checkinId);
	
}
