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
import java.util.Locale;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;

/**
 * @author Craig Walls
 */
public class UserTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	public void getUserProfile_currentUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/me"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("full-profile"), MediaType.APPLICATION_JSON));

		FacebookProfile profile = facebook.userOperations().getUserProfile();
		assertBasicProfileData(profile, true);
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("http://www.facebook.com/habuma", profile.getLink());
		assertEquals("xyz123abc987", profile.getThirdPartyId());
		assertEquals(Float.valueOf(-5), profile.getTimezone());  // should be -6 ???
		assertEquals(toDate("2010-08-22T00:01:59+0000"), profile.getUpdatedTime());
		assertTrue(profile.isVerified());
		assertEquals("Just some dude", profile.getAbout());
		assertEquals("I was born at a very early age.", profile.getBio());
		assertEquals("06/09/1971", profile.getBirthday());
		assertEquals("111762725508574", profile.getLocation().getId());
		assertEquals("Dallas, Texas", profile.getLocation().getName());
		assertEquals("107925612568471", profile.getHometown().getId());
		assertEquals("Plano, Texas", profile.getHometown().getName());
		assertEquals(1, profile.getInterestedIn().size());
		assertEquals("female", profile.getInterestedIn().get(0));
		assertEquals("Jedi", profile.getReligion());
		assertEquals("Galactic Republic", profile.getPolitical());
		assertEquals("\"May the force be with you.\" - Common Jedi greeting", profile.getQuotes());
		assertEquals("Married", profile.getRelationshipStatus());
		assertEquals("533477039", profile.getSignificantOther().getId());
		assertEquals("Raymie Walls", profile.getSignificantOther().getName());
		assertEquals("http://www.habuma.com", profile.getWebsite());
		assertEquals(3, profile.getInspirationalPeople().size());
		assertEquals("121966051173827", profile.getInspirationalPeople().get(0).getId());
		assertEquals("Homer Simpson", profile.getInspirationalPeople().get(0).getName());
		assertEquals("44596990399", profile.getInspirationalPeople().get(1).getId());
		assertEquals("Alice Cooper", profile.getInspirationalPeople().get(1).getName());
		assertEquals("56368119740", profile.getInspirationalPeople().get(2).getId());
		assertEquals("Captain Jack Sparrow", profile.getInspirationalPeople().get(2).getName());
		assertEquals(2, profile.getLanguages().size());
		assertEquals("106059522759137", profile.getLanguages().get(0).getId());
		assertEquals("English", profile.getLanguages().get(0).getName());
		assertEquals("113599388650247", profile.getLanguages().get(1).getId());
		assertEquals("Klingon", profile.getLanguages().get(1).getName());
		assertEquals(1, profile.getSports().size());
		assertEquals("114371035246890", profile.getSports().get(0).getId());
		assertEquals("Ping Pong", profile.getSports().get(0).getName());
		assertEquals(3, profile.getFavoriteTeams().size());
		assertEquals("37152881613", profile.getFavoriteTeams().get(0).getId());
		assertEquals("Chicago Bulls", profile.getFavoriteTeams().get(0).getName());
		assertEquals("159957123994", profile.getFavoriteTeams().get(1).getId());
		assertEquals("Oklahoma City Thunder", profile.getFavoriteTeams().get(1).getName());
		assertEquals("92774416228", profile.getFavoriteTeams().get(2).getId());
		assertEquals("Baltimore Ravens", profile.getFavoriteTeams().get(2).getName());
		assertEquals(3, profile.getFavoriteAtheletes().size());
		assertEquals("107670255929059", profile.getFavoriteAtheletes().get(0).getId());
		assertEquals("Emmitt Smith", profile.getFavoriteAtheletes().get(0).getName());
		assertEquals("108193242541968", profile.getFavoriteAtheletes().get(1).getId());
		assertEquals("Cal Ripken, Jr.", profile.getFavoriteAtheletes().get(1).getName());
		assertEquals("62975399193", profile.getFavoriteAtheletes().get(2).getId());
		assertEquals("Michael Jordan", profile.getFavoriteAtheletes().get(2).getName());
		assertWorkHistory(profile.getWork());
		assertEducationHistory(profile.getEducation());
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getUserProfile_currentUser_unauthorized() {
		unauthorizedFacebook.userOperations().getUserProfile();
	}

	@Test
	public void getUserProfile_specificUserByUserId() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile"), MediaType.APPLICATION_JSON));

		FacebookProfile profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, true);
	}

	@Test
	public void getUserProfile_specificUserByUserId_noMiddleName() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-no-middle-name"), MediaType.APPLICATION_JSON));

		FacebookProfile profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, false);
	}
	
	@Test
	public void getUserProfile_specificUserByUserId_withRealTimezone() {
		mockServer.expect(requestTo("https://graph.facebook.com/123456789"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-timezone"), MediaType.APPLICATION_JSON));

		FacebookProfile profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, true);
		assertEquals(Float.valueOf("-4.5"), profile.getTimezone()); 
	}

	@Test
	public void getUserProfileImage() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/picture?type=normal"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage();
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getUserProfileImage_currentUser_unauthorized() {
		unauthorizedFacebook.userOperations().getUserProfileImage();
	}

	@Test
	public void getUserProfileImage_specificUserByUserId() {
		mockServer.expect(requestTo("https://graph.facebook.com/1234567/picture?type=normal"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage("1234567");
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test
	public void getUserProfileImage_specificUserAndType() {
		mockServer.expect(requestTo("https://graph.facebook.com/1234567/picture?type=large"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage("1234567", ImageType.LARGE);
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getUserProfileImage_currentUser_specificType_unauthorized() {
		unauthorizedFacebook.userOperations().getUserProfileImage(ImageType.NORMAL);
	}

	@Test
	public void getUserPermissions() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/permissions"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-permissions"), MediaType.APPLICATION_JSON));
		List<String> permissions = facebook.userOperations().getUserPermissions();
		assertEquals(4, permissions.size());
		assertTrue(permissions.contains("status_update"));
		assertTrue(permissions.contains("offline_access"));
		assertTrue(permissions.contains("read_stream"));
		assertTrue(permissions.contains("publish_stream"));
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void getUserPermissions_unauthorized() {
		unauthorizedFacebook.userOperations().getUserPermissions();
	}
	
	@Test
	public void search() {
		mockServer.expect(requestTo("https://graph.facebook.com/search?q=Michael+Scott&type=user"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-references"), MediaType.APPLICATION_JSON));
		List<Reference> results = facebook.userOperations().search("Michael Scott");
		assertEquals(3, results.size());
		assertEquals("100000737708615", results.get(0).getId());
		assertEquals("Michael Scott", results.get(0).getName());
		assertEquals("100000354483321", results.get(1).getId());
		assertEquals("Michael Scott", results.get(1).getName());
		assertEquals("1184963857", results.get(2).getId());
		assertEquals("Michael Scott", results.get(2).getName());
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void search_unauthorized() {
		unauthorizedFacebook.userOperations().search("Michael Scott");
	}

	private void assertBasicProfileData(FacebookProfile profile, boolean withMiddleName) {
		assertEquals("123456789", profile.getId());
		assertEquals("Michael", profile.getFirstName());
		if (withMiddleName) {
			assertEquals("Craig", profile.getMiddleName());
		} else {
			assertNull(profile.getMiddleName());
		}
		assertEquals("Walls", profile.getLastName());
		assertEquals("Michael Craig Walls", profile.getName());
		assertEquals(Locale.US, profile.getLocale());
		assertEquals("male", profile.getGender());
	}

	private void assertEducationHistory(List<EducationEntry> educationHistory) {
		assertEquals(2, educationHistory.size());
		assertEquals("College", educationHistory.get(0).getType());
		assertEquals("103768553006294", educationHistory.get(0).getSchool().getId());
		assertEquals("New Mexico", educationHistory.get(0).getSchool().getName());
		assertEquals("117348274968344", educationHistory.get(0).getYear().getId());
		assertEquals("1994", educationHistory.get(0).getYear().getName());
		List<Reference> concentration = educationHistory.get(0).getConcentration();
		assertEquals(2, concentration.size());
		assertEquals("192578844099494", concentration.get(0).getId());
		assertEquals("Computer Science", concentration.get(0).getName());
		assertEquals("146136662113078", concentration.get(1).getId());
		assertEquals("Mathematics", concentration.get(1).getName());
		assertEquals("High School", educationHistory.get(1).getType());
		assertEquals("115157218496067", educationHistory.get(1).getSchool().getId());
		assertEquals("Jal High School", educationHistory.get(1).getSchool().getName());
		assertEquals("127132740657422", educationHistory.get(1).getYear().getId());
		assertEquals("1989", educationHistory.get(1).getYear().getName());
		assertNull(educationHistory.get(1).getConcentration());
	}

	private void assertWorkHistory(List<WorkEntry> workHistory) {
		assertEquals(2, workHistory.size());
		assertEquals("119387448093014", workHistory.get(0).getEmployer().getId());
		assertEquals("SpringSource", workHistory.get(0).getEmployer().getName());
		assertEquals("0000-00", workHistory.get(0).getStartDate());
		assertEquals("0000-00", workHistory.get(0).getEndDate());
		assertEquals("298846151879", workHistory.get(1).getEmployer().getId());
		assertEquals("Improving", workHistory.get(1).getEmployer().getName());
		assertEquals("2009-03", workHistory.get(1).getStartDate());
		assertEquals("2010-05", workHistory.get(1).getEndDate());
	}

}
