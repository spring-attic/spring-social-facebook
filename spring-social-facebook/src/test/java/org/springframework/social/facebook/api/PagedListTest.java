/*
 * Copyright 2002-2016 the original author or authors.
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
package org.springframework.social.facebook.api;

import java.util.List;

import org.junit.Test;

import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test class for the pagination workflow
 *
 * @author <a href="mailto:bouhanef.hamdi@gmail.com">Hamdi BOUHANEF</a>
 */
public class PagedListTest extends AbstractFacebookApiTest {

	private static final String FIELDS_PARAM = "&fields=id%2Cactions%2Cadmin_creator%2Capplication%2Ccaption%2Ccreated_time%2Cdescription%2Cfrom%2Cicon%2Cis_hidden%2Cis_published%2Clink%2Cmessage%2Cmessage_tags%2Cname%2Cobject_id%2Cpicture%2Cplace%2Cprivacy%2Cproperties%2Csource%2Cstatus_type%2Cstory%2Cto%2Ctype%2Cupdated_time%2Cwith_tags%2Cshares";

	@Test
	public void getFeedPaged() {
		mockServer.expect(requestTo(fbUrl("me/feed?limit=2" + FIELDS_PARAM)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("feedPage1"), MediaType.APPLICATION_JSON));
        final String uriFeeds = "100001387295207/feed?";
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage2Token" )))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("feedPage2"), MediaType.APPLICATION_JSON));
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage3Token")))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("feedPage3"), MediaType.APPLICATION_JSON));
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage4Token")))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("emptyFeed"), MediaType.APPLICATION_JSON));
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage2Token")))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("feedPage2"), MediaType.APPLICATION_JSON));
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage1Token")))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("feedPage1"), MediaType.APPLICATION_JSON));
		mockServer.expect(requestTo(fbUrl(uriFeeds +
				"format=json&limit=2&access_token=fakeToken&__paging_token=fakePage0Token")))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("emptyFeed"), MediaType.APPLICATION_JSON));
		PagingParameters pagedListMapram = new PagingParameters(2, null, null, null);

		//Next page
		PagedList<Post> feedsPage1 = facebook.feedOperations().getFeed(pagedListMapram);
		assertEquals(2, feedsPage1.size());
		assertFeedEntriesPage1(feedsPage1);
		final PagedList<Post> feedsPage2 = facebook.fetchNextPagedConnections(feedsPage1, Post.class);
		assertEquals(2, feedsPage2.size());
		assertFeedEntriesPage2(feedsPage2);
		final PagedList<Post> feedsPage3 = facebook.fetchNextPagedConnections(feedsPage2, Post.class);
		assertEquals(1, feedsPage3.size());
		assertFeedEntriesPage3(feedsPage3);
		final PagedList<Post> feedPage4 = facebook.fetchNextPagedConnections(feedsPage3, Post.class);
		assertEquals(0, feedPage4.size());

		//Previous page
		final PagedList<Post> feedsPreviousPage3 = facebook.fetchPreviousPagedConnections(feedsPage3, Post.class);
		assertEquals(2, feedsPreviousPage3.size());
		assertFeedEntriesPage2(feedsPreviousPage3);
		final PagedList<Post> feedsPreviousPage2 = facebook.fetchPreviousPagedConnections(feedsPage2, Post.class);
		assertEquals(2, feedsPreviousPage2.size());
		assertFeedEntriesPage1(feedsPreviousPage2);
		final PagedList<Post> feedsPreviousPage1 = facebook.fetchPreviousPagedConnections(feedsPage1, Post.class);
		assertEquals(0, feedsPreviousPage1.size());
	}

	private void assertFeedEntriesPage1(List<Post> feed) {
		assertEquals("100001387295207_160065090716400", feed.get(0).getId());
		assertEquals("100001387295207_160064384049804", feed.get(1).getId());
	}

	private void assertFeedEntriesPage2(List<Post> feed) {
		assertEquals("100001387295207_153453231377586", feed.get(0).getId());
		assertEquals("100001387295207_189394164450159", feed.get(1).getId());
	}

	private void assertFeedEntriesPage3(List<Post> feed) {
		assertEquals("100001387295207_189396471116595", feed.get(0).getId());
	}
}
