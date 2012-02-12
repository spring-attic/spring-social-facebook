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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.Event;
import org.springframework.social.facebook.api.EventInvitee;
import org.springframework.social.facebook.api.EventOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.Invitation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class EventTemplate extends AbstractFacebookOperations implements EventOperations {
			
	private final GraphApi graphApi;

	public EventTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public List<Invitation> getInvitations() {
		return getInvitations("me", 0, 25);
	}

	public List<Invitation> getInvitations(int offset, int limit) {
		return getInvitations("me", offset, limit);
	}

	public List<Invitation> getInvitations(String since, String until) {
		return getInvitations("me", since, until);
	}

	public List<Invitation> getInvitations(String userId) {
		return getInvitations(userId, 0, 25);
	}
	
	public List<Invitation> getInvitations(String userId, int offset, int limit) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("offset", String.valueOf(offset));
		parameters.set("limit", String.valueOf(limit));
		return graphApi.fetchConnections(userId, "events", Invitation.class, parameters);
	}

	public List<Invitation> getInvitations(String userId, String since, String until) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("since", String.valueOf(since));
		parameters.set("until", String.valueOf(until));
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

	public List<EventInvitee> getInvited(String eventId) {
		return graphApi.fetchConnections(eventId, "invited", EventInvitee.class);
	}

	public List<EventInvitee> getAttending(String eventId) {
		return graphApi.fetchConnections(eventId, "attending", EventInvitee.class);
	}
	
	public List<EventInvitee> getMaybeAttending(String eventId) {
		return graphApi.fetchConnections(eventId, "maybe", EventInvitee.class);
	}
	
	public List<EventInvitee> getNoReplies(String eventId) {
		return graphApi.fetchConnections(eventId, "noreply", EventInvitee.class);
	}

	public List<EventInvitee> getDeclined(String eventId) {
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
	
	public List<Event> search(String query) {
		return search(query, 0, 25);
	}
	
	public List<Event> search(String query, int offset, int limit) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "event");
		queryMap.add("offset", String.valueOf(offset));
		queryMap.add("limit", String.valueOf(limit));
		return graphApi.fetchConnections("search", null, Event.class, queryMap);
	}

	public List<Event> search(String query, String since, String until) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("q", query);
		queryMap.add("type", "event");
		queryMap.add("since", String.valueOf(since));
		queryMap.add("until", String.valueOf(until));
		return graphApi.fetchConnections("search", null, Event.class, queryMap);
	}

}
