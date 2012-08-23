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
import static org.springframework.test.web.client.RequestMatchers.*;
import static org.springframework.test.web.client.ResponseCreators.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.RevokedAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorHandlingTest extends AbstractFacebookApiTest {

	private static final String LOGGED_OUT_REVOKATION = "The authorization has been revoked. Reason: Error validating access token: The session is invalid because the user logged out.";
	private static final String NOT_AUTHORIZED_REVOKATION = "The authorization has been revoked. Reason: Error validating access token: 123456789 has not authorized application 987654321";

	@Test
	public void insufficientPrivileges() {
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/193482154020832/declined"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withStatus(HttpStatus.FORBIDDEN).body(jsonResource("testdata/error-insufficient-privilege")).contentType(MediaType.APPLICATION_JSON));
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
				.andRespond(withServerError().body(jsonResource("testdata/error-not-a-friend")).contentType(MediaType.APPLICATION_JSON));
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
				.andRespond(withBadRequest().body(jsonResource("testdata/error-unknown-path")).contentType(MediaType.APPLICATION_JSON));
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
				.andRespond(withServerError().body(jsonResource("testdata/error-not-the-owner")).contentType(MediaType.APPLICATION_JSON));
			facebook.friendOperations().deleteFriendList("1234567890");
			fail();
		} catch (ResourceOwnershipException e) {
			assertEquals("User must be an owner of the friendlist", e.getMessage());
		}		
	}
	
	@Test
	@Ignore("This doesn't seem to be true anymore...It looks like FB fixed their status code.")
	public void unknownAlias_HTTP200() {
		// yes, Facebook really does return this error as HTTP 200 (probably should be 404)
		try {
			mockServer.expect(requestTo("https://graph.facebook.com/dummyalias"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("testdata/error-unknown-alias"), MediaType.APPLICATION_JSON));
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
			.andRespond(withBadRequest().body(jsonResource("testdata/error-current-user-no-token")).contentType(MediaType.APPLICATION_JSON));
		facebook.userOperations().getUserProfile();
	}
		
	@Test(expected=UncategorizedApiException.class)
	public void htmlErrorResponse() {
		try {
			FacebookTemplate facebook = new FacebookTemplate(); // use anonymous FacebookTemplate in this test
			MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
			mockServer.expect(requestTo("https://graph.facebook.com/123456/picture?type=normal"))
				.andExpect(method(GET))
				.andRespond(withBadRequest().body(new ClassPathResource("testdata/error-not-json.html", getClass())).contentType(MediaType.TEXT_HTML));
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
			.andRespond(withBadRequest().body(jsonResource("testdata/error-expired-token")).contentType(MediaType.APPLICATION_JSON));
		facebook.userOperations().getUserProfile();
	}
	
	@Test
	public void tokenInvalid_passwordChanged_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))			
			.andRespond(withBadRequest().body(jsonResource("testdata/error-invalid-token-password")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(CHANGED_PASSWORD_REVOKATION, e.getMessage());
		}
	}
	
	@Test
	public void tokenInvalid_applicationDeauthorized_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-invalid-token-deauth")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(NOT_AUTHORIZED_REVOKATION, e.getMessage());
		}
	}

	@Test
	public void tokenInvalid_signedOutOfFacebook_badRequest() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-invalid-token-signout")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(LOGGED_OUT_REVOKATION, e.getMessage());
		}
	}

	@Test
	public void tokenInvalid_passwordChanged_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-invalid-token-password")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(CHANGED_PASSWORD_REVOKATION, e.getMessage());
		}
	}
	
	@Test
	public void tokenInvalid_applicationDeauthorized_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withStatus(HttpStatus.UNAUTHORIZED).body(jsonResource("testdata/error-invalid-token-deauth")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(NOT_AUTHORIZED_REVOKATION, e.getMessage());
		}
	}

	@Test
	public void tokenInvalid_signedOutOfFacebook_unauthorized() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
			.andExpect(method(GET))
			.andRespond(withStatus(HttpStatus.UNAUTHORIZED).body(jsonResource("testdata/error-invalid-token-signout")).contentType(MediaType.APPLICATION_JSON));
		try {
			facebook.userOperations().getUserProfile();
			fail("Expected RevokedAuthorizationException");
		} catch (RevokedAuthorizationException e) {
			assertEquals(LOGGED_OUT_REVOKATION, e.getMessage());
		}
	}
	
	@Test(expected = OperationNotPermittedException.class)
	public void appDoesNotHaveCapability() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-app-capability")).contentType(MediaType.APPLICATION_JSON));
		facebook.likeOperations().like("123456");
	}

	@Test(expected = OperationNotPermittedException.class)
	public void appMustBeOnWhitelist() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(body("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-whitelist")).contentType(MediaType.APPLICATION_JSON));
		facebook.likeOperations().unlike("123456");
	}
	
	@Test(expected = OperationNotPermittedException.class)
	public void invalidObject_urlParameterError() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-url-parameter")).contentType(MediaType.APPLICATION_JSON));
		facebook.likeOperations().like("123456");
	}

	@Test(expected = OperationNotPermittedException.class)
	public void invalidObject_invalidFbidError() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-invalid-fbid")).contentType(MediaType.APPLICATION_JSON));
		facebook.likeOperations().like("123456");
	}
	
	@Test(expected = InsufficientPermissionException.class)
	@Ignore("This doesn't seem to happen anymore...It looks like FB fixed their errors.")
	public void falseResponse() {
		mockServer.expect(requestTo("https://graph.facebook.com/someobject"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("false", MediaType.APPLICATION_JSON));
		facebook.fetchObject("someobject", FacebookProfile.class);
	}

	@Test(expected = RateLimitExceededException.class)
	public void rateLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/feed"))
			.andExpect(method(POST))
			.andExpect(body("message=Test+Message"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withBadRequest().body(jsonResource("testdata/error-rate-limit")).contentType(MediaType.APPLICATION_JSON));
		facebook.feedOperations().updateStatus("Test Message");
	}

	private static final String CHANGED_PASSWORD_REVOKATION = "The authorization has been revoked. Reason: Error validating access token: " +
			"The session has been invalidated because the user has changed the password.";

}
