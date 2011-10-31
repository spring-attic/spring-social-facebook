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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ResultSet;
import org.springframework.social.facebook.api.ads.AdGroup;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.AdGroupStats;
import org.springframework.social.facebook.api.ads.Stats;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * The <code>AdGroupTemplate</code> implements the {@link AdGroupOperations}
 * interface} in terms of the {@link #graphApi}.
 * 
 * @author Karthick Sankarachary
 */
class AdGroupTemplate extends AbstractAdsOperations implements
		AdGroupOperations {

	private ObjectMapper objectMapper;

	public AdGroupTemplate(GraphApi graphApi, ObjectMapper objectMapper,
			RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
		this.objectMapper = objectMapper;
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adaccount", "adgroup" };
	}

	public List<AdGroup> getAdGroups(String accountId) {
		requireAuthorization();
		return getConnections(getAccountId(accountId), AdGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List<AdGroup> getAdGroups(List<String> adGroupIds) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("ids", join(adGroupIds));
		return graphApi.fetchObject("", List.class, parameters);
	}

	public AdGroup getAdGroup(String adGroupId) {
		requireAuthorization();
		return graphApi.fetchObject(adGroupId, AdGroup.class);
	}

	public List<AdGroup> getCampaignAdGroups(String campaignId) {
		requireAuthorization();
		return getConnections(getPath(campaignId, "adgroups"), AdGroup.class);
	}

	public Identifier createAdGroup(String accountId, AdGroup adGroup) {
		requireAuthorization();
		String id = graphApi.publish(getAccountId(accountId), "adgroups",
				getAdGroupData(adGroup));
		return new Identifier(id);
	}

	public boolean updateAdGroup(String adGroupId, AdGroup adGroup) {
		requireAuthorization();
		return graphApi.update(adGroupId, getAdGroupData(adGroup));
	}

	public boolean deleteAdGroup(String adGroupId) {
		requireAuthorization();
		String status = graphApi.delete(adGroupId);
		return Boolean.valueOf(status);
	}

	public AdGroupStats getAdGroupStats(String adGroupId, long startTime,
			long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.set("start_time", String.valueOf(startTime));
		vars.set("end_time", String.valueOf(endTime));
		return graphApi.fetchObject(getPath(adGroupId, "stats"),
				AdGroupStats.class, vars);
	}

	public List<AdGroupStats> getAdGroupsStats(List<String> adGroupIds,
			long startTime, long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("start_time", String.valueOf(startTime));
		parameters.set("end_time", String.valueOf(endTime));
		parameters.set("ids", join(adGroupIds));
		parameters.set("stats", "");
		@SuppressWarnings("unchecked")
		ResultSet<AdGroupStats> resultSet = graphApi.fetchObject("",
				ResultSet.class);
		return resultSet.getData();
	}

	public List<AdGroupStats> getAdGroupsStats(String accountId,
			long startTime, long endTime) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("start_time", String.valueOf(startTime));
		parameters.set("end_time", String.valueOf(endTime));
		@SuppressWarnings("unchecked")
		ResultSet<AdGroupStats> resultSet = graphApi.fetchObject(
				getPath(getAccountId(accountId), "adgroupstats"),
				ResultSet.class);
		return resultSet.getData();
	}

	public Stats getAdGroupKeywordStats(String adGroupId) {
		requireAuthorization();
		return graphApi.fetchObject(getPath(adGroupId, "keywordstats"),
				Stats.class);
	}

	private MultiValueMap<String, Object> getAdGroupData(AdGroup adGroup) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		data.set("ad_id", String.valueOf(adGroup.getAdId()));
		data.set("campaign_id", String.valueOf(adGroup.getCampaignId()));
		data.set("name", adGroup.getName());
		data.set("adgroup_status",
				String.valueOf(adGroup.getAdGroupStatus().getValue()));
		data.set("bid_type", String.valueOf(adGroup.getBidType().getValue()));
		data.set("max_bid", adGroup.getMaxBid());
		try {
			data.set("targeting",
					objectMapper.writeValueAsString(adGroup.getTargeting()));
			data.set("creative",
					objectMapper.writeValueAsString(adGroup.getCreative()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adGroup.setStartTime(new Date());
		adGroup.setEndTime(new Date());
		data.set("adgroup_id", String.valueOf(adGroup.getAdGroupId()));
		data.set("end_time", getUnixTime(adGroup.getEndTime()));
		data.set("start_time", getUnixTime(adGroup.getStartTime()));
		data.set("updated_time", getUnixTime(adGroup.getUpdatedTime()));
		// data.set("bid_info", adGroup.getBidInfo());
		data.set("disapprove_reason_descriptions",
				join(adGroup.getDisapproveReasonDescriptions()));
		return data;
	}
}
