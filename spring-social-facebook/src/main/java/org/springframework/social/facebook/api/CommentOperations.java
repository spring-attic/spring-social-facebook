/*
 * Copyright 2014 the original author or authors.
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
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;


/**
 * Defines operations for reading and posting comments to Facebook.
 * @author Craig Walls
 */
public interface CommentOperations {
	
	/**
	 * Retrieves the first 25 comments for a given object.
	 * @param objectId the ID of the object
	 * @return a list of {@link Comment}s for the specified object
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Comment> getComments(String objectId);

	/**
	 * Retrieves comments for a given object.
	 * @param objectId the ID of the object
	 * @param offset the offset into the list of comments to start retrieving comments
	 * @param limit the maximum number of comments to retrieve
	 * @return a list of {@link Comment}s for the specified object
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @deprecated Use {@link CommentOperations#getComments(String, PagingParameters)} instead
	 */
	@Deprecated
	PagedList<Comment> getComments(String objectId, int offset, int limit);

	/**
	 * Retrieves comments for a given object.
	 * @param objectId the ID of the object
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Comment}s for the specified object
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Comment> getComments(String objectId, PagingParameters pagedListParameters);

	/**
	 * Retrieves a single comment
	 * @param commentId the comment ID
	 * @return the requested {@link Comment}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	Comment getComment(String commentId);
	
	/**
	 * Posts a comment on an object on behalf of the authenticated user.
	 * Requires "publish_stream" permission.
	 * @param objectId the object ID
	 * @param message the comment message
	 * @return the new comment's ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String addComment(String objectId, String message);

	/**
	 * Deletes a comment.
	 * Requires "publish_stream" permission.
	 * @param commentId the comment ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void deleteComment(String commentId);

	/**
	 * Retrieve a list of references to users who have liked a given object.
	 * @param objectId
	 * @return a list of {@link Reference}s
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Reference> getLikes(String objectId);

}
