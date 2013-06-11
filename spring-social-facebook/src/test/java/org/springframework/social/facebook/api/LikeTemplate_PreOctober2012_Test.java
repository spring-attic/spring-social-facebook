/*
 * Copyright 2012 the original author or authors.
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
import static org.springframework.test.web.client.match.RequestMatchers.*;
import static org.springframework.test.web.client.response.ResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.NotAuthorizedException;

/**
 * On 10/3/2012, Facebook changes the "picture" property on Page objects from a String to an object structure.
 * Prior to this change, in August 2012, Facebook rolled this change out, but giving each application the chance to opt-out until October 3.
 * This means that between August 2012 and October 2012, an application could be receiving either format.
 * This test exists to ensure that the old String-based "picture" property can still be deserialized for apps that have not yet enabled the breaking October 3rd changes.
 * This test can be removed after October 3, 2012.
 * @author habuma
 */
public class LikeTemplate_PreOctober2012_Test extends AbstractFacebookApiTest {
	
	@Test
	public void like() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));			
		facebook.likeOperations().like("123456");
		mockServer.verify();
	}
	
	@Test(expected = InsufficientPermissionException.class)
	public void like_objectAccessNotPermitted() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://graph.facebook.com/123456/likes"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withStatus(HttpStatus.FORBIDDEN).body(jsonResource("testdata/error-permission")).contentType(MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-references"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
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
			.andRespond(withSuccess(jsonResource("testdata/user-likes"), MediaType.APPLICATION_JSON));
		List<Page> likes = facebook.likeOperations().getInterests("123456789");
		assertLikes(likes);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getInterests_forSpecificUser_unauthorized() {
		unauthorizedFacebook.likeOperations().getInterests("123456789");
	}
	
	private void assertLikes(List<Page> likes) {
		assertEquals(3, likes.size());
		Page like1 = likes.get(0);
		assertEquals("5678046685", like1.getId());
		assertEquals("U2", like1.getName());
		assertEquals("Musician/band", like1.getCategory());
		assertEquals("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-snc4/277150_5678046685_1509018948_q.jpg", like1.getPicture());
		Page like2 = likes.get(1);
		assertEquals("113294925350820", like2.getId());
		assertEquals("Pirates of the Caribbean", like2.getName());
		assertEquals("Movie", like2.getCategory());
		assertEquals("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCawivbIbiha8dS&w=50&h=50&url=http\u00253A\u00252F\u00252Fupload.wikimedia.org\u00252Fwikipedia\u00252Fen\u00252F6\u00252F68\u00252FPiratesDVDs.jpg&crop&fallback=hub_movie&prefix=q", like2.getPicture());
		Page like3 = likes.get(2);
		assertEquals("10264922373", like3.getId());
		assertEquals("Freebirds World Burrito", like3.getName());
		assertEquals("Food/beverages", like3.getCategory());
		assertEquals("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/50427_10264922373_2128398611_q.jpg", like3.getPicture());
	}

}
