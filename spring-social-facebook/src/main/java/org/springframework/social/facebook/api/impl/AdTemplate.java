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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.AdAccount;
import org.springframework.social.facebook.api.AdAccountGroup;
import org.springframework.social.facebook.api.AdCampaign;
import org.springframework.social.facebook.api.AdCreative;
import org.springframework.social.facebook.api.AdGroup;
import org.springframework.social.facebook.api.AdOperations;
import org.springframework.social.facebook.api.GraphApi;

/**
 * Facebook Advertising API Operations
 *
 * @author Will Taylor
 *
 */
public class AdTemplate extends AbstractFacebookOperations implements AdOperations {

    private static final String ACT_PREFIX = "act_";

    private final GraphApi graphApi;
    
    public AdTemplate(GraphApi graphApi, boolean isAuthorized) {
        super(isAuthorized);
        this.graphApi = graphApi;
    }

    @Override
    public List<AdAccountGroup> getAdAccountGroupsForUser(String userId) {
        return graphApi.fetchConnections(userId, "adaccountgroups", AdAccountGroup.class);
    }

    @Override
    public AdAccountGroup getAdAccountGroup(String adAccountGroupId) {
        return graphApi.fetchObject(adAccountGroupId, AdAccountGroup.class);
    }

    @Override
    public List<AdAccount> getAdAccountsForUser(String userId) {
        return graphApi.fetchConnections(userId, "adaccounts", AdAccount.class);
    }

    @Override
    public AdAccount getAdAccount(String adAccountId) {
        return graphApi.fetchObject(ACT_PREFIX + adAccountId, AdAccount.class);
    }

    @Override
    public List<AdGroup> getAdGroups(String adAccountId) {
        return graphApi.fetchConnections(ACT_PREFIX + adAccountId, "adgroups", AdGroup.class);
    }

    @Override
    public AdGroup getAdGroup(String adGroupId) {
        return graphApi.fetchObject(adGroupId, AdGroup.class);
    }

    @Override
    public List<AdCreative> getCreatives(String adAccountId) {
        return graphApi.fetchConnections(ACT_PREFIX + adAccountId, "adcreatives", AdCreative.class);
    }

    @Override
    public AdCreative getCreative(String creativeId) {
        return graphApi.fetchObject(creativeId, AdCreative.class);
    }

    @Override
    public List<AdCampaign> getAdCampaigns(String adAccountId) {
        return graphApi.fetchConnections(ACT_PREFIX + adAccountId, "adcampaigns", AdCampaign.class);
    }

    @Override
    public AdCampaign getAdCampaign(String campaignId) {
        return graphApi.fetchObject(campaignId, AdCampaign.class);
    }

    @Override
    public List<AdGroup> getAdGroupsForCampaign(String campaignId) {
        return graphApi.fetchConnections(campaignId, "adgroups", AdGroup.class);
    }

}
