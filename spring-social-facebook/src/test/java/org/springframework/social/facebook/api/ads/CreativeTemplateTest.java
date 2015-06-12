package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.PagedList;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class CreativeTemplateTest extends AbstractFacebookAdsApiTest {

	@Test
	public void getAccountCreatives() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcreatives?fields=actor_id%2Cbody%2Cimage_hash%2Cimage_url%2Clink_url%2Cname%2Cobject_id%2Cobject_story_id%2Cobject_url%2Crun_status%2Ctitle%2Curl_tags%2Cthumbnail_url%2Cobject_type%2Cid"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creatives"), MediaType.APPLICATION_JSON));

		List<AdCreative> creatives = facebookAds.creativeOperations().getAccountCreatives("123456789");
		verifyAdCreatives(creatives);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountCreatives_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().getAccountCreatives("123456789");
	}

	@Test
	public void getAdSetCreatives() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/700123456789/adcreatives?fields=actor_id%2Cbody%2Cimage_hash%2Cimage_url%2Clink_url%2Cname%2Cobject_id%2Cobject_story_id%2Cobject_url%2Crun_status%2Ctitle%2Curl_tags%2Cthumbnail_url%2Cobject_type%2Cid"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creatives"), MediaType.APPLICATION_JSON));

		List<AdCreative> creatives = facebookAds.creativeOperations().getAdSetCreatives("700123456789");
		verifyAdCreatives(creatives);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdSetCreatives_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().getAdSetCreatives("700123456789");
	}

	@Test
	public void getAdCreative() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789?fields=actor_id%2Cbody%2Cimage_hash%2Cimage_url%2Clink_url%2Cname%2Cobject_id%2Cobject_story_id%2Cobject_url%2Crun_status%2Ctitle%2Curl_tags%2Cthumbnail_url%2Cobject_type%2Cid"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creative"), MediaType.APPLICATION_JSON));

		AdCreative creative = facebookAds.creativeOperations().getAdCreative("800123456789");
		assertEquals("800123456789", creative.getId());
		assertEquals(AdCreative.AdCreativeType.STATUS, creative.getType());
		assertEquals("https://example.net/safe_image.php?d=123456789", creative.getThumbnailUrl());
		assertEquals(AdCreative.AdCreativeStatus.ACTIVE, creative.getStatus());
		assertEquals("123456789_987654321", creative.getObjectStoryId());
		assertEquals("Creative test name", creative.getName());
		mockServer.verify();
	}

	@Test
	public void getAdCreative_withWrongType() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/801123456789?fields=actor_id%2Cbody%2Cimage_hash%2Cimage_url%2Clink_url%2Cname%2Cobject_id%2Cobject_story_id%2Cobject_url%2Crun_status%2Ctitle%2Curl_tags%2Cthumbnail_url%2Cobject_type%2Cid"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creative-wrong-type"), MediaType.APPLICATION_JSON));
		AdCreative creative = facebookAds.creativeOperations().getAdCreative("801123456789");
		assertEquals("801123456789", creative.getId());
		assertEquals(AdCreative.AdCreativeType.UNKNOWN, creative.getType());
		verifyAdCreative(creative);
		assertEquals(AdCreative.AdCreativeStatus.ACTIVE, creative.getStatus());
		mockServer.verify();
	}

	@Test
	public void getAdCreative_withWrongStatus() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/802123456789?fields=actor_id%2Cbody%2Cimage_hash%2Cimage_url%2Clink_url%2Cname%2Cobject_id%2Cobject_story_id%2Cobject_url%2Crun_status%2Ctitle%2Curl_tags%2Cthumbnail_url%2Cobject_type%2Cid"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creative-wrong-status"), MediaType.APPLICATION_JSON));
		AdCreative creative = facebookAds.creativeOperations().getAdCreative("802123456789");
		assertEquals("802123456789", creative.getId());
		assertEquals(AdCreative.AdCreativeType.DOMAIN, creative.getType());
		verifyAdCreative(creative);
		assertEquals(AdCreative.AdCreativeStatus.UNKNOWN, creative.getStatus());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdCreative_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().getAdCreative("800123456789");
	}

	@Test
	public void getAdCreativeInsight() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cactions_per_impression%2Cclicks%2Cunique_clicks%2Ccost_per_result%2Ccost_per_total_action%2Ccpc%2Ccost_per_unique_click%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Cobjective%2Creach%2Cresult_rate%2Cresults%2Croas%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctoday_spend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cvideo_start_actions"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-creative-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.creativeOperations().getAdCreativeInsight("800123456789");
		assertEquals("123456789", insight.getAccountId());
		assertEquals("Test account name", insight.getAccountName());
		assertEquals(0.016042780748663, insight.getActionsPerImpression(), EPSILON);
		assertEquals(8, insight.getClicks());
		assertEquals(5, insight.getUniqueClicks());
		assertEquals(0.66666666666667, insight.getCostPerResult(), EPSILON);
		assertEquals(0.66666666666667, insight.getCostPerTotalAction(), EPSILON);
		assertEquals(0.25, insight.getCostPerClick(), EPSILON);
		assertEquals(0.4, insight.getCostPerUniqueClick(), EPSILON);
		assertEquals(10.695187165775, insight.getCpm(), EPSILON);
		assertEquals(10.869565217391, insight.getCpp(), EPSILON);
		assertEquals(4.2780748663102, insight.getCtr(), EPSILON);
		assertEquals(2.7173913043478, insight.getUniqueCtr(), EPSILON);
		assertEquals(1.0163043478261, insight.getFrequency(), EPSILON);
		assertEquals(187, insight.getImpressions());
		assertEquals(184, insight.getUniqueImpressions());
		assertEquals(184, insight.getReach());
		assertEquals(1.6042780748663, insight.getResultRate(), EPSILON);
		assertEquals(3, insight.getResults());
		assertEquals(0, insight.getRoas());
		assertEquals(0, insight.getSocialClicks());
		assertEquals(0, insight.getUniqueSocialClicks());
		assertEquals(0, insight.getSocialImpressions());
		assertEquals(0, insight.getUniqueSocialImpressions());
		assertEquals(0, insight.getSocialReach());
		assertEquals(2, insight.getSpend());
		assertEquals(0, insight.getTodaySpend());
		assertEquals(0, insight.getTotalActionValue());
		assertEquals(3, insight.getTotalActions());
		assertEquals(2, insight.getTotalUniqueActions());
		assertEquals(4, insight.getActions().size());
		assertEquals("comment", insight.getActions().get(0).getActionType());
		assertEquals(2, insight.getActions().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getActions().get(1).getActionType());
		assertEquals(1, insight.getActions().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getActions().get(2).getActionType());
		assertEquals(3, insight.getActions().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getActions().get(3).getActionType());
		assertEquals(3, insight.getActions().get(3).getValue(), EPSILON);
		assertEquals(4, insight.getUniqueActions().size());
		assertEquals("comment", insight.getUniqueActions().get(0).getActionType());
		assertEquals(1, insight.getUniqueActions().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getUniqueActions().get(1).getActionType());
		assertEquals(1, insight.getUniqueActions().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getUniqueActions().get(2).getActionType());
		assertEquals(2, insight.getUniqueActions().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getUniqueActions().get(3).getActionType());
		assertEquals(2, insight.getUniqueActions().get(3).getValue(), EPSILON);
		assertEquals(4, insight.getCostPerActionType().size());
		assertEquals("comment", insight.getCostPerActionType().get(0).getActionType());
		assertEquals(1, insight.getCostPerActionType().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getCostPerActionType().get(1).getActionType());
		assertEquals(2, insight.getCostPerActionType().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getCostPerActionType().get(2).getActionType());
		assertEquals(0.66666666666667, insight.getCostPerActionType().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getCostPerActionType().get(3).getActionType());
		assertEquals(0.66666666666667, insight.getCostPerActionType().get(3).getValue(), EPSILON);
		assertEquals(1, insight.getVideoStartActions().size());
		assertEquals("video_view", insight.getVideoStartActions().get(0).getActionType());
		assertEquals(0, insight.getVideoStartActions().get(0).getValue(), EPSILON);

		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdCreativeInsight_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().getAdCreativeInsight("800123456789");
	}

	@Test
	public void createAdCreative_linkAd() throws Exception {
		String requestBody = "title=Ad+creative+title&body=Over+the+past+few+years+REST+has+become+an+important&image_url=http%3A%2F%2Fexample.com%2Fstatics_s2_20150603-0041%2Fstyles%2Fi%2Flogo_bigger.jpg&object_url=http%3A%2F%2Fwww.example.com%2Fsome_object";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcreatives"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"801123456789\"}", MediaType.APPLICATION_JSON));

		AdCreative creative = new AdCreative();
		creative.setTitle("Ad creative title");
		creative.setObjectUrl("http://www.example.com/some_object");
		creative.setBody("Over the past few years REST has become an important");
		creative.setImageUrl("http://example.com/statics_s2_20150603-0041/styles/i/logo_bigger.jpg");
		assertEquals("801123456789", facebookAds.creativeOperations().createAdCreative("123456789", creative));
		mockServer.verify();
	}

	@Test
	public void createAdCreative_postLikeAd() throws Exception {
		String requestBody = "name=Post+Like+Ad+Creative&body=Some+body+here&object_id=600123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcreatives"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"802123456789\"}", MediaType.APPLICATION_JSON));

		AdCreative creative = new AdCreative();
		creative.setName("Post Like Ad Creative");
		creative.setObjectId("600123456789");
		creative.setBody("Some body here");
		assertEquals("802123456789", facebookAds.creativeOperations().createAdCreative("123456789", creative));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createAdCreative_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().createAdCreative("123456789", new AdCreative());
	}

	@Test
	public void renameAdCreative() throws Exception {
		String requestBody = "name=New+creative+name";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));

		facebookAds.creativeOperations().renameAdCreative("800123456789", "New creative name");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void renameAdCreative_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().renameAdCreative("800123456789", "New anauthorized name!");
	}

	@Test
	public void deleteAdCreative() throws Exception {
		String requestBody = "run_status=DELETED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));

		facebookAds.creativeOperations().deleteAdCreative("800123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAdCreative_unauthorized() throws Exception {
		unauthorizedFacebookAds.creativeOperations().deleteAdCreative("800123456789");
	}

	private void verifyAdCreatives(List<AdCreative> creatives) {
		assertEquals(3, creatives.size());
		assertEquals("This is the body of the ad creative1", creatives.get(0).getBody());
		assertEquals("ac67a06abb35a3bef8256c8c9085548e1", creatives.get(0).getImageHash());
		assertEquals("https://example.com/images/image1.png", creatives.get(0).getImageUrl());
		assertEquals("Name of the ad creative1", creatives.get(0).getName());
		assertEquals("900123456789", creatives.get(0).getObjectId());
		assertEquals(AdCreative.AdCreativeStatus.ACTIVE, creatives.get(0).getStatus());
		assertEquals("https://example.com/thumbnails/thuumbnail_url1", creatives.get(0).getThumbnailUrl());
		assertEquals(AdCreative.AdCreativeType.PAGE, creatives.get(0).getType());
		assertEquals("801123456789", creatives.get(0).getId());
		assertEquals("This is the body of the ad creative2", creatives.get(1).getBody());
		assertEquals("ac67a06abb35a3bef8256c8c9085548e2", creatives.get(1).getImageHash());
		assertEquals("https://example.com/images/image2.png", creatives.get(1).getImageUrl());
		assertEquals("Name of the ad creative2", creatives.get(1).getName());
		assertEquals("http://example.com/objects/object", creatives.get(1).getObjectUrl());
		assertEquals(AdCreative.AdCreativeStatus.ACTIVE, creatives.get(1).getStatus());
		assertEquals("Test Ad Creative", creatives.get(1).getTitle());
		assertEquals("https://example.com/thumbnails/thuumbnail_url2", creatives.get(1).getThumbnailUrl());
		assertEquals(AdCreative.AdCreativeType.DOMAIN, creatives.get(1).getType());
		assertEquals("802123456789", creatives.get(1).getId());
		assertEquals("This is the body of the ad creative3", creatives.get(2).getName());
		assertEquals("987654321_123456789", creatives.get(2).getObjectStoryId());
		assertEquals(AdCreative.AdCreativeStatus.ACTIVE, creatives.get(2).getStatus());
		assertEquals("https://example.com/thumbnails/thuumbnail_url3", creatives.get(2).getThumbnailUrl());
		assertEquals(AdCreative.AdCreativeType.STATUS, creatives.get(2).getType());
		assertEquals("803123456789", creatives.get(2).getId());
	}

	private void verifyAdCreative(AdCreative creative) {
		assertEquals("This is the body of the ad creative", creative.getBody());
		assertEquals("ac67a06abb35a3bef8256c8c9085548e", creative.getImageHash());
		assertEquals("https://example.com/images/image.png", creative.getImageUrl());
		assertEquals("Name of the ad creative", creative.getName());
		assertEquals("http://example.com/objects/object", creative.getObjectUrl());
		assertEquals("Title of the ad creative", creative.getTitle());
		assertEquals("https://example.com/thumbnails/thuumbnail_url", creative.getThumbnailUrl());
	}
}
