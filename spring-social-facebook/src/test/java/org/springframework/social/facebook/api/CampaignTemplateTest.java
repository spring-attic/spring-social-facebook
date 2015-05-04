package org.springframework.social.facebook.api;

import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.AdCampaign.CampaignStatus;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
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
		assertEquals(CampaignStatus.UNKNOWN, campaign.getCampaignStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.UNKNOWN, campaign.getObjective());

	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaign("600123456789");
	}


}
