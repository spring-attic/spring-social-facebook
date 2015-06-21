package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.InvalidCampaignStatusException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;

import static org.junit.Assert.*;
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
		mockServer.verify();
	}

	@Test
	public void getCampaign_withEmptyBuyingType() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/609123456789?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-campaign-empty-buying-type"), MediaType.APPLICATION_JSON));

		AdCampaign campaign = facebookAds.campaignOperations().getAdCampaign("609123456789");
		assertEquals("609123456789", campaign.getId());
		assertEquals("123456789", campaign.getAccountId());
		assertNull(campaign.getBuyingType());
		assertEquals(CampaignStatus.ACTIVE, campaign.getStatus());
		assertEquals("The test campaign name", campaign.getName());
		assertEquals(CampaignObjective.POST_ENGAGEMENT, campaign.getObjective());
		assertEquals(1000, campaign.getSpendCap());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaign("600123456789");
	}

	@Test
	public void getAdCampaignSets() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789/adcampaigns?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-sets"), MediaType.APPLICATION_JSON));

		PagedList<AdSet> adSets = facebookAds.campaignOperations().getAdCampaignSets("600123456789");
		assertEquals(2, adSets.size());
		assertEquals("123456789", adSets.get(0).getAccountId());
		assertEquals(BidType.ABSOLUTE_OCPM, adSets.get(0).getBidType());
		assertEquals(37407, adSets.get(0).getBudgetRemaining());
		assertEquals("600123456789", adSets.get(0).getCampaignId());
		assertEquals(AdSet.AdSetStatus.PAUSED, adSets.get(0).getStatus());
		assertEquals(toDate("2015-05-27T11:58:34+0200"), adSets.get(0).getCreatedTime());
		assertEquals(40000, adSets.get(0).getDailyBudget());
		assertEquals(toDate("2015-05-29T22:26:40+0200"), adSets.get(0).getEndTime());
		assertEquals("700123456789", adSets.get(0).getId());
		assertTrue(adSets.get(0).isAutobid());
		assertEquals(0, adSets.get(0).getLifetimeBudget());
		assertEquals("Test AdSet", adSets.get(0).getName());
		assertEquals(toDate("2015-05-27T11:58:34+0200"), adSets.get(0).getStartTime());
		assertEquals(Integer.valueOf(65), adSets.get(0).getTargeting().getAgeMax());
		assertEquals(Integer.valueOf(18), adSets.get(0).getTargeting().getAgeMin());
		assertEquals("BR", adSets.get(0).getTargeting().getGeoLocations().getCountries().get(0));
		assertEquals(TargetingLocation.LocationType.HOME, adSets.get(0).getTargeting().getGeoLocations().getLocationTypes().get(0));
		assertEquals(toDate("2015-05-27T11:58:34+0200"), adSets.get(0).getUpdatedTime());

		assertEquals("123456789", adSets.get(1).getAccountId());
		assertEquals(BidType.ABSOLUTE_OCPM, adSets.get(1).getBidType());
		assertEquals(0, adSets.get(1).getBudgetRemaining());
		assertEquals("600123456789", adSets.get(1).getCampaignId());
		assertEquals(AdSet.AdSetStatus.ACTIVE, adSets.get(1).getStatus());
		assertEquals(toDate("2015-04-10T09:28:54+0200"), adSets.get(1).getCreatedTime());
		assertEquals(0, adSets.get(1).getDailyBudget());
		assertEquals(toDate("2015-04-13T09:19:00+0200"), adSets.get(1).getEndTime());
		assertEquals("701123456789", adSets.get(1).getId());
		assertTrue(adSets.get(1).isAutobid());
		assertEquals(200, adSets.get(1).getLifetimeBudget());
		assertEquals("Real ad set", adSets.get(1).getName());
		assertEquals(toDate("2015-04-12T09:19:00+0200"), adSets.get(1).getStartTime());
		assertEquals(Integer.valueOf(20), adSets.get(1).getTargeting().getAgeMax());
		assertEquals(Integer.valueOf(18), adSets.get(1).getTargeting().getAgeMin());
		assertEquals(6004854404172L, adSets.get(1).getTargeting().getBehaviors().get(0).getId());
		assertEquals("Technology late adopters", adSets.get(1).getTargeting().getBehaviors().get(0).getName());
		assertEquals(Targeting.Gender.MALE, adSets.get(1).getTargeting().getGenders().get(0));
		assertEquals("PL", adSets.get(1).getTargeting().getGeoLocations().getCountries().get(0));
		assertEquals(TargetingLocation.LocationType.HOME, adSets.get(1).getTargeting().getGeoLocations().getLocationTypes().get(0));
		assertEquals(TargetingLocation.LocationType.RECENT, adSets.get(1).getTargeting().getGeoLocations().getLocationTypes().get(1));
		assertEquals(6003629266583L, adSets.get(1).getTargeting().getInterests().get(0).getId());
		assertEquals("Hard drives", adSets.get(1).getTargeting().getInterests().get(0).getName());
		assertEquals(Targeting.PageType.FEED, adSets.get(1).getTargeting().getPageTypes().get(0));
		assertEquals(toDate("2015-04-10T13:32:09+0200"), adSets.get(1).getUpdatedTime());

		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdCampaignSets_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaignSets("600123456789");
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
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
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
		facebookAds.campaignOperations().deleteAdCampaign("600123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAdCampaign_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().deleteAdCampaign("600123456789");
	}

	@Test
	public void getAdCampaignInsights() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cactions_per_impression%2Cclicks%2Cunique_clicks%2Ccost_per_result%2Ccost_per_total_action%2Ccpc%2Ccost_per_unique_click%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Cobjective%2Creach%2Cresult_rate%2Cresults%2Croas%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctoday_spend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cvideo_start_actions"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-campaign-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.campaignOperations().getAdCampaignInsight("600123456789");
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
	public void getAdCampaignInsights_unauthorized() throws Exception {
		unauthorizedFacebookAds.campaignOperations().getAdCampaignInsight("600123456789");
	}
}
