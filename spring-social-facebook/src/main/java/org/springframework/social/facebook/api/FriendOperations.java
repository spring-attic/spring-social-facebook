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
 * Defines operations for interacting with a user's friends and friend lists.
 * @author Craig Walls
 */
public interface FriendOperations {

	/**
	 * Retrieves a list of custom friend lists belonging to the authenticated user.
	 * Requires "read_custom_friendlists" permission.
	 * @return a list {@link Reference}s, each representing a friends list for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_custom_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<FriendList> getFriendLists();

	/**
	 * Retrieves a reference to the specified friend list.
	 * @param friendListId the friend list ID.
	 * @return a {@link Reference} to the requested friend list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	FriendList getFriendList(String friendListId);
	
	/**
	 * Retrieves a list of user references for the authenticated user's friends.
	 * @return a list {@link Reference}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Reference> getFriends();
	
	/**
	 * Retrieves a list of the authenticating user's friends' IDs.
	 * @return a list of Strings, where each entry is the ID of one of the user's friends.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<String> getFriendIds();
	
	/**
	 * Retrieves the profile data for all of an authenticated user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @return a list {@link User}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<User> getFriendProfiles();

	/**
	 * Retrieves profile data for the authenticated user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link User}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<User> getFriendProfiles(PagingParameters pagedListParameters);

	/**
	 * Retrieves a list of user references for the specified user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list {@link Reference}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Reference> getFriends(String userId);

	/**
	 * Retrieves a list of the authenticating user's friends' IDs.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list of Strings, where each entry is the ID of one of the user's friends.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<String> getFriendIds(String userId);
	
	/**
	 * Retrieves profile data for all of an authenticated user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list {@link User}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<User> getFriendProfiles(String userId);
	
	/**
	 * Retrieves profile data for the specified user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link User}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<User> getFriendProfiles(String userId, PagingParameters pagedListParameters);
	
	/**
	 * Retrieves a list of FamilyMember references for the authenticated user.
	 * @return a list of {@link FamilyMember}s, each representing a Facebook user that the user is related to.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<FamilyMember> getFamily();
	
	/**
	 * Retrieves a list of FamilyMember references for the specified user.
	 * @param userId the ID of the user to retrieve family members for.
	 * @return a list of {@link FamilyMember}s, each representing a Facebook user that the user is related to.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<FamilyMember> getFamily(String userId);

	/**
	 * Retrieves a list of the authenticated user's friends that can be invited.
	 * @return list of taggable friends
	 */
	PagedList<UserInvitableFriend> getInvitableFriends();

	/**
	 * Retrieves a list of the authenticated user's friends that can be tagged in a post.
	 * @return list of taggable friends
	 */
	PagedList<UserTaggableFriend> getTaggableFriends();
	
}
