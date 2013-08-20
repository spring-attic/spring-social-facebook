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
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.Post.PostType;
import org.springframework.social.facebook.api.Post.Privacy;

/**
 * @author Craig Walls
 */
public class FeedTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void getFeed() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?limit=25"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(5, feed.size());
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?offset=40&limit=20"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed(40, 20);
		assertEquals(5, feed.size());
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withPagedListParameters_since() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?limit=25&since=1360384019"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed(new PagingParameters(25, null, 1360384019L, null));
		assertEquals(5, feed.size());
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withPagedListParameters_until() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?limit=25&until=1360384019"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed(new PagingParameters(25, null, null, 1360384019L));
		assertEquals(5, feed.size());
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withUnknownType() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?limit=25"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed-with-unknown-type"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(1, feed.size());		
		assertTrue(feed.get(0) instanceof Post);
		assertEquals(PostType.POST, feed.get(0).getType());
		assertEquals("100001387295207_160065090716400", feed.get(0).getId());
		assertEquals("Just trying something", feed.get(0).getMessage());
		assertEquals("100001387295207", feed.get(0).getFrom().getId());
		assertEquals("Art Names", feed.get(0).getFrom().getName());
		assertNull(feed.get(0).getApplication());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFeed_unauthorized() {
		unauthorizedFacebook.feedOperations().getFeed();
	}

	@Test
	public void getFeed_forOwnerId() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345678/feed?limit=25"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed("12345678");
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test
	public void getFeed_forOwnerId_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345678/feed?offset=100&limit=50"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed("12345678", 100, 50);
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test(expected = NotAuthorizedException.class)
	public void getFeed_forOwnerId_unauthorized() {
		unauthorizedFacebook.feedOperations().getFeed("12345678");
	}

	@Test
	public void getHomeFeed() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/home?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> homeFeed = facebook.feedOperations().getHomeFeed();
		assertEquals(5, homeFeed.size());
		assertFeedEntries(homeFeed);
	}

	@Test
	public void getHomeFeed_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/home?offset=40&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> homeFeed = facebook.feedOperations().getHomeFeed(40, 20);
		assertEquals(5, homeFeed.size());
		assertFeedEntries(homeFeed);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getHomeFeed_unauthorized() {
		unauthorizedFacebook.feedOperations().getHomeFeed();
	}

	@Test
	public void getStatuses() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/statuses?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses());
	}

	@Test
	public void getStatuses_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/statuses?offset=30&limit=10"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses(30, 10));
	}

	@Test(expected = NotAuthorizedException.class)
	public void getStatuses_unauthorized() {
		unauthorizedFacebook.feedOperations().getStatuses();
	}

	@Test
	public void getStatuses_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/24680/statuses?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses("24680"));
	}

	@Test
	public void getStatuses_forSpecificUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/24680/statuses?offset=15&limit=5"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses("24680", 15, 5));
	}

	@Test(expected = NotAuthorizedException.class)
	public void getStatuses_forSpecificUser_unauthorized() {
		unauthorizedFacebook.feedOperations().getStatuses("12345678");
	}

	@Test
	public void getLinks_preOctober2012() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/links?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links_preOct2012"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks());
	}

	@Test
	public void getLinks() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/links?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks());
	}

	@Test
	public void getLinks_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/links?offset=40&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks(40, 20));
	}

	@Test(expected = NotAuthorizedException.class)
	public void getLinks_unauthorized() {
		unauthorizedFacebook.feedOperations().getLinks();
	}

	@Test
	public void getLinks_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/13579/links?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks("13579"));
	}

	@Test
	public void getLinks_forSpecificUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/13579/links?offset=40&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks("13579", 40, 20));
	}

	@Test(expected = NotAuthorizedException.class)
	public void getLinks_forSpecificUser_unauthorized() {
		unauthorizedFacebook.feedOperations().getLinks("12345678");
	}

	@Test
	public void getNotes() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/notes?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-notes"), MediaType.APPLICATION_JSON));
		List<NotePost> notes = facebook.feedOperations().getNotes();
		assertNotes(notes);
	}

	@Test
	public void getNotes_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/notes?offset=60&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-notes"), MediaType.APPLICATION_JSON));
		List<NotePost> notes = facebook.feedOperations().getNotes(60, 20);
		assertNotes(notes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getNotes_unauthorized() {
		unauthorizedFacebook.feedOperations().getNotes();
	}

	@Test
	public void getNotes_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345/notes?limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-notes"), MediaType.APPLICATION_JSON));
		List<NotePost> notes = facebook.feedOperations().getNotes("12345");
		assertNotes(notes);
	}

	@Test
	public void getNotes_forSpecificUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345/notes?offset=60&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-notes"), MediaType.APPLICATION_JSON));
		List<NotePost> notes = facebook.feedOperations().getNotes("12345", 60, 20);
		assertNotes(notes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getNotes_unauthorized_forSpecificUser() {
		unauthorizedFacebook.feedOperations().getNotes("12345");
	}
	
	@Test
	public void getPosts() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/posts?limit=25"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts();
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test
	public void getPosts_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/posts?offset=30&limit=15"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts(30, 15);
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getPosts_unauthorized() {
		unauthorizedFacebook.feedOperations().getPosts();
	}

	@Test
	public void getPosts_forOwnerId() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345678/posts?limit=25"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts("12345678");
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test
	public void getPosts_forOwnerId_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345678/posts?offset=30&limit=15"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts("12345678", 30, 15);
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test(expected = NotAuthorizedException.class)
	public void getPosts_unauthorized_forSpecificUser() {
		unauthorizedFacebook.feedOperations().getPosts("12345");
	}

	@Test 
	public void getFeedEntry_preSeptember2012() {
		mockServer.expect(requestTo("https://graph.facebook.com/100001387295207_123939024341978"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post_preSept2012"), MediaType.APPLICATION_JSON));
		Post feedEntry = facebook.feedOperations().getPost("100001387295207_123939024341978");
		assertEquals(PostType.STATUS, feedEntry.getType());
		assertEquals("100001387295207_123939024341978", feedEntry.getId());
		assertEquals("Hello world!", feedEntry.getMessage());
		assertEquals("100001387295207", feedEntry.getFrom().getId());
		assertEquals("Art Names", feedEntry.getFrom().getName());
		assertEquals(2, (int) feedEntry.getLikeCount());
		assertEquals(2, feedEntry.getComments().size());
		assertNull(feedEntry.getComments().get(1).getLikes());
		assertEquals(3, feedEntry.getComments().get(1).getLikesCount());
	}

	@Test 
	public void getFeedEntry() {
		mockServer.expect(requestTo("https://graph.facebook.com/100001387295207_123939024341978"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post"), MediaType.APPLICATION_JSON));
		Post feedEntry = facebook.feedOperations().getPost("100001387295207_123939024341978");
		assertEquals(PostType.STATUS, feedEntry.getType());
		assertEquals("100001387295207_123939024341978", feedEntry.getId());
		assertEquals("Hello world!", feedEntry.getMessage());
		assertEquals("100001387295207", feedEntry.getFrom().getId());
		assertEquals("Art Names", feedEntry.getFrom().getName());
		assertEquals(2, (int) feedEntry.getLikeCount());
		List<Reference> likes = feedEntry.getLikes();
		assertEquals("1533260333", likes.get(0).getId());
		assertEquals("Roy Clarkson", likes.get(0).getName());
		assertEquals("1322692345", likes.get(1).getId());
		assertEquals("Jim Smith", likes.get(1).getName());
		assertEquals(2, feedEntry.getComments().size());
		assertEquals(2, feedEntry.getCommentCount());
		assertNull(feedEntry.getComments().get(1).getLikes());
		assertEquals(3, feedEntry.getComments().get(1).getLikesCount());
	}

	@Test 
	public void getFeedEntry_noLikes() {
		mockServer.expect(requestTo("https://graph.facebook.com/100001387295207_123939024341978"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post_nolikes"), MediaType.APPLICATION_JSON));
		Post feedEntry = facebook.feedOperations().getPost("100001387295207_123939024341978");
		assertEquals(PostType.STATUS, feedEntry.getType());
		assertEquals("100001387295207_123939024341978", feedEntry.getId());
		assertEquals("Hello world!", feedEntry.getMessage());
		assertEquals("100001387295207", feedEntry.getFrom().getId());
		assertEquals("Art Names", feedEntry.getFrom().getName());
		assertNull(feedEntry.getLikeCount());
		assertNull(feedEntry.getLikes());
		assertEquals(2, feedEntry.getComments().size());
		assertEquals(2, feedEntry.getCommentCount());
		assertNull(feedEntry.getComments().get(1).getLikes());
		assertEquals(3, feedEntry.getComments().get(1).getLikesCount());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFeedEntry_unauthorized() {
		unauthorizedFacebook.feedOperations().getPost("12345");
	}

	@Test
	public void updateStatus() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().updateStatus("Hello Facebook World"));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateStatus_unauthorized() {
		unauthorizedFacebook.feedOperations().updateStatus("Hello");
	}

	@Test(expected = DuplicateStatusException.class)
	public void updateStatus_duplicate() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string("message=Duplicate"))
			.andRespond(withBadRequest().body(jsonResource("error-duplicate-status")).contentType(MediaType.APPLICATION_JSON));
		facebook.feedOperations().updateStatus("Duplicate");
	}

	@Test
	public void post_message() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post("123456789", "Hello Facebook World"));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void postMessage_unauthorized() {
		unauthorizedFacebook.feedOperations().post("123456789", "Hello Facebook World");
	}

	@Test
	public void post_link() throws Exception {
		String requestBody = "link=someLink&name=some+name&caption=some+caption&description=some+description&message=Hello+Facebook+World";
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed")).andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		assertEquals("123456_78901234", facebook.feedOperations().postLink("Hello Facebook World", link));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void postLink_unauthorized() {
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		unauthorizedFacebook.feedOperations().postLink("Hello Facebook World", link);
	}
	
	@Test
	public void post_NewPost_messageOnly() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World")));
		mockServer.verify();		
	}

	@Test
	public void post_NewPost_withPrivacy() throws Exception {
		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27ALL_FRIENDS%27%7D";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.ALL_FRIENDS)));
		mockServer.verify();		
	}

	@Test(expected=IllegalArgumentException.class)
	public void post_NewPost_withCustomPrivacy_noAllowOrDeny() throws Exception {
		facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM));
	}
	
	@Test
	public void post_NewPost_withCustomPrivacy_allow() throws Exception {
		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27allow%27%3A+%2712345%2C54321%27%7D";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).allow("12345","54321")));
		mockServer.verify();		
	}

	@Test
	public void post_NewPost_withCustomPrivacy_deny() throws Exception {
		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27deny%27%3A+%2712345%2C54321%27%7D";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).deny("12345","54321")));
		mockServer.verify();		
	}

	@Test
	public void post_NewPost_withCustomPrivacy_allowAndDeny() throws Exception {
		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27allow%27%3A+%2712345%2C54321%27%2C%27deny%27%3A+%2767890%2C98765%27%7D";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).deny("67890","98765").allow("12345", "54321")));
		mockServer.verify();		
	}

	@Test
	public void post_NewPost_link() throws Exception {
		String requestBody = "message=Hello+Facebook+World&link=someLink&name=some+name&caption=some+caption&description=some+description";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		PostData newPost = new PostData("123456789")
				.link("someLink")
				.name("some name")
				.caption("some caption")
				.description("some description")
				.message("Hello Facebook World");
		assertEquals("123456_78901234", facebook.feedOperations().post(newPost));
		mockServer.verify();		
	}

	@Test
	public void post_link_toAnotherFeed() throws Exception {
		String requestBody = "link=someLink&name=some+name&caption=some+caption&description=some+description&message=Hello+Facebook+World";
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed")).andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		assertEquals("123456_78901234", facebook.feedOperations().postLink("123456789", "Hello Facebook World", link));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void postLink_toAnotherFeed_unauthorized() {
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		unauthorizedFacebook.feedOperations().postLink("123456789", "Hello Facebook World", link);
	}

	@Test
	public void deleteFeedEntry() {
		String requestBody = "method=delete";
		mockServer.expect(requestTo("https://graph.facebook.com/123456_78901234"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken")).andExpect(content().string(requestBody))
				.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
		facebook.feedOperations().deletePost("123456_78901234");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteFeedEntry_unauthorized() {
		unauthorizedFacebook.feedOperations().deletePost("123456_78901234");
	}

	@Test
	public void searchPublicFeed() {
		mockServer.expect(requestTo("https://graph.facebook.com/search?q=Dr%20Seuss&type=post&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post-list"), MediaType.APPLICATION_JSON));
		List<Post> posts = facebook.feedOperations().searchPublicFeed("Dr Seuss");
		assertPostList(posts);
	}

	@Test
	public void searchPublicFeed_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/search?q=Dr%20Seuss&type=post&offset=40&limit=10"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post-list"), MediaType.APPLICATION_JSON));
		List<Post> posts = facebook.feedOperations().searchPublicFeed("Dr Seuss", 40, 10);
		assertPostList(posts);
	}

	@Test
	public void searchHomeFeed() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/home?q=Dr+Seuss&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post-list"), MediaType.APPLICATION_JSON));
		List<Post> posts = facebook.feedOperations().searchHomeFeed("Dr Seuss");
		assertPostList(posts);
	}

	@Test
	public void searchHomeFeed_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/home?q=Dr+Seuss&offset=20&limit=5"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post-list"), MediaType.APPLICATION_JSON));
		List<Post> posts = facebook.feedOperations().searchHomeFeed("Dr Seuss", 20, 5);
		assertPostList(posts);
	}

	@Test(expected = NotAuthorizedException.class)
	public void searchHomeFeed_unauthorized() {
		unauthorizedFacebook.feedOperations().searchHomeFeed("Dr Seuss");
	}

	@Test 
	public void searchUserFeed_currentUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?q=Football&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().searchUserFeed("Football");		
		assertEquals(5, feed.size());		
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test 
	public void searchUserFeed_currentUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?q=Football&offset=50&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().searchUserFeed("Football", 50, 25);		
		assertEquals(5, feed.size());		
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test(expected = NotAuthorizedException.class)
	public void searchUserFeed_currentUser_unauthorized() {
		unauthorizedFacebook.feedOperations().searchUserFeed("Football");
	}
	
	@Test 
	public void searchUserFeed_specificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed?q=Football&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().searchUserFeed("123456789", "Football");		
		assertEquals(5, feed.size());		
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test 
	public void searchUserFeed_specificUser_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/feed?q=Football&offset=80&limit=20"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().searchUserFeed("123456789", "Football", 80, 20);		
		assertEquals(5, feed.size());		
		assertTrue(feed.get(0) instanceof StatusPost);
		assertTrue(feed.get(1) instanceof PhotoPost);
		assertTrue(feed.get(2) instanceof StatusPost);
		assertTrue(feed.get(3) instanceof SwfPost);
		assertTrue(feed.get(4) instanceof MusicPost);
		assertFeedEntries(feed);
	}

	@Test(expected = NotAuthorizedException.class)
	public void searchUserFeed_specificUser_unauthorized() {
		unauthorizedFacebook.feedOperations().searchUserFeed("123456789", "Football");
	}
	
	private void assertPostList(List<Post> posts) {
		assertEquals(3, posts.size());
		assertTrue(posts.get(0) instanceof StatusPost);
		assertEquals("825100071_10150596184825072", posts.get(0).getId());
		assertEquals("825100071", posts.get(0).getFrom().getId());
		assertEquals("Adrian Hunley", posts.get(0).getFrom().getName());
		assertEquals("\"Today you are you! That is truer than true! There is no one alive who is you-er than you!\"\n-Dr. Seuss", posts.get(0).getMessage());
		assertEquals(toDate("2011-05-13T02:32:21+0000"), posts.get(0).getCreatedTime());
		assertEquals(toDate("2011-05-13T02:32:21+0000"), posts.get(0).getUpdatedTime());
		assertTrue(posts.get(1) instanceof StatusPost);
		assertEquals("100000224762665_227473300603494", posts.get(1).getId());
		assertEquals("100000224762665", posts.get(1).getFrom().getId());
		assertEquals("Machelle Allen Bitton", posts.get(1).getFrom().getName());
		assertEquals("When beetles fight these battles in a bottle with their paddles\nand the bottle's on a poodle and the poodle's eating noodles...\n...they call this a muddle puddle tweetle poodle beetle noodle\nbottle paddle battle.\"\n\u2014 Dr. Seuss (Fox in Socks)", posts.get(1).getMessage());
		assertEquals(toDate("2011-05-13T01:41:50+0000"), posts.get(1).getCreatedTime());
		assertEquals(toDate("2011-05-13T01:41:50+0000"), posts.get(1).getUpdatedTime());
		assertEquals(12, (int) posts.get(1).getLikeCount());
		assertTrue(posts.get(2) instanceof StatusPost);
		assertEquals("100000132946459_227565820591181", posts.get(2).getId());
		assertEquals("100000132946459", posts.get(2).getFrom().getId());
		assertEquals("William Terry", posts.get(2).getFrom().getName());
		assertEquals("and that's when I realized, the greatest rapper of all time, was Dr. Seuss", posts.get(2).getMessage());
		assertEquals(toDate("2011-05-13T01:26:13+0000"), posts.get(2).getCreatedTime());
		assertEquals(toDate("2011-05-13T01:26:13+0000"), posts.get(2).getUpdatedTime());
	}
	
	private void assertFeedEntries(List<Post> feed) {
		assertEquals(PostType.STATUS, feed.get(0).getType());
		assertEquals("100001387295207_160065090716400", feed.get(0).getId());
		assertEquals("Just trying something", feed.get(0).getMessage());
		assertEquals("100001387295207", feed.get(0).getFrom().getId());
		assertEquals("Art Names", feed.get(0).getFrom().getName());
		assertEquals(23, feed.get(0).getSharesCount());
		assertNull(feed.get(0).getApplication());
		assertEquals(PostType.PHOTO, feed.get(1).getType());
		assertEquals("100001387295207_160064384049804", feed.get(1).getId());
		assertEquals("Check out my ride", feed.get(1).getMessage());
		assertEquals("100001387295207", feed.get(1).getFrom().getId());
		assertEquals("Art Names", feed.get(1).getFrom().getName());
		assertEquals(0, feed.get(1).getSharesCount());
		assertNull(feed.get(1).getApplication());
		assertEquals(PostType.STATUS, feed.get(2).getType());
		assertEquals("100001387295207_153453231377586", feed.get(2).getId());
		assertEquals("Hello Facebook!", feed.get(2).getMessage());
		assertEquals("100001387295207", feed.get(2).getFrom().getId());
		assertEquals("Art Names", feed.get(2).getFrom().getName());
		assertEquals("162886103757745", feed.get(2).getApplication().getId());
		assertEquals(19, feed.get(2).getSharesCount());
		assertEquals("Spring Social Showcase", feed.get(2).getApplication().getName());
	}
	
	@SuppressWarnings("deprecation")
	private void assertLinks(List<LinkPost> feed) {
		assertEquals(2, feed.size());
		assertEquals(PostType.LINK, feed.get(0).getType());
		assertEquals("125736073702566", feed.get(0).getId());
		assertEquals("Warning about Facebook Phishing: See http://www.facebook.com/group.php?gid=9874388706", feed.get(0).getMessage());
		assertEquals("738140579", feed.get(0).getFrom().getId());
		assertEquals("Craig Walls", feed.get(0).getFrom().getName());
		assertEquals("http://profile.ak.fbcdn.net/hprofile-ak-snc4/50255_9874388706_6623_n.jpg", feed.get(0).getPicture());
		assertEquals("http://static.ak.fbcdn.net/rsrc.php/v1/yD/r/aS8ecmYRys0.gif", feed.get(0).getIcon());
		assertEquals("Facebook Phishing Scam Awareness", feed.get(0).getName());
		assertNull(feed.get(0).getDescription());
		assertNull(feed.get(0).getLink()); // sometimes links are in the message
		assertEquals(PostType.LINK, feed.get(1).getType());
		assertEquals("147264864601", feed.get(1).getId());
		assertEquals("Hey, let's go buy some furniture from the guy who's off his meds.", feed.get(1).getMessage());
		assertEquals("738140579", feed.get(1).getFrom().getId());
		assertEquals("Craig Walls", feed.get(1).getFrom().getName());
		assertEquals("http://i.ytimg.com/vi/QSLT2N-Ome8/2.jpg", feed.get(1).getPicture());
		assertEquals("http://static.ak.fbcdn.net/rsrc.php/v1/yD/r/aS8ecmYRys0.gif", feed.get(1).getIcon());
		assertEquals("Competition Beatdown Fail", feed.get(1).getName());
		assertEquals("What was this guy thinking?", feed.get(1).getDescription());
		assertEquals("http://www.youtube.com/watch?v=QSLT2N-Ome8", feed.get(1).getLink());
	}

	private void assertNotes(List<NotePost> notes) {
		assertEquals(2, notes.size());
		assertEquals(PostType.NOTE, notes.get(0).getType());
		assertEquals("161200187269557", notes.get(0).getId());
		assertEquals("100001387295207", notes.get(0).getFrom().getId());
		assertEquals("Art Names", notes.get(0).getFrom().getName());
		assertEquals("Just a note", notes.get(0).getSubject());
		assertEquals("<p>This is just a test note. Nothing special to see here.</p>", notes.get(0).getMessage());
		assertEquals("http://static.ak.fbcdn.net/rsrc.php/v1/yY/r/1gBp2bDGEuh.gif", notes.get(0).getIcon());
		assertEquals(toDate("2011-03-28T15:17:41+0000"), notes.get(0).getCreatedTime());
		assertEquals(toDate("2011-03-28T15:17:41+0000"), notes.get(0).getUpdatedTime());
		assertEquals(PostType.NOTE, notes.get(1).getType());
		assertEquals("160546394001603", notes.get(1).getId());
		assertEquals("100001387295207", notes.get(1).getFrom().getId());
		assertEquals("Art Names", notes.get(1).getFrom().getName());
		assertEquals("Test Note", notes.get(1).getSubject());
		assertEquals("<p>Just a <strong>test</strong> note...nothing to see here.</p>", notes.get(1).getMessage());
		assertEquals("http://static.ak.fbcdn.net/rsrc.php/v1/yY/r/1gBp2bDGEuh.gif", notes.get(1).getIcon());
		assertEquals(toDate("2011-03-25T18:25:01+0000"), notes.get(1).getCreatedTime());
		assertEquals(toDate("2011-03-25T20:08:27+0000"), notes.get(1).getUpdatedTime());
	}
	
	private void assertStatuses(List<StatusPost> statuses) {
		assertEquals(3, statuses.size());
		assertEquals(PostType.STATUS, statuses.get(0).getType());
		assertEquals("161195833936659", statuses.get(0).getId());
		assertEquals("100001387295207", statuses.get(0).getFrom().getId());
		assertEquals("Art Names", statuses.get(0).getFrom().getName());
		assertEquals("One more...just for fun", statuses.get(0).getMessage());
		assertEquals(toDate("2011-03-28T14:54:07+0000"), statuses.get(0).getUpdatedTime());
		assertEquals(PostType.STATUS, statuses.get(1).getType());
		assertEquals("161195783936664", statuses.get(1).getId());
		assertEquals("100001387295207", statuses.get(1).getFrom().getId());
		assertEquals("Art Names", statuses.get(1).getFrom().getName());
		assertEquals("Just another status.", statuses.get(1).getMessage());
		assertEquals(toDate("2011-03-28T14:53:57+0000"), statuses.get(1).getUpdatedTime());
		assertEquals("161195107270065", statuses.get(2).getId());
		assertEquals("100001387295207", statuses.get(2).getFrom().getId());
		assertEquals("Art Names", statuses.get(2).getFrom().getName());
		assertEquals("Good morning Monday!", statuses.get(2).getMessage());
		assertEquals(toDate("2011-03-28T14:50:27+0000"), statuses.get(2).getUpdatedTime());
	}

}
