package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.connect.FacebookServiceProvider;

public class UserTemplateITest extends AbstractFacebookITest implements ShowcaseApp {

	@Test
	public void getUserPermissions() {
		TestUser evie = createTestUser("Evie Starlight", "email,user_birthday,user_about_me");
		FacebookServiceProvider provider = new FacebookServiceProvider(APP_ID, APP_SECRET, APP_NAMESPACE);
		Facebook evieFb = provider.getApi(evie.getAccessToken());
		PagedList<String> permissions = evieFb.userOperations().getUserPermissions();
		List<String> expectedPermissions = Arrays.asList("installed", "basic_info", "public_profile", "email", "user_birthday", "user_friends", "user_about_me");
		assertEquals(expectedPermissions, permissions);
	}
	
	@Test
	public void getUserProfile() {
		FacebookProfile profile = fb.userOperations().getUserProfile();
		assertEquals(testUser.getId(), profile.getId());
		assertEquals("Jack Diamond", profile.getName());
		assertEquals("Jack", profile.getFirstName());
		assertEquals("Diamond", profile.getLastName());
		assertEquals("08/08/1980", profile.getBirthday());
		assertEquals("https://www.facebook.com/app_scoped_user_id/"+testUser.getId()+'/', profile.getLink());
		assertEquals(new Locale("en", "US"), profile.getLocale());
		assertEquals(0.0f, (float) profile.getTimezone(), 0.0f);
		assertFalse(profile.isVerified());
		assertNotNull(profile.getUpdatedTime()); // TODO : Verify time is (within reason) near the current time
		
		// some data is randomly generated for the test user, so tests cannot be precise
		assertTrue(profile.getEmail().matches("jack_(.*)_diamond@tfbnw.net"));
		assertTrue(profile.getGender().equals("male") || profile.getGender().equals("female"));
	}
	
	@Test
	public void getUserProfile_byUserId() {
		FacebookProfile profile = fb.userOperations().getUserProfile(testUser.getId());
		assertEquals(testUser.getId(), profile.getId());
		assertEquals("Jack Diamond", profile.getName());
		assertEquals("Jack", profile.getFirstName());
		assertEquals("Diamond", profile.getLastName());
		assertEquals("08/08/1980", profile.getBirthday());
		assertEquals("https://www.facebook.com/app_scoped_user_id/"+testUser.getId()+'/', profile.getLink());
		assertEquals(new Locale("en", "US"), profile.getLocale());
		assertEquals(0.0f, (float) profile.getTimezone(), 0.0f);
		assertFalse(profile.isVerified());
		assertNotNull(profile.getUpdatedTime()); // TODO : Verify time is (within reason) near the current time
		
		// some data is randomly generated for the test user, so tests cannot be precise
		assertTrue(profile.getEmail().matches("jack_(.*)_diamond@tfbnw.net"));
		assertTrue(profile.getGender().equals("male") || profile.getGender().equals("female"));
	}

	@Test
	public void getUserProfile_byUserId_someOtherUser() {
		TestUser mimi = createTestUser("Mimi Kaboom", ALL_PERMISSIONS);
		
		FacebookProfile profile = fb.userOperations().getUserProfile(mimi.getId());
		assertEquals(mimi.getId(), profile.getId());
		assertEquals("Mimi Kaboom", profile.getName());
		assertEquals("Mimi", profile.getFirstName());
		assertEquals("Kaboom", profile.getLastName());
		assertNull(profile.getBirthday());
		assertEquals("https://www.facebook.com/app_scoped_user_id/"+mimi.getId()+'/', profile.getLink());
		assertEquals(new Locale("en", "US"), profile.getLocale());
		assertNull(profile.getTimezone());
		assertNull(profile.isVerified());
		assertNotNull(profile.getUpdatedTime()); // TODO : Verify time is (within reason) near the current time
		
		// some data is randomly generated for the test user, so tests cannot be precise
		assertNull(profile.getEmail());
		assertTrue(profile.getGender().equals("male") || profile.getGender().equals("female"));
		deleteTestUser(mimi);
	}
	
	@Test
	public void getUserProfileImage() {
		byte[] userProfileImage = fb.userOperations().getUserProfileImage();
		assertTrue(userProfileImage.length > 0);
	}

	@Test
	public void getUserProfileImage_forUser() {
		byte[] userProfileImage = fb.userOperations().getUserProfileImage(testUser.getId());
		assertTrue(userProfileImage.length > 0);
	}

	@Test
	public void getUserProfileImage_forSomeOtherUser() {
		TestUser stone = createTestUser("Stone Granite", ALL_PERMISSIONS);
		byte[] userProfileImage = fb.userOperations().getUserProfileImage(stone.getId());
		assertTrue(userProfileImage.length > 0);
	}

	@Test
	public void getUserProfileImage_byType() {
		byte[] square = fb.userOperations().getUserProfileImage(ImageType.SQUARE);
		assertTrue(square.length > 0);
		byte[] large = fb.userOperations().getUserProfileImage(ImageType.LARGE);
		assertTrue(large.length > 0);
		byte[] normal = fb.userOperations().getUserProfileImage(ImageType.NORMAL);
		assertTrue(normal.length > 0);
		byte[] small = fb.userOperations().getUserProfileImage(ImageType.SMALL);
		assertTrue(small.length > 0);
	}

	@Test
	public void getUserProfileImage_byType_forSomeOtherUser() {
		TestUser peabody = createTestUser("Percivel Peabody", ALL_PERMISSIONS);
		byte[] square = fb.userOperations().getUserProfileImage(peabody.getId(), ImageType.SQUARE);
		assertTrue(square.length > 0);
		byte[] large = fb.userOperations().getUserProfileImage(peabody.getId(), ImageType.LARGE);
		assertTrue(large.length > 0);
		byte[] normal = fb.userOperations().getUserProfileImage(peabody.getId(), ImageType.NORMAL);
		assertTrue(normal.length > 0);
		byte[] small = fb.userOperations().getUserProfileImage(peabody.getId(), ImageType.SMALL);
		assertTrue(small.length > 0);
	}

	@Override
	protected AppCredentials getAppCredentials() {
		return new AppCredentials(APP_ID, APP_SECRET, APP_NAMESPACE);
	}
}
