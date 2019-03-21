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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

/**
 * @author Craig Walls
 */
public class UserTemplateTest extends AbstractFacebookApiTest {

	private static String PROFILE_FIELDS;
	
	static {
		StringBuilder builder = new StringBuilder(UserOperations.PROFILE_FIELDS[0]);
		for (int i=1; i < UserOperations.PROFILE_FIELDS.length; i++) {
			builder.append("%2C").append(UserOperations.PROFILE_FIELDS[i]);
		}
		PROFILE_FIELDS = builder.toString();
	}
	
	@Test
	public void getUserProfile_currentUser() {
		mockServer.expect(requestTo(fbUrl("me?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("full-profile"), MediaType.APPLICATION_JSON));

		User profile = facebook.userOperations().getUserProfile();
		assertBasicProfileData(profile, true);
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("http://www.facebook.com/habuma", profile.getLink());
		assertEquals("xyz123abc987", profile.getThirdPartyId());
		assertEquals(Float.valueOf(-6), profile.getTimezone());  // should be -6 ???
		assertEquals(toDate("2010-08-22T00:01:59+0000"), profile.getUpdatedTime());
		assertTrue(profile.isVerified());
		assertEquals("Just some dude", profile.getAbout());
		assertEquals("06/09/1971", profile.getBirthday());
		assertEquals("111762725508574", profile.getLocation().getId());
		assertEquals("Dallas, Texas", profile.getLocation().getName());
		assertEquals("107925612568471", profile.getHometown().getId());
		assertEquals("Plano, Texas", profile.getHometown().getName());
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
	
	@Test
	public void getUserProfile_specificUserByUserId() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile"), MediaType.APPLICATION_JSON));

		User profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, true);
	}

	@Test
	public void getUserProfile_specificUserByUserId_noMiddleName() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-no-middle-name"), MediaType.APPLICATION_JSON));

		User profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, false);
	}
	
	@Test
	public void getUserProfile_specificUserByUserId_withRealTimezone() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-timezone"), MediaType.APPLICATION_JSON));

