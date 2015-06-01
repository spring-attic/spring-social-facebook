package org.springframework.social.facebook.api.ads;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.*;
import org.springframework.social.facebook.api.ads.AdAccount.Capabilities;
import org.springframework.social.facebook.api.ads.AdAccount.TaxStatus;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.ads.AdUser.AdUserPermission;
import org.springframework.social.facebook.api.ads.AdUser.AdUserRole;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class AccountTemplateTest extends AbstractFacebookAdsApiTest {

	private static final String GET_ADACCOUNT_REQUEST_URI = "https://graph.facebook.com/v2.3/act_123456789?fields=id%2Caccount_id%2Caccount_status%2Cage%2Camount_spent%2Cbalance%2Cbusiness_city%2Cbusiness_country_code%2Cbusiness_name%2Cbusiness_state%2Cbusiness_street%2Cbusiness_street2%2Cbusiness_zip%2Ccapabilities%2Ccreated_time%2Ccurrency%2Cdaily_spend_limit%2Cend_advertiser%2Cfunding_source%2Cfunding_source_details%2Cis_personal%2Cmedia_agency%2Cname%2Coffsite_pixels_tos_accepted%2Cpartner%2Cspend_cap%2Ctimezone_id%2Ctimezone_name%2Ctimezone_offset_hours_utc%2Cusers%2Ctax_id_status";
	private static final String GET_ADACCOUNTS_REQUEST_URI = "https://graph.facebook.com/v2.3/1234/adaccounts?fields=id%2Caccount_id%2Caccount_status%2Cage%2Camount_spent%2Cbalance%2Cbusiness_city%2Cbusiness_country_code%2Cbusiness_name%2Cbusiness_state%2Cbusiness_street%2Cbusiness_street2%2Cbusiness_zip%2Ccapabilities%2Ccreated_time%2Ccurrency%2Cdaily_spend_limit%2Cend_advertiser%2Cfunding_source%2Cfunding_source_details%2Cis_personal%2Cmedia_agency%2Cname%2Coffsite_pixels_tos_accepted%2Cpartner%2Cspend_cap%2Ctimezone_id%2Ctimezone_name%2Ctimezone_offset_hours_utc%2Cusers%2Ctax_id_status";
	private static final String GET_ADACCOUNT_USERS_REQUEST_URI = "https://graph.facebook.com/v2.3/act_123456789/users";
	private static final String GET_ADACCOUNT_INSIGHT = "https://graph.facebook.com/v2.3/act_123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cactions_per_impression%2Cclicks%2Cunique_clicks%2Ccost_per_result%2Ccost_per_total_action%2Ccpc%2Ccost_per_unique_click%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Cobjective%2Creach%2Cresult_rate%2Cresults%2Croas%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctoday_spend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cvideo_start_actions";


	@Test
	public void getAccounts() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNTS_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-accounts"), MediaType.APPLICATION_JSON));

		List<AdAccount> adAccounts = facebookAds.accountOperations().getAdAccounts("1234");
		assertEquals(2, adAccounts.size());
		assertAdAccountsFields(adAccounts);
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccounts_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().getAdAccounts("1234");
	}

	@Test
	public void getAdAccount() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(AdAccount.AccountStatus.ACTIVE, adAccount.getStatus());
		assertEquals(1, adAccount.getUsers().size());
		assertEquals("1234", adAccount.getUsers().get(0).getId());
		assertEquals(AdUserPermission.ACCOUNT_ADMIN, adAccount.getUsers().get(0).getPermissions().get(0));
		assertEquals(AdUserPermission.ADMANAGER_READ, adAccount.getUsers().get(0).getPermissions().get(1));
		assertEquals(AdUserPermission.ADMANAGER_WRITE, adAccount.getUsers().get(0).getPermissions().get(2));
		assertEquals(AdUserRole.ADMINISTRATOR, adAccount.getUsers().get(0).getRole());
	}

	@Test
	public void getAdAccount_withStatusTemporarilyUnavailable() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-temporarily-unavailable"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(AdAccount.AccountStatus.TEMPORARILY_UNAVAILABLE, adAccount.getStatus());
		assertEquals(1, adAccount.getUsers().size());
		assertEquals("1234", adAccount.getUsers().get(0).getId());
		assertEquals(AdUserPermission.ACCOUNT_ADMIN, adAccount.getUsers().get(0).getPermissions().get(0));
		assertEquals(AdUserPermission.ADMANAGER_READ, adAccount.getUsers().get(0).getPermissions().get(1));
		assertEquals(AdUserPermission.ADMANAGER_WRITE, adAccount.getUsers().get(0).getPermissions().get(2));
		assertEquals(AdUserRole.ADMINISTRATOR, adAccount.getUsers().get(0).getRole());
	}

	@Test
	public void getAdAccount_withUnknownCapabilities() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-unknown-capabilities"), MediaType.APPLICATION_JSON));
		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertEquals("act_123456789", adAccount.getId());
		assertEquals(123456789, adAccount.getAccountId());
		assertEquals(4, adAccount.getCapabilities().size());
		assertEquals(Capabilities.UNKNOWN, adAccount.getCapabilities().get(0));
		assertEquals(Capabilities.UNKNOWN, adAccount.getCapabilities().get(1));
		assertEquals(Capabilities.UNKNOWN, adAccount.getCapabilities().get(2));
		assertEquals(Capabilities.PREMIUM, adAccount.getCapabilities().get(3));
	}

	@Test
	public void getAdAccount_withEmptyUsers() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-no-users"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(0, adAccount.getUsers().size());
	}

	@Test
	public void getAdAccount_withFewUsers() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-with-few-users"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(2, adAccount.getUsers().size());
		assertEquals("1234", adAccount.getUsers().get(0).getId());
		assertEquals(AdUserPermission.ACCOUNT_ADMIN, adAccount.getUsers().get(0).getPermissions().get(0));
		assertEquals(AdUserPermission.ADMANAGER_READ, adAccount.getUsers().get(0).getPermissions().get(1));
		assertEquals(AdUserPermission.ADMANAGER_WRITE, adAccount.getUsers().get(0).getPermissions().get(2));
		assertEquals(AdUserRole.ADMINISTRATOR, adAccount.getUsers().get(0).getRole());
		assertEquals("3421", adAccount.getUsers().get(1).getId());
		assertEquals(AdUserPermission.BILLING_WRITE, adAccount.getUsers().get(1).getPermissions().get(0));
		assertEquals(AdUserPermission.REPORTS, adAccount.getUsers().get(1).getPermissions().get(1));
		assertEquals(AdUserRole.ANALYST, adAccount.getUsers().get(1).getRole());
	}

	@Test
	public void getAdAccount_userWithNoPermissions() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-without-permission"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(1, adAccount.getUsers().size());
		assertEquals("1234", adAccount.getUsers().get(0).getId());
		assertEquals(0, adAccount.getUsers().get(0).getPermissions().size());
		assertEquals(AdUserRole.ADMINISTRATOR, adAccount.getUsers().get(0).getRole());
	}

	@Test
	public void getAdAccount_withAgencyClientDeclaration() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-with-agency"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertEquals(1, adAccount.getAgencyClientDeclaration().getAgencyRepresentingClient());
		assertEquals(0, adAccount.getAgencyClientDeclaration().getClientBasedInFrance());
		assertEquals("Warsaw", adAccount.getAgencyClientDeclaration().getClientCity());
		assertEquals("PL", adAccount.getAgencyClientDeclaration().getClientCountryCode());
		assertEquals("example@example.com", adAccount.getAgencyClientDeclaration().getClientEmailAddress());
		assertEquals("Some client", adAccount.getAgencyClientDeclaration().getClientName());
		assertEquals("66-777", adAccount.getAgencyClientDeclaration().getClientPostalCode());
		assertEquals("malopolska", adAccount.getAgencyClientDeclaration().getClientProvince());
		assertEquals("Marszalkowska", adAccount.getAgencyClientDeclaration().getClientStreet());
		assertEquals("1A", adAccount.getAgencyClientDeclaration().getClientStreet2());
		assertEquals(0, adAccount.getAgencyClientDeclaration().getHasWrittenMandateFromAdvertiser());
		assertEquals(1, adAccount.getAgencyClientDeclaration().getIsClientPayingInvoices());
	}

	@Test
	public void getAdAccount_withFundingDetails() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-with-funding-details"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertTrue(adAccount.getFundingSourceDetails().containsKey("id"));
		assertEquals("12345678987654321", adAccount.getFundingSourceDetails().get("id"));
		assertTrue(adAccount.getFundingSourceDetails().containsKey("display_string"));
		assertEquals("Visa *0001", adAccount.getFundingSourceDetails().get("display_string"));
		assertTrue(adAccount.getFundingSourceDetails().containsKey("type"));
		assertEquals(1, adAccount.getFundingSourceDetails().get("type"));
	}

	@Test
	public void getAdAccount_withTosAccepted() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-with-tos-accepted"), MediaType.APPLICATION_JSON));

		AdAccount adAccount = facebookAds.accountOperations().getAdAccount("123456789");
		assertAdAccountFields(adAccount);
		assertTrue(adAccount.getTosAccepted().containsKey("206760949512025"));
		assertEquals((Integer) 1, adAccount.getTosAccepted().get("206760949512025"));
		assertTrue(adAccount.getTosAccepted().containsKey("215449065224656"));
		assertEquals((Integer) 1, adAccount.getTosAccepted().get("215449065224656"));
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdAccount_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().getAdAccount("123456789");
	}

	@Test
	public void getAdAccountCampaigns() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/adcampaign_groups?fields=id%2Caccount_id%2Cbuying_type%2Ccampaign_group_status%2Cname%2Cobjective%2Cspend_cap"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-campaigns"), MediaType.APPLICATION_JSON));
		PagedList<AdCampaign> campaigns = facebookAds.accountOperations().getAdAccountCampaigns("123456789");
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

	@Test
	public void getAccountUsers() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_USERS_REQUEST_URI))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-users"), MediaType.APPLICATION_JSON));

		List<AdUser> adAccountUsers = facebookAds.accountOperations().getAdAccountUsers("123456789");
		assertEquals(3, adAccountUsers.size());
		assertEquals("123456789", adAccountUsers.get(0).getId());
		assertEquals("Account #1", adAccountUsers.get(0).getName());
		assertEquals(6, adAccountUsers.get(0).getPermissions().size());
		assertEquals(AdUserPermission.ACCOUNT_ADMIN, adAccountUsers.get(0).getPermissions().get(0));
		assertEquals(AdUserPermission.ADMANAGER_READ, adAccountUsers.get(0).getPermissions().get(1));
		assertEquals(AdUserPermission.ADMANAGER_WRITE, adAccountUsers.get(0).getPermissions().get(2));
		assertEquals(AdUserPermission.BILLING_READ, adAccountUsers.get(0).getPermissions().get(3));
		assertEquals(AdUserPermission.BILLING_WRITE, adAccountUsers.get(0).getPermissions().get(4));
		assertEquals(AdUserPermission.REPORTS, adAccountUsers.get(0).getPermissions().get(5));
		assertEquals(AdUserRole.ADMINISTRATOR, adAccountUsers.get(0).getRole());
		assertEquals("987654321", adAccountUsers.get(1).getId());
		assertEquals("Account #2", adAccountUsers.get(1).getName());
		assertEquals(1, adAccountUsers.get(1).getPermissions().size());
		assertEquals(AdUserPermission.REPORTS, adAccountUsers.get(1).getPermissions().get(0));
		assertEquals(AdUserRole.ANALYST, adAccountUsers.get(1).getRole());
		assertEquals("1122334455", adAccountUsers.get(2).getId());
		assertEquals("Account #3", adAccountUsers.get(2).getName());
		assertEquals(2, adAccountUsers.get(2).getPermissions().size());
		assertEquals(AdUserPermission.ADMANAGER_READ, adAccountUsers.get(2).getPermissions().get(0));
		assertEquals(AdUserPermission.BILLING_READ, adAccountUsers.get(2).getPermissions().get(1));
		assertEquals(AdUserRole.SALES, adAccountUsers.get(2).getRole());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountUsers_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().getAdAccountUsers("123456789");
	}

	@Test
	public void addUserToAccount() throws Exception {
		String requestBody = "uid=123456&role=1002";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/users/"))
				.andExpect(method(POST))
				.andExpect(content().string(requestBody))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.accountOperations().addUserToAdAccount("123456789", "123456", AdUserRole.ADVERTISER);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void addUserToAccount_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().addUserToAdAccount("123456789", "123456", AdUserRole.ADVERTISER);
	}

	@Test
	public void deleteUserFromAccount() throws Exception {
		String requestBody = "method=delete";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789/users/123456"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		facebookAds.accountOperations().deleteUserFromAdAccount("123456789", "123456");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteUserFromAccount_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().deleteUserFromAdAccount("123456789", "123456");
	}

	@Test
	public void getAccountInsight() throws Exception {
		mockServer.expect(requestTo(GET_ADACCOUNT_INSIGHT))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-account-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.accountOperations().getAdAccountInsight("123456789");
		assertEquals("123456789", insight.getAccountId());
		assertEquals("Account Test Name #1", insight.getAccountName());
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
		assertEquals(1, insight.getRoas());
		assertEquals(2, insight.getSocialClicks());
		assertEquals(3, insight.getUniqueSocialClicks());
		assertEquals(4, insight.getSocialImpressions());
		assertEquals(5, insight.getUniqueSocialImpressions());
		assertEquals(6, insight.getSocialReach());
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
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountInsight_unauthorized() throws Exception {
		unauthorizedFacebookAds.accountOperations().getAdAccountInsight("123456789");
	}

	@Test
	public void updateAdAccount_nameOnly() throws Exception {
		String requestBody = "name=New+Test+Name";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		AdAccount adAccount = new AdAccount();
		adAccount.setName("New Test Name");
		boolean updateStatus = facebookAds.accountOperations().updateAdAccount("123456789", adAccount);
		assertTrue(updateStatus);
		mockServer.verify();
	}

	@Test
	public void updateAdAccount_spendCapOnly() throws Exception {
		String requestBody = "spend_cap=10000";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		AdAccount adAccount = new AdAccount();
		adAccount.setSpendCap("10000");
		boolean updateStatus = facebookAds.accountOperations().updateAdAccount("123456789", adAccount);
		assertTrue(updateStatus);
		mockServer.verify();
	}

	@Test
	public void updateAdAccount_bothNameAndSpendCap() throws Exception {
		String requestBody = "name=Super+cool+name&spend_cap=11111";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.3/act_123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		AdAccount adAccount = new AdAccount();
		adAccount.setName("Super cool name");
		adAccount.setSpendCap("11111");
		boolean updateStatus = facebookAds.accountOperations().updateAdAccount("123456789", adAccount);
		assertTrue(updateStatus);
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAdAccount_unauthorized() throws Exception {
		AdAccount adAccount = new AdAccount();
		adAccount.setName("abc");
		unauthorizedFacebookAds.accountOperations().updateAdAccount("123456789", adAccount);
	}

	private void assertAdAccountsFields(List<AdAccount> adAccounts) {
		assertAdAccountFields(adAccounts.get(0));
		assertAdAccountFields(adAccounts.get(0));
		assertEquals("act_77777777", adAccounts.get(1).getId());
		assertEquals(2, adAccounts.get(1).getAccountGroups().size());
		assertEquals("987654321", adAccounts.get(1).getAccountGroups().get(0).getId());
		assertEquals("Test group name", adAccounts.get(1).getAccountGroups().get(0).getName());
		assertEquals(1, adAccounts.get(1).getAccountGroups().get(0).getStatus());
		assertEquals("11223344", adAccounts.get(1).getAccountGroups().get(1).getId());
		assertEquals("Test group name 2", adAccounts.get(1).getAccountGroups().get(1).getName());
		assertEquals(1, adAccounts.get(1).getAccountGroups().get(1).getStatus());
		assertEquals(77777777, adAccounts.get(1).getAccountId());
		assertEquals(AdAccount.AccountStatus.DISABLED, adAccounts.get(1).getStatus());
		assertEquals(777, adAccounts.get(1).getAge());
		assertEquals("7777", adAccounts.get(1).getAmountSpent());
		assertEquals("77777", adAccounts.get(1).getBalance());
		assertEquals("Warsaw", adAccounts.get(1).getBusinessCity());
		assertEquals("PL", adAccounts.get(1).getBusinessCountryCode());
		assertEquals("Some business name for the account 2", adAccounts.get(1).getBusinessName());
		assertEquals("mazowieckie", adAccounts.get(1).getBusinessState());
		assertEquals("Some street 2", adAccounts.get(1).getBusinessStreet());
		assertEquals(null, adAccounts.get(1).getBusinessStreet2());
		assertEquals("77-777", adAccounts.get(1).getBusinessZip());
		assertEquals(2, adAccounts.get(1).getCapabilities().size());
		assertEquals(Capabilities.DIRECT_SALES, adAccounts.get(1).getCapabilities().get(0));
		assertEquals(Capabilities.VIEW_TAGS, adAccounts.get(1).getCapabilities().get(1));
		assertEquals(toDate("2015-04-20T00:31:33+0100"), adAccounts.get(1).getCreatedTime());
		assertEquals("PLN", adAccounts.get(1).getCurrency());
		assertEquals("77", adAccounts.get(1).getDailySpendLimit());
		assertEquals(987654321, adAccounts.get(1).getEndAdvertiser());
		assertEquals("77", adAccounts.get(1).getFundingSource());
		assertEquals(1, adAccounts.get(1).getIsPersonal());
		assertEquals(54321, adAccounts.get(1).getMediaAgency());
		assertEquals("This is a test account 2", adAccounts.get(1).getName());
		assertEquals(false, adAccounts.get(1).isOffsitePixelsTOSAccepted());
		assertEquals(111222333, adAccounts.get(1).getPartner());
		assertEquals("0", adAccounts.get(1).getSpendCap());
		assertEquals(106, adAccounts.get(1).getTimezoneId());
		assertEquals("Europe/Warsaw", adAccounts.get(1).getTimezoneName());
		assertEquals(2, adAccounts.get(1).getTimezoneOffsetHoursUTC());
		assertEquals(TaxStatus.ACCOUNT_IS_PERSONAL_ACCOUNT, adAccounts.get(1).getTaxStatus());
	}


	private void assertAdAccountFields(AdAccount adAccount) {
		assertEquals("act_123456789", adAccount.getId());
		assertEquals(1, adAccount.getAccountGroups().size());
		assertEquals("987654321", adAccount.getAccountGroups().get(0).getId());
		assertEquals("Test group name", adAccount.getAccountGroups().get(0).getName());
		assertEquals(1, adAccount.getAccountGroups().get(0).getStatus());
		assertEquals(123456789, adAccount.getAccountId());
		assertEquals(10, adAccount.getAge());
		assertEquals("1234", adAccount.getAmountSpent());
		assertEquals("10000", adAccount.getBalance());
		assertEquals("Poznan", adAccount.getBusinessCity());
		assertEquals("PL", adAccount.getBusinessCountryCode());
		assertEquals("Some business name for the account", adAccount.getBusinessName());
		assertEquals("wielkopolska", adAccount.getBusinessState());
		assertEquals("Some street 79A", adAccount.getBusinessStreet());
		assertEquals(null, adAccount.getBusinessStreet2());
		assertEquals("66-777", adAccount.getBusinessZip());
		assertEquals(2, adAccount.getCapabilities().size());
		assertEquals(Capabilities.DIRECT_SALES, adAccount.getCapabilities().get(0));
		assertEquals(Capabilities.VIEW_TAGS, adAccount.getCapabilities().get(1));
		assertEquals(toDate("2015-02-19T00:31:33+0100"), adAccount.getCreatedTime());
		assertEquals("PLN", adAccount.getCurrency());
		assertEquals("93263", adAccount.getDailySpendLimit());
		assertEquals(987654321, adAccount.getEndAdvertiser());
		assertEquals("1122334455", adAccount.getFundingSource());
		assertEquals(1, adAccount.getIsPersonal());
		assertEquals(54321, adAccount.getMediaAgency());
		assertEquals("This is a test account", adAccount.getName());
		assertEquals(false, adAccount.isOffsitePixelsTOSAccepted());
		assertEquals(111222333, adAccount.getPartner());
		assertEquals("0", adAccount.getSpendCap());
		assertEquals(106, adAccount.getTimezoneId());
		assertEquals("Europe/Warsaw", adAccount.getTimezoneName());
		assertEquals(2, adAccount.getTimezoneOffsetHoursUTC());
		assertEquals(TaxStatus.VAT_INFORMATION_SUBMITTED, adAccount.getTaxStatus());
	}
}
