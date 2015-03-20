/*
 * Copyright 2015 the original author or authors.
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
package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Ignore;
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
	
	public FeedOperationsITests() {
		super(APP_ID, APP_SECRET);
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream", "Alice Arensen");
		testUser2 = createTestUser(true, "publish_actions,read_stream", "Bob Beeswax");

		// set up these test users as friends
		FacebookTemplate facebook1 = new FacebookTemplate(testUser1.getAccessToken());
		FacebookTemplate facebook2 = new FacebookTemplate(testUser2.getAccessToken());
		facebook1.testUserOperations().sendConfirmFriends(testUser1, testUser2);
		facebook2.testUserOperations().sendConfirmFriends(testUser2, testUser1);
		
		feedOps1 = facebook1.feedOperations();
		feedOps2 = facebook2.feedOperations();
	}
	

	@Test
	public  void statusTests() {
		PagedList<Post> statuses = feedOps1.getFeed();
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
	@Ignore("Facebook is confusing me on this...step away and come back later")
	public void postTests() throws Exception {
		PagedList<Post> posts = feedOps1.getPosts();
		assertEquals(0, posts.size());
		feedOps1.post(new PostData("me").message("Yo!"));
		posts = feedOps1.getPosts();
		assertEquals(1, posts.size());
		
		PagedList<Post> feed = feedOps1.getFeed();
		System.out.println(feed.size() + " :: " + feed.get(0).getMessage() + " :: " + feed.get(0).getId() + " :: " + feed.get(0).getFrom().getName());
		
		PagedList<Post> feed2 = feedOps2.getFeed();
		System.out.println(feed2.size() + " :: " + feed2.get(0).getMessage() + " :: " + feed2.get(0).getId() + " :: " + feed2.get(0).getFrom().getName());
		
		// Can't post to another user's feed without the application itself arranging it with Facebook. So can't test that.
		// "Feed story publishing to other users is disabled for this application."
		//		PagedList<Post> posts2 = feedOps2.getPosts();
		//		assertEquals(0, posts2.size());
		//		feedOps1.post(testUser2.getId(), "Hi there");
		//		posts2 = feedOps2.getPosts();
		//		assertEquals(1, posts2.size());
	}
	
	@Test
	public void linkTests() throws Exception {
		PagedList<Post> links = feedOps1.getLinks();
		assertEquals(0, links.size());
		String linkId = feedOps1.postLink("Check it out", new FacebookLink("http://test.org", "Name", "Caption", "Description"));
		logger.info("CREATED LINK: " + linkId);
		links = feedOps1.getLinks();
		assertEquals(1, links.size());
	}
	
	private String extractPostId(String fullPostId) {
		// The full post ID is the post ID prefixed by "{user-id}_"...strip off the prefix.
		return fullPostId.substring(testUser1.getId().length() + 1);
	}

}
