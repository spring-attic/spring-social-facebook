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

import org.junit.Test;
import org.springframework.http.MediaType;

public class GeneralActionTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void like() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/og.likes")))
			.andExpect(method(POST))
			.andExpect(content().string("object=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().generalActions().like("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

	@Test
	public void like_withPrivacy() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/og.likes")))
			.andExpect(method(POST))
			.andExpect(content().string("privacy=%7B%22value%22%3A%22SELF%22%7D&object=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().generalActions().like("http://samples.ogp.me/226075010839791", new ActionMetadata().privacy("SELF"));
		assertEquals("123456789080", actionId);
	}

	@Test
	public void follow() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/og.follows")))
			.andExpect(method(POST))
			.andExpect(content().string("profile=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().generalActions().follow("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}
	
}
