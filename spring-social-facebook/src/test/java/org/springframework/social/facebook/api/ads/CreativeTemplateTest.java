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
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789"))
				.andExpect(method(DELETE))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
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
