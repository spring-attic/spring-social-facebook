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

public class MusicActionsTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void listenToSong() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.listens")))
			.andExpect(method(POST))
			.andExpect(content().string("song=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().listenToSong("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

	@Test
	public void listenToAlbum() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.listens")))
			.andExpect(method(POST))
			.andExpect(content().string("album=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().listenToAlbum("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}
	
	@Test
	public void listenToRadioStation() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.listens")))
			.andExpect(method(POST))
			.andExpect(content().string("radio_station=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().listenToRadioStation("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

	@Test
	public void listenToMusician() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.listens")))
			.andExpect(method(POST))
			.andExpect(content().string("musician=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().listenToMusician("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}
	
	@Test
	public void listenToPlaylist() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.listens")))
			.andExpect(method(POST))
			.andExpect(content().string("playlist=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().listenToPlaylist("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

	@Test
	public void createPlaylist() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/music.playlists")))
			.andExpect(method(POST))
			.andExpect(content().string("playlist=http%3A%2F%2Fsamples.ogp.me%2F226075010839791"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\": \"123456789080\"}", MediaType.APPLICATION_JSON));
		
		String actionId = facebook.openGraphOperations().musicActions().createPlaylist("http://samples.ogp.me/226075010839791");
		assertEquals("123456789080", actionId);
	}

}
