package org.springframework.social.facebook.api;

import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.AdCampaign.CampaignStatus;

import static org.junit.Assert.assertEquals;
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
	public void getCampaign() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-campaign"), MediaType.APPLICATION_JSON));

		AdCampaign campaign = facebookAds.campaignOperations().getAdCampaign("600123456789");
		assertEquals("600123456789", campaign.getId());
		assertEquals("123456789", campaign.getAccountId());
		assertEquals(BuyingType.AUCTION, campaign.getBuyingType());
		assertEquals(CampaignStatus.ACTIVE, campaign.getCampaignStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.POST_ENGAGEMENT, campaign.getObjective());
		assertEquals("1000", campaign.getSpendCap());
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
		assertEquals(CampaignStatus.UNKNOWN, campaign.getCampaignStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.UNKNOWN, campaign.getObjective());

	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaign("600123456789");
	}

	@Test
	public void createCampaign_withNameAndStatus() throws Exception {
		String requestBody = "name=Campaign+created+by+SpringSocialFacebook&campaign_group_status=PAUSED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", "Campaign created by SpringSocialFacebook", CampaignStatus.PAUSED));
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

		try {
			facebookAds.campaignOperations().createAdCampaign("123456789", "Campaign with invalid status", CampaignStatus.ARCHIVED);
			fail();
		} catch (InvalidCampaignStatusException e) {
			assertEquals("New campaigns need to be either active or paused.", e.getMessage());
			assertEquals("facebook", e.getProviderId());
		}
	}

	@Test
	public void createCampaign_withObjective() throws Exception {
		String requestBody = "name=Campaign+with+objective&campaign_group_status=ACTIVE&objective=PAGE_LIKES&spend_cap=50000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", "Campaign with objective",
				CampaignStatus.ACTIVE, CampaignObjective.PAGE_LIKES, "50000"));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withoutSpendCap() throws Exception {
		String requestBody = "name=Campaign+with+spend+cap&campaign_group_status=ACTIVE&objective=PAGE_LIKES";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", "Campaign with spend cap",
				CampaignStatus.ACTIVE, CampaignObjective.PAGE_LIKES, null));
		mockServer.verify();
	}

	@Test
	public void createCampaign_withBuyingType() throws Exception {
		String requestBody = "name=Campaign+with+objective&campaign_group_status=ACTIVE&objective=PAGE_LIKES&spend_cap=50000&buying_type=AUCTION";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"601123456789\"}", MediaType.APPLICATION_JSON));
		assertEquals("601123456789", facebookAds.campaignOperations().createAdCampaign("123456789", "Campaign with objective",
				CampaignStatus.ACTIVE, CampaignObjective.PAGE_LIKES, "50000", BuyingType.AUCTION));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().createAdCampaign("123456789", "Campaign created by SpringSocialFacebook", CampaignStatus.PAUSED);
	}

	@Test
	public void updateAdCampaignName() throws Exception {
		String requestBody = "name=New+campaign+name";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().updateAdCampaignName("600123456789", "New campaign name");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdCampaignName_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().updateAdCampaignName("600123456789", "New campaign name");
	}

	@Test
	public void updateAdCampaignStatus() throws Exception {
		String requestBody = "status=ACTIVE";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().updateAdCampaignStatus("600123456789", CampaignStatus.ACTIVE);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdCampaignStatus_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().updateAdCampaignStatus("600123456789", CampaignStatus.ACTIVE);
	}

	@Test
	public void updateAdCampaignObjective() throws Exception {
		String requestBody = "objective=POST_ENGAGEMENT";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().updateAdCampaignObjective("600123456789", CampaignObjective.POST_ENGAGEMENT);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdCampaignObjective_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().updateAdCampaignObjective("600123456789", CampaignObjective.POST_ENGAGEMENT);
	}

	@Test
	public void updateAdCampaignSpendCap() throws Exception {
		String requestBody = "spend_cap=60000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().updateAdCampaignSpendCap("600123456789", 60000);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdCampaignSpendCap_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().updateAdCampaignSpendCap("600123456789", 60000);
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
