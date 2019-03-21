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

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.CommentOperations;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class CommentOperationsITests extends FacebookITest implements ITestCredentials {
	
	private TestUser testUser1;
	private TestUser testUser2;
	private CommentOperations commentOps1;
	private CommentOperations commentOps2;

	private FeedOperations feedOps1;
	
	public CommentOperationsITests() {
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
		commentOps1 = facebook1.commentOperations();
		commentOps2 = facebook2.commentOperations();
	}
	

	@Test
	public  void commentTests() {
		String statusId = feedOps1.updateStatus("Hello");
		PagedList<Comment> comments = commentOps1.getComments(statusId);
		assertEquals(0, comments.size());
		String commentId1 = commentOps1.addComment(statusId, "Hiya");
		comments = commentOps1.getComments(statusId);
		assertEquals(1, comments.size());
		
		String commentId2 = commentOps2.addComment(statusId, "Hello back");
		comments = commentOps1.getComments(statusId);
		assertEquals(2, comments.size());
		
		comments = commentOps2.getComments(statusId);
		assertEquals(2, comments.size());

		Comment comment = commentOps1.getComment(commentId1);
		assertEquals("Hiya", comment.getMessage());
		assertEquals(testUser1.getId(), comment.getFrom().getId());
		assertEquals("Alice Arensen", comment.getFrom().getName());
		
		comment = commentOps2.getComment(commentId1);
		assertEquals("Hiya", comment.getMessage());
		assertEquals(testUser1.getId(), comment.getFrom().getId());
		assertEquals("Alice Arensen", comment.getFrom().getName());

		commentOps1.deleteComment(commentId1);
		comments = commentOps1.getComments(statusId);
		assertEquals(1, comments.size());

		commentOps1.deleteComment(commentId2);
		comments = commentOps1.getComments(statusId);
		assertEquals(0, comments.size());
	}
	
}
