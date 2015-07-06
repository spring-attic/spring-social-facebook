package org.springframework.social.facebook.api.ads;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.PagedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class AdTemplateTest extends AbstractFacebookAdsApiTest {

	@Test
	public void getAccountAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adgroups?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-account"), MediaType.APPLICATION_JSON));

		PagedList<Ad> accountAds = facebookAds.adOperations().getAccountAds("123456789");
		assertEquals(3, accountAds.size());
		assertEquals("101123456789", accountAds.get(0).getId());
		assertEquals("801123456789", accountAds.get(0).getAdSetId());
		assertEquals("701123456789", accountAds.get(0).getCampaignId());
		assertEquals("123456789", accountAds.get(0).getAccountId());
		assertEquals("102123456789", accountAds.get(1).getId());
		assertEquals("802123456789", accountAds.get(1).getAdSetId());
		assertEquals("702123456789", accountAds.get(1).getCampaignId());
		assertEquals("123456789", accountAds.get(1).getAccountId());
		assertEquals("103123456789", accountAds.get(2).getId());
		assertEquals("803123456789", accountAds.get(2).getAdSetId());
		assertEquals("703123456789", accountAds.get(2).getCampaignId());
		assertEquals("123456789", accountAds.get(2).getAccountId());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAccountAds("123456789");
	}

	@Test
	public void getCampaignAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/701123456789/adgroups?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-campaign"), MediaType.APPLICATION_JSON));

		PagedList<Ad> campaignAds = facebookAds.adOperations().getCampaignAds("701123456789");
		assertEquals(3, campaignAds.size());
		assertEquals("101123456789", campaignAds.get(0).getId());
		assertEquals("801123456789", campaignAds.get(0).getAdSetId());
		assertEquals("701123456789", campaignAds.get(0).getCampaignId());
		assertEquals("102123456789", campaignAds.get(1).getId());
		assertEquals("802123456789", campaignAds.get(1).getAdSetId());
		assertEquals("701123456789", campaignAds.get(1).getCampaignId());
		assertEquals("103123456789", campaignAds.get(2).getId());
		assertEquals("803123456789", campaignAds.get(2).getAdSetId());
		assertEquals("701123456789", campaignAds.get(2).getCampaignId());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaignAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getCampaignAds("701123456789");
	}

	@Test
	public void getAdSetAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/800123456789/adgroups?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-ad-set"), MediaType.APPLICATION_JSON));

		PagedList<Ad> adSetAds = facebookAds.adOperations().getAdSetAds("800123456789");
		assertEquals(2, adSetAds.size());
		assertEquals("101123456789", adSetAds.get(0).getId());
		assertEquals("800123456789", adSetAds.get(0).getAdSetId());
		assertEquals("700123456789", adSetAds.get(0).getCampaignId());

		assertEquals("102123456789", adSetAds.get(1).getId());
		assertEquals("800123456789", adSetAds.get(1).getAdSetId());
		assertEquals("701123456789", adSetAds.get(1).getCampaignId());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdSetAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAdSetAds("800123456789");
	}

	@Test
	public void getAd() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(Ad.AdStatus.ACTIVE, ad.getStatus());
		assertEquals(BidType.ABSOLUTE_OCPM, ad.getBidType());
		mockServer.verify();
	}

	@Test
	public void getAd_withWrongStatus() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-wrong-status"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(Ad.AdStatus.UNKNOWN, ad.getStatus());
		assertEquals(BidType.ABSOLUTE_OCPM, ad.getBidType());
		mockServer.verify();
	}

	@Test
	public void getAd_withWrongBidType() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789?fields=id%2Caccount_id%2Cadgroup_status%2Cbid_type%2Cbid_info%2Ccampaign_id%2Ccampaign_group_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-wrong-bid-type"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(Ad.AdStatus.ACTIVE, ad.getStatus());
		assertEquals(BidType.UNKNOWN, ad.getBidType());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAd("100123456789");
	}

	@Test
	public void getAdInsight() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cactions_per_impression%2Cclicks%2Cunique_clicks%2Ccost_per_result%2Ccost_per_total_action%2Ccpc%2Ccost_per_unique_click%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Cobjective%2Creach%2Cresult_rate%2Cresults%2Croas%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctoday_spend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cvideo_start_actions"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.adOperations().getAdInsight("100123456789");
		Assert.assertEquals("123456789", insight.getAccountId());
		Assert.assertEquals("Test account name", insight.getAccountName());
		Assert.assertEquals(0.016042780748663, insight.getActionsPerImpression(), EPSILON);
		Assert.assertEquals(8, insight.getClicks());
		Assert.assertEquals(5, insight.getUniqueClicks());
		Assert.assertEquals(0.66666666666667, insight.getCostPerResult(), EPSILON);
		Assert.assertEquals(0.66666666666667, insight.getCostPerTotalAction(), EPSILON);
		Assert.assertEquals(0.25, insight.getCostPerClick(), EPSILON);
		Assert.assertEquals(0.4, insight.getCostPerUniqueClick(), EPSILON);
		Assert.assertEquals(10.695187165775, insight.getCpm(), EPSILON);
		Assert.assertEquals(10.869565217391, insight.getCpp(), EPSILON);
		Assert.assertEquals(4.2780748663102, insight.getCtr(), EPSILON);
		Assert.assertEquals(2.7173913043478, insight.getUniqueCtr(), EPSILON);
		Assert.assertEquals(1.0163043478261, insight.getFrequency(), EPSILON);
		Assert.assertEquals(187, insight.getImpressions());
		Assert.assertEquals(184, insight.getUniqueImpressions());
		Assert.assertEquals(184, insight.getReach());
		Assert.assertEquals(1.6042780748663, insight.getResultRate(), EPSILON);
		Assert.assertEquals(3, insight.getResults());
		Assert.assertEquals(0, insight.getRoas());
		Assert.assertEquals(0, insight.getSocialClicks());
		Assert.assertEquals(0, insight.getUniqueSocialClicks());
		Assert.assertEquals(0, insight.getSocialImpressions());
		Assert.assertEquals(0, insight.getUniqueSocialImpressions());
		Assert.assertEquals(0, insight.getSocialReach());
		Assert.assertEquals(2, insight.getSpend());
		Assert.assertEquals(0, insight.getTodaySpend());
		Assert.assertEquals(0, insight.getTotalActionValue());
		Assert.assertEquals(3, insight.getTotalActions());
		Assert.assertEquals(2, insight.getTotalUniqueActions());
		Assert.assertEquals(4, insight.getActions().size());
		Assert.assertEquals("comment", insight.getActions().get(0).getActionType());
		Assert.assertEquals(2, insight.getActions().get(0).getValue(), EPSILON);
		Assert.assertEquals("post_like", insight.getActions().get(1).getActionType());
		Assert.assertEquals(1, insight.getActions().get(1).getValue(), EPSILON);
		Assert.assertEquals("page_engagement", insight.getActions().get(2).getActionType());
		Assert.assertEquals(3, insight.getActions().get(2).getValue(), EPSILON);
		Assert.assertEquals("post_engagement", insight.getActions().get(3).getActionType());
		Assert.assertEquals(3, insight.getActions().get(3).getValue(), EPSILON);
		Assert.assertEquals(4, insight.getUniqueActions().size());
		Assert.assertEquals("comment", insight.getUniqueActions().get(0).getActionType());
		Assert.assertEquals(1, insight.getUniqueActions().get(0).getValue(), EPSILON);
		Assert.assertEquals("post_like", insight.getUniqueActions().get(1).getActionType());
		Assert.assertEquals(1, insight.getUniqueActions().get(1).getValue(), EPSILON);
		Assert.assertEquals("page_engagement", insight.getUniqueActions().get(2).getActionType());
		Assert.assertEquals(2, insight.getUniqueActions().get(2).getValue(), EPSILON);
		Assert.assertEquals("post_engagement", insight.getUniqueActions().get(3).getActionType());
		Assert.assertEquals(2, insight.getUniqueActions().get(3).getValue(), EPSILON);
		Assert.assertEquals(4, insight.getCostPerActionType().size());
		Assert.assertEquals("comment", insight.getCostPerActionType().get(0).getActionType());
		Assert.assertEquals(1, insight.getCostPerActionType().get(0).getValue(), EPSILON);
		Assert.assertEquals("post_like", insight.getCostPerActionType().get(1).getActionType());
		Assert.assertEquals(2, insight.getCostPerActionType().get(1).getValue(), EPSILON);
		Assert.assertEquals("page_engagement", insight.getCostPerActionType().get(2).getActionType());
		Assert.assertEquals(0.66666666666667, insight.getCostPerActionType().get(2).getValue(), EPSILON);
		Assert.assertEquals("post_engagement", insight.getCostPerActionType().get(3).getActionType());
		Assert.assertEquals(0.66666666666667, insight.getCostPerActionType().get(3).getValue(), EPSILON);
		Assert.assertEquals(1, insight.getVideoStartActions().size());
		Assert.assertEquals("video_view", insight.getVideoStartActions().get(0).getActionType());
		Assert.assertEquals(0, insight.getVideoStartActions().get(0).getValue(), EPSILON);

		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdInsight_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAdInsight("100123456789");
	}

	@Test
	public void createAd() throws Exception {
		String requestBody = "name=Test+ad&adgroup_status=PAUSED&creative=%7B%22creative_id%22%3A+%22900123456789%22%7D&campaign_id=800123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adgroups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"100123456789\"}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setName("Test ad");
		ad.setStatus(Ad.AdStatus.PAUSED);
		ad.setAdSetId("800123456789");
		ad.setCreativeId("900123456789");
		assertEquals("100123456789", facebookAds.adOperations().createAd("123456789", ad));
		mockServer.verify();
	}

	@Test
	public void createAd_withBidInfo() throws Exception {
		String requestBody = "name=Test+ad&adgroup_status=PAUSED&bid_info=%7B%22REACH%22%3A%2211%22%2C%22ACTIONS%22%3A%2210%22%2C%22SOCIAL%22%3A%2250%22%2C%22CLICKS%22%3A%2212%22%7D&creative=%7B%22creative_id%22%3A+%22900123456789%22%7D&campaign_id=800123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adgroups"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"100123456789\"}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setName("Test ad");
		ad.setStatus(Ad.AdStatus.PAUSED);
		ad.setAdSetId("800123456789");
		ad.setCreativeId("900123456789");
		BidInfo bidInfo = new BidInfo();
		bidInfo.put("ACTIONS", "10");
		bidInfo.put("REACH", "11");
		bidInfo.put("CLICKS", "12");
		bidInfo.put("SOCIAL", "50");
		ad.setBidInfo(bidInfo);
		assertEquals("100123456789", facebookAds.adOperations().createAd("123456789", ad));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().createAd("123456789", new Ad());
	}

	@Test
	public void updateAd() throws Exception {
		String requestBody = "name=Updated+Ad&adgroup_status=ARCHIVED&bid_info=%7B%22CLICKS%22%3A%22500%22%7D";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setStatus(Ad.AdStatus.ARCHIVED);
		ad.setName("Updated Ad");
		BidInfo bidInfo = new BidInfo();
		bidInfo.put("CLICKS", "500");
		ad.setBidInfo(bidInfo);
		assertTrue(facebookAds.adOperations().updateAd("100123456789", ad));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().updateAd("100123456789", new Ad());
	}

	@Test
	public void deleteAd() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/100123456789"))
				.andExpect(method(DELETE))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
		facebookAds.adOperations().deleteAd("100123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().deleteAd("100123456789");
	}

	private void verifyAd(Ad ad) {
		assertEquals("100123456789", ad.getId());
		assertEquals("123456789", ad.getAccountId());
		assertEquals("800123456789", ad.getAdSetId());
		assertEquals("700123456789", ad.getCampaignId());
		assertEquals(toDate("2015-04-10T09:28:54+0200"), ad.getCreatedTime());
		assertEquals("900123456789", ad.getCreativeId());
		assertEquals("Test ad name", ad.getName());
		assertEquals(Integer.valueOf(18), ad.getTargeting().getAgeMin());
		assertEquals(Integer.valueOf(20), ad.getTargeting().getAgeMax());
		assertEquals(6004854404172L, ad.getTargeting().getBehaviors().get(0).getId());
		assertEquals("Technology late adopters", ad.getTargeting().getBehaviors().get(0).getName());
		assertEquals(Targeting.Gender.MALE, ad.getTargeting().getGenders().get(0));
		assertEquals("PL", ad.getTargeting().getGeoLocations().getCountries().get(0));
		assertEquals(TargetingLocation.LocationType.HOME, ad.getTargeting().getGeoLocations().getLocationTypes().get(0));
		assertEquals(TargetingLocation.LocationType.RECENT, ad.getTargeting().getGeoLocations().getLocationTypes().get(1));
		assertEquals(6003629266583L, ad.getTargeting().getInterests().get(0).getId());
		assertEquals("Hard drives", ad.getTargeting().getInterests().get(0).getName());
		assertEquals(Targeting.PageType.FEED, ad.getTargeting().getPageTypes().get(0));
		assertEquals(toDate("2015-04-10T13:32:09+0200"), ad.getUpdatedTime());
	}
}
