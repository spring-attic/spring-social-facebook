/*
 * Copyright 2011 the original author or authors.
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
import static org.springframework.social.test.client.RequestMatchers.*;
import static org.springframework.social.test.client.ResponseCreators.*;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorHandlingTest extends AbstractFacebookApiTest {

	@Test
	public void insufficientPrivileges() {
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/declined"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/error-insufficient-privilege"), responseHeaders, HttpStatus.FORBIDDEN, ""));
			facebook.eventOperations().declineInvitation("193482154020832");
			fail();
		} catch (InsufficientPermissionException e) {
			assertEquals("The operation requires 'rsvp_event' permission.", e.getMessage());
			assertEquals("rsvp_event", e.getRequiredPermission());
		}
	}
	
	@Test
	public void notAFriend() {
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/119297590579/members/100001387295207"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/error-not-a-friend"), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR, ""));
			facebook.friendOperations().addToFriendList("119297590579", "100001387295207");
			fail();
		} catch (NotAFriendException e) {
			assertEquals("The member must be a friend of the current user.", e.getMessage());
		}		
	}
	
	@Test
	public void unknownPath() {
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/me/boguspath"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/error-unknown-path"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
			facebook.fetchConnections("me", "boguspath", String.class);
			fail();
		} catch (ResourceNotFoundException e) {
			assertEquals("Unknown path components: /boguspath", e.getMessage());
		}
	}
	
	@Test
	public void notTheOwner() {
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/1234567890"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(body("method=delete"))
				.andRespond(withResponse(jsonResource("testdata/error-not-the-owner"), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR, ""));
			facebook.friendOperations().deleteFriendList("1234567890");
			fail();
		} catch (ResourceOwnershipException e) {
			assertEquals("User must be an owner of the friendlist", e.getMessage());
		}		
	}
	
	@Test
	public void unknownAlias_HTTP200() {
		// yes, Facebook really does return this error as HTTP 200 (probably should be 404)
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/dummyalias"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/error-unknown-alias"), responseHeaders, HttpStatus.OK, ""));
			facebook.fetchObject("dummyalias", FacebookProfile.class);
			fail("Expected GraphAPIException when fetching an unknown object alias");
		} catch (ResourceNotFoundException e) {
			assertEquals("(#803) Some of the aliases you requested do not exist: dummyalias", e.getMessage());
		}				
	}
	
	@Test(expected = MissingAuthorizationException.class)
	public void currentUser_noAccessToken() {
		FacebookTemplate facebook = new FacebookTemplate(); // use anonymous FacebookTemplate in this test
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-current-user-no-token"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.userOperations().getUserProfile();
	}
		
	@Test(expected=UncategorizedApiException.class)
	public void htmlErrorResponse() {
		try {
			FacebookTemplate facebook = new FacebookTemplate(); // use anonymous FacebookTemplate in this test
			MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
			mockServer.expect(requestTo("https://graph.facebook.com/123456/picture?type=normal"))
				.andExpect(method(GET))
				.andRespond(withResponse(new ClassPathResource("testdata/error-not-json.html", getClass()), responseHeaders, HttpStatus.BAD_REQUEST, ""));
			facebook.userOperations().getUserProfileImage("123456");
			fail("Expected UncategorizedApiException");
		} catch (UncategorizedApiException e) {
			assertTrue(e.getCause() instanceof HttpClientErrorException);
			throw e;
		}
	}
	
	@Test(expected = ExpiredAuthorizationException.class)
	public void tokenInvalid_tokenExpired() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-expired-token"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.userOperations().getUserProfile();
	}
	
	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_passwordChanged_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-password"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.userOperations().getUserProfile();
	}
	
	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_applicationDeauthorized_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-deauth"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.userOperations().getUserProfile();
	}

	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_signedOutOfFacebook_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-signout"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.userOperations().getUserProfile();
	}

	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_passwordChanged_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-password"), responseHeaders, HttpStatus.UNAUTHORIZED, ""));
		facebook.userOperations().getUserProfile();
	}
	
	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_applicationDeauthorized_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-deauth"), responseHeaders, HttpStatus.UNAUTHORIZED, ""));
		facebook.userOperations().getUserProfile();
	}

	@Test(expected = RevokedAuthorizationException.class)
	public void tokenInvalid_signedOutOfFacebook_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-token-signout"), responseHeaders, HttpStatus.UNAUTHORIZED, ""));
		facebook.userOperations().getUserProfile();
	}
	
	@Test(expected = OperationNotPermittedException.class)
	public void appDoesNotHaveCapability() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-app-capability"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.likeOperations().like("123456");
	}

	@Test(expected = OperationNotPermittedException.class)
	public void appMustBeOnWhitelist() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(body("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-whitelist"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.likeOperations().unlike("123456");
	}
	
	@Test(expected = OperationNotPermittedException.class)
	public void invalidObject_urlParameterError() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-url-parameter"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.likeOperations().like("123456");
	}

	@Test(expected = OperationNotPermittedException.class)
	public void invalidObject_invalidFbidError() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-invalid-fbid"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.likeOperations().like("123456");
	}
	
	@Test(expected = InsufficientPermissionException.class)
	public void falseResponse() {
		mockServer.expect(requestTo("https://graph.facebook.com/someobject"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse("false", responseHeaders, HttpStatus.OK, ""));
		facebook.fetchObject("someobject", FacebookProfile.class);
	}

	@Test(expected = RateLimitExceededException.class)
	public void rateLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed"))
			.andExpect(method(POST))
			.andExpect(body("message=Test+Message"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-rate-limit"), responseHeaders, HttpStatus.BAD_REQUEST, ""));
		facebook.feedOperations().updateStatus("Test Message");
	}

}
