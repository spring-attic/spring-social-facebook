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

import java.util.List;

import org.junit.Test;
import org.springframework.social.NotAuthorizedException;

public class LikeTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void like() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse("{}", responseHeaders));
		facebook.likeOperations().like("123456");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void like_unauthorized() {
		unauthorizedFacebook.likeOperations().like("123456");
	}
	
	@Test
	public void unlike() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(body("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse("{}", responseHeaders));
		facebook.likeOperations().unlike("123456");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void unlike_unauthorized() {
		unauthorizedFacebook.likeOperations().unlike("123456");
	}
	
	@Test
	public void getLikes() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/likes")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getLikes();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getLikes_unauthorized() {
		unauthorizedFacebook.likeOperations().getLikes();
	}
	
	@Test
	public void getLikes_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/likes")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getLikes("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getLikes_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getLikes("123456789");
	}
	
	@Test
	public void getBooks() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/books")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getBooks();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getBooks_unauthorized() {
		unauthorizedFacebook.likeOperations().getBooks();
	}
	
	@Test
	public void getBooks_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/books")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getBooks("123456789");
		assertLikes(likes);
	}	
	
	@Test(expected = NotAuthorizedException.class)
	public void getBooks_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getBooks("123456789");
	}
	
	@Test
	public void getMovies() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/movies")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getMovies();
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getMovies_unauthorized() {
		unauthorizedFacebook.likeOperations().getMovies();
	}

	@Test
	public void getMovies_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/movies")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getMovies("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getMovies_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getMovies("123456789");
	}
	
	@Test
	public void getMusic() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/music")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getMusic();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getMusic_unauthorized() {
		unauthorizedFacebook.likeOperations().getMusic();
	}
	
	@Test
	public void getMusic_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/music")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getMusic("123456789");
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getMusic_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getMusic("123456789");
	}
	
	@Test
	public void getTelevision() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/television")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getTelevision();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getTelevision_unauthorized() {
		unauthorizedFacebook.likeOperations().getTelevision();
	}
	
	@Test
	public void getTelevision_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/television")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getTelevision("123456789");
		assertLikes(likes);
	}
		
	@Test(expected = NotAuthorizedException.class)
	public void getTelevision_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getTelevision("123456789");
	}
	
	@Test
	public void getActivities() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/activities")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getActivities();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getActivities_unauthorized() {
		unauthorizedFacebook.likeOperations().getActivities();
	}
	
	@Test
	public void getActivities_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/activities")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getActivities("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getActivities_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getActivities("123456789");
	}

	@Test
	public void getInterests() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/interests")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getInterests();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getInterests_unauthorized() {
		unauthorizedFacebook.likeOperations().getInterests();
	}

	@Test
	public void getInterests_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/interests")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<UserLike> likes = facebook.likeOperations().getInterests("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getInterests_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getInterests("123456789");
	}
	
	private void assertLikes(List<UserLike> likes) {
		assertEquals(3, likes.size());
		UserLike like1 = likes.get(0);
		assertEquals("113294925350820", like1.getId());
		assertEquals("Pirates of the Caribbean", like1.getName());
		assertEquals("Movie", like1.getCategory());
		UserLike like2 = likes.get(1);
		assertEquals("38073733123", like2.getId());
		assertEquals("Dublin Dr Pepper", like2.getName());
		assertEquals("Company", like2.getCategory());
		UserLike like3 = likes.get(2);
		assertEquals("10264922373", like3.getId());
		assertEquals("Freebirds World Burrito", like3.getName());
		assertEquals("Restaurant/cafe", like3.getCategory());
	}

}
