package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.TestUserOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<String, Object>();
		request.set("installed", "" + installed);
		if (name != null) {
			request.set("name", name);
		}
		
		if (permissions != null) {
			request.set("permissions", permissions);
		}

		return restTemplate.postForObject(GraphApi.GRAPH_API_URL + "{appId}/accounts/test-users", request, TestUser.class, appId);
	}
	
	public void sendConfirmFriends(TestUser testUser1, TestUser testUser2) {
		RestOperations userRest = new FacebookTemplate(testUser1.getAccessToken()).restOperations();
		
		userRest.postForObject(GraphApi.GRAPH_API_URL + "{testUserId1}/friends/{testUserId2}", "", String.class, testUser1.getId(), testUser2.getId());
	}
	
	public void deleteTestUser(String testUserId) {
		restTemplate.delete(GraphApi.GRAPH_API_URL + "{testUserId}", testUserId);
	}
	
}
