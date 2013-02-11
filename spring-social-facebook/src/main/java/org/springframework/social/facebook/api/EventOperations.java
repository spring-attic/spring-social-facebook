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
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;


/**
 * Defines operations for creating and reading event data as well as RSVP'ing to events on behalf of a user.
 * @author Craig Walls
 */
public interface EventOperations {

	/**
	 * Retrieves a list of up to 25 events that the authenticated user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations();

	/**
	 * Retrieves a list of events that the authenticated user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @param offset the offset into the list of events
	 * @param limit the maximum number of events to return
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations(int offset, int limit);

	/**
	 * Retrieves a list of events that the authenticated user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations(PagedListParameters pagedListParameters);

	/**
	 * Retrieves a list of events that the specified user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @param userId the user's ID
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations(String userId);

	/**
	 * Retrieves a list of events that the specified user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @param userId the user's ID
	 * @param offset the offset into the list of events
	 * @param limit the maximum number of events to return
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations(String userId, int offset, int limit);

	/**
	 * Retrieves a list of events that the specified user has been invited to.
	 * Requires "user_events" or "friends_events" permission.
	 * @param userId the user's ID
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link Invitation}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" or "friends_events" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Invitation> getInvitations(String userId, PagedListParameters pagedListParameters);

	/**
	 * Retrieves event data for a specified event.
	 * @param eventId the event ID
	 * @return an {@link Event} object
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	Event getEvent(String eventId);
	
	/**
	 * Retrieves an event's image as an array of bytes. Returns the image in Facebook's "normal" type.
	 * @param eventId the event ID
	 * @return an array of bytes containing the event's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getEventImage(String eventId);

	/**
	 * Retrieves an event's image as an array of bytes.
	 * @param eventId the event ID
	 * @param imageType the image type (eg., small, normal, large. square)
	 * @return an array of bytes containing the event's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	byte[] getEventImage(String eventId, ImageType imageType);
	
	/**
	 * Creates an event.
	 * Requires "create_event" permission.
	 * The String passed in for start time and end time is flexible in regard to format. Some valid examples are:
	 * <ul>
	 * <li>2011-04-01T15:30:00 (3:30PM on April 1, 2011)</li>
	 * <li>2011-04-01 (midnight on April 1, 2011)</li>
	 * <li>April 1, 2011 (midnight on April 1, 2011)</li>
	 * <li>17:00:00 (5:00PM today)</li>
	 * <li>10-11-2011 (November 10, 2012)</li>
	 * <li>10/11/2012 (October 11, 2012)</li>
	 * <li>10.11.2012 (November 10, 2012)</li>
	 * <li>Tomorrow 2PM</li>
	 * </ul>
	 * @param name the name of the event
	 * @param startTime the start time of the event.
	 * @param endTime the end time of the event.
	 * @return the newly created event's ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "create_event" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String createEvent(String name, String startTime, String endTime);
	
	/**
	 * Deletes an event.
	 * Requires "create_event" permission.
	 * @param eventId the ID of the event
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "create_event" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void deleteEvent(String eventId);
	
	/**
	 * Retrieves the list of an event's invitees.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getInvited(String eventId);
	
	/**
	 * Retrieves the list of an event's invitees who have accepted the invitation.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getAttending(String eventId);
	
	/**
	 * Retrieves the list of an event's invitees who have indicated that they may attend the event.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getMaybeAttending(String eventId);
	
	/**
	 * Retrieves the list of an event's invitees who have not yet RSVP'd.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getNoReplies(String eventId);
	
	/**
	 * Retrieves the list of an event's invitees who have declined the invitation.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getDeclined(String eventId);

	/**
	 * Accepts an invitation to an event.
	 * Requires "rsvp_event" permission.
	 * @param eventId the event ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "rsvp_event" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void acceptInvitation(String eventId);
	
	/**
	 * RSVPs to an event with a maybe.
	 * Requires "rsvp_event" permission.
	 * @param eventId the event ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "rsvp_event" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void maybeInvitation(String eventId);
	
	/**
	 * Declines an invitation to an event.
	 * Requires "rsvp_event" permission.
	 * @param eventId the event ID
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "rsvp_event" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	void declineInvitation(String eventId);

	/**
	 * Search for events.
	 * @param query the search query (e.g., "Spring User Group")
	 * @return a list of {@link Event}s matching the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Event> search(String query);

	/**
	 * Search for events.
	 * @param query the search query (e.g., "Spring User Group")
	 * @param offset the offset into the list of events
	 * @param limit the maximum number of events to return
	 * @return a list of {@link Event}s matching the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Event> search(String query, int offset, int limit);

	/**
	 * Search for events.
	 * @param query the search query (e.g., "Spring User Group")
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Event}s matching the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Event> search(String query, PagedListParameters pagedListParameters);

}
