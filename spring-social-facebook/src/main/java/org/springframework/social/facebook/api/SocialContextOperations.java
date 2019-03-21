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


/**
 * API Binding for Facebook social context queries.
 * 
 * @author Craig Walls
 */
public interface SocialContextOperations {

	/**
	 * Fetches a counted list of (at most 25) friends that the authenticated user shares in common with the given user.
	 * The list will be limited to 25 (at most) mutual friends that also use the requesting application.
	 * @param userId the ID of the user to query for mutual friends
	 * @return a {@link CountedList} of friends that the authenticated user has in common with the given user.
	 */
	CountedList<Reference> getMutualFriends(String userId);

	/**
	 * Fetches a counted list of friends that the authenticated user shares in common with the given user.
	 * The list will be limited to only mutual friends that also use the requesting application.
	 * @param userId the ID of the user to query for mutual friends
	 * @param limit the maximum number of common friends to return.
	 * @return a {@link CountedList} of friends that the authenticated user has in common with the given user.
	 */
	CountedList<Reference> getMutualFriends(String userId, int limit);

	/**
	 * Fetches a counted list of (at most 25) friends that the authenticated user shares in common with the given user.
	 * This list will include up to 25 (at most) mutual friends, even if they do not use the requesting application.
	 * In order to user this method, however, you must submit your application for review (https://developers.facebook.com/docs/apps/review). 
	 * @param userId the ID of the user to query for mutual friends
	 * @return a {@link CountedList} of friends that the authenticated user has in common with the given user.
	 */
	CountedList<Reference> getAllMutualFriends(String userId);

	/**
	 * Fetches a counted list of friends that the authenticated user shares in common with the given user.
	 * This list will include all of the mutual friends, even if they do not use the requesting application.
	 * In order to user this method, however, you must submit your application for review (https://developers.facebook.com/docs/apps/review). 
	 * @param userId the ID of the user to query for mutual friends
	 * @param limit the maximum number of common friends to return.
	 * @return a {@link CountedList} of friends that the authenticated user has in common with the given user.
	 */
	CountedList<Reference> getAllMutualFriends(String userId, int limit);

	/**
	 * Fetches a counted list of (at most 25) pages that both the authenticated user shares and the given user have liked.
	 * @param userId the ID of the user to query for mutual likes
	 * @return a {@link CountedList} of page references that both the authenticated user shares and the given user have liked.
	 */
	CountedList<Reference> getMutualLikes(String userId);
	
	/**
	 * Fetches a counted list of pages that both the authenticated user shares and the given user have liked.
	 * @param userId the ID of the user to query for mutual likes
	 * @param limit the maximum number of common likes to return.
	 * @return a {@link CountedList} of page references that both the authenticated user shares and the given user have liked.
	 */
	CountedList<Reference> getMutualLikes(String userId, int limit);
	
	CountedList<Reference> getFriendsUsingApp(String appId);

	CountedList<Reference> getFriendsUsingApp(String appId, int limit);

	CountedList<Reference> getFriendsWhoLike(String objectId);

	CountedList<Reference> getFriendsWhoLike(String objectId, int limit);

	CountedList<Reference> getFriendsWhoWatched(String videoId);

	CountedList<Reference> getFriendsWhoWatched(String videoId, int limit);
	
	CountedList<Reference> getFriendsWhoListenTo(String objectId);

	CountedList<Reference> getFriendsWhoListenTo(String objectId, int limit);
	
	CountedList<Reference> getFriendsTaggedAt(String placeId);

	CountedList<Reference> getFriendsTaggedAt(String placeId, int limit);

}
