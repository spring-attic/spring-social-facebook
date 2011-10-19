/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.social.test.client.RequestMatchers.*;
import static org.springframework.social.test.client.ResponseCreators.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.social.facebook.api.AdAccountGroup.LiteAdAccount;
import org.springframework.social.facebook.api.AdAccountGroup.LiteAdAccountUser;
import org.springframework.social.facebook.api.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.AdGroup.AdGroupStatus;
import org.springframework.social.facebook.api.AdGroup.BidType;
import org.springframework.social.facebook.api.AdTargeting.BroadAge;
import org.springframework.social.facebook.api.AdTargeting.EducationStatus;
import org.springframework.social.facebook.api.AdTargeting.Gender;
import org.springframework.social.facebook.api.AdTargeting.RelationshipStatus;
import org.springframework.social.facebook.api.AdTargeting.UserEvent;

/**
 * Tests for <code>org.springframework.social.facebook.api.AdOperations</code>
 *
 * @author Will Taylor
 */
public class AdTemplateTest extends AbstractFacebookApiTest {

    @Test
    public void getAdAccount() {
        mockServer.expect(requestTo("https://graph.facebook.com/act_123456"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adaccount"), responseHeaders));

        AdAccount adAccount = facebook.adOperations().getAdAccount("123456");
        assertNotNull(adAccount);
        assertEquals("act_123456", adAccount.getId());
        assertEquals("123456", adAccount.getAccountId());
        assertEquals("Will's Test Account", adAccount.getName());
        assertEquals(1, adAccount.getStatus());
        assertEquals("USD", adAccount.getCurrency());
        assertEquals(7, adAccount.getTimezoneId());
        assertEquals("America/New_York", adAccount.getTimezoneName());
        assertEquals(25000, adAccount.getDailySpendLimit());
        assertEquals(Arrays.asList(), adAccount.getCapabilities());
        assertEquals(17, adAccount.getVerticalId());
        assertEquals("Other Services", adAccount.getVerticalName());
        assertEquals(0, adAccount.getIsPersonal());
        assertEquals("Will Taylor", adAccount.getBusinessName());
        assertEquals("123 Fake St", adAccount.getBusinessStreet());
        assertEquals("", adAccount.getBusinessStreet2());
        assertEquals("Springfield", adAccount.getBusinessCity());
        assertEquals("VT", adAccount.getBusinessState());
        assertEquals("12345", adAccount.getBusinessZip());
        assertEquals("US", adAccount.getBusinessCountryCode());
        assertEquals(1, adAccount.getVatStatus());

        List<AdAccountUser> users = adAccount.getUsers();
        assertNotNull(users);
        assertEquals(2, users.size());

        AdAccountUser user = users.get(0);
        assertNotNull(user);
        assertEquals("234567", user.getUid());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 7), user.getPermissions());
        assertEquals(1001, user.getRole());

        user = users.get(1);
        assertNotNull(user);
        assertEquals("345678", user.getUid());
        assertEquals(Arrays.asList(7), user.getPermissions());
        assertEquals(1003, user.getRole());

        List<AdAccountGroup> groups = adAccount.getAccountGroups();
        assertNotNull(groups);
        assertEquals(1, groups.size());
        AdAccountGroup group = groups.get(0);
        assertNotNull(group);
        assertEquals("987654", group.getAccountGroupId());
        assertEquals("Test Account Group 1", group.getName());
        assertEquals(1, group.getStatus());
    }

