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
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.facebook.api.Post.PostType;
import org.springframework.util.StringUtils;

/**
 * @author Craig Walls
 */
public class FeedTemplateTest extends AbstractFacebookApiTest {

	
	
	@Test
	public void getFeed() {
		mockServer.expect(requestTo(fbUrl("me/feed?limit=25&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withPagedListParameters_since() {
		mockServer.expect(requestTo(fbUrl("me/feed?limit=25&since=1360384019&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed(new PagingParameters(25, null, 1360384019L, null));
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withPagedListParameters_until() {
		mockServer.expect(requestTo(fbUrl("me/feed?limit=25&until=1360384019&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed(new PagingParameters(25, null, null, 1360384019L));
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test
	public void getFeed_withUnknownType() {
		mockServer.expect(requestTo(fbUrl("me/feed?limit=25&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed-with-unknown-type"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(1, feed.size());		
		assertTrue(feed.get(0) instanceof Post);
		assertEquals(PostType.UNKNOWN, feed.get(0).getType());
		assertEquals("100001387295207_160065090716400", feed.get(0).getId());
		assertEquals("Just trying something", feed.get(0).getMessage());
		assertEquals("100001387295207", feed.get(0).getFrom().getId());
		assertEquals("Art Names", feed.get(0).getFrom().getName());
		assertNull(feed.get(0).getApplication());
	}

	@Test
	public void getFeed_forOwnerId() {
		mockServer.expect(requestTo(fbUrl("12345678/feed?limit=25&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getFeed("12345678");
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test
	public void getHomeFeed() {
		mockServer.expect(requestTo(fbUrl("me/home?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> homeFeed = facebook.feedOperations().getHomeFeed();
		assertEquals(5, homeFeed.size());
		assertFeedEntries(homeFeed);
	}

	@Test
	public void getStatuses() {
		mockServer.expect(requestTo(fbUrl("me/statuses?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses());
	}


	@Test
	public void getStatuses_forSpecificUser() {
		mockServer.expect(requestTo(fbUrl("24680/statuses?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-statuses"), MediaType.APPLICATION_JSON));
		assertStatuses(facebook.feedOperations().getStatuses("24680"));
	}

	@Test
	public void getLinks_preOctober2012() {
		mockServer.expect(requestTo(fbUrl("me/links?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links_preOct2012"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks());
	}

	@Test
	public void getLinks() {
		mockServer.expect(requestTo(fbUrl("me/links?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks());
	}

	@Test
	public void getLinks_forSpecificUser() {
		mockServer.expect(requestTo(fbUrl("13579/links?limit=25&fields=" + ALL_POST_FIELDS_STR)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("links"), MediaType.APPLICATION_JSON));
		assertLinks(facebook.feedOperations().getLinks("13579"));
	}

	@Test
	public void getPosts() {
		mockServer.expect(requestTo(fbUrl("me/posts?limit=25&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts();
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}

	@Test
	public void getPosts_forOwnerId() {
		mockServer.expect(requestTo(fbUrl("12345678/posts?limit=25&fields=" + ALL_POST_FIELDS_STR)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));
		List<Post> feed = facebook.feedOperations().getPosts("12345678");
		assertEquals(5, feed.size());
		assertFeedEntries(feed);
	}	

	@Test 
	public void getFeedEntry() {
		mockServer.expect(requestTo(fbUrl("100001387295207_123939024341978")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post"), MediaType.APPLICATION_JSON));
		Post feedEntry = facebook.feedOperations().getPost("100001387295207_123939024341978");
		assertEquals(PostType.STATUS, feedEntry.getType());
		assertEquals("100001387295207_123939024341978", feedEntry.getId());
		assertEquals("Hello world!", feedEntry.getMessage());
		assertEquals("100001387295207", feedEntry.getFrom().getId());
		assertEquals("Art Names", feedEntry.getFrom().getName());
	}

	@Test 
	public void getFeedEntry_noLikes() {
		mockServer.expect(requestTo(fbUrl("100001387295207_123939024341978")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("post_nolikes"), MediaType.APPLICATION_JSON));
		Post feedEntry = facebook.feedOperations().getPost("100001387295207_123939024341978");
		assertEquals(PostType.STATUS, feedEntry.getType());
		assertEquals("100001387295207_123939024341978", feedEntry.getId());
		assertEquals("Hello world!", feedEntry.getMessage());
		assertEquals("100001387295207", feedEntry.getFrom().getId());
		assertEquals("Art Names", feedEntry.getFrom().getName());
	}

	@Test
	public void updateStatus() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo(fbUrl("me/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().updateStatus("Hello Facebook World"));
		mockServer.verify();
	}

	@Test
	public void updateStatus_duplicate() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string("message=Duplicate"))
			.andRespond(withBadRequest().body(jsonResource("error-duplicate-status")).contentType(MediaType.APPLICATION_JSON));
		
		try {
			facebook.feedOperations().updateStatus("Duplicate");
			fail();
		} catch (DuplicateStatusException e) {
			assertEquals("Duplicate status message", e.getMessage());
			assertEquals("facebook", e.getProviderId());
		}
	}

	@Test
	public void post_message() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo(fbUrl("123456789/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post("123456789", "Hello Facebook World"));
		mockServer.verify();
	}

	@Test
	public void post_link() throws Exception {
		String requestBody = "link=someLink&name=some+name&caption=some+caption&description=some+description&message=Hello+Facebook+World";
		mockServer.expect(requestTo(fbUrl("me/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		assertEquals("123456_78901234", facebook.feedOperations().postLink("Hello Facebook World", link));
		mockServer.verify();
	}

	@Test
	public void post_NewPost_messageOnly() throws Exception {
		String requestBody = "message=Hello+Facebook+World";
		mockServer.expect(requestTo(fbUrl("123456789/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World")));
		mockServer.verify();		
	}

	// TODO: REVISIT POSTING WITH PRIVACY SETTINGS
	
//	@Test
//	public void post_NewPost_withPrivacy() throws Exception {
//		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27ALL_FRIENDS%27%7D";
//		mockServer.expect(requestTo(fbUrl("123456789/feed"))
//				.andExpect(method(POST))
//				.andExpect(header("Authorization", "OAuth someAccessToken"))
//				.andExpect(content().string(requestBody))
//				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
//		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.ALL_FRIENDS)));
//		mockServer.verify();		
//	}
//
//	@Test(expected=IllegalArgumentException.class)
//	public void post_NewPost_withCustomPrivacy_noAllowOrDeny() throws Exception {
//		facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM));
//	}
//	
//	@Test
//	public void post_NewPost_withCustomPrivacy_allow() throws Exception {
//		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27allow%27%3A+%2712345%2C54321%27%7D";
//		mockServer.expect(requestTo(fbUrl("123456789/feed"))
//				.andExpect(method(POST))
//				.andExpect(header("Authorization", "OAuth someAccessToken"))
//				.andExpect(content().string(requestBody))
//				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
//		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).allow("12345","54321")));
//		mockServer.verify();		
//	}
//
//	@Test
//	public void post_NewPost_withCustomPrivacy_deny() throws Exception {
//		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27deny%27%3A+%2712345%2C54321%27%7D";
//		mockServer.expect(requestTo(fbUrl("123456789/feed"))
//				.andExpect(method(POST))
//				.andExpect(header("Authorization", "OAuth someAccessToken"))
//				.andExpect(content().string(requestBody))
//				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
//		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).deny("12345","54321")));
//		mockServer.verify();		
//	}
//
//	@Test
//	public void post_NewPost_withCustomPrivacy_allowAndDeny() throws Exception {
//		String requestBody = "message=Hello+Facebook+World&privacy=%7B%27value%27%3A+%27CUSTOM%27%2C%27allow%27%3A+%2712345%2C54321%27%2C%27deny%27%3A+%2767890%2C98765%27%7D";
//		mockServer.expect(requestTo(fbUrl("123456789/feed"))
//				.andExpect(method(POST))
//				.andExpect(header("Authorization", "OAuth someAccessToken"))
//				.andExpect(content().string(requestBody))
//				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
//		assertEquals("123456_78901234", facebook.feedOperations().post(new PostData("123456789").message("Hello Facebook World").privacy(Privacy.CUSTOM).deny("67890","98765").allow("12345", "54321")));
//		mockServer.verify();		
//	}

	@Test
	public void post_NewPost_link() throws Exception {
		String requestBody = "message=Hello+Facebook+World&link=someLink&name=some+name&caption=some+caption&description=some+description";
		mockServer.expect(requestTo(fbUrl("123456789/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		PostData newPost = new PostData("123456789")
				.link("someLink", null, "some name", "some caption", "some description")
				.message("Hello Facebook World");
		assertEquals("123456_78901234", facebook.feedOperations().post(newPost));
		mockServer.verify();		
	}

	@Test
	public void post_link_toAnotherFeed() throws Exception {
		String requestBody = "link=someLink&name=some+name&caption=some+caption&description=some+description&message=Hello+Facebook+World";
		mockServer.expect(requestTo(fbUrl("123456789/feed")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		assertEquals("123456_78901234", facebook.feedOperations().postLink("123456789", "Hello Facebook World", link));
		mockServer.verify();
	}

	@Test
	public void deleteFeedEntry() {
		String requestBody = "method=delete";
		mockServer.expect(requestTo(fbUrl("123456_78901234")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken")).andExpect(content().string(requestBody))
				.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
		facebook.feedOperations().deletePost("123456_78901234");
		mockServer.verify();
	}

	@Test
	public void getCheckins() {
		mockServer.expect(requestTo(fbUrl("me/posts?offset=0&limit=25&with=location")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("checkins"), MediaType.APPLICATION_JSON));
		List<Post> checkins = facebook.feedOperations().getCheckins();
		assertCheckins(checkins);
	}

	@Test
	public void getCheckin() {
		mockServer.expect(requestTo(fbUrl("10150431253050580")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("checkin"), MediaType.APPLICATION_JSON));
		Post checkin = facebook.feedOperations().getCheckin("10150431253050580");
		assertSingleCheckin(checkin);		
	}

	@Test
	public void getCheckin_withStringLocation() {
		mockServer.expect(requestTo(fbUrl("10150431253050580")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("checkin-with-string-location"), MediaType.APPLICATION_JSON));
		Post checkin = facebook.feedOperations().getCheckin("10150431253050580");
		assertEquals("10150811140650580", checkin.getId());
		assertEquals("738140579", checkin.getFrom().getId());
		assertEquals("Craig Walls", checkin.getFrom().getName());
		Page place1 = checkin.getPlace();
		assertEquals("218213061560639", place1.getId());
		assertEquals("f8 HACK 2011", place1.getName());
		assertEquals("Facebook", checkin.getPlace().getLocation().getDescription());
		assertEquals("6628568379", checkin.getApplication().getId());
		assertEquals("Facebook for iPhone", checkin.getApplication().getName());
		assertEquals(toDate("2011-09-23T18:16:19+0000"), checkin.getCreatedTime());
	}


	private void assertSingleCheckin(Post checkin) {
		assertEquals("10150431253050580", checkin.getId());
		assertEquals("738140579", checkin.getFrom().getId());
		assertEquals("Craig Walls", checkin.getFrom().getName());
		Page place1 = checkin.getPlace();
		assertEquals("117372064948189", place1.getId());
		assertEquals("Freebirds World Burrito", place1.getName());
		assertEquals("238 W Campbell Rd", place1.getLocation().getStreet());
		assertEquals("Richardson", place1.getLocation().getCity());
		assertEquals("TX", place1.getLocation().getState());
		assertEquals("United States", place1.getLocation().getCountry());
		assertEquals("75080-3512", place1.getLocation().getZip());
		assertEquals(32.975537, place1.getLocation().getLatitude(), 0.0001);
		assertEquals(-96.722944, place1.getLocation().getLongitude(), 0.0001);
		assertEquals("6628568379", checkin.getApplication().getId());
		assertEquals("Facebook for iPhone", checkin.getApplication().getName());
		assertEquals(toDate("2011-03-13T01:00:49+0000"), checkin.getCreatedTime());
	}
	
	private void assertCheckins(List<Post> checkins) {
		assertEquals(2, checkins.size());
		assertSingleCheckin(checkins.get(0));
		Post checkin2 = checkins.get(1);
		assertEquals("10150140239512040", checkin2.getId());
		assertEquals("533477039", checkin2.getFrom().getId());
		assertEquals("Raymie Walls", checkin2.getFrom().getName());
		assertEquals(1, checkin2.getWithTags().size());
		assertEquals("738140579", checkin2.getWithTags().get(0).getId());
		assertEquals("Craig Walls", checkin2.getWithTags().get(0).getName());
		assertEquals("With my favorite people! ;-)", checkin2.getMessage());
		Page place2 = checkin2.getPlace();
		assertEquals("150366431753543", place2.getId());
		assertEquals("Somewhere", place2.getName());
		assertEquals(35.0231428, place2.getLocation().getLatitude(), 0.0001);
		assertEquals(-98.740305416667, place2.getLocation().getLongitude(), 0.0001);
		assertEquals("6628568379", checkin2.getApplication().getId());
		assertEquals("Facebook for iPhone", checkin2.getApplication().getName());
		assertEquals(toDate("2011-02-11T20:59:41+0000"), checkin2.getCreatedTime());
	}
	
	private void assertFeedEntries(List<Post> feed) {
		assertEquals(PostType.STATUS, feed.get(0).getType());
		assertEquals("100001387295207_160065090716400", feed.get(0).getId());
		assertEquals("Just trying something", feed.get(0).getMessage());
		assertEquals("100001387295207", feed.get(0).getFrom().getId());
		assertEquals("Art Names", feed.get(0).getFrom().getName());
		assertNull(feed.get(0).getApplication());
		assertEquals(2, feed.get(0).getActions().size());
		assertEquals("Comment", feed.get(0).getActions().get(0).getName());
		assertEquals("https://www.facebook.com/73579/posts/160065090716400", feed.get(0).getActions().get(0).getLink());
		assertEquals("Like", feed.get(0).getActions().get(1).getName());
		assertEquals("https://www.facebook.com/73579/posts/160065090716400", feed.get(0).getActions().get(1).getLink());
		assertEquals(2, feed.get(0).getProperties().size());
		assertEquals("By", feed.get(0).getProperties().get(0).getName());
		assertEquals("George", feed.get(0).getProperties().get(0).getText());
		assertEquals("https://www.facebook.com/george", feed.get(0).getProperties().get(0).getHref());
		assertEquals("Length", feed.get(0).getProperties().get(1).getName());
		assertEquals("1:23", feed.get(0).getProperties().get(1).getText());
		assertNull(feed.get(0).getProperties().get(1).getHref());
		assertEquals(PostType.PHOTO, feed.get(1).getType());
		assertEquals("100001387295207_160064384049804", feed.get(1).getId());
		assertEquals("Check out my ride", feed.get(1).getMessage());
		assertEquals("100001387295207", feed.get(1).getFrom().getId());
		assertEquals("Art Names", feed.get(1).getFrom().getName());
		assertNull(feed.get(1).getApplication());
		assertEquals(2, feed.get(1).getActions().size());
		assertEquals("Comment", feed.get(1).getActions().get(0).getName());
		assertEquals("https://www.facebook.com/73579/posts/160064377383138", feed.get(1).getActions().get(0).getLink());
		assertEquals("Like", feed.get(1).getActions().get(1).getName());
		assertEquals("https://www.facebook.com/73579/posts/160064377383138", feed.get(1).getActions().get(1).getLink());
		assertEquals(PostType.STATUS, feed.get(2).getType());
		assertEquals("100001387295207_153453231377586", feed.get(2).getId());
		assertEquals("Hello Facebook!", feed.get(2).getMessage());
		assertEquals("100001387295207", feed.get(2).getFrom().getId());
		assertEquals("Art Names", feed.get(2).getFrom().getName());
		assertEquals("162886103757745", feed.get(2).getApplication().getId());
		assertEquals("Spring Social Showcase", feed.get(2).getApplication().getName());
		assertEquals(2, feed.get(2).getActions().size());
		assertEquals("Comment", feed.get(2).getActions().get(0).getName());
		assertEquals("https://www.facebook.com/73579/posts/153453231377586", feed.get(2).getActions().get(0).getLink());
		assertEquals("Like", feed.get(2).getActions().get(1).getName());
		assertEquals("https://www.facebook.com/73579/posts/153453231377586", feed.get(2).getActions().get(1).getLink());
	}
	
	private void assertLinks(List<Post> feed) {
		assertEquals(2, feed.size());
		assertEquals(PostType.LINK, feed.get(0).getType());
		assertEquals("125736073702566", feed.get(0).getId());
		assertEquals("Warning about Facebook Phishing: See https://www.facebook.com/group.php?gid=9874388706", feed.get(0).getMessage());
		assertEquals("738140579", feed.get(0).getFrom().getId());
		assertEquals("Craig Walls", feed.get(0).getFrom().getName());
		assertEquals("https://profile.ak.fbcdn.net/hprofile-ak-snc4/50255_9874388706_6623_n.jpg", feed.get(0).getPicture());
		assertEquals("https://static.ak.fbcdn.net/rsrc.php/v1/yD/r/aS8ecmYRys0.gif", feed.get(0).getIcon());
		assertEquals("Facebook Phishing Scam Awareness", feed.get(0).getName());
		assertNull(feed.get(0).getDescription());
		assertNull(feed.get(0).getLink()); // sometimes links are in the message
		assertEquals(PostType.LINK, feed.get(1).getType());
		assertEquals("147264864601", feed.get(1).getId());
		assertEquals("Hey, let's go buy some furniture from the guy who's off his meds.", feed.get(1).getMessage());
		assertEquals("738140579", feed.get(1).getFrom().getId());
		assertEquals("Craig Walls", feed.get(1).getFrom().getName());
		assertEquals("https://i.ytimg.com/vi/QSLT2N-Ome8/2.jpg", feed.get(1).getPicture());
		assertEquals("https://static.ak.fbcdn.net/rsrc.php/v1/yD/r/aS8ecmYRys0.gif", feed.get(1).getIcon());
		assertEquals("Competition Beatdown Fail", feed.get(1).getName());
		assertEquals("What was this guy thinking?", feed.get(1).getDescription());
		assertEquals("https://www.youtube.com/watch?v=QSLT2N-Ome8", feed.get(1).getLink());
	}

	private void assertStatuses(List<Post> statuses) {
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

	private static final String[] ALL_POST_FIELDS = {
			"id", "actions", "admin_creator", "application", "caption", "created_time", "description", "from", "icon",
			"is_hidden", "is_published", "link", "message", "message_tags", "name", "object_id", "picture", "place", 
			"privacy", "properties", "source", "status_type", "story", "to", "type", "updated_time", "with_tags", "shares"
	};

	private static final String ALL_POST_FIELDS_STR = StringUtils.arrayToCommaDelimitedString(ALL_POST_FIELDS).replace(",", "%2C");
}
