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

public class SocialContextTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void getMutualFriends() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28mutual_friends.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("mutual_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualFriends = facebook.socialContextOperations().getMutualFriends("12345");
		assertEquals(2, mutualFriends.size());
		assertEquals(177, mutualFriends.getTotalCount().intValue());
		Reference friend = mutualFriends.get(0);
		assertEquals("708598160", friend.getId());
		assertEquals("Josh Long", friend.getName());
		friend = mutualFriends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getMutualFriends_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28mutual_friends.limit%2810%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("mutual_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualFriends = facebook.socialContextOperations().getMutualFriends("12345", 10);
		assertEquals(2, mutualFriends.size());
		assertEquals(177, mutualFriends.getTotalCount().intValue());
		Reference friend = mutualFriends.get(0);
		assertEquals("708598160", friend.getId());
		assertEquals("Josh Long", friend.getName());
		friend = mutualFriends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getAllMutualFriends() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28all_mutual_friends.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("all_mutual_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualFriends = facebook.socialContextOperations().getAllMutualFriends("12345");
		assertEquals(2, mutualFriends.size());
		assertEquals(177, mutualFriends.getTotalCount().intValue());
		Reference friend = mutualFriends.get(0);
		assertEquals("708598160", friend.getId());
		assertEquals("Josh Long", friend.getName());
		friend = mutualFriends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getAllMutualFriends_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28all_mutual_friends.limit%2810%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("all_mutual_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualFriends = facebook.socialContextOperations().getAllMutualFriends("12345", 10);
		assertEquals(2, mutualFriends.size());
		assertEquals(177, mutualFriends.getTotalCount().intValue());
		Reference friend = mutualFriends.get(0);
		assertEquals("708598160", friend.getId());
		assertEquals("Josh Long", friend.getName());
		friend = mutualFriends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getMutualLikes() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28mutual_likes.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("mutual_likes"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualLikes = facebook.socialContextOperations().getMutualLikes("12345");
		assertEquals(2, mutualLikes.size());
		assertEquals(68, mutualLikes.getTotalCount().intValue());
		Reference like = mutualLikes.get(0);
		assertEquals("36643253738", like.getId());
		assertEquals("Mulch, Sweat, & Shears", like.getName());
		like = mutualLikes.get(1);
		assertEquals("188620089283", like.getId());
		assertEquals("Torchy's Tacos", like.getName());
		mockServer.verify();
	}

	@Test
	public void getMutualLikes_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28mutual_likes.limit%2810%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("mutual_likes"), MediaType.APPLICATION_JSON));
		CountedList<Reference> mutualLikes = facebook.socialContextOperations().getMutualLikes("12345", 10);
		assertEquals(2, mutualLikes.size());
		assertEquals(68, mutualLikes.getTotalCount().intValue());
		Reference like = mutualLikes.get(0);
		assertEquals("36643253738", like.getId());
		assertEquals("Mulch, Sweat, & Shears", like.getName());
		like = mutualLikes.get(1);
		assertEquals("188620089283", like.getId());
		assertEquals("Torchy's Tacos", like.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsUsingApp() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_using_app.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_using_app"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsUsingApp("12345");
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsUsingApp_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_using_app.limit%2815%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_using_app"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsUsingApp("12345", 15);
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoLike() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_who_like.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_who_like"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoLike("12345");
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoLike_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_who_like.limit%2815%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_who_like"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoLike("12345", 15);
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoWatched() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28video_watch_friends.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("video_watch_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoWatched("12345");
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoWatched_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28video_watch_friends.limit%2815%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("video_watch_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoWatched("12345", 15);
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoListenTo() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28music_listen_friends.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("music_listen_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoListenTo("12345");
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsWhoListenTo_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28music_listen_friends.limit%2815%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("music_listen_friends"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsWhoListenTo("12345", 15);
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsTaggedAt() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_tagged_at.limit%2825%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_tagged_at"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsTaggedAt("12345");
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

	@Test
	public void getFriendsTaggedAt_withLimit() {
		mockServer.expect(requestTo(fbUrl("12345?fields=context.fields%28friends_tagged_at.limit%2815%29%29")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends_tagged_at"), MediaType.APPLICATION_JSON));
		CountedList<Reference> friends = facebook.socialContextOperations().getFriendsTaggedAt("12345", 15);
		assertEquals(2, friends.size());
		assertEquals(7, friends.getTotalCount().intValue());
		Reference friend = friends.get(0);
		assertEquals("726452090", friend.getId());
		assertEquals("Guillaume Laforge", friend.getName());
		friend = friends.get(1);
		assertEquals("1255689239", friend.getId());
		assertEquals("Keith Donald", friend.getName());
		mockServer.verify();
	}

}
