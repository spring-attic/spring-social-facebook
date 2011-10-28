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

import java.util.Date;
import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ResultSet;
import org.springframework.social.facebook.api.ads.AdCampaign;
import org.springframework.social.facebook.api.ads.AdCampaignList;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CampaignStats;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * The <code>CampaignTemplate</code> implements the {@link CampaignOperations}
 * interface} in terms of the {@link #graphApi}.
 * 
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

	public List<AdCampaign> getCampaigns(List<String> campaignIds,
			MultiValueMap<String, String> vars) {
		requireAuthorization();
		vars.set("ids", join(campaignIds));
		AdCampaignList resultSet = graphApi.fetchObject("",
				AdCampaignList.class, vars);
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

	public List<CampaignStats> getCampaignsStats(String accountId,
			long startTime, long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("date_format", "U");
		parameters.set("start_time", getUnixTime(startTime));
		parameters.set("end_time", getUnixTime(endTime));
		@SuppressWarnings("unchecked")
		ResultSet<CampaignStats> resultSet = graphApi.fetchObject(
				getPath(getAccountId(accountId), "adcampaignstats"),
				ResultSet.class);
		return resultSet.getData();
	}

	public List<CampaignStats> getCampaignsStats(List<String> campaignIds,
			long startTime, long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("date_format", "U");
		parameters.set("start_time", getUnixTime(startTime));
		parameters.set("end_time", getUnixTime(endTime));
		parameters.set("ids", join(campaignIds));
		parameters.set("stats", "");
		@SuppressWarnings("unchecked")
		ResultSet<CampaignStats> resultSet = graphApi.fetchObject("",
				ResultSet.class);
		return resultSet.getData();
	}

	public CampaignStats getCampaignStats(String campaignId, long startTime,
			long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("date_format", "U");
		parameters.set("start_time", getUnixTime(startTime));
		parameters.set("end_time", getUnixTime(endTime));
		return graphApi.fetchObject(getPath(campaignId, "stats"),
				CampaignStats.class);
	}

	public Identifier createCampaign(String accountId, AdCampaign campaign) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		data.set("name", campaign.getName());
		data.set("start_time", getUnixTime(new Date()));
		data.set("end_time", getUnixTime(campaign.getEndTime()));
		data.set("daily_budget", String.valueOf(campaign.getDailyBudget()));
		data.set("campaign_status",
				String.valueOf(campaign.getCampaignStatus()));
		data.set("lifetime_budget",
				String.valueOf(campaign.getLifetimeBudget()));
		String id = graphApi.publish(getAccountId(accountId), "adcampaigns",
				data);
		return new Identifier(id);
	}

	public boolean updateCampaign(String campaignId, AdCampaign campaign) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		data.set("name", campaign.getName());
		data.set("start_time", getUnixTime(campaign.getStartTime()));
		data.set("end_time", getUnixTime(campaign.getEndTime()));
		data.set("daily_budget", String.valueOf(campaign.getDailyBudget()));
		data.set("campaign_status",
				String.valueOf(campaign.getCampaignStatus()));
		data.set("lifetime_budget",
				String.valueOf(campaign.getLifetimeBudget()));
		return graphApi.update(campaignId, data);
	}

	public boolean deleteCampaign(String campaignId) {
		requireAuthorization();
		String status = graphApi.delete(campaignId);
		return Boolean.valueOf(status);
	}
}
