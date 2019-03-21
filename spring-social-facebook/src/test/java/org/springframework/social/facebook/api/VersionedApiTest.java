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
package org.springframework.social.facebook.api;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

/*
 * This is a quick test to verify that setting the API version on a FacebookTemplate does, in fact, influence the URL
 * used to access the Graph API. It is intended to be temporary until time allows for a more extensive suite of tests
 * around various versions of the Graph API.
 */
public class VersionedApiTest extends AbstractFacebookApiTest {

	@Test
	public void testVersionedFacebookTemplate() {
		
		facebook.setApiVersion("2.2");
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/feed?limit=25&fields=" + ALL_POST_FIELDS_STR))
				  .andExpect(method(GET))
				  .andExpect(header("Authorization", "OAuth someAccessToken"))
				  .andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));

		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(5, feed.size());
	}
	
	@Test
	public void testUnversionedFacebookTemplate() {
		
		facebook.setApiVersion(null);
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed?limit=25&fields=" + ALL_POST_FIELDS_STR))
				  .andExpect(method(GET))
				  .andExpect(header("Authorization", "OAuth someAccessToken"))
				  .andRespond(withSuccess(jsonResource("feed"), MediaType.APPLICATION_JSON));

		List<Post> feed = facebook.feedOperations().getFeed();
		assertEquals(5, feed.size());
	}

	
	private static final String[] ALL_POST_FIELDS = {
			"id", "actions", "admin_creator", "application", "caption", "created_time", "description", "from", "icon",
			"is_hidden", "is_published", "link", "message", "message_tags", "name", "object_id", "picture", "place", 
			"privacy", "properties", "source", "status_type", "story", "to", "type", "updated_time", "with_tags", "shares"
	};

	private static final String ALL_POST_FIELDS_STR = StringUtils.arrayToCommaDelimitedString(ALL_POST_FIELDS).replace(",", "%2C");

}
