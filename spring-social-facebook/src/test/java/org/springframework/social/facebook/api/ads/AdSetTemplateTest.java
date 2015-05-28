package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.ads.AdSet.AdSetStatus;
import org.springframework.social.facebook.api.ads.AdSet.BidType;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class AdSetTemplateTest extends AbstractFacebookAdsApiTest {

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
	public void createAdSet() throws Exception {
		String requestBody = "date_format=U&campaign_group_id=600123456789&name=Test+AdSet&campaign_status=PAUSED&is_autobid=true&bid_type=ABSOLUTE_OCPM&daily_budget=0&lifetime_budget=200&targeting=%7B%22geo_locations%22%3A%7B%22countries%22%3A%5B%22PL%22%5D%7D%7D&end_time=1432231200";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaigns"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"701123456789\"}", MediaType.APPLICATION_JSON));
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

		assertEquals("701123456789", facebookAds.adSetOperations().createAdSet("123456789", adSet));
		mockServer.verify();
	}

	@Test
	public void createAdSet_withAllFields() throws Exception {
		String requestBody = "date_format=U&campaign_group_id=601123456789&name=Test+AdSet+2&campaign_status=ACTIVE&is_autobid=false&" +
				"bid_info=%7B%22REACH%22%3A%221000%22%2C%22ACTIONS%22%3A%22200%22%2C%22SOCIAL%22%3A%22110%22%2C%22CLICKS%22%3A%22500%22%7D&" +
				"bid_type=ABSOLUTE_OCPM&daily_budget=4000&lifetime_budget=0&" +
				"targeting=%7B%22genders%22%3A%5B1%2C2%5D%2C%22age_min%22%3A45%2C%22age_max%22%3A55%2C%22relationship_statuses%22%3A%5B10%2C12%5D%2C%22interested_in%22%3A%5B1%2C2%5D%2C%22geo_locations%22%3A%7B%22countries%22%3A%5B%22PL%22%2C%22DE%22%2C%22US%22%2C%22FR%22%5D%2C%22regions%22%3A%5B%7B%22key%22%3A%223847%22%7D%2C%7B%22key%22%3A%221111%22%7D%2C%7B%22key%22%3A%221234%22%7D%2C%7B%22key%22%3A%229888%22%7D%5D%2C%22cities%22%3A%5B%7B%22key%22%3A%222430536%22%2C%22radius%22%3A12%2C%22distance_unit%22%3A%22mile%22%7D%2C%7B%22key%22%3A%22777555%22%2C%22radius%22%3A1024%2C%22distance_unit%22%3A%22kilometer%22%7D%5D%2C%22zips%22%3A%5B%7B%22key%22%3A%22PL%3A62030%22%7D%2C%7B%22key%22%3A%22US%3A88123%22%7D%2C%7B%22key%22%3A%22FR%3A33144%22%7D%5D%2C%22location_types%22%3A%5B%22home%22%2C%22recent%22%5D%7D%2C%22excluded_geo_locations%22%3A%7B%22countries%22%3A%5B%22HU%22%2C%22JP%22%5D%2C%22regions%22%3A%5B%7B%22key%22%3A%221122%22%7D%2C%7B%22key%22%3A%2231415%22%7D%5D%2C%22cities%22%3A%5B%7B%22key%22%3A%2288997766%22%2C%22radius%22%3A12345%2C%22distance_unit%22%3A%22mile%22%7D%5D%2C%22zips%22%3A%5B%7B%22key%22%3A%22JP%3A44552%22%7D%5D%2C%22location_types%22%3A%5B%22home%22%5D%7D%2C%22page_types%22%3A%5B%22desktopfeed%22%2C%22mobilefeed-and-external%22%5D%2C%22connections%22%3A%5B%22123456789%22%2C%2255442211%22%5D%2C%22excluded_connections%22%3A%5B%2233441122%22%5D%2C%22friends_of_connections%22%3A%5B%22987654321%22%5D%2C%22interests%22%3A%5B%7B%22id%22%3A986123123123%2C%22name%22%3A%22Football%22%7D%5D%2C%22behaviors%22%3A%5B%7B%22id%22%3A1%2C%22name%22%3A%22Some+behavior%22%7D%5D%2C%22education_schools%22%3A%5B%7B%22id%22%3A10593123549%2C%22name%22%3A%22Poznan+University+of+Technology%22%7D%5D%2C%22education_statuses%22%3A%5B9%5D%2C%22college_years%22%3A%5B8%5D%2C%22education_majors%22%3A%5B%7B%22id%22%3A12%2C%22name%22%3A%22Some+major%22%7D%5D%2C%22work_employers%22%3A%5B%7B%22id%22%3A43125%2C%22name%22%3A%22Super+company%22%7D%5D%2C%22work_positions%22%3A%5B%7B%22id%22%3A11111%2C%22name%22%3A%22Developer%22%7D%5D%7D&" +
				"start_time=1432742400&end_time=1435420799";
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
		bidInfo.put("ACTIONS", "200");
		bidInfo.put("REACH", "1000");
		bidInfo.put("CLICKS", "500");
		bidInfo.put("SOCIAL", "110");
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
}
