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
 * Defines operations for creating and reading event data as well as RSVP'ing to events on behalf of a user.
 * @author Craig Walls
 */
public interface EventOperations {

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
	 * Retrieves the list of an event's invitees.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getInvited(String eventId);

	/**
	 * Retrieves a list of invitations for events that the authenticated user has created.
	 * Requires "user_events" permission.
	 * @return a list of {@link Invitation}s for events that the user has created.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getCreated();	

	/**
	 * Retrieves a list of invitations for events that the authenticated user has created.
	 * Requires "user_events" permission.
	 * @param pagingParams paging parameters
	 * @return a list of {@link Invitation}s for events that the user has created.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getCreated(PagingParameters pagingParams);	

	/**
	 * Retrieves a list of invitations for events that the authenticated user is attending.
	 * Requires "user_events" permission.
	 * @return a list of {@link Invitation}s for events that the user is attending.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getAttending();	

	/**
	 * Retrieves a list of invitations for events that the authenticated user is attending.
	 * Requires "user_events" permission.
	 * @param pagingParams paging parameters
	 * @return a list of {@link Invitation}s for events that the user is attending.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getAttending(PagingParameters pagingParams);	

	/**
	 * Retrieves the list of an event's invitees who have accepted the invitation.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getAttending(String eventId);
	
	/**
	 * Retrieves a list of invitations for events that the authenticated user may be attending.
	 * Requires "user_events" permission.
	 * @return a list of {@link Invitation}s for events that the user is attending.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getMaybeAttending();	

	/**
	 * Retrieves a list of invitations for events that the authenticated user may be attending.
	 * Requires "user_events" permission.
	 * @param pagingParams paging parameters
	 * @return a list of {@link Invitation}s for events that the user is attending.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getMaybeAttending(PagingParameters pagingParams);	

	/**
	 * Retrieves the list of an event's invitees who have indicated that they may attend the event.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getMaybeAttending(String eventId);
	
	/**
	 * Retrieves a list of invitations for events that the authenticated user has not replied.
	 * Requires "user_events" permission.
	 * @return a list of {@link Invitation}s for events that the user has not replied.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getNoReplies();	

	/**
	 * Retrieves a list of invitations for events that the authenticated user has not replied.
	 * Requires "user_events" permission.
	 * @param pagingParams paging parameters
	 * @return a list of {@link Invitation}s for events that the user has not replied.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getNoReplies(PagingParameters pagingParams);	

	/**
	 * Retrieves the list of an event's invitees who have not yet RSVP'd.
	 * @param eventId the event ID.
	 * @return a list of {@link EventInvitee}s for the event.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<EventInvitee> getNoReplies(String eventId);
	
	/**
	 * Retrieves a list of invitations for events that the authenticated user has declined.
	 * Requires "user_events" permission.
	 * @return a list of {@link Invitation}s for events that the user has declined.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getDeclined();	

	/**
	 * Retrieves a list of invitations for events that the authenticated user has declined.
	 * Requires "user_events" permission.
	 * @param pagingParams paging parameters
	 * @return a list of {@link Invitation}s for events that the user has declined.
	 * @throws InsufficientPermissionException if the user has not granted "user_events" permission.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Invitation> getDeclined(PagingParameters pagingParams);	

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
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Event}s matching the search query
	 * @throws ApiException if there is an error while communicating with Facebook.
	 */
	PagedList<Event> search(String query, PagingParameters pagedListParameters);

}