		User profile = facebook.userOperations().getUserProfile("123456789");
		assertBasicProfileData(profile, true);
		assertEquals(Float.valueOf("-4.5"), profile.getTimezone()); 
	}
	
	@Test
	public void getUserProfile_withAgeRange_13_17() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-age-range-13-17"), MediaType.APPLICATION_JSON));
		
		User profile = facebook.userOperations().getUserProfile("123456789");
		assertEquals(AgeRange.AGE_13_17, profile.getAgeRange());
		assertEquals(13, profile.getAgeRange().getMin().intValue());
		assertEquals(17, profile.getAgeRange().getMax().intValue());
	}

	@Test
	public void getUserProfile_withAgeRange_18_20() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-age-range-18-20"), MediaType.APPLICATION_JSON));
		
		User profile = facebook.userOperations().getUserProfile("123456789");
		assertEquals(AgeRange.AGE_18_20, profile.getAgeRange());
		assertEquals(18, profile.getAgeRange().getMin().intValue());
		assertEquals(20, profile.getAgeRange().getMax().intValue());
	}

	@Test
	public void getUserProfile_withAgeRange_21_plus() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-age-range-21-plus"), MediaType.APPLICATION_JSON));
		
		User profile = facebook.userOperations().getUserProfile("123456789");
		assertEquals(AgeRange.AGE_21_PLUS, profile.getAgeRange());
		assertEquals(21, profile.getAgeRange().getMin().intValue());
		assertNull(profile.getAgeRange().getMax());
	}

	@Test
	public void getUserProfile_withAgeRange_unknown() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile-with-age-range-unknown"), MediaType.APPLICATION_JSON));
		
		User profile = facebook.userOperations().getUserProfile("123456789");
		assertEquals(AgeRange.UNKNOWN, profile.getAgeRange());
		assertEquals(33, profile.getAgeRange().getMin().intValue());
		assertEquals(42, profile.getAgeRange().getMax().intValue());
	}

	@Test
	public void getUserProfile_withAgeRange_null() {
		mockServer.expect(requestTo(fbUrl("123456789?fields=" + PROFILE_FIELDS)))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("minimal-profile"), MediaType.APPLICATION_JSON));
		
		User profile = facebook.userOperations().getUserProfile("123456789");
		assertEquals(AgeRange.UNKNOWN, profile.getAgeRange());
		assertNull(profile.getAgeRange().getMin());
		assertNull(profile.getAgeRange().getMax());
	}

	@Test
	public void getUserProfileImage() {
		mockServer.expect(requestTo(fbUrl("me/picture?type=normal")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage();
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test
	public void getUserProfileImage_specificUserByUserId() {
		mockServer.expect(requestTo(fbUrl("1234567/picture?type=normal")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage("1234567");
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test
	public void getUserProfileImage_specificUserAndType() {
		mockServer.expect(requestTo(fbUrl("1234567/picture?type=large")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(new ClassPathResource("tinyrod.jpg", getClass()), MediaType.IMAGE_JPEG));
		facebook.userOperations().getUserProfileImage("1234567", ImageType.LARGE);
		// TODO: Fix mock server handle binary data so we can test contents (or at least size) of image data.
		mockServer.verify();
	}
	
	@Test
	public void getUserPermissions() {
		mockServer.expect(requestTo(fbUrl("me/permissions")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-permissions"), MediaType.APPLICATION_JSON));
		List<Permission> permissions = facebook.userOperations().getUserPermissions();
		
		
		Map<String, String> expectedPermissions = new HashMap<String, String>();
		expectedPermissions.put("user_photos", "granted");
		expectedPermissions.put("user_location", "declined");
		expectedPermissions.put("read_stream", "granted");
		expectedPermissions.put("publish_stream", "declined");
		assertEquals(4, permissions.size());
		for (Permission permission : permissions) {
			assertTrue(expectedPermissions.containsKey(permission.getName()));
			assertEquals(expectedPermissions.get(permission.getName()), permission.getStatus());
		}
	}
	
	@Test
	public void getIdsForBusiness() {
		mockServer.expect(requestTo(fbUrl("me/ids_for_business")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("ids_for_business"), MediaType.APPLICATION_JSON));
		List<UserIdForApp> idsForBusiness = facebook.userOperations().getIdsForBusiness();
		
		assertEquals(2, idsForBusiness.size());
		assertEquals("123456", idsForBusiness.get(0).getId());
		assertEquals("APP1", idsForBusiness.get(0).getApp().getId());
		assertEquals("App one", idsForBusiness.get(0).getApp().getName());
		assertEquals("987654", idsForBusiness.get(1).getId());
		assertEquals("APP2", idsForBusiness.get(1).getApp().getId());
		assertEquals("App two", idsForBusiness.get(1).getApp().getName());
	}
	
	@Test
	public void getTaggedPlaces() {
		mockServer.expect(requestTo(fbUrl("me/tagged_places")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("tagged_places"), MediaType.APPLICATION_JSON));
		List<PlaceTag> taggedPlaces = facebook.userOperations().getTaggedPlaces();
		assertEquals(2, taggedPlaces.size());
		PlaceTag placeTag = taggedPlaces.get(0);
		assertEquals("10155117367290580", placeTag.getId());
		assertEquals("158232190858368", placeTag.getPlace().getId());
		assertEquals(toDate("2015-02-04T14:28:51+0000"), placeTag.getCreatedTime());
		assertEquals("Lake Buena Vista", placeTag.getPlace().getLocation().getCity());
		assertEquals("United States", placeTag.getPlace().getLocation().getCountry());
		assertEquals("FL", placeTag.getPlace().getLocation().getState());
		assertEquals("32830", placeTag.getPlace().getLocation().getZip());
		assertEquals(28.415153357468, placeTag.getPlace().getLocation().getLatitude(), 0.001);
		assertEquals(-81.580595083499, placeTag.getPlace().getLocation().getLongitude(), 0.001);
		assertEquals("Disney's Magic Kingdom", placeTag.getPlace().getName());
		placeTag = taggedPlaces.get(1);
		assertEquals("10154984104080580", placeTag.getId());
		assertEquals("142223762504319", placeTag.getPlace().getId());
		assertEquals(toDate("2015-01-03T15:47:59+0000"), placeTag.getCreatedTime());
		assertEquals("Lake Buena Vista", placeTag.getPlace().getLocation().getCity());
		assertEquals("United States", placeTag.getPlace().getLocation().getCountry());
		assertEquals("FL", placeTag.getPlace().getLocation().getState());
		assertEquals("32830", placeTag.getPlace().getLocation().getZip());
		assertEquals(28.350102, placeTag.getPlace().getLocation().getLatitude(), 0.001);
		assertEquals(-81.548863, placeTag.getPlace().getLocation().getLongitude(), 0.001);
		assertEquals("Disney's Art of Animation Resort", placeTag.getPlace().getName());
	}
	
	@Test
	public void search() {
		mockServer.expect(requestTo(fbUrl("search?q=Michael+Scott&type=user")))
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
	
	private void assertBasicProfileData(User profile, boolean withMiddleName) {
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

	private void assertEducationHistory(List<EducationExperience> educationHistory) {
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
