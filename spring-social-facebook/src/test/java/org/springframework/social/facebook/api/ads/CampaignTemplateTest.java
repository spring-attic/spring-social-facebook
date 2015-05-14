package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.InvalidCampaignStatusException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.AbstractFacebookAdsApiTest;
import org.springframework.social.facebook.api.ads.AdCampaign;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class CampaignTemplateTest extends AbstractFacebookAdsApiTest {

	@Test
	public void getAdCampaigns() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-campaigns"), MediaType.APPLICATION_JSON));
		PagedList<AdCampaign> campaigns = facebookAds.campaignOperations().getAdCampaigns("123456789");
		assertEquals(3, campaigns.size());
		assertEquals("601123456789", campaigns.get(0).getId());
		assertEquals("123456789", campaigns.get(0).getAccountId());
		assertEquals(BuyingType.AUCTION, campaigns.get(0).getBuyingType());
		assertEquals(CampaignStatus.ACTIVE, campaigns.get(0).getStatus());
		assertEquals("Campaign #1", campaigns.get(0).getName());
		assertEquals(CampaignObjective.POST_ENGAGEMENT, campaigns.get(0).getObjective());
		assertEquals(0, campaigns.get(0).getSpendCap());
		assertEquals("602123456789", campaigns.get(1).getId());
		assertEquals("123456789", campaigns.get(1).getAccountId());
		assertEquals(BuyingType.FIXED_CPM, campaigns.get(1).getBuyingType());
		assertEquals(CampaignStatus.PAUSED, campaigns.get(1).getStatus());
		assertEquals("Campaign #2", campaigns.get(1).getName());
		assertEquals(CampaignObjective.NONE, campaigns.get(1).getObjective());
		assertEquals(0, campaigns.get(1).getSpendCap());
		assertEquals("603123456789", campaigns.get(2).getId());
		assertEquals("123456789", campaigns.get(2).getAccountId());
		assertEquals(BuyingType.RESERVED, campaigns.get(2).getBuyingType());
		assertEquals(CampaignStatus.ARCHIVED, campaigns.get(2).getStatus());
		assertEquals("Campaign #3", campaigns.get(2).getName());
		assertEquals(CampaignObjective.WEBSITE_CONVERSIONS, campaigns.get(2).getObjective());
		assertEquals(50000, campaigns.get(2).getSpendCap());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdCampaigns_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaigns("123456789");
	}

	@Test
	public void getCampaign() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-campaign"), MediaType.APPLICATION_JSON));

		AdCampaign campaign = facebookAds.campaignOperations().getAdCampaign("600123456789");
		assertEquals("600123456789", campaign.getId());
		assertEquals("123456789", campaign.getAccountId());
		assertEquals(BuyingType.AUCTION, campaign.getBuyingType());
		assertEquals(CampaignStatus.ACTIVE, campaign.getStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.POST_ENGAGEMENT, campaign.getObjective());
		assertEquals(1000, campaign.getSpendCap());
	}

	@Test
	public void getCampaign_withUnknownEnums() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-campaign-with-unknown-enums"), MediaType.APPLICATION_JSON));

		AdCampaign campaign = facebookAds.campaignOperations().getAdCampaign("600123456789");
		assertEquals("600123456789", campaign.getId());
		assertEquals("123456789", campaign.getAccountId());
		assertEquals(BuyingType.UNKNOWN, campaign.getBuyingType());
		assertEquals(CampaignStatus.UNKNOWN, campaign.getStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.UNKNOWN, campaign.getObjective());

	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaign("600123456789");
	}

	@Test
	public void createCampaign_withNameOnly() throws Exception {
		String requestBody = "name=Campaign+created+by+SpringSocialFacebook";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setName("Campaign created by SpringSocialFacebook");
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withStatusOnly() throws Exception {
		String requestBody = "campaign_group_status=PAUSED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setStatus(CampaignStatus.PAUSED);
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withInvalidStatus() throws Exception {
		// new campaigns can be created only with statuses ACTIVE or PAUSED
		String requestBody = "name=Campaign+with+invalid+status&campaign_group_status=ARCHIVED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withBadRequest().body(jsonResource("error-invalid-update-campaign-status")).contentType(MediaType.APPLICATION_JSON));

		AdCampaign campaign = new AdCampaign();
		campaign.setName("Campaign with invalid status");
		campaign.setStatus(CampaignStatus.ARCHIVED);
		try {
			facebookAds.campaignOperations().createAdCampaign("123456789", campaign);
			fail();
		} catch (InvalidCampaignStatusException e) {
			assertEquals("New campaigns need to be either active or paused.", e.getMessage());
			assertEquals("facebook", e.getProviderId());
		}
	}

	@Test
	public void createCampaign_withObjectiveOnly() throws Exception {
		String requestBody = "objective=VIDEO_VIEWS";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setObjective(CampaignObjective.VIDEO_VIEWS);
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withSpendCapOnly() throws Exception {
		String requestBody = "spend_cap=240000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setSpendCap(240000);
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withBuyingTypeOnly() throws Exception {
		String requestBody = "buying_type=AUCTION";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setBuyingType(BuyingType.AUCTION);
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}


	@Test
	public void createCampaign_withAllFields() throws Exception {
		String requestBody = "name=Full+campaign&campaign_group_status=ACTIVE&objective=PAGE_LIKES&spend_cap=60000&buying_type=RESERVED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setName("Full campaign");
		campaign.setStatus(CampaignStatus.ACTIVE);
		campaign.setObjective(CampaignObjective.PAGE_LIKES);
		campaign.setSpendCap(60000);
		campaign.setBuyingType(BuyingType.RESERVED);
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", campaign));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().createAdCampaign("123456789", new AdCampaign());
	}

	@Test
	public void updateAdCampaign_withNameOnly() throws Exception {
		String requestBody = "name=New+campaign+name";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": \"true\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setName("New campaign name");
		assertTrue(facebookAds.campaignOperations().updateAdCampaign("600123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void updateAdCampaign_withStatusOnly() throws Exception {
		String requestBody = "campaign_group_status=ACTIVE";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": \"true\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setStatus(CampaignStatus.ACTIVE);
		assertTrue(facebookAds.campaignOperations().updateAdCampaign("600123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void updateAdCampaign_withObjectiveOnly() throws Exception {
		String requestBody = "objective=POST_ENGAGEMENT";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": \"true\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setObjective(CampaignObjective.POST_ENGAGEMENT);
		assertTrue(facebookAds.campaignOperations().updateAdCampaign("600123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void updateAdCampaign_withSpendCapOnly() throws Exception {
		String requestBody = "spend_cap=60000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": \"true\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setSpendCap(60000);
		assertTrue(facebookAds.campaignOperations().updateAdCampaign("600123456789", campaign));
		mockServer.verify();
	}

	@Test
	public void updateAdCampaign_withAllFields() throws Exception {
		String requestBody = "name=Updated+campaign&campaign_group_status=ARCHIVED&objective=CANVAS_APP_ENGAGEMENT&spend_cap=60000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": \"true\"}", MediaType.APPLICATION_JSON));
		AdCampaign campaign = new AdCampaign();
		campaign.setName("Updated campaign");
		campaign.setStatus(CampaignStatus.ARCHIVED);
		campaign.setObjective(CampaignObjective.CANVAS_APP_ENGAGEMENT);
		campaign.setSpendCap(60000);
		assertTrue(facebookAds.campaignOperations().updateAdCampaign("600123456789", campaign));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().updateAdCampaign("600123456789", new AdCampaign());
	}

	@Test
	public void deleteAdCampaign() throws Exception {
		String requestBody = "campaign_group_status=DELETED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().deleteAdCampaign("600123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAdCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().deleteAdCampaign("600123456789");
	}
}
