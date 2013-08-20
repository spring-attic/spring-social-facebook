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

/**
 * @author Craig Walls
 */
public class CommentTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void getComments() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/comments?offset=0&limit=25"))
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
		mockServer.expect(requestTo("https://graph.facebook.com/123456/comments?offset=75&limit=100"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comments"), MediaType.APPLICATION_JSON));
		
		List<Comment> comments = facebook.commentOperations().getComments("123456", 75, 100);
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

	/*
	 * This test is for comment "likes" property before Facebook's breaking change to be applied on Sept 5, 2012.
	 * See https://developers.facebook.com/roadmap/#september-2012
	 * Note that even with the September breaking changes enabled, there are some cases where comments will have a "likes" property instead of "like_count".
	 * This seems like a bug on Facebook's side, but Spring Social Facebook will handle both properties for the time being just in case.
	 */
	@Test
	public void getComment_preSeptember2012BreakingChanges() {
		mockServer.expect(requestTo("https://graph.facebook.com/1533260333_122829644452184_587062"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comment_preSept2012"), MediaType.APPLICATION_JSON));
		Comment comment = facebook.commentOperations().getComment("1533260333_122829644452184_587062");
		assertEquals("1533260333", comment.getFrom().getId());
		assertEquals("Art Names", comment.getFrom().getName());
		assertEquals("Howdy!", comment.getMessage());
		assertNull(comment.getLikes());		
		assertEquals(4, comment.getLikesCount());
	}
	
	/*
	 * This test is for comment "like_count" property after Facebook's breaking change are applied on Sept 5, 2012.
	 * See https://developers.facebook.com/roadmap/#september-2012
	 * Note that even with the September breaking changes enabled, there are some cases where comments will have a "likes" property instead of "like_count".
	 * This seems like a bug on Facebook's side, but Spring Social Facebook will handle both properties for the time being just in case.
	 */
	@Test
	public void getComment_postSeptember2012BreakingChanges() {
		mockServer.expect(requestTo("https://graph.facebook.com/1533260333_122829644452184_587062"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("comment"), MediaType.APPLICATION_JSON));
		Comment comment = facebook.commentOperations().getComment("1533260333_122829644452184_587062");
		assertEquals("1533260333", comment.getFrom().getId());
		assertEquals("Art Names", comment.getFrom().getName());
		assertEquals("Howdy!", comment.getMessage());
		assertNull(comment.getLikes());		
		assertEquals(4, comment.getLikesCount());
	}
	
	@Test
	public void addComment() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/comments"))
			.andExpect(method(POST))
			.andExpect(content().string("message=Cool+beans"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"123456_543210\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_543210", facebook.commentOperations().addComment("123456", "Cool beans"));
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void addComment_unauthorized() {
		unauthorizedFacebook.commentOperations().addComment("123456", "Cool beans");
	}
	
	@Test
	public void deleteComment() {
		mockServer.expect(requestTo("https://graph.facebook.com/1533260333_122829644452184_587062"))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
		facebook.commentOperations().deleteComment("1533260333_122829644452184_587062");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteComment_unauthorized() {
		unauthorizedFacebook.commentOperations().deleteComment("1533260333_122829644452184_587062");
	}
	
	@Test
	public void getLikes() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("likes"), MediaType.APPLICATION_JSON));
		List<Reference> likes = facebook.commentOperations().getLikes("123456");
		assertEquals(3, likes.size());
		Reference like1 = likes.get(0);
		assertEquals("1122334455", like1.getId());
		assertEquals("Jack Bauer", like1.getName());
		Reference like2 = likes.get(1);
		assertEquals("5544332211", like2.getId());
		assertEquals("Chuck Norris", like2.getName());
		Reference like3 = likes.get(2);
		assertEquals("1324354657", like3.getId());
		assertEquals("Edmund Blackadder", like3.getName());
	}

}
