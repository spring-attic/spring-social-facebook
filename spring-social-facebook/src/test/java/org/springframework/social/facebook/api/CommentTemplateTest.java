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
package org.springframework.social.facebook.api;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @author Craig Walls
 */
public class CommentTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void getComments() throws Exception {
		mockServer.expect(requestTo(fbUrl("123456/comments?offset=0&limit=25")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comments"), MediaType.APPLICATION_JSON));
		
		List<Comment> comments = facebook.commentOperations().getComments("123456");
		assertEquals(2, comments.size());
		Comment comment1 = comments.get(0);
		assertEquals("1533260333", comment1.getFrom().getId());
		assertEquals("Art Names", comment1.getFrom().getName());
		assertEquals("Howdy!", comment1.getMessage());
		Comment comment2 = comments.get(1);
		assertEquals("638140578", comment2.getFrom().getId());
		assertEquals("Chuck Wagon", comment2.getFrom().getName());
		assertEquals("The world says hello back", comment2.getMessage());
	}

	@Test
	public void getComments_withOffsetAndLimit() {
		mockServer.expect(requestTo(fbUrl("123456/comments?offset=75&limit=100")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comments"), MediaType.APPLICATION_JSON));
		
		List<Comment> comments = facebook.commentOperations().getComments("123456", new PagingParameters(100, 75, null, null));
		assertEquals(2, comments.size());
		Comment comment1 = comments.get(0);
		assertEquals("1533260333", comment1.getFrom().getId());
		assertEquals("Art Names", comment1.getFrom().getName());
		assertEquals("Howdy!", comment1.getMessage());
		assertFalse(comment1.canComment());
		assertFalse(comment1.canRemove());
		assertFalse(comment1.userLikes());
		assertNull(comment1.getCommentCount());
		assertNull(comment1.getLikeCount());
		assertEquals(0, comment1.getMessageTags().size());
		Comment comment2 = comments.get(1);
		assertEquals("638140578", comment2.getFrom().getId());
		assertEquals("Chuck Wagon", comment2.getFrom().getName());
		assertEquals("The world says hello back", comment2.getMessage());
		assertTrue(comment2.canComment());
		assertTrue(comment2.canRemove());
		assertTrue(comment2.userLikes());
		assertEquals(40, (int) comment2.getCommentCount());
		assertEquals(90, (int) comment2.getLikeCount());
		assertEquals(0, comment2.getMessageTags().size());
	}
	
	@Test
	public void getComment() {
		mockServer.expect(requestTo(fbUrl("1533260333_122829644452184_587062?fields=id%2Cattachment%2Ccan_comment%2Ccan_remove%2Ccomment_count%2Ccreated_time%2Cfrom%2Clike_count%2Cmessage%2Cparent%2Cuser_likes")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comment"), MediaType.APPLICATION_JSON));
		Comment comment = facebook.commentOperations().getComment("1533260333_122829644452184_587062");
		assertEquals("1533260333", comment.getFrom().getId());
		assertEquals("Art Names", comment.getFrom().getName());
		assertEquals("Howdy!", comment.getMessage());
		assertEquals(4, (int) comment.getLikeCount());
		assertEquals(50, (int) comment.getCommentCount());
		assertTrue(comment.canComment());
		assertTrue(comment.canRemove());
		assertTrue(comment.userLikes());
		StoryAttachment attachment = comment.getAttachment();
		assertNotNull(attachment);
		assertEquals("Some description text", attachment.getDescription());
		assertEquals("share", attachment.getType());
		assertEquals("Some title text", attachment.getTitle());
		assertEquals("203272_269965", attachment.getTarget().getId());
		assertEquals("https://l.facebook.com/l.php?u=http%3A%2F%2Fsomeurl.com", attachment.getTarget().getUrl());
		assertEquals("http://someurl.com", attachment.getUrl());
		assertEquals(500, attachment.getMedia().getImage().getHeight());
		assertEquals(200, attachment.getMedia().getImage().getWidth());
		assertEquals("http://someurl.com/image.png", attachment.getMedia().getImage().getSource());
		assertEquals(1, comment.getMessageTags().size());
	}
	
	@Test
	public void addComment() {
		mockServer.expect(requestTo(fbUrl("123456/comments")))
			.andExpect(method(POST))
			.andExpect(content().string("message=Cool+beans"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"123456_543210\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_543210", facebook.commentOperations().addComment("123456", "Cool beans"));
	}
	
	@Test
	public void deleteComment() {
		mockServer.expect(requestTo(fbUrl("1533260333_122829644452184_587062")))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
		facebook.commentOperations().deleteComment("1533260333_122829644452184_587062");
		mockServer.verify();
	}

}
