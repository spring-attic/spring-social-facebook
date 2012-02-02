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

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.social.InsufficientPermissionException;
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
	
	@Test(expected = InsufficientPermissionException.class)
	public void like_objectAccessNotPermitted() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/error-permission"), responseHeaders, HttpStatus.FORBIDDEN, ""));
		facebook.likeOperations().like("123456");
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
		mockServer.expect(requestTo("https://graph.facebook.com/12345678/likes")).andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withResponse(jsonResource("testdata/user-references"), responseHeaders));
		List<Reference> likes = facebook.likeOperations().getLikes("12345678");
		assertEquals(3, likes.size());
		assertEquals("Michael Scott", likes.get(0).getName());
		assertEquals("100000737708615", likes.get(0).getId());
		assertEquals("Michael Scott", likes.get(1).getName());
		assertEquals("100000354483321", likes.get(1).getId());
		assertEquals("Michael Scott", likes.get(2).getName());
		assertEquals("1184963857", likes.get(2).getId());
	}
	
	@Test
	public void getPagesLiked() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/likes?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getPagesLiked();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getPagesLiked_unauthorized() {
		unauthorizedFacebook.likeOperations().getPagesLiked();
	}
	
	@Test
	public void getPagesLiked_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/likes?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getPagesLiked("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getLikes_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getPagesLiked("123456789");
	}
	
	@Test
	public void getBooks() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/books?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getBooks();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getBooks_unauthorized() {
		unauthorizedFacebook.likeOperations().getBooks();
	}
	
	@Test
	public void getBooks_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/books?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getBooks("123456789");
		assertLikes(likes);
	}	
	
	@Test(expected = NotAuthorizedException.class)
	public void getBooks_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getBooks("123456789");
	}
	
	@Test
	public void getMovies() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/movies?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getMovies();
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getMovies_unauthorized() {
		unauthorizedFacebook.likeOperations().getMovies();
	}

	@Test
	public void getMovies_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/movies?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getMovies("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getMovies_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getMovies("123456789");
	}
	
	@Test
	public void getMusic() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/music?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getMusic();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getMusic_unauthorized() {
		unauthorizedFacebook.likeOperations().getMusic();
	}
	
	@Test
	public void getMusic_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/music?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getMusic("123456789");
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getMusic_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getMusic("123456789");
	}
	
	@Test
	public void getTelevision() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/television?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getTelevision();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getTelevision_unauthorized() {
		unauthorizedFacebook.likeOperations().getTelevision();
	}
	
	@Test
	public void getTelevision_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/television?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getTelevision("123456789");
		assertLikes(likes);
	}
		
	@Test(expected = NotAuthorizedException.class)
	public void getTelevision_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getTelevision("123456789");
	}
	
	@Test
	public void getActivities() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/activities?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getActivities();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getActivities_unauthorized() {
		unauthorizedFacebook.likeOperations().getActivities();
	}
	
	@Test
	public void getActivities_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/activities?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getActivities("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getActivities_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getActivities("123456789");
	}

	@Test
	public void getInterests() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/interests?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getInterests();
		assertLikes(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getInterests_unauthorized() {
		unauthorizedFacebook.likeOperations().getInterests();
	}

	@Test
	public void getInterests_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/interests?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/user-likes"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getInterests("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getInterests_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getInterests("123456789");
	}

	@Test
	public void getGames() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/games?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/games"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getGames();
		assertGames(likes);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getGames_unauthorized() {
		unauthorizedFacebook.likeOperations().getGames();
	}

	@Test
	public void getGames_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789/games?fields=id%2Cname%2Ccategory%2Cdescription%2Clocation%2Cwebsite%2Cpicture%2Cphone%2Caffiliation%2Ccompany_overview%2Clikes%2Ccheckins")).andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withResponse(jsonResource("testdata/games"), responseHeaders));
		List<Page> likes = facebook.likeOperations().getGames("123456789");
		assertGames(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getGames_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getGames("123456789");
	}
		
	private void assertLikes(List<Page> likes) {
		assertEquals(3, likes.size());
		Page like1 = likes.get(0);
		assertEquals("113294925350820", like1.getId());
		assertEquals("Pirates of the Caribbean", like1.getName());
		assertEquals("Movie", like1.getCategory());
		Page like2 = likes.get(1);
		assertEquals("38073733123", like2.getId());
		assertEquals("Dublin Dr Pepper", like2.getName());
		assertEquals("Company", like2.getCategory());
		Page like3 = likes.get(2);
		assertEquals("10264922373", like3.getId());
		assertEquals("Freebirds World Burrito", like3.getName());
		assertEquals("Restaurant/cafe", like3.getCategory());
	}

	private void assertGames(List<Page> games) {
		assertEquals(2, games.size());
		Page game1 = games.get(0);
		assertEquals("113744711969537", game1.getId());
		assertEquals("Super Mario Stadium Baseball", game1.getName());
		assertEquals("Games/toys", game1.getCategory());
		Page game2 = games.get(1);
		assertEquals("128165803879512", game2.getId());
		assertEquals("Disney Epic Mickey Video Game", game2.getName());
		assertEquals("Games/toys", game2.getCategory());

	}

}
