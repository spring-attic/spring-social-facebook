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

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Craig Walls
 */
public class TestUserTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void post_message() throws Exception {
		String requestBody = "installed=true&name=Jack+Sparrow&permissions=publish_actions";
		mockServer.expect(requestTo(fbUrl("APP_ID/accounts/test-users")))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"12345\",\"email\":\"jack@sparrow.com\",\"password\":\"password\",\"access_token\":\"ACCESS_TOKEN\",\"login_url\":\"LOGIN_URL\"}", MediaType.APPLICATION_JSON));
		
		TestUser testUser = facebook.testUserOperations().createTestUser(true, "publish_actions", "Jack Sparrow");
		assertEquals("12345", testUser.getId());
		assertEquals("jack@sparrow.com", testUser.getEmail());
		assertEquals("password", testUser.getPassword());
		assertEquals("ACCESS_TOKEN", testUser.getAccessToken());
		assertEquals("LOGIN_URL", testUser.getLoginUrl());
		mockServer.verify();
	}
	
	// id, email, password, access_token, login_url

	@Test
	public void get_test_user_list() throws Exception {
		String responseBody = "{\"data\":[{\"id\":\"101234413904069\",\"login_url\":\"LOGIN_URL\",\"access_token\":\"ACCESS_TOKEN\"}],\"paging\":{\"cursors\":{\"before\":\"MTAxMjM0NDEzOTA0MDY5\",\"after\":\"MTA1MDE2MjcwMTg3ODMz\"}}}";
		mockServer.expect(requestTo(fbUrl("APP_ID/accounts/test-users")))
				  .andExpect(method(GET))
				  .andExpect(header("Authorization", "OAuth someAccessToken"))
				  .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		List<TestUser> testUserList = facebook.testUserOperations().getTestUsers();
		assertTrue(testUserList.size() == 1);
		TestUser testUser= testUserList.get(0);
		assertEquals("101234413904069", testUser.getId());
		assertEquals("ACCESS_TOKEN", testUser.getAccessToken());
		assertEquals("LOGIN_URL", testUser.getLoginUrl());
		mockServer.verify();
	}

}
