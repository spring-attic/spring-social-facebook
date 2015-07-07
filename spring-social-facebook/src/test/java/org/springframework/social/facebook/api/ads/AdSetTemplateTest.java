package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.AdSet.AdSetStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class AdSetTemplateTest extends AbstractFacebookAdsApiTest {

	@Test
	public void getAdSets() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaigns?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-sets"), MediaType.APPLICATION_JSON));

		PagedList<AdSet> adSets = facebookAds.adSetOperations().getAccountAdSets("123456789");
		verifyAdSets(adSets);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdSets_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().getAccountAdSets("123456789");
	}

	@Test
	public void getCampaignAdsets() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/600123456789/adcampaigns?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-sets"), MediaType.APPLICATION_JSON));

		PagedList<AdSet> adSets = facebookAds.adSetOperations().getCampaignAdSets("600123456789");
		verifyAdSets(adSets);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaignAdSets_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().getCampaignAdSets("600123456789");
	}

	@Test
	public void getAdSet() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/700123456789?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-set"), MediaType.APPLICATION_JSON));
		AdSet adSet = facebookAds.adSetOperations().getAdSet("700123456789");
		assertEquals("700123456789", adSet.getId());
		assertEquals("123456789", adSet.getAccountId());
		assertEquals("600123456789", adSet.getCampaignId());
		assertEquals("Test AdSet", adSet.getName());
		assertEquals(AdSetStatus.ACTIVE, adSet.getStatus());
		assertTrue(adSet.isAutobid());
		assertEquals(BidType.ABSOLUTE_OCPM, adSet.getBidType());
		assertEquals(50, adSet.getBudgetRemaining());
		assertEquals(0, adSet.getDailyBudget());
		assertEquals(200, adSet.getLifetimeBudget());
		// targeting
		assertEquals(Integer.valueOf(20), adSet.getTargeting().getAgeMax());
		assertEquals(Integer.valueOf(18), adSet.getTargeting().getAgeMin());
		assertEquals(1, adSet.getTargeting().getBehaviors().size());
		assertEquals(6004854404172L, adSet.getTargeting().getBehaviors().get(0).getId());
		assertEquals("Technology late adopters", adSet.getTargeting().getBehaviors().get(0).getName());
		assertEquals(1, adSet.getTargeting().getGenders().size());
		assertEquals(Targeting.Gender.MALE, adSet.getTargeting().getGenders().get(0));
		assertEquals(1, adSet.getTargeting().getGeoLocations().getCountries().size());
		assertEquals("PL", adSet.getTargeting().getGeoLocations().getCountries().get(0));
		assertEquals(2, adSet.getTargeting().getGeoLocations().getRegions().size());
		assertEquals("3847", adSet.getTargeting().getGeoLocations().getRegions().get(0));
		assertEquals("1122", adSet.getTargeting().getGeoLocations().getRegions().get(1));
		assertEquals(2, adSet.getTargeting().getGeoLocations().getCities().size());
		assertEquals("2430536", adSet.getTargeting().getGeoLocations().getCities().get(0).getKey());
		assertEquals(12, adSet.getTargeting().getGeoLocations().getCities().get(0).getRadius());
		assertEquals("mile", adSet.getTargeting().getGeoLocations().getCities().get(0).getDistanceUnit());
		assertEquals("11223344", adSet.getTargeting().getGeoLocations().getCities().get(1).getKey());
		assertEquals(55, adSet.getTargeting().getGeoLocations().getCities().get(1).getRadius());
		assertEquals("kilometer", adSet.getTargeting().getGeoLocations().getCities().get(1).getDistanceUnit());
		assertEquals(2, adSet.getTargeting().getGeoLocations().getZips().size());
		assertEquals("US:94304", adSet.getTargeting().getGeoLocations().getZips().get(0));
		assertEquals("US:00501", adSet.getTargeting().getGeoLocations().getZips().get(1));
		assertEquals(2, adSet.getTargeting().getGeoLocations().getLocationTypes().size());
		assertEquals(TargetingLocation.LocationType.HOME, adSet.getTargeting().getGeoLocations().getLocationTypes().get(0));
		assertEquals(TargetingLocation.LocationType.RECENT, adSet.getTargeting().getGeoLocations().getLocationTypes().get(1));
		assertEquals(1, adSet.getTargeting().getInterests().size());
		assertEquals(6003629266583L, adSet.getTargeting().getInterests().get(0).getId());
		assertEquals("Hard drives", adSet.getTargeting().getInterests().get(0).getName());
		assertEquals(2, adSet.getTargeting().getPageTypes().size());
		assertEquals(Targeting.PageType.FEED, adSet.getTargeting().getPageTypes().get(0));
		assertEquals(Targeting.PageType.DESKTOP_AND_MOBILE_AND_EXTERNAL, adSet.getTargeting().getPageTypes().get(1));
		assertEquals(2, adSet.getTargeting().getRelationshipStatuses().size());
		assertEquals(Targeting.RelationshipStatus.IN_RELATIONSHIP, adSet.getTargeting().getRelationshipStatuses().get(0));
		assertEquals(Targeting.RelationshipStatus.IN_OPEN_RELATIONSHIP, adSet.getTargeting().getRelationshipStatuses().get(1));
		assertEquals(1, adSet.getTargeting().getInterestedIn().size());
		assertEquals(Targeting.InterestedIn.WOMEN, adSet.getTargeting().getInterestedIn().get(0));
		assertEquals(1, adSet.getTargeting().getEducationSchools().size());
		assertEquals(105930651606L, adSet.getTargeting().getEducationSchools().get(0).getId());
		assertEquals("Harvard University", adSet.getTargeting().getEducationSchools().get(0).getName());
		assertEquals(3, adSet.getTargeting().getEducationStatuses().size());
		assertEquals(Targeting.EducationStatus.HIGH_SCHOOL, adSet.getTargeting().getEducationStatuses().get(0));
		assertEquals(Targeting.EducationStatus.MASTER_DEGREE, adSet.getTargeting().getEducationStatuses().get(1));
		assertEquals(Targeting.EducationStatus.SOME_HIGH_SCHOOL, adSet.getTargeting().getEducationStatuses().get(2));
		assertEquals(1, adSet.getTargeting().getWorkEmployers().size());
		assertEquals(50431654L, adSet.getTargeting().getWorkEmployers().get(0).getId());
		assertEquals("Microsoft", adSet.getTargeting().getWorkEmployers().get(0).getName());
		assertEquals(1, adSet.getTargeting().getWorkPositions().size());
		assertEquals(105763692790962L, adSet.getTargeting().getWorkPositions().get(0).getId());
		assertEquals("Business Analyst", adSet.getTargeting().getWorkPositions().get(0).getName());

		assertEquals(toDate("2015-04-12T09:19:00+0200"), adSet.getStartTime());
		assertEquals(toDate("2015-04-13T09:19:00+0200"), adSet.getEndTime());
		assertEquals(toDate("2015-04-10T09:28:54+0200"), adSet.getCreatedTime());
		assertEquals(toDate("2015-04-10T13:32:09+0200"), adSet.getUpdatedTime());
	}

	@Test
	public void getAdSet_wrongStatus() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/709123456789?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-set-wrong-status"), MediaType.APPLICATION_JSON));
		AdSet adSet = facebookAds.adSetOperations().getAdSet("709123456789");
		assertEquals("709123456789", adSet.getId());
		assertEquals("123456789", adSet.getAccountId());
		assertEquals("600123456789", adSet.getCampaignId());
		assertEquals("Test AdSet", adSet.getName());
		assertEquals(AdSetStatus.UNKNOWN, adSet.getStatus());
		mockServer.verify();
	}

	@Test
	public void getAdSet_wrongBidType() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/710123456789?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-set-wrong-bid-type"), MediaType.APPLICATION_JSON));
		AdSet adSet = facebookAds.adSetOperations().getAdSet("710123456789");
		assertEquals("710123456789", adSet.getId());
		assertEquals("123456789", adSet.getAccountId());
		assertEquals("600123456789", adSet.getCampaignId());
		assertEquals("Test AdSet", adSet.getName());
		assertEquals(AdSetStatus.ACTIVE, adSet.getStatus());
		assertEquals(BidType.UNKNOWN, adSet.getBidType());
		mockServer.verify();
	}

	@Test
	public void getAdSet_withPromotedObject() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/705123456789?fields=account_id%2Cbid_info%2Cbid_type%2Cbudget_remaining%2Ccampaign_group_id%2Ccampaign_status%2Ccreated_time%2Ccreative_sequence%2Cdaily_budget%2Cend_time%2Cid%2Cis_autobid%2Clifetime_budget%2Cname%2Cpromoted_object%2Cstart_time%2Ctargeting%2Cupdated_time"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-set-promoted-object"), MediaType.APPLICATION_JSON));
		AdSet adSet = facebookAds.adSetOperations().getAdSet("705123456789");
		assertEquals("705123456789", adSet.getId());
		assertEquals("123456789", adSet.getAccountId());
		assertEquals(Integer.valueOf(500), adSet.getBidInfo().get("CLICKS"));
		assertEquals(BidType.ABSOLUTE_OCPM, adSet.getBidType());
		assertEquals(807, adSet.getBudgetRemaining());
		assertEquals("600123456789", adSet.getCampaignId());
		assertEquals(AdSetStatus.PAUSED, adSet.getStatus());
		assertEquals(2000, adSet.getDailyBudget());
		assertFalse(adSet.isAutobid());
		assertEquals(0, adSet.getLifetimeBudget());
		assertEquals("Test promoted object", adSet.getName());
		assertEquals("999888777666555", adSet.getPromotedObject().get("page_id"));
		assertEquals(toDate("2015-07-06T14:18:55+0200"), adSet.getCreatedTime());
		assertEquals(toDate("2015-07-06T14:18:55+0200"), adSet.getStartTime());
		assertEquals(toDate("2015-07-06T14:18:55+0200"), adSet.getUpdatedTime());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdSet_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().getAdSet("700123456789");
	}

	@Test
	public void getAdSetInsights() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/700123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cactions_per_impression%2Cclicks%2Cunique_clicks%2Ccost_per_result%2Ccost_per_total_action%2Ccpc%2Ccost_per_unique_click%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Cobjective%2Creach%2Cresult_rate%2Cresults%2Croas%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctoday_spend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cvideo_start_actions"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-set-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.adSetOperations().getAdSetInsight("700123456789");

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
	public void getAdSetInsights_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().getAdSetInsight("700123456789");
	}

	@Test
	public void createAdSet() throws Exception {
		String requestBody = "date_format=U&name=Test+AdSet&campaign_status=PAUSED&is_autobid=true&bid_type=ABSOLUTE_OCPM&daily_budget=0&lifetime_budget=200&targeting=%7B%22geo_locations%22%3A%7B%22countries%22%3A%5B%22PL%22%5D%7D%7D&end_time=1432231200&campaign_group_id=600123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaigns"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"701123456789\"}", MediaType.APPLICATION_JSON));
		AdSet adSet = createSampleAdSet();

		assertEquals("701123456789", facebookAds.adSetOperations().createAdSet("123456789", adSet));
		mockServer.verify();
	}

	@Test
	public void createAdSet_withAllFields() throws Exception {
		String requestBody = "date_format=U&name=Test+AdSet+2&campaign_status=ACTIVE&is_autobid=false&" +
				"bid_info=%7B%22REACH%22%3A1000%2C%22ACTIONS%22%3A200%2C%22SOCIAL%22%3A110%2C%22CLICKS%22%3A500%7D&" +
				"bid_type=ABSOLUTE_OCPM&daily_budget=4000&lifetime_budget=0&" +
				"targeting=%7B%22genders%22%3A%5B1%2C2%5D%2C%22age_min%22%3A45%2C%22age_max%22%3A55%2C%22relationship_statuses%22%3A%5B10%2C12%5D%2C%22interested_in%22%3A%5B1%2C2%5D%2C%22geo_locations%22%3A%7B%22countries%22%3A%5B%22PL%22%2C%22DE%22%2C%22US%22%2C%22FR%22%5D%2C%22regions%22%3A%5B%7B%22key%22%3A%223847%22%7D%2C%7B%22key%22%3A%221111%22%7D%2C%7B%22key%22%3A%221234%22%7D%2C%7B%22key%22%3A%229888%22%7D%5D%2C%22cities%22%3A%5B%7B%22key%22%3A%222430536%22%2C%22radius%22%3A12%2C%22distance_unit%22%3A%22mile%22%7D%2C%7B%22key%22%3A%22777555%22%2C%22radius%22%3A1024%2C%22distance_unit%22%3A%22kilometer%22%7D%5D%2C%22zips%22%3A%5B%7B%22key%22%3A%22PL%3A62030%22%7D%2C%7B%22key%22%3A%22US%3A88123%22%7D%2C%7B%22key%22%3A%22FR%3A33144%22%7D%5D%2C%22location_types%22%3A%5B%22home%22%2C%22recent%22%5D%7D%2C%22excluded_geo_locations%22%3A%7B%22countries%22%3A%5B%22HU%22%2C%22JP%22%5D%2C%22regions%22%3A%5B%7B%22key%22%3A%221122%22%7D%2C%7B%22key%22%3A%2231415%22%7D%5D%2C%22cities%22%3A%5B%7B%22key%22%3A%2288997766%22%2C%22radius%22%3A12345%2C%22distance_unit%22%3A%22mile%22%7D%5D%2C%22zips%22%3A%5B%7B%22key%22%3A%22JP%3A44552%22%7D%5D%2C%22location_types%22%3A%5B%22home%22%5D%7D%2C%22page_types%22%3A%5B%22desktopfeed%22%2C%22mobilefeed-and-external%22%5D%2C%22connections%22%3A%5B%22123456789%22%2C%2255442211%22%5D%2C%22excluded_connections%22%3A%5B%2233441122%22%5D%2C%22friends_of_connections%22%3A%5B%22987654321%22%5D%2C%22interests%22%3A%5B%7B%22id%22%3A986123123123%2C%22name%22%3A%22Football%22%7D%5D%2C%22behaviors%22%3A%5B%7B%22id%22%3A1%2C%22name%22%3A%22Some+behavior%22%7D%5D%2C%22education_schools%22%3A%5B%7B%22id%22%3A10593123549%2C%22name%22%3A%22Poznan+University+of+Technology%22%7D%5D%2C%22education_statuses%22%3A%5B9%5D%2C%22college_years%22%3A%5B8%5D%2C%22education_majors%22%3A%5B%7B%22id%22%3A12%2C%22name%22%3A%22Some+major%22%7D%5D%2C%22work_employers%22%3A%5B%7B%22id%22%3A43125%2C%22name%22%3A%22Super+company%22%7D%5D%2C%22work_positions%22%3A%5B%7B%22id%22%3A11111%2C%22name%22%3A%22Developer%22%7D%5D%7D&" +
				"start_time=1432742400&end_time=1435420799&campaign_group_id=601123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaigns"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"702123456789\"}", MediaType.APPLICATION_JSON));
		AdSet adSet = new AdSet();
		adSet.setCampaignId("601123456789");
		adSet.setName("Test AdSet 2");
		adSet.setStatus(AdSetStatus.ACTIVE);
		adSet.setAutobid(false);
		BidInfo bidInfo = new BidInfo();
		bidInfo.put("ACTIONS", 200);
		bidInfo.put("REACH", 1000);
		bidInfo.put("CLICKS", 500);
		bidInfo.put("SOCIAL", 110);
		adSet.setBidInfo(bidInfo);
		adSet.setBidType(BidType.ABSOLUTE_OCPM);
		adSet.setDailyBudget(4000);
		adSet.setLifetimeBudget(0);
		// targeting
		Targeting targeting = new Targeting();
		targeting.setGenders(Arrays.asList(Targeting.Gender.MALE, Targeting.Gender.FEMALE));
		targeting.setAgeMin(45);
		targeting.setAgeMax(55);
		targeting.setRelationshipStatuses(Arrays.asList(Targeting.RelationshipStatus.ITS_COMPLICATED, Targeting.RelationshipStatus.DIVORCED));
		targeting.setInterestedIn(Arrays.asList(Targeting.InterestedIn.MEN, Targeting.InterestedIn.WOMEN));
		// targeting - geoLocations
		TargetingLocation geoLocation = new TargetingLocation();
		geoLocation.setCountries(Arrays.asList("PL", "DE", "US", "FR"));
		geoLocation.setRegions(Arrays.asList("3847", "1111", "1234", "9888"));
		geoLocation.setCities(Arrays.asList(new TargetingCityEntry("2430536", 12, "mile"), new TargetingCityEntry("777555", 1024, "kilometer")));
		geoLocation.setZips(Arrays.asList("PL:62030", "US:88123", "FR:33144"));
		geoLocation.setLocationTypes(Arrays.asList(TargetingLocation.LocationType.HOME, TargetingLocation.LocationType.RECENT));
		targeting.setGeoLocations(geoLocation);
		// targeting - excludedGeoLocations
		TargetingLocation excludedGeoLocations = new TargetingLocation();
		excludedGeoLocations.setCountries(Arrays.asList("HU", "JP"));
		excludedGeoLocations.setRegions(Arrays.asList("1122", "31415"));
		excludedGeoLocations.setCities(Arrays.asList(new TargetingCityEntry("88997766", 12345, "mile")));
		excludedGeoLocations.setZips(Arrays.asList("JP:44552"));
		excludedGeoLocations.setLocationTypes(Arrays.asList(TargetingLocation.LocationType.HOME));
		targeting.setExcludedGeoLocations(excludedGeoLocations);
		// targeting cd.
		targeting.setPageTypes(Arrays.asList(Targeting.PageType.DESKTOP_FEED, Targeting.PageType.MOBILE_FEED_AND_EXTERNAL));
		targeting.setConnections(Arrays.asList("123456789", "55442211"));
		targeting.setExcludedConnections(Arrays.asList("33441122"));
		targeting.setFriendsOfConnections(Arrays.asList("987654321"));
		targeting.setInterests(Arrays.asList(new TargetingEntry(986123123123L, "Football")));
		targeting.setBehaviors(Arrays.asList(new TargetingEntry(1L, "Some behavior")));
		targeting.setEducationSchools(Arrays.asList(new TargetingEntry(10593123549L, "Poznan University of Technology")));
		targeting.setEducationStatuses(Arrays.asList(Targeting.EducationStatus.MASTER_DEGREE));
		targeting.setCollegeYears(Arrays.asList(Integer.valueOf(8)));
		targeting.setEducationMajors(Arrays.asList(new TargetingEntry(12L, "Some major")));
		targeting.setWorkEmployers(Arrays.asList(new TargetingEntry(43125L, "Super company")));
		targeting.setWorkPositions(Arrays.asList(new TargetingEntry(11111L, "Developer")));
		adSet.setTargeting(targeting);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		adSet.setStartTime(formatter.parse("2015-05-27 18:00:00"));
		adSet.setEndTime(formatter.parse("2015-06-27 17:59:59"));

		assertEquals("702123456789", facebookAds.adSetOperations().createAdSet("123456789", adSet));
		mockServer.verify();
	}

	@Test
	public void createAdSet_withPromotedObject() throws Exception {
		String requestBody = "date_format=U&name=Test+AdSet&campaign_status=PAUSED&is_autobid=true&bid_type=ABSOLUTE_OCPM&daily_budget=0&lifetime_budget=200&promoted_object=%7B%22page_id%22%3A%22111222333444555%22%7D&targeting=%7B%22geo_locations%22%3A%7B%22countries%22%3A%5B%22PL%22%5D%7D%7D&end_time=1432231200&campaign_group_id=600123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaigns"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"702123456789\"}", MediaType.APPLICATION_JSON));

		AdSet adSet = createSampleAdSet();
		PromotedObject promotedObject = new PromotedObject();
		promotedObject.put("page_id", "111222333444555");
		adSet.setPromotedObject(promotedObject);

		assertEquals("702123456789", facebookAds.adSetOperations().createAdSet("123456789", adSet));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createAdSet_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().createAdSet("123456789", new AdSet());
	}

	@Test
	public void updateAdSet() throws Exception {
		String requestBody = "date_format=U&name=New+AdSet+name&campaign_status=ARCHIVED&is_autobid=true&daily_budget=0&lifetime_budget=50000&start_time=1432833720";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/700123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));

		AdSet adSet = new AdSet();
		adSet.setName("New AdSet name");
		adSet.setStatus(AdSetStatus.ARCHIVED);
		adSet.setLifetimeBudget(50000);
		adSet.setAutobid(true);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		adSet.setStartTime(formatter.parse("2015-05-28 19:22:00"));

		assertTrue(facebookAds.adSetOperations().updateAdSet("700123456789", adSet));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdSet_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().updateAdSet("700123456789", new AdSet());
	}

	@Test
	public void deleteAdSet() throws Exception {
		String requestBody = "campaign_status=DELETED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/700123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"status\": \"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.adSetOperations().deleteAdSet("700123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAdSet_unauthorized() throws Exception {
		unauthorizedFacebookAds.adSetOperations().deleteAdSet("700123456789");
	}

	private void verifyAdSets(PagedList<AdSet> adSets) {
		assertEquals(2, adSets.size());
		assertEquals("123456789", adSets.get(0).getAccountId());
		assertEquals(BidType.ABSOLUTE_OCPM, adSets.get(0).getBidType());
		assertEquals(37407, adSets.get(0).getBudgetRemaining());
		assertEquals("600123456789", adSets.get(0).getCampaignId());
		assertEquals(AdSetStatus.PAUSED, adSets.get(0).getStatus());
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
		assertEquals(AdSetStatus.ACTIVE, adSets.get(1).getStatus());
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
	}

	private AdSet createSampleAdSet() throws ParseException {
		AdSet adSet = new AdSet();
		adSet.setAutobid(true);
		adSet.setBidType(BidType.ABSOLUTE_OCPM);
		adSet.setCampaignId("600123456789");
		adSet.setStatus(AdSetStatus.PAUSED);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		adSet.setEndTime(formatter.parse("2015-05-21 20:00:00"));
		adSet.setName("Test AdSet");
		TargetingLocation location = new TargetingLocation();
		location.setCountries(Arrays.asList("PL"));
		Targeting targeting = new Targeting();
		targeting.setGeoLocations(location);
		adSet.setTargeting(targeting);
		adSet.setLifetimeBudget(200);
		return adSet;
	}
}
