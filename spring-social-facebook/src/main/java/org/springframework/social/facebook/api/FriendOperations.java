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
 * Defines operations for interacting with a user's friends and friend lists.
 * @author Craig Walls
 */
public interface FriendOperations {

	/**
	 * Retrieves a list of custom friend lists belonging to the authenticated user.
	 * Requires "read_friendlists" permission.
	 * @return a list {@link Reference}s, each representing a friends list for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getFriendLists();

	/**
	 * Retrieves a list of custom friend lists belonging to the specified user.
	 * Requires "read_friendlists" permission.
	 * @param userId the user's ID
	 * @return a list {@link Reference}s, each representing a friends list for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "read_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getFriendLists(String userId);

	/**
	 * Retrieves a reference to the specified friend list.
	 * @param friendListId the friend list ID.
	 * @return a {@link Reference} to the requested friend list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	Reference getFriendList(String friendListId);
	
	/**
	 * Retrieves references for all users who are members of the specified friend list.
	 * @param friendListId the friend list ID.
	 * @return a list of {@link Reference}, each representing a member of the friend list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getFriendListMembers(String friendListId);

	/**
	 * Creates a new friend list for the authenticated user.
	 * Requires "manage_friendlists" permission.
	 * @param name the name of the friend list.
	 * @return the ID of the newly created friend list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "manage_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String createFriendList(String name);

	/**
	 * Creates a new friend list.
	 * Requires "manage_friendlists" permission.
	 * @param userId the user ID to create the friend list for.
	 * @param name the name of the friend list.
	 * @return the ID of the newly created friend list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "manage_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String createFriendList(String userId, String name);
	
	/**
	 * Deletes a friend list.
	 * Requires "manage_friendlists" permission.
	 * @param friendListId the ID of the friend list to remove.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "manage_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void deleteFriendList(String friendListId);

	/**
	 * Adds a friend to a friend list.
	 * Requires "manage_friendlists" permission.
	 * @param friendListId the friend list ID
	 * @param friendId The ID of the user to add to the list. The user must be a friend of the list's owner.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "manage_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void addToFriendList(String friendListId, String friendId);
	
	/**
	 * Removes a friend from a friend list.
	 * Requires "manage_friendlists" permission.
	 * @param friendListId the friend list ID
	 * @param friendId The ID of the user to add to the list.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "manage_friendlists" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void removeFromFriendList(String friendListId, String friendId);
	
	/**
	 * Retrieves a list of user references for the authenticated user's friends.
	 * @return a list {@link Reference}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getFriends();
	
	/**
	 * Retrieves a list of the authenticating user's friends' IDs.
	 * @return a list of Strings, where each entry is the ID of one of the user's friends.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<String> getFriendIds();
	
	/**
	 * Retrieves profile data for up to 100 of the authenticated user's friends.
	 * For additional friend profiles, you must specify the offset and limit.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @return a list {@link FacebookProfile}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FacebookProfile> getFriendProfiles();

	/**
	 * Retrieves profile data for the authenticated user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param offset the offset into the friends list to start retrieving profiles.
	 * @param limit the maximum number of profiles to return.
	 * @return a list {@link FacebookProfile}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FacebookProfile> getFriendProfiles(int offset, int limit);

	/**
	 * Retrieves a list of user references for the specified user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list {@link Reference}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getFriends(String userId);

	/**
	 * Retrieves a list of the authenticating user's friends' IDs.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list of Strings, where each entry is the ID of one of the user's friends.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<String> getFriendIds(String userId);
	
	/**
	 * Retrieves profile data for up to 100 of the specified user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @return a list {@link FacebookProfile}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FacebookProfile> getFriendProfiles(String userId);

	/**
	 * Retrieves profile data for the specified user's friends.
	 * The list of profiles is ordered by each user's Facebook ID.
	 * @param userId the user's ID
	 * @param offset the offset into the friends list to start retrieving profiles.
	 * @param limit the maximum number of profiles to return.
	 * @return a list {@link FacebookProfile}s, each representing a friend of the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FacebookProfile> getFriendProfiles(String userId, int offset, int limit);
	
	/**
	 * Retrieves a list of FamilyMember references for the authenticated user.
	 * @return a list of {@link FamilyMember}s, each representing a Facebook user that the user is related to.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FamilyMember> getFamily();
	
	/**
	 * Retrieves a list of FamilyMember references for the specified user.
	 * @param userId the ID of the user to retrieve family members for.
	 * @return a list of {@link FamilyMember}s, each representing a Facebook user that the user is related to.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<FamilyMember> getFamily(String userId);
	
	/**
	 * Retrieves a list of user references that the authenticated user and the specified user have in common as friends.
	 * @param userId the ID of the user to check for common friendships with.
	 * @return a list of {@link Reference}s, each representing a friend that the two users have in common.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Reference> getMutualFriends(String userId);

}
