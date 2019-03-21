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
package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.EventOperations;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class EventOperationsITests extends FacebookITest implements ITestCredentials {

	private static final Logger logger = Logger.getLogger(EventOperationsITests.class.getName());
	
	private TestUser testUser1;
	private EventOperations eventOps1;
	private FacebookTemplate facebook1;

	private TestUser testUser2;
	private FacebookTemplate facebook2;

	public EventOperationsITests() {
		super(APP_ID, APP_SECRET);
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Alice Arensen");
		testUser2 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Bob Beeswax");

		facebook1 = new FacebookTemplate(testUser1.getAccessToken());
		facebook2 = new FacebookTemplate(testUser2.getAccessToken());
		facebook1.testUserOperations().sendConfirmFriends(testUser1, testUser2);
		facebook2.testUserOperations().sendConfirmFriends(testUser2, testUser1);
		
		eventOps1 = facebook1.eventOperations();
		
		logger.info("CREATED TEST USERS: " + testUser1.getId() + " , " + testUser2.getId());
	}
	

	@Test
	public  void eventEndpointTests() {
	
		// NOTE: These tests are surface tests only. In other words, they only test to make sure that the endpoints
		//       won't fail. There's no good way to setup a test event or invitations in an automated way, so it's
		//       difficult to properly integration test event operations more than ensure that the endpoints don't
		//       fail. Moreover, without an invitation or event to work with, there are a few endpoints that aren't
		//       covered here. 
		//
		//       As a TODO, maybe create some dedicated permanent users, events, and invitations manually. Even then,
		//       once a test user accepts an invitation, is it possible to turn around and decline or maybe it? If not
		//       then there'd be a manual effort for each time the test is run.
		
		assertEquals(0, eventOps1.getAttending().size());
		assertEquals(0, eventOps1.getCreated().size());
		assertEquals(0, eventOps1.getDeclined().size());
		assertEquals(0, eventOps1.getMaybeAttending().size());
		assertEquals(0, eventOps1.getNoReplies().size());
	}

}
