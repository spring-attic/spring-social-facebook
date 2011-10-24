/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads.impl;

import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ResultSet;
import org.springframework.social.facebook.api.ads.AdCampaign;
import org.springframework.social.facebook.api.ads.AdCampaignList;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.Id;
import org.springframework.social.facebook.api.ads.Stats;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Karthick Sankarachary
 */
class CampaignTemplate extends AbstractAdsOperations implements
		CampaignOperations {
	public CampaignTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adgroup" };
	}

	public List<AdCampaign> getCampaigns(String accountId) {
		requireAuthorization();
		AdCampaignList resultSet = graphApi.fetchObject(getAccountId(accountId)
				+ "/adcampaigns", AdCampaignList.class);
		return resultSet.getData();
	}

	public List<AdCampaign> getCampaigns(
			List<String> campaignIds, MultiValueMap<String, String> vars) {
		requireAuthorization();
		vars.set("ids", join(campaignIds));
		AdCampaignList resultSet = graphApi.fetchObject("", AdCampaignList.class, vars);
		return resultSet.getData();
	}
	
	public List<AdCampaign> getCampaigns(String accountId,
			List<String> campaignIds, MultiValueMap<String, String> vars) {
		requireAuthorization();
		AdCampaignList resultSet = graphApi.fetchObject(getAccountId(accountId)
				+ "/adcampaigns", AdCampaignList.class, vars);
		return resultSet.getData();
	}

	public AdCampaign getCampaign(String campaignId) {
		requireAuthorization();
		return graphApi.fetchObject(campaignId, AdCampaign.class);
	}

	public List<Stats> getCampaignsStats(String accountId, long startTime,
			long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("start_time", String.valueOf(startTime));
		parameters.set("end_time", String.valueOf(endTime));
		@SuppressWarnings("unchecked")
		ResultSet<Stats> resultSet = graphApi.fetchObject(
				getPath(getAccountId(accountId), "adcampaignstats"),
				ResultSet.class);
		return resultSet.getData();
	}

	public List<Stats> getCampaignsStats(List<String> campaignIds,
			long startTime, long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("start_time", String.valueOf(startTime));
		parameters.set("end_time", String.valueOf(endTime));
		parameters.set("ids", join(campaignIds));
		parameters.set("stats", "");
		@SuppressWarnings("unchecked")
		ResultSet<Stats> resultSet = graphApi.fetchObject("", ResultSet.class);
		return resultSet.getData();
	}

	public Stats getCampaignStats(String campaignId, long startTime,
			long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("start_time", String.valueOf(startTime));
		parameters.set("end_time", String.valueOf(endTime));
		return graphApi.fetchObject(getPath(campaignId, "stats"), Stats.class);
	}

	public Id createCampaign(String accountId, AdCampaign campaign) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", campaign.getName());
		data.set("start_time", campaign.getStartTime());
		data.set("end_time", campaign.getEndTime());
		data.set("daily_budget", campaign.getDailyBudget());
		data.set("campaign_status", campaign.getCampaignStatus());
		data.set("lifetime_budget", campaign.getLifetimeBudget());
		String id = graphApi.publish(getAccountId(accountId), "adcampaigns",
				data);
		return new Id(id);
	}

	public boolean updateCampaign(String campaignId, AdCampaign campaign) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", campaign.getName());
		data.set("start_time", campaign.getStartTime());
		data.set("end_time", campaign.getEndTime());
		data.set("daily_budget", campaign.getDailyBudget());
		data.set("campaign_status", campaign.getCampaignStatus());
		data.set("lifetime_budget", campaign.getLifetimeBudget());
		return graphApi.update(campaignId, data);
	}

	public boolean deleteCampaign(String campaignId) {
		requireAuthorization();
		String status = graphApi.delete(campaignId);
		return Boolean.valueOf(status);
	}
}
