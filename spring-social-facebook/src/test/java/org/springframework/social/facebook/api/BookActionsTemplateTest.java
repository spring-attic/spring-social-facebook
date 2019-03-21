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

public class BookActionsTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void readBook() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/book.reads")))
			.andExpect(method(POST))
			.andExpect(content().string("book=http%3A%2F%2Fsamples.ogp.me%2F226075010839791&progress%3Atimestamp=1378241299&progress%3Apercent_complete=3.1415927"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().bookActions().readBook("http://samples.ogp.me/226075010839791", 1378241299, 3.1415926535f);
		assertEquals("123456789080", actionId);
	}

	@Test
	public void quoteBook() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/books.quotes")))
			.andExpect(method(POST))
			.andExpect(content().string("book=http%3A%2F%2Fsamples.ogp.me%2F226075010839791&body=Test+quote"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().bookActions().quoteBook("http://samples.ogp.me/226075010839791", "Test quote");
		assertEquals("123456789080", actionId);
	}

	@Test
	public void rateBook() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/books.rates")))
			.andExpect(method(POST))
			.andExpect(content().string("book=http%3A%2F%2Fsamples.ogp.me%2F226075010839791&rating%3Avalue=3.5&rating%3Ascale=5"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().bookActions().rateBook("http://samples.ogp.me/226075010839791", 3.5f, 5);
		assertEquals("123456789080", actionId);
	}

	@Test
	public void wantsToRead() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/books.wants_to_read")))
			.andExpect(method(POST))
			.andExpect(content().string("book=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().bookActions().wantsToRead("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

}
