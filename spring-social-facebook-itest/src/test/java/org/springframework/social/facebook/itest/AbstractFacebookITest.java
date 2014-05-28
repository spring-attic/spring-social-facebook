package org.springframework.social.facebook.itest;

import org.junit.After;
import org.junit.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookServiceProvider;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class AbstractFacebookITest {

	public static final String USER_EXTENDED_PERMISSIONS = "user_about_me,user_activities,user_birthday,user_checkins,user_education_history,user_events,user_groups,user_hometown,user_interests,user_likes,user_location,user_notes,user_photos,user_questions,user_relationships,user_relationship_details,user_religion_politics,user_status,user_subscriptions,user_videos,user_website,user_work_history";
	public static final String FRIENDS_EXTENDED_PERMISSIONS = "friends_about_me,friends_activities,friends_birthday,friends_checkins,friends_education_history,friends_events,friends_groups,friends_hometown,friends_interests,friends_likes,friends_location,friends_notes,friends_photos,friends_questions,friends_relationships,friends_relationship_details,friends_religion_politics,friends_status,friends_subscriptions,friends_videos,friends_website,friends_work_history";
	public static final String EXTENDED_READ_PERMISSIONS = "read_friendlists,read_insights,read_mailbox,read_requests,read_stream,xmpp_login,user_online_presence,friends_online_presence";
	public static final String EXTENDED_PUBLISH_PERMISSIONS = "create_event,manage_friendlists,manage_notifications,publish_actions,publish_stream,rsvp_event";
	public static final String USER_OPEN_GRAPH_PERMISSIONS = "publish_actions,user_actions.music,user_actions.news,user_actions.video,user_games_activity";
	public static final String FRIENDS_OPEN_GRAPH_PERMISSIONS = "friends_actions.music,friends_actions.news,friends_actions.video,friends_games_activity";
	public static final String ALL_PERMISSIONS = "email" + "," + USER_EXTENDED_PERMISSIONS + "," + FRIENDS_EXTENDED_PERMISSIONS + "," + EXTENDED_READ_PERMISSIONS + "," + EXTENDED_PUBLISH_PERMISSIONS;

	private Facebook CLIENT_FB;
	
	protected abstract AppCredentials getAppCredentials();

	protected TestUser testUser;
	
	protected FacebookTemplate fb;

	@Before
	public void setup() {
		AppCredentials app = getAppCredentials();
		FacebookServiceProvider sp = new FacebookServiceProvider(app.getAppId(), app.getAppSecret(), app.getAppNamespace());
		AccessGrant clientGrant = sp.getOAuthOperations().authenticateClient();
		this.CLIENT_FB = new FacebookTemplate(clientGrant.getAccessToken());
		this.testUser = createTestUser("Jack Diamond", ALL_PERMISSIONS);
		this.fb = new FacebookTemplate(this.testUser.getAccessToken());
	}

	@After
	public void after() {
		deleteTestUser(testUser);
	}

	protected TestUser createTestUser(String name, String permissions) {
		MultiValueMap<String, Object> req = new LinkedMultiValueMap<String, Object>();
		req.set("name", name);
		req.set("installed", "true");
		req.set("permissions", permissions);
		
		ResponseEntity<TestUser> entity = CLIENT_FB.restOperations().postForEntity("https://graph.facebook.com/{appId}/accounts/test-users", req, TestUser.class, getAppCredentials().getAppId());
		return entity.getBody();
	}
	
	protected void deleteTestUser(TestUser testUser) {
		CLIENT_FB.restOperations().delete("https://graph.facebook.com/{userId}", testUser.getId());
	}

	static class AppCredentials {
		private final String appId;
		private final String appSecret;
		private final String appNamespace;

		public AppCredentials(String appId, String appSecret, String appNamespace) {
			this.appId = appId;
			this.appSecret = appSecret;
			this.appNamespace = appNamespace;
		}

		public String getAppId() {
			return appId;
		}

		public String getAppSecret() {
			return appSecret;
		}

		public String getAppNamespace() {
			return appNamespace;
		}

	}
	
}