    @Test
    public void getAdAccountGroupsForUser() {
        mockServer.expect(requestTo("https://graph.facebook.com/123456/adaccountgroups"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adaccountgroups"), responseHeaders));

        List<AdAccountGroup> adAccountGroups = facebook.adOperations().getAdAccountGroupsForUser("123456");
        assertNotNull(adAccountGroups);
        assertEquals(2, adAccountGroups.size());

        AdAccountGroup group = adAccountGroups.get(0);
        assertNotNull(group);
        assertEquals("112233445566", group.getAccountGroupId());
        assertEquals("Test Account Group 2", group.getName());
        assertEquals(1, group.getStatus());
        assertEquals("112233445566", group.getId());

        List<LiteAdAccountUser> users = group.getUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        LiteAdAccountUser user = users.get(0);
        assertNotNull(user);
        assertEquals("12345667", user.getUid());
        assertEquals(1001, user.getRole());

        List<LiteAdAccount> accounts = group.getAccounts();
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        LiteAdAccount account = accounts.get(0);
        assertNotNull(account);
        assertEquals("2345678", account.getAccountId());
        assertEquals(1, account.getStatus());

        group = adAccountGroups.get(1);
        assertNotNull(group);
        assertEquals("223344556677", group.getAccountGroupId());
        assertEquals("Test Account Group 1", group.getName());
        assertEquals(1, group.getStatus());
        assertEquals("223344556677", group.getId());

        users = group.getUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        user = users.get(0);
        assertNotNull(user);
        assertEquals("12345667", user.getUid());
        assertEquals(1001, user.getRole());

        accounts = group.getAccounts();
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        account = accounts.get(0);
        assertNotNull(account);
        assertEquals("1234567", account.getAccountId());
        assertEquals(1, account.getStatus());
    }

    @Test
    public void getAdAccountGroup() {
        mockServer.expect(requestTo("https://graph.facebook.com/112233445566"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adaccountgroup"), responseHeaders));

        AdAccountGroup group = facebook.adOperations().getAdAccountGroup("112233445566");
        assertNotNull(group);
        assertEquals("112233445566", group.getAccountGroupId());
        assertEquals("Test Account Group 2", group.getName());
        assertEquals(1, group.getStatus());
        assertEquals("112233445566", group.getId());

        List<LiteAdAccountUser> users = group.getUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        LiteAdAccountUser user = users.get(0);
        assertNotNull(user);
        assertEquals("12345667", user.getUid());
        assertEquals(1001, user.getRole());

        List<LiteAdAccount> accounts = group.getAccounts();
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        LiteAdAccount account = accounts.get(0);
        assertNotNull(account);
        assertEquals("2345678", account.getAccountId());
        assertEquals(1, account.getStatus());
    }

    @Test public void getAdAccountsForUser() {
        mockServer.expect(requestTo("https://graph.facebook.com/123456/adaccounts"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adaccounts"), responseHeaders));

        List<AdAccount> adAccounts = facebook.adOperations().getAdAccountsForUser("123456");
        assertNotNull(adAccounts);
    }

    @Test
    public void getAdGroups() throws ParseException {
        mockServer.expect(requestTo("https://graph.facebook.com/act_123456/adgroups"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adgroups"), responseHeaders));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        List<AdGroup> adGroups = facebook.adOperations().getAdGroups("123456");
        assertNotNull(adGroups);
        assertEquals(1, adGroups.size());

        AdGroup adGroup = adGroups.get(0);
        assertNotNull(adGroup);
        assertEquals(df.parse("2011-09-09T12:58:18+0000"), adGroup.getUpdatedTime());
        assertEquals("636694801526", adGroup.getAccountId());
        assertEquals("1123456703", adGroup.getAdGroupId());
        assertEquals("1123456703", adGroup.getAdId());
        assertEquals("64819048213", adGroup.getCampaignId());
        assertEquals(Arrays.asList("574017476042"), adGroup.getCreativeIds());
        assertEquals(df.parse("2011-11-09T12:58:18+0000"), adGroup.getEndTime());
        assertEquals("1123456703", adGroup.getId());
        assertEquals("80", adGroup.getMaxBid());
        assertEquals("Test Ad", adGroup.getName());
        assertEquals(df.parse("2011-10-09T12:58:18+0000"), adGroup.getStartTime());
        assertEquals(df.parse("2011-09-09T12:58:18+0000"), adGroup.getUpdatedTime());
        assertEquals(Arrays.asList("The content promoted in your ad or Sponsored Story violates Facebook's Advertising Guidelines. Please visit our Help Center (http://www.facebook.com/help/?page=174952259238535) for additional information and examples compliant with our Advertising Guidelines."),
                    adGroup.getDisapproveReasonDescriptions());

        Map<String, String> bidInfo = new HashMap<String, String>();
        bidInfo.put("1", "80");
        bidInfo.put("37", "0");
        bidInfo.put("38", "0");
        bidInfo.put("44", "0");
        bidInfo.put("45", "0");
        bidInfo.put("46", "20");

        assertEquals(bidInfo, adGroup.getBidInfo());
        assertEquals(AdGroupStatus.PENDING_REVIEW, adGroup.getAdGroupStatus());

        assertEquals(4, adGroup.getAdStatus());
        assertEquals(6, adGroup.getBidType());

        AdTargeting targeting = adGroup.getTargeting();
        assertNotNull(targeting);
        assertEquals("50", targeting.getRadius());
        assertEquals(18, targeting.getAgeMin().intValue());
        assertEquals(64, targeting.getAgeMax().intValue());
        assertEquals(BroadAge.getValue(1), targeting.getBroadAge());
        assertEquals(UserEvent.getValue(1), targeting.getUserEvent());
        assertEquals(Arrays.asList("24", "43", "51", "6"), targeting.getLocales());
        assertEquals(Arrays.asList("05401", "05446", "90210", "90266", "90267"), targeting.getZips());
        assertEquals(Arrays.asList(Gender.getValue(1), Gender.getValue(2)), targeting.getGenders());
        assertEquals(Arrays.asList(Gender.getValue(1), Gender.getValue(2)), targeting.getInterestedIn());
        assertEquals(Arrays.asList("US", "CA"), targeting.getCountries());
        assertEquals(Arrays.asList("dirtbikes"), targeting.getKeywords());
        assertEquals(Arrays.asList("business", "computer science"), targeting.getCollegeMajors());
        assertEquals(Arrays.asList(2012, 2013, 2014, 2015), targeting.getCollegeYears());
        assertEquals(Arrays.asList(RelationshipStatus.getValue(1),
                                   RelationshipStatus.getValue(2),
                                   RelationshipStatus.getValue(3),
                                   RelationshipStatus.getValue(4)), targeting.getRelationshipStatuses());
        assertEquals(Arrays.asList(EducationStatus.getValue(1),
                                   EducationStatus.getValue(2),
                                   EducationStatus.getValue(3)), targeting.getEducationStatuses());

        List<Reference> workNetworks = targeting.getWorkNetworks();
        assertNotNull(workNetworks);
        assertEquals(1, workNetworks.size());
        Reference workNetwork = workNetworks.get(0);
        assertNotNull(workNetwork);
        assertEquals("50432078", workNetwork.getId());
        assertEquals("IBM", workNetwork.getName());

        List<Reference> collegeNetworks = targeting.getCollegeNetworks();
        assertNotNull(collegeNetworks);
        assertEquals(1, collegeNetworks.size());
        Reference collegeNetwork = collegeNetworks.get(0);
        assertNotNull(collegeNetwork);
        assertEquals("16777220", collegeNetwork.getId());
        assertEquals("Yale", collegeNetwork.getName());

        List<Reference> cities = targeting.getCities();
        assertNotNull(cities);
        assertEquals(2, cities.size());

        Reference city = cities.get(0);
        assertNotNull(city);
        assertEquals("2420467", city.getId());
        assertEquals("Manhattan Beach, CA", city.getName());

        city = cities.get(1);
        assertNotNull(city);
        assertEquals("2532970", city.getId());
        assertEquals("Burlington, VT", city.getName());

        List<Reference> regions = targeting.getRegions();
        assertNotNull(regions);
        assertEquals(2, regions.size());

        Reference region = regions.get(0);
        assertNotNull(region);
        assertEquals("50", region.getId());
        assertEquals("Vermont", region.getName());

        region = regions.get(1);
        assertNotNull(region);
        assertEquals("6", region.getId());
        assertEquals("California", region.getName());

        List<Reference> connections = targeting.getConnections();
        assertNotNull(connections);
        assertEquals(1, connections.size());
        Reference connection = connections.get(0);
        assertNotNull(connection);
        assertEquals("125614587505822", connection.getId());
        assertEquals("Demo Motors", connection.getName());

        List<Reference> excludedConnections = targeting.getExcludedConnections();
        assertNotNull(excludedConnections);
        assertEquals(1, excludedConnections.size());
        Reference excludedConnection = excludedConnections.get(0);
        assertNotNull(excludedConnection);
        assertEquals("125614587505822", excludedConnection.getId());
        assertEquals("Demo Motors", excludedConnection.getName());

        List<Reference> friendsOfConnections = targeting.getFriendsOfConnections();
        assertNotNull(friendsOfConnections);
        assertEquals(1, friendsOfConnections.size());
        Reference friendsOfConnection = friendsOfConnections.get(0);
        assertNotNull(friendsOfConnection);
        assertEquals("47190376042235", friendsOfConnection.getId());
        assertEquals("Scuba Steve", friendsOfConnection.getName());

        List<Reference> userAdClusters = targeting.getUserAdClusters();
        assertNotNull(userAdClusters);
        assertEquals(3, userAdClusters.size());

        Reference userAdCluster = userAdClusters.get(0);
        assertNotNull(userAdCluster);
        assertEquals("6002714398172", userAdCluster.getId());
        assertEquals("Newlywed (<1 year)", userAdCluster.getName());

        userAdCluster = userAdClusters.get(1);
        assertNotNull(userAdCluster);
        assertEquals("6002714398772", userAdCluster.getId());
        assertEquals("Engaged (<6 months)", userAdCluster.getName());

        userAdCluster = userAdClusters.get(2);
        assertNotNull(userAdCluster);
        assertEquals("6002714885172", userAdCluster.getId());
        assertEquals("Cooking", userAdCluster.getName());
    }

    @Test
    public void getAdGroup() {
        mockServer.expect(requestTo("https://graph.facebook.com/123456"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adgroup"), responseHeaders));

        AdGroup adGroup = facebook.adOperations().getAdGroup("123456");
        assertNotNull(adGroup);
    }

    @Test public void getCreatives() {
        mockServer.expect(requestTo("https://graph.facebook.com/act_123456/adcreatives"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adcreatives"), responseHeaders));

        List<AdCreative> creatives = facebook.adOperations().getCreatives("123456");
        assertNotNull(creatives);
        assertEquals(5, creatives.size());

        AdCreative creative = creatives.get(0);
        assertNotNull(creative);
        assertEquals("", creative.getViewTag());
        assertEquals(Arrays.asList(), creative.getAltViewTags());
        assertEquals(6003608508993l, creative.getCreativeId());
        assertEquals(2, creative.getType());
        assertEquals("", creative.getTitle());
        assertEquals("this is some ad copy", creative.getBody());
        assertEquals("1b1134eae61cdd6d9be8499b8dcbe663", creative.getImageHash());
        assertEquals("http://www.facebook.com/demomotors?sk=wall", creative.getLinkUrl());
        assertEquals(125614587505822l, creative.getObjectId());
        assertEquals("Demo Motors-00", creative.getName());
        assertEquals(1, creative.getRunStatus());
        assertEquals("http://www.facebook.com/ads/api/creative_preview.php?cid=6003608508993", creative.getPreviewUrl());
        assertEquals(0, creative.getCountCurrentAdGroups());
        assertEquals("6003608508993", creative.getId());
        assertEquals("http://creative.ak.fbcdn.net/v41818/flyers/35/10/1315831679242172105_1_e0cee20a.jpg", creative.getImageUrl());
        assertNull( creative.getUrlTags());
    }

    @Test public void getCreative() {
        mockServer.expect(requestTo("https://graph.facebook.com/123456"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adcreative"), responseHeaders));

        AdCreative creative = facebook.adOperations().getCreative("123456");
        assertNotNull(creative);
        assertEquals("", creative.getViewTag());
        assertEquals(Arrays.asList(), creative.getAltViewTags());
        assertEquals(6003608508993l, creative.getCreativeId());
        assertEquals(2, creative.getType());
        assertEquals("", creative.getTitle());
        assertEquals("this is some ad copy", creative.getBody());
        assertEquals("1b1134eae61cdd6d9be8499b8dcbe663", creative.getImageHash());
        assertEquals("http://www.facebook.com/demomotors?sk=wall", creative.getLinkUrl());
        assertEquals(125614587505822l, creative.getObjectId());
        assertEquals("Demo Motors-00", creative.getName());
        assertEquals(1, creative.getRunStatus());
        assertEquals("http://www.facebook.com/ads/api/creative_preview.php?cid=6003608508993", creative.getPreviewUrl());
        assertEquals(0, creative.getCountCurrentAdGroups());
        assertEquals("6003608508993", creative.getId());
        assertEquals("http://creative.ak.fbcdn.net/v41818/flyers/35/10/1315831679242172105_1_e0cee20a.jpg", creative.getImageUrl());
        assertNull( creative.getUrlTags());
    }

    @Test
    public void getAdCampaigns() throws ParseException {
        mockServer.expect(requestTo("https://graph.facebook.com/act_123456/adcampaigns"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adcampaigns"), responseHeaders));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        List<AdCampaign> adCampaigns = facebook.adOperations().getAdCampaigns("123456");
        assertNotNull(adCampaigns);
        assertEquals(1, adCampaigns.size());

        AdCampaign campaign = adCampaigns.get(0);
        assertNotNull(campaign);
        assertEquals("234567", campaign.getAccountId());
        assertEquals("123456", campaign.getCampaignId());
        assertEquals("My Ads", campaign.getName());
        assertEquals(500, campaign.getDailyBudget());
        assertEquals(CampaignStatus.ACTIVE, campaign.getCampaignStatus());
        assertEquals(0, campaign.getDailyImpressions());
        assertEquals("123456", campaign.getId());
        assertEquals(df.parse("2011-10-31T22:00:00+0000"), campaign.getStartTime());
        assertEquals(df.parse("2011-11-18T22:00:00+0000"), campaign.getEndTime());
        assertEquals(df.parse("2011-10-19T18:37:08+0000"), campaign.getUpdatedTime());
    }

    @Test
    public void getAdCampaign() throws ParseException {
        mockServer.expect(requestTo("https://graph.facebook.com/234567"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adcampaign"), responseHeaders));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        AdCampaign campaign = facebook.adOperations().getAdCampaign("234567");
        assertNotNull(campaign);
        assertEquals("234567", campaign.getAccountId());
        assertEquals("123456", campaign.getCampaignId());
        assertEquals("My Ads", campaign.getName());
        assertEquals(500, campaign.getDailyBudget());
        assertEquals(CampaignStatus.ACTIVE, campaign.getCampaignStatus());
        assertEquals(0, campaign.getDailyImpressions());
        assertEquals("123456", campaign.getId());
        assertEquals(df.parse("2011-10-31T22:00:00+0000"), campaign.getStartTime());
        assertEquals(df.parse("2011-11-18T22:00:00+0000"), campaign.getEndTime());
        assertEquals(df.parse("2011-10-19T18:37:08+0000"), campaign.getUpdatedTime());
    }

    @Test
    public void getAdGroupsForCampaign() {
        mockServer.expect(requestTo("https://graph.facebook.com/234567/adgroups"))
                  .andExpect(method(GET))
                  .andExpect(header("Authorization", "OAuth someAccessToken"))
                  .andRespond(withResponse(jsonResource("testdata/adgroups"), responseHeaders));

        List<AdGroup> adGroups = facebook.adOperations().getAdGroupsForCampaign("234567");
        assertNotNull(adGroups);
        assertEquals(1, adGroups.size());
    }

    @Test
    public void gender() {
        assertEquals(Gender.MALE, Gender.getValue(1));
        assertEquals(1, Gender.MALE.getGender());
        assertEquals(Gender.FEMALE, Gender.getValue(2));
        assertEquals(2, Gender.FEMALE.getGender());

        EnumConstantNotPresentException exception = null;
        try {
            Gender.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            Gender.getValue(3);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void broadAge() {
        assertEquals(BroadAge.ENABLED, BroadAge.getValue(1));
        assertEquals(1, BroadAge.ENABLED.getBroadAge());

        EnumConstantNotPresentException exception = null;
        try {
            BroadAge.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            BroadAge.getValue(2);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void relationshipStatus() {
        assertEquals(RelationshipStatus.SINGLE, RelationshipStatus.getValue(1));
        assertEquals(1, RelationshipStatus.SINGLE.getRelationshipStatus());
        assertEquals(RelationshipStatus.IN_RELATIONSHIP, RelationshipStatus.getValue(2));
        assertEquals(2, RelationshipStatus.IN_RELATIONSHIP.getRelationshipStatus());
        assertEquals(RelationshipStatus.MARRIED, RelationshipStatus.getValue(3));
        assertEquals(3, RelationshipStatus.MARRIED.getRelationshipStatus());
        assertEquals(RelationshipStatus.ENGAGED, RelationshipStatus.getValue(4));
        assertEquals(4, RelationshipStatus.ENGAGED.getRelationshipStatus());

        EnumConstantNotPresentException exception = null;
        try {
            RelationshipStatus.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            RelationshipStatus.getValue(5);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void userEvent() {
        assertEquals(UserEvent.BIRTHDAY, UserEvent.getValue(1));
        assertEquals(1, UserEvent.BIRTHDAY.getUserEvent());

        EnumConstantNotPresentException exception = null;
        try {
            UserEvent.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            UserEvent.getValue(2);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void educationStatus() {
        assertEquals(EducationStatus.HIGH_SCHOOL, EducationStatus.getValue(1));
        assertEquals(1, EducationStatus.HIGH_SCHOOL.getEducationStatus());
        assertEquals(EducationStatus.UNDERGRAD, EducationStatus.getValue(2));
        assertEquals(2, EducationStatus.UNDERGRAD.getEducationStatus());
        assertEquals(EducationStatus.ALUM, EducationStatus.getValue(3));
        assertEquals(3, EducationStatus.ALUM.getEducationStatus());

        EnumConstantNotPresentException exception = null;
        try {
            EducationStatus.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            EducationStatus.getValue(4);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void campaignStatus() {
        assertEquals(CampaignStatus.ACTIVE, CampaignStatus.getValue(1));
        assertEquals(1, CampaignStatus.ACTIVE.getCampaignStatus());
        assertEquals(CampaignStatus.PAUSED, CampaignStatus.getValue(2));
        assertEquals(2, CampaignStatus.PAUSED.getCampaignStatus());
        assertEquals(CampaignStatus.DELETED, CampaignStatus.getValue(3));
        assertEquals(3, CampaignStatus.DELETED.getCampaignStatus());

        EnumConstantNotPresentException exception = null;
        try {
            CampaignStatus.getValue(0);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try {
            CampaignStatus.getValue(4);
        } catch ( EnumConstantNotPresentException e ) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void adGroupStatus() {
        assertEquals(AdGroupStatus.ACTIVE, AdGroupStatus.getValue(1));
        assertEquals(1, AdGroupStatus.ACTIVE.getAdGroupStatus());
        assertEquals(AdGroupStatus.DELETED, AdGroupStatus.getValue(3));
        assertEquals(3, AdGroupStatus.DELETED.getAdGroupStatus());
        assertEquals(AdGroupStatus.PENDING_REVIEW, AdGroupStatus.getValue(4));
        assertEquals(4, AdGroupStatus.PENDING_REVIEW.getAdGroupStatus());
        assertEquals(AdGroupStatus.DISAPPROVED, AdGroupStatus.getValue(5));
        assertEquals(5, AdGroupStatus.DISAPPROVED.getAdGroupStatus());
        assertEquals(AdGroupStatus.CAMPAIGN_PAUSED, AdGroupStatus.getValue(8));
        assertEquals(8, AdGroupStatus.CAMPAIGN_PAUSED.getAdGroupStatus());
        assertEquals(AdGroupStatus.ADGROUP_PAUSED, AdGroupStatus.getValue(9));
        assertEquals(9, AdGroupStatus.ADGROUP_PAUSED.getAdGroupStatus());

        for ( int invalid : Arrays.asList(0, 2, 6, 7, 10) ) {
            EnumConstantNotPresentException exception = null;
            try {
                AdGroupStatus.getValue(invalid);
            } catch ( EnumConstantNotPresentException e ) {
                exception = e;
            }
            assertNotNull(exception);
        }
    }

    @Test
    public void bidType() {
        assertEquals(BidType.CPC, BidType.getValue(1));
        assertEquals(1, BidType.CPC.getBidType());
        assertEquals(BidType.CPM, BidType.getValue(2));
        assertEquals(2, BidType.CPM.getBidType());

        for ( int invalid : Arrays.asList(0, 3) ) {
            EnumConstantNotPresentException exception = null;
            try {
                BidType.getValue(invalid);
            } catch ( EnumConstantNotPresentException e ) {
                exception = e;
            }
            assertNotNull(exception);
        }
    }

}
