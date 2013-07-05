/*
 * Copyright 2013 the original author or authors.
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
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s for the authenticated user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getFeed(PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> getFeed(int offset, int limit);

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
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s for the specified user. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getFeed(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> getFeed(String ownerId, int offset, int limit);

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
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s from the authenticated user's home feed.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getHomeFeed(PagingParameters)} instead
	 */
	@Deprecated
	PagedList<Post> getHomeFeed(int offset, int limit);

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
	PagedList<StatusPost> getStatuses();

	/**
	 * Retrieves the status entries from the authenticated user's feed.
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getStatuses(PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<StatusPost> getStatuses(int offset, int limit);

	/**
	 * Retrieves the status entries from the authenticated user's feed.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<StatusPost> getStatuses(PagingParameters pagedListParameters);


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
	PagedList<StatusPost> getStatuses(String userId);

	/**
	 * Retrieves the status entries from the specified user's feed.
	 * Requires "read_stream" permission. 
	 * @param userId the user's ID
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of status {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getStatuses(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<StatusPost> getStatuses(String userId, int offset, int limit);

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
	PagedList<StatusPost> getStatuses(String userId, PagingParameters pagedListParameters);
	
	/**
	 * Retrieves the link entries from the authenticated user's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<LinkPost> getLinks();

	/**
	 * Retrieves the link entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated User {@link #getLinks(PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<LinkPost> getLinks(int offset, int limit);

	/**
	 * Retrieves the link entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<LinkPost> getLinks(PagingParameters pagedListParameters);	
	
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
	PagedList<LinkPost> getLinks(String ownerId);

	/**
	 * Retrieves the link entries from the specified owner's feed.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of link {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getLinks(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<LinkPost> getLinks(String ownerId, int offset, int limit);

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
	PagedList<LinkPost> getLinks(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Retrieves the note entries from the authenticated user's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<NotePost> getNotes();

	/**
	 * Retrieves the note entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getNotes(PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<NotePost> getNotes(int offset, int limit);

	/**
	 * Retrieves the note entries from the authenticated user's feed.
	 * Requires "read_stream" permission. 
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<NotePost> getNotes(PagingParameters pagedListParameters);
	
	/**
	 * Retrieves the note entries from the specified owner's feed.
	 * Returns up to the most recent 25 posts.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<NotePost> getNotes(String ownerId);

	/**
	 * Retrieves the note entries from the specified owner's feed.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getNotes(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<NotePost> getNotes(String ownerId, int offset, int limit);

	/**
	 * Retrieves the note entries from the specified owner's feed.
	 * Requires "read_stream" permission. 
	 * @param ownerId the owner of the feed (could be a user, page, event, etc)
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of note {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<NotePost> getNotes(String ownerId, PagingParameters pagedListParameters);

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
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getPosts(PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> getPosts(int offset, int limit);

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
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of post {@link Post}s. 
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #getPosts(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> getPosts(String ownerId, int offset, int limit);

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
	 * Posts a status update to the authenticated user's feed.
	 * Requires "publish_stream" permission.
	 * @param message the message to post.
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the status message duplicates a previously posted status.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String updateStatus(String message);

	/**
	 * Posts a link to the authenticated user's feed.
	 * Requires "publish_stream" permission.
	 * @param message a message to send with the link.
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postLink(String message, FacebookLink link);

	/**
	 * Posts a message to a feed.
	 * Requires "publish_stream" permission.
	 * @param ownerId the feed owner ID. Could be a user ID or a page ID.
	 * @param message the message to post.
	 * @return the id of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws RateLimitExceededException if the per-user/per-app rate limit is exceeded.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
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
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String post(PostData post);
	
	/**
	 * Posts a link to a feed.
	 * Requires "publish_stream" permission.
	 * @param ownerId the feed owner ID. Could be a user ID or a page ID.
	 * @param message a message to send with the link.
	 * @return the ID of the new feed entry.
	 * @throws DuplicateStatusException if the post duplicates a previous post.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postLink(String ownerId, String message, FacebookLink link);

	/**
	 * Deletes a post.
	 * Requires "publish_stream" permission and the post must have been created by the same application.
	 * @param id the feed entry ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void deletePost(String id);

	/**
	 * Searches Facebook's public feed.
	 * Returns up to 25 posts that match the query.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Post> searchPublicFeed(String query);

	/**
	 * Searches Facebook's public feed.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @deprecated Use {@link #searchPublicFeed(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> searchPublicFeed(String query, int offset, int limit);

	/**
	 * Searches Facebook's public feed.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Post> searchPublicFeed(String query, PagingParameters pagedListParameters);

	/**
	 * Searches the authenticated user's home feed.
	 * Returns up to 25 posts that match the query.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchHomeFeed(String query);

	/**
	 * Searches the authenticated user's home feed.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #searchHomeFeed(String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> searchHomeFeed(String query, int offset, int limit);

	/**
	 * Searches the authenticated user's home feed.
	 * @param query the search query (e.g., "Dr Seuss")
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchHomeFeed(String query, PagingParameters pagedListParameters);

	/**
	 * Searches the authenticated user's feed.
	 * Returns up to 25 posts that match the query.
	 * @param query the search query (e.g., "football")
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchUserFeed(String query);

	/**
	 * Searches the authenticated user's feed.
	 * @param query the search query (e.g., "football")
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #searchUserFeed(String, PagingParameters)} instead
	 */
	@Deprecated
	PagedList<Post> searchUserFeed(String query, int offset, int limit);

	/**
	 * Searches the authenticated user's feed.
	 * @param query the search query (e.g., "football")
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchUserFeed(String query, PagingParameters pagedListParameters);

	/**
	 * Searches a specified user's feed.
	 * Returns up to 25 posts that match the query.
	 * @param userId the ID of the user whose feed is to be searched
	 * @param query the search query (e.g., "football")
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchUserFeed(String userId, String query);

	/**
	 * Searches a specified user's feed.
	 * @param userId the ID of the user whose feed is to be searched
	 * @param query the search query (e.g., "football")
	 * @param offset the offset into the feed to start retrieving posts.
	 * @param limit the maximum number of posts to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 * @deprecated Use {@link #searchUserFeed(String, String, PagingParameters)} instead.
	 */
	@Deprecated
	PagedList<Post> searchUserFeed(String userId, String query, int offset, int limit);

	/**
	 * Searches a specified user's feed.
	 * @param userId the ID of the user whose feed is to be searched
	 * @param query the search query (e.g., "football")
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Post}s that match the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Post> searchUserFeed(String userId, String query, PagingParameters pagedListParameters);

}
