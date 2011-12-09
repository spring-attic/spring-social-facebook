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

import java.util.List;

/**
 * Facebook Advertising API Operations
 *
 * @author Will Taylor
 *
 */
public interface AdOperations {

    List<AdAccountGroup> getAdAccountGroupsForUser(String userId);

    AdAccountGroup getAdAccountGroup(String adAccountGroupId);

    List<AdAccount> getAdAccountsForUser(String userId);

    AdAccount getAdAccount(String adAccountId);

    List<AdGroup> getAdGroups(String adAccountId);

    AdGroup getAdGroup(String adGroupId);

    List<AdCreative> getCreatives(String adAccountId);

    AdCreative getCreative(String creativeId);

    List<AdCampaign> getAdCampaigns(String adAccountId);

    AdCampaign getAdCampaign(String campaignId);

    List<AdGroup> getAdGroupsForCampaign(String campaignId);

}
