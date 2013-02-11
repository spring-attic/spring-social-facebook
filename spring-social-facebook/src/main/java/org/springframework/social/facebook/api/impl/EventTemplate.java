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
package org.springframework.social.facebook.api.impl;

import static org.springframework.social.facebook.api.impl.PagedListUtils.*;

import org.springframework.social.facebook.api.Event;
import org.springframework.social.facebook.api.EventInvitee;
import org.springframework.social.facebook.api.EventOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.Invitation;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagedListParameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class EventTemplate extends AbstractFacebookOperations implements EventOperations {
			
	private final GraphApi graphApi;

	public EventTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public PagedList<Invitation> getInvitations() {
		return getInvitations("me", 0, 25);
	}

	public PagedList<Invitation> getInvitations(int offset, int limit) {
		return getInvitations("me", offset, limit);
	}

	public PagedList<Invitation> getInvitations(PagedListParameters pagedListParameters) {
		return getInvitations("me", pagedListParameters);
	}

	public PagedList<Invitation> getInvitations(String userId) {
		return getInvitations(userId, 0, 25);
	}
	
	public PagedList<Invitation> getInvitations(String userId, int offset, int limit) {
		return getInvitations(userId, new PagedListParameters(offset, limit, null, null));
	}
	
	public PagedList<Invitation> getInvitations(String userId, PagedListParameters pagedListParameters) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = getPagingParameters(pagedListParameters);
		return graphApi.fetchConnections(userId, "events", Invitation.class, parameters);
	}
	
	public Event getEvent(String eventId) {
		return graphApi.fetchObject(eventId, Event.class);
	}
	
	public byte[] getEventImage(String eventId) {
		return getEventImage(eventId, ImageType.NORMAL);
	}
	
	public byte[] getEventImage(String eventId, ImageType imageType) {
		return graphApi.fetchImage(eventId, "picture", imageType);
	}
	
	public String createEvent(String name, String startTime, String endTime) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", name);
		data.set("start_time", startTime);
		data.set("end_time", endTime);
		return graphApi.publish("me", "events", data);
	}
	
	public void deleteEvent(String eventId) {
		requireAuthorization();
		graphApi.delete(eventId);
	}

	public PagedList<EventInvitee> getInvited(String eventId) {
		return graphApi.fetchConnections(eventId, "invited", EventInvitee.class);
	}

	public PagedList<EventInvitee> getAttending(String eventId) {
		return graphApi.fetchConnections(eventId, "attending", EventInvitee.class);
	}
	
	public PagedList<EventInvitee> getMaybeAttending(String eventId) {
		return graphApi.fetchConnections(eventId, "maybe", EventInvitee.class);
	}
	
	public PagedList<EventInvitee> getNoReplies(String eventId) {
		return graphApi.fetchConnections(eventId, "noreply", EventInvitee.class);
	}

	public PagedList<EventInvitee> getDeclined(String eventId) {
		return graphApi.fetchConnections(eventId, "declined", EventInvitee.class);
	}
	
	public void acceptInvitation(String eventId) {
		requireAuthorization();
		graphApi.post(eventId, "attending", new LinkedMultiValueMap<String, String>());
	}

	public void maybeInvitation(String eventId) {
		requireAuthorization();
		graphApi.post(eventId, "maybe", new LinkedMultiValueMap<String, String>());
	}

	public void declineInvitation(String eventId) {
		requireAuthorization();
		graphApi.post(eventId, "declined", new LinkedMultiValueMap<String, String>());
	}
	
	public PagedList<Event> search(String query) {
		return search(query, 0, 25);
	}
	
	public PagedList<Event> search(String query, int offset, int limit) {
		return search(query, new PagedListParameters(offset, limit, null, null));
	}
	
	public PagedList<Event> search(String query, PagedListParameters pagedListParameters) {
		MultiValueMap<String, String> queryMap = getPagingParameters(pagedListParameters);
		queryMap.add("q", query);
		queryMap.add("type", "event");
		return graphApi.fetchConnections("search", null, Event.class, queryMap);
	}
	
}
