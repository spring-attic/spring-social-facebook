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
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.PostData;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class FeedOperationsITests extends FacebookITest implements ITestCredentials {

	private static final Logger logger = Logger.getLogger(FeedOperationsITests.class.getName());
	
	private TestUser testUser1;
	private TestUser testUser2;
	private FeedOperations feedOps1;
	private FeedOperations feedOps2;
	private FacebookTemplate facebook1;
	private FacebookTemplate facebook2;
	
	public FeedOperationsITests() {
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
		
		feedOps1 = facebook1.feedOperations();
		feedOps2 = facebook2.feedOperations();
		
		logger.info("CREATED TEST USERS: " + testUser1.getId() + " , " + testUser2.getId());
	}
	

	@Test
	public  void statusTests() {
		PagedList<Post> statuses = feedOps1.getStatuses();
		assertEquals(0, statuses.size());
		String fullPostId = feedOps1.updateStatus("Hello");
		String postId = extractPostId(fullPostId);
		logger.info("CREATED POST: " + fullPostId + "  (" + postId + ")");
		
		statuses = feedOps1.getStatuses();
		assertEquals(1, statuses.size());
		Post post = statuses.get(0);
		assertEquals(postId, post.getId());
		assertEquals("Hello", post.getMessage());
		feedOps1.deletePost(fullPostId);
		statuses = feedOps1.getStatuses();
		assertEquals(0, statuses.size());
	}

	@Test
	public void feedTests() throws Exception {
		// Slight delay to give the friendship story time to hit the feed...it usually isn't ready right away.
		// But sometimes it is and the test fails in inconsistent ways if you don't give it time to sort itself out.
		Thread.sleep(5000);
		PagedList<Post> feed = feedOps1.getFeed();
		assertEquals(1, feed.size());
		assertEquals("Alice Arensen and Bob Beeswax are now friends.", feed.get(0).getStory());

		String statusId = feedOps1.updateStatus("Hello");
		feed = feedOps1.getFeed();
		assertEquals(2, feed.size());
		
		String linkId = feedOps1.postLink("Here's a link", new FacebookLink("http://test.org", "NAME", "CAPTION", "DESCRIPTION"));
		feed = feedOps1.getFeed();
		assertEquals(3, feed.size());
		
		PagedList<Post> links = feedOps1.getLinks();
		assertEquals(1, links.size());
		assertEquals(extractPostId(linkId), links.get(0).getId());
		
		PagedList<Post> statuses = feedOps1.getStatuses();
		assertEquals(2, statuses.size());
		statusId = extractPostId(statusId);
		// the status is never consistently the first or second, so just assert that it is one of the two statuses.
		assertTrue(statusId.equals(statuses.get(0).getId()) || statusId.equals(statuses.get(1).getId()));
		
		PagedList<Post> posts = feedOps1.getPosts();
		assertEquals(3, posts.size());
		
		PagedList<Post> homeFeed = feedOps1.getHomeFeed();
		assertEquals(2, homeFeed.size());
		assertEquals("Here's a link", homeFeed.get(0).getMessage());
		assertEquals("Hello", homeFeed.get(1).getMessage());
	}
	
	@Test
	public void tagTests() throws Exception {
		// tag a user in a post
		feedOps2.post(new PostData("me").message("Hiya!").place("111625055543961").tags(testUser1.getId()));
		PagedList<Post> feed = feedOps1.getFeed();
		assertEquals(1, feed.size());
		assertEquals("Hiya!", feed.get(0).getMessage());
		assertEquals("111625055543961", feed.get(0).getPlace().getId());
		assertEquals(testUser2.getId(), feed.get(0).getFrom().getId());

		// TODO: Figure out why the /tagged endpoint is returning an empty list here.
//		PagedList<Post> tagged = feedOps1.getTagged();
//		assertEquals(1, tagged.size());
//		assertEquals(tagPostId, tagged.get(0).getId());
//		assertEquals("Hiya!", tagged.get(0).getMessage());
//		assertEquals(testUser2.getId(), tagged.get(0).getFrom().getId());
	}
	
	@Test
	public void checkinTests() throws Exception {
		PagedList<Post> checkins = feedOps1.getCheckins();
		assertEquals(0, checkins.size());
		String postId = feedOps1.post(new PostData("me").message("Yo!").place("111625055543961"));
		checkins = feedOps1.getCheckins();
		assertEquals(1, checkins.size());
		assertEquals(postId, checkins.get(0).getId());
		assertEquals("Yo!", checkins.get(0).getMessage());
		assertEquals("111625055543961", checkins.get(0).getPlace().getId());
	}
	
	private String extractPostId(String fullPostId) {
		// The full post ID is the post ID prefixed by "{user-id}_"...strip off the prefix.
		return fullPostId.substring(testUser1.getId().length() + 1);
	}

}
