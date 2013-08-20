/*
 * Copyright 2013 the original author or authors.
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
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;

public class FriendTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void getFriendLists() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friendlists"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friend-lists"), MediaType.APPLICATION_JSON));
		List<Reference> friendLists = facebook.friendOperations().getFriendLists();
		assertFriendLists(friendLists);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFriendLists_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendLists();
	}

	@Test
	public void getFriendLists_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/11223344/friendlists"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friend-lists"), MediaType.APPLICATION_JSON));
		List<Reference> friendLists = facebook.friendOperations().getFriendLists("11223344");
		assertFriendLists(friendLists);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getFriendLists_forSpecificUser_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendLists("11223344");
	}

	@Test
	public void getFriendList() {
		mockServer.expect(requestTo("https://graph.facebook.com/11929590579"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friend-list"), MediaType.APPLICATION_JSON));
		Reference friendList = facebook.friendOperations().getFriendList("11929590579");
		assertEquals("11929590579", friendList.getId());
		assertEquals("High School Friends", friendList.getName());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFriendList_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendList("11929590579");
	}

	@Test
	public void getFriendListMembers() {
		mockServer.expect(requestTo("https://graph.facebook.com/192837465/members"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends"), MediaType.APPLICATION_JSON));
		List<Reference> members = facebook.friendOperations().getFriendListMembers("192837465");
		assertFriends(members);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getFriendListMembers_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendListMembers("192837465");
	}
	
	@Test
	public void createFriendList() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friendlists"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andExpect(content().string("name=My+List"))
			.andRespond(withSuccess(jsonResource("create-friend-list"), MediaType.APPLICATION_JSON));
		String friendListId = facebook.friendOperations().createFriendList("My List");
		assertEquals("11929590579", friendListId);
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void createFriendList_unauthorized() {
		unauthorizedFacebook.friendOperations().createFriendList("My List");
	}

	@Test
	public void deleteFriendList() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456"))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		facebook.friendOperations().deleteFriendList("123456");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteFriendList_unauthorized() {
		unauthorizedFacebook.friendOperations().deleteFriendList("123456");
	}

	@Test
	public void addToFriendList() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/members/7890123"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.friendOperations().addToFriendList("123456", "7890123");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void addToFriendList_unauthorized() {
		unauthorizedFacebook.friendOperations().addToFriendList("123456", "7890123");
	}

	@Test
	public void removeFromFriendList() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456/members/7890123"))
			.andExpect(method(DELETE))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.friendOperations().removeFromFriendList("123456", "7890123");
		mockServer.verify();		
	}

	@Test(expected = NotAuthorizedException.class)
	public void removeFromFriendList_unauthorized() {
		unauthorizedFacebook.friendOperations().removeFromFriendList("123456", "7890123");
	}

	@Test
	public void getFriends() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friends"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends"), MediaType.APPLICATION_JSON));
		List<Reference> friends = facebook.friendOperations().getFriends();
		assertFriends(friends);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFriends_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriends();
	}

	@Test
	public void getFriends_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/912873465/friends"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends"), MediaType.APPLICATION_JSON));
		List<Reference> friends = facebook.friendOperations().getFriends("912873465");
		assertFriends(friends);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFriends_forSpecificUser_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriends("912873465");
	}

	@Test
	public void getFriendIds() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friends?fields=id"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friend-ids"), MediaType.APPLICATION_JSON));
		List<String> friendIds = facebook.friendOperations().getFriendIds();
		assertFriendIds(friendIds);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFriendIds_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendIds();
	}

	@Test
	public void getFriendIds_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/912873465/friends?fields=id"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friend-ids"), MediaType.APPLICATION_JSON));
		// TODO: Come up with a better set of representative test data
		List<String> friendIds = facebook.friendOperations().getFriendIds("912873465");
		assertFriendIds(friendIds);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getFriendIds_forSpecificUser_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendIds("912873465");
	}

	@Test
	public void getFriendProfiles() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friends?fields=id%2Cusername%2Cname%2Cfirst_name%2Clast_name%2Cgender%2Clocale%2Ceducation%2Cwork%2Cemail%2Cthird_party_id%2Clink%2Ctimezone%2Cupdated_time%2Cverified%2Cabout%2Cbio%2Cbirthday%2Clocation%2Chometown%2Cinterested_in%2Creligion%2Cpolitical%2Cquotes%2Crelationship_status%2Csignificant_other%2Cwebsite"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-profiles"), MediaType.APPLICATION_JSON));
		List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
		assertFriendProfiles(friends);
	}

	@Test
	public void getFriendProfiles_withOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/friends?offset=100&limit=150&fields=id%2Cusername%2Cname%2Cfirst_name%2Clast_name%2Cgender%2Clocale%2Ceducation%2Cwork%2Cemail%2Cthird_party_id%2Clink%2Ctimezone%2Cupdated_time%2Cverified%2Cabout%2Cbio%2Cbirthday%2Clocation%2Chometown%2Cinterested_in%2Creligion%2Cpolitical%2Cquotes%2Crelationship_status%2Csignificant_other%2Cwebsite"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-profiles"), MediaType.APPLICATION_JSON));
		List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles(100, 150);
		assertFriendProfiles(friends);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getFriendProfiles_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendProfiles();
	}

	@Test
	public void getFriendProfiles_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/1234567/friends?fields=id%2Cusername%2Cname%2Cfirst_name%2Clast_name%2Cgender%2Clocale%2Ceducation%2Cwork%2Cemail%2Cthird_party_id%2Clink%2Ctimezone%2Cupdated_time%2Cverified%2Cabout%2Cbio%2Cbirthday%2Clocation%2Chometown%2Cinterested_in%2Creligion%2Cpolitical%2Cquotes%2Crelationship_status%2Csignificant_other%2Cwebsite"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-profiles"), MediaType.APPLICATION_JSON));
		List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles("1234567");
		assertFriendProfiles(friends);
	}

	@Test
	public void getFriendProfiles_forSpecificUserWithOffsetAndLimit() {
		mockServer.expect(requestTo("https://graph.facebook.com/1234567/friends?offset=100&limit=150&fields=id%2Cusername%2Cname%2Cfirst_name%2Clast_name%2Cgender%2Clocale%2Ceducation%2Cwork%2Cemail%2Cthird_party_id%2Clink%2Ctimezone%2Cupdated_time%2Cverified%2Cabout%2Cbio%2Cbirthday%2Clocation%2Chometown%2Cinterested_in%2Creligion%2Cpolitical%2Cquotes%2Crelationship_status%2Csignificant_other%2Cwebsite"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-profiles"), MediaType.APPLICATION_JSON));
		List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles("1234567", 100, 150);
		assertFriendProfiles(friends);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getFriendProfiles_forSpecificUser_unauthorized() {
		unauthorizedFacebook.friendOperations().getFriendProfiles("912873465");
	}
	
	@Test
	public void getFamily() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/family"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("family"), MediaType.APPLICATION_JSON));
		List<FamilyMember> family = facebook.friendOperations().getFamily();
		assertFamilyMembers(family);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFamily_unauthorized() {
		unauthorizedFacebook.friendOperations().getFamily();
	}

	@Test
	public void getFamily_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/12345678900/family"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("family"), MediaType.APPLICATION_JSON));
		List<FamilyMember> family = facebook.friendOperations().getFamily("12345678900");
		assertFamilyMembers(family);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getFamily_forSpecificUser_unauthorized() {
		unauthorizedFacebook.friendOperations().getFamily("12345678900");
	}
	
	@Test
	public void getMutualFriends() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/mutualfriends?user=12345678900"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("friends"), MediaType.APPLICATION_JSON));
		List<Reference> mutualFriends = facebook.friendOperations().getMutualFriends("12345678900");
		assertFriends(mutualFriends);		
	}

	@Test(expected = NotAuthorizedException.class)
	public void getMutualFriends_unauthorized() {
		unauthorizedFacebook.friendOperations().getMutualFriends("12345678900");
	}
	
	@Test
	@Ignore("Graph API doesn't seem to support this for any user other than /me.")
	public void getMutualFriends_forSpecificUser() {
//		mockServer.expect(requestTo("https://graph.facebook.com/9876543210/mutualfriends?user=12345678900"))
//			.andExpect(method(GET))
//			.andExpect(header("Authorization", "OAuth someAccessToken"))
//			.andRespond(withResponse(jsonResource("friends"), responseHeaders));
//		List<Reference> mutualFriends = facebook.friendOperations().getMutualFriends("9876543210", "12345678900");
//		assertFriends(mutualFriends);		
	}
	
	@Test
	public void getSubscribedTo() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/subscribedTo"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("subscribe-list"), MediaType.APPLICATION_JSON));
		List<Reference> subscriptions = facebook.friendOperations().getSubscribedTo();
		assertEquals(2, subscriptions.size());
		assertEquals("4", subscriptions.get(0).getId());
		assertEquals("Mark Zuckerberg", subscriptions.get(0).getName());
		assertEquals("1075423140", subscriptions.get(1).getId());
		assertEquals("Rod Johnson", subscriptions.get(1).getName());
	}

	@Test
	public void getSubscribedTo_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/9876543210/subscribedTo"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("subscribe-list"), MediaType.APPLICATION_JSON));
		List<Reference> subscriptions = facebook.friendOperations().getSubscribedTo("9876543210");
		assertEquals(2, subscriptions.size());
		assertEquals("4", subscriptions.get(0).getId());
		assertEquals("Mark Zuckerberg", subscriptions.get(0).getName());
		assertEquals("1075423140", subscriptions.get(1).getId());
		assertEquals("Rod Johnson", subscriptions.get(1).getName());
	}

	@Test
	public void getSubscribers() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/subscribers"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("subscribe-list"), MediaType.APPLICATION_JSON));
		List<Reference> subscriptions = facebook.friendOperations().getSubscribers();
		assertEquals(2, subscriptions.size());
		assertEquals("4", subscriptions.get(0).getId());
		assertEquals("Mark Zuckerberg", subscriptions.get(0).getName());
		assertEquals("1075423140", subscriptions.get(1).getId());
		assertEquals("Rod Johnson", subscriptions.get(1).getName());
	}

	@Test
	public void getSubscribers_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/9876543210/subscribers"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("subscribe-list"), MediaType.APPLICATION_JSON));
		List<Reference> subscriptions = facebook.friendOperations().getSubscribers("9876543210");
		assertEquals(2, subscriptions.size());
		assertEquals("4", subscriptions.get(0).getId());
		assertEquals("Mark Zuckerberg", subscriptions.get(0).getName());
		assertEquals("1075423140", subscriptions.get(1).getId());
		assertEquals("Rod Johnson", subscriptions.get(1).getName());
	}

	private void assertFriends(List<Reference> friends) {
		assertEquals(3, friends.size());
		assertEquals("12345", friends.get(0).getId());
		assertEquals("Roy Clarkson", friends.get(0).getName());
		assertEquals("67890", friends.get(1).getId());
		assertEquals("Keith Donald", friends.get(1).getName());
		assertEquals("24680", friends.get(2).getId());
		assertEquals("Rod Johnson", friends.get(2).getName());
	}

	private void assertFriendLists(List<Reference> friendLists) {
		assertEquals(3, friendLists.size());
		assertEquals("11929590579", friendLists.get(0).getId());
		assertEquals("High School Friends", friendLists.get(0).getName());
		assertEquals("7770595579", friendLists.get(1).getId());
		assertEquals("Family", friendLists.get(1).getName());
		assertEquals("7716889379", friendLists.get(2).getId());
		assertEquals("College Friends", friendLists.get(2).getName());
	}

	private void assertFriendIds(List<String> friendIds) {
		assertEquals(3, friendIds.size());
		assertEquals("7918522", friendIds.get(0));
		assertEquals("149000307", friendIds.get(1));
		assertEquals("151101314", friendIds.get(2));
	}

	private void assertFriendProfiles(List<FacebookProfile> friends) {
		// TODO assert friend profiles		
	}

	private void assertFamilyMembers(List<FamilyMember> family) {
		assertEquals(5, family.size());
		FamilyMember member1 = family.get(0);
		assertEquals("12345678901", member1.getId());
		assertEquals("Homer Simpson", member1.getName());
		assertEquals("father", member1.getRelationship());
		FamilyMember member2 = family.get(1);
		assertEquals("12345678902", member2.getId());
		assertEquals("Marge Simpson", member2.getName());
		assertEquals("mother", member2.getRelationship());
		FamilyMember member3 = family.get(2);
		assertEquals("12345678903", member3.getId());
		assertEquals("Lisa Simpson", member3.getName());
		assertEquals("sister", member3.getRelationship());
		FamilyMember member4 = family.get(3);
		assertEquals("12345678904", member4.getId());
		assertEquals("Maggie Simpson", member4.getName());
		assertEquals("sister", member4.getRelationship());
		FamilyMember member5 = family.get(4);
		assertEquals("12345678905", member5.getId());
		assertEquals("Abraham Simpson", member5.getName());
		assertEquals("grandfather", member5.getRelationship());
	}

}
