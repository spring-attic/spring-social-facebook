package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.TestUserOperations;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class TestUserTemplate extends AbstractFacebookOperations implements TestUserOperations {

	private String appId;
	
	private RestTemplate restTemplate;

	public TestUserTemplate(RestTemplate restTemplate, String appId) {
		super(false);
		this.restTemplate = restTemplate;
		this.appId = appId;
	}

	public TestUser createTestUser(boolean installed, String permissions) {
		return createTestUser(installed, permissions, null);
	}
	
	public TestUser createTestUser(boolean installed, String permissions, String name) {
		TestUserForm form = new TestUserForm(installed, permissions, name);
		return restTemplate.postForObject("https://graph.facebook.com/v2.2/{appId}/accounts/test-users", form, TestUser.class, appId);
	}
	
	public void sendConfirmFriends(TestUser testUser1, TestUser testUser2) {
		RestOperations userRest = new FacebookTemplate(testUser1.getAccessToken()).restOperations();
		
		userRest.postForObject("https://graph.facebook.com/v2.2/{testUserId1}/friends/{testUserId2}", "", String.class, testUser1.getId(), testUser2.getId());
	}
	
	public void deleteTestUser(String testUserId) {
		restTemplate.delete("https://graph.facebook.com/v2.2/{testUserId}", testUserId);
	}
	
	private static class TestUserForm {
		
		private final boolean installed;
		private final String permissions;
		private final String name;

		public TestUserForm(boolean installed, String permissions, String name) {
			this.installed = installed;
			this.permissions = permissions;
			this.name = name;
		}
		
		public boolean isInstalled() {
			return installed;
		}
		
		public String getPermissions() {
			return permissions;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
