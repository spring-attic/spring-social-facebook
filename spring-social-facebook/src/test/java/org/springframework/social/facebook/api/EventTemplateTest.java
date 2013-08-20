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

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;

public class EventTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void getInvitations() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/events?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getInvitations();
		assertInvitations(events);
	}

	@Test
	public void getInvitations_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/events?offset=50&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getInvitations(50, 25);
		assertInvitations(events);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getInvitations_unauthorized() {
		unauthorizedFacebook.eventOperations().getInvitations();
	}

	@Test
	public void getInvitations_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/events?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getInvitations("123456789");
		assertInvitations(events);
	}

	@Test
	public void getInvitations_forSpecificUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/events?offset=60&limit=30"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getInvitations("123456789", 60, 30);
		assertInvitations(events);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getInvitations_forSpecificUser_unauthorized() {
		unauthorizedFacebook.eventOperations().getInvitations("123456789");
	}

	@Test
	public void getEvent() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("simple-event"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertSimpleEvent(event, Event.Privacy.OPEN);
	}
	
	@Test
	public void getEvent_withFriendPrivacyLevel() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("simple-event-friend-privacy"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertSimpleEvent(event, Event.Privacy.FRIENDS);
	}
	
	@Test
	public void getEvent_withLocationAndDescription() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("full-event"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertEquals("193482154020832", event.getId());
		assertEquals("100001387295207", event.getOwner().getId());
		assertEquals("Art Names", event.getOwner().getName());
		assertEquals("Breakdancing Class", event.getName());
		assertEquals(Event.Privacy.SECRET, event.getPrivacy());
		assertEquals(toDate("2011-03-30T14:30:00+0000"), event.getStartTime());
		assertEquals(toDate("2011-03-30T17:30:00+0000"), event.getEndTime());
		assertEquals(toDate("2011-03-30T14:38:40+0000"), event.getUpdatedTime());
		assertEquals("Bring your best parachute pants!", event.getDescription());
		assertEquals("2400 Dunlavy Dr, Denton, TX", event.getLocation());
	}
	
	@Test
	public void createEvent() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/events"))
			.andExpect(method(POST))
			.andExpect(content().string("name=Test+Event&start_time=2011-04-01T15%3A30%3A00&end_time=2011-04-01T18%3A30%3A00"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"193482145020832\"}", MediaType.APPLICATION_JSON));
		String eventId = facebook.eventOperations().createEvent("Test Event", "2011-04-01T15:30:00", "2011-04-01T18:30:00");
		assertEquals("193482145020832", eventId);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void createEvent_unauthorized() {
		unauthorizedFacebook.eventOperations().createEvent("Test Event", "2011-04-01T15:30:00", "2011-04-01T18:30:00");
	}
	
	@Test
	public void deleteEvent() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789"))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		facebook.eventOperations().deleteEvent("123456789");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void deleteEvent_unauthorized() {
		unauthorizedFacebook.eventOperations().deleteEvent("123456789");
	}
	
	@Test
	public void sendInvitation_toOneUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/invited/123123123"))
			.andExpect(method(POST))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().sendInvitation("123456789", "123123123");
	}

	@Test
	public void sendInvitation_toManyUsers() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/invited"))
			.andExpect(method(POST))
			.andExpect(content().string("users=12345%2C54321"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().sendInvitation("123456789", "12345", "54321");
	}

	@Test(expected = IllegalArgumentException.class)
	public void sendInvitation_noUsersGiven() {
		try {
			facebook.eventOperations().sendInvitation("123456789");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("At least one user ID must be given when sending an invitation.", e.getMessage());
			throw e;
		}
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void sendInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().sendInvitation("123456789", "123123123");
	}
	
	@Test
	public void getInvited() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/invited"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("invited"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getInvited("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.UNSURE);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.NOT_REPLIED);
	}
	
	@Test
	public void getAttending() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/attending"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("attending"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getAttending("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.ATTENDING);
	}
	
	@Test
	public void getMaybeAttending() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/maybe"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("maybe-attending"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getMaybeAttending("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.UNSURE);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.UNSURE);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.UNSURE);
	}
	
	@Test
	public void getNoReplies() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/noreply"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("no-replies"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getNoReplies("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.NOT_REPLIED);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.NOT_REPLIED);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.NOT_REPLIED);
	}
	
	@Test
	public void getDeclined() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/declined"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("declined"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getDeclined("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.DECLINED);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.DECLINED);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.DECLINED);
	}

	@Test
	public void acceptInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/attending"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().acceptInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void acceptInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().acceptInvitation("123456789");
	}

	@Test
	public void maybeInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/maybe"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().maybeInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void maybeInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().maybeInvitation("123456789");
	}
	
	@Test
	public void declineInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/declined"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().declineInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void declineInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().declineInvitation("123456789");
	}

	@Test
	public void search() {
		mockServer.expect(requestTo("https://graph.facebook.com/search?offset=0&limit=25&q=Spring+User+Group&type=event"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("event-list"), MediaType.APPLICATION_JSON));
		List<Event> results = facebook.eventOperations().search("Spring User Group");
		assertEquals(1, results.size());
		assertEquals("196119297091135", results.get(0).getId());
		assertEquals("FLUG (Florida Local Users Group) Spring User Conference", results.get(0).getName());
		assertEquals("Radisson Resort at the Port", results.get(0).getLocation());
		assertEquals(toDate("2011-06-01T08:00:00+0000"), results.get(0).getStartTime());
		assertEquals(toDate("2011-06-03T16:00:00+0000"), results.get(0).getEndTime());
	}

	@Test
	public void search_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/search?offset=30&limit=15&q=Spring+User+Group&type=event"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("event-list"), MediaType.APPLICATION_JSON));
		List<Event> results = facebook.eventOperations().search("Spring User Group", 30, 15);
		assertEquals(1, results.size());
		assertEquals("196119297091135", results.get(0).getId());
		assertEquals("FLUG (Florida Local Users Group) Spring User Conference", results.get(0).getName());
		assertEquals("Radisson Resort at the Port", results.get(0).getLocation());
		assertEquals(toDate("2011-06-01T08:00:00+0000"), results.get(0).getStartTime());
		assertEquals(toDate("2011-06-03T16:00:00+0000"), results.get(0).getEndTime());
	}
	
	private void assertInvitee(EventInvitee invitee, String id, String name, RsvpStatus rsvpStatus) {
		assertEquals(id, invitee.getId());
		assertEquals(name, invitee.getName());
		assertEquals(rsvpStatus, invitee.getRsvpStatus());
	}
	
	private void assertInvitations(List<Invitation> events) {
		assertEquals(2, events.size());
		assertEquals("188420717869087", events.get(0).getEventId());
		assertEquals("Afternoon naptime", events.get(0).getName());
		assertEquals("On the couch", events.get(0).getLocation());
		// Facebook event times don't have a timezone component, so they end up parsed as in +0000
		// Unfortunately, this is probably not the actual time of the event.
		assertEquals(toDate("2011-03-26T14:00:00+0000"), events.get(0).getStartTime());
		assertEquals(toDate("2011-03-26T15:00:00+0000"), events.get(0).getEndTime());
		assertEquals(RsvpStatus.ATTENDING, events.get(0).getRsvpStatus());
		assertEquals("188420717869780", events.get(1).getEventId());
		assertEquals("Mow the lawn", events.get(1).getName());
		assertNull(events.get(1).getLocation());
		assertEquals(toDate("2011-03-26T15:00:00+0000"), events.get(1).getStartTime());
		assertEquals(toDate("2011-03-26T16:00:00+0000"), events.get(1).getEndTime());
		assertEquals(RsvpStatus.NOT_REPLIED, events.get(1).getRsvpStatus());
	}
	
	private void assertSimpleEvent(Event event, Event.Privacy privacy) {
		assertEquals("193482154020832", event.getId());
		assertEquals("100001387295207", event.getOwner().getId());
		assertEquals("Art Names", event.getOwner().getName());
		assertEquals("Breakdancing Class", event.getName());
		assertEquals(privacy, event.getPrivacy());
		assertEquals(toDate("2011-03-30T14:30:00+0000"), event.getStartTime());
		assertEquals(toDate("2011-03-30T17:30:00+0000"), event.getEndTime());
		assertEquals(toDate("2011-03-30T14:30:28+0000"), event.getUpdatedTime());
		assertNull(event.getDescription());
		assertNull(event.getLocation());
	}
	
}
