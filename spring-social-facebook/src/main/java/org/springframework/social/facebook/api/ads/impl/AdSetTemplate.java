package org.springframework.social.facebook.api.ads.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.AdInsight;
import org.springframework.social.facebook.api.ads.AdSet;
import org.springframework.social.facebook.api.ads.AdSetOperations;
import org.springframework.social.facebook.api.impl.AbstractFacebookOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sebastian Górecki
 */
public class AdSetTemplate extends AbstractFacebookOperations implements AdSetOperations {
	private GraphApi graphApi;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;

	public AdSetTemplate(GraphApi graphApi, RestTemplate restTemplate, ObjectMapper mapper, boolean authorized) {
		super(authorized);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}

	public PagedList<AdSet> getAdSets(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public PagedList<AdSet> getCampaignAdSets(String campaignId) {
		requireAuthorization();
		return graphApi.fetchConnections(campaignId, "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdSet getAdSet(String id) {
		requireAuthorization();
		return graphApi.fetchObject(id, AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdInsight getAdSetInsight(String adSetId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(adSetId, "insights", AdInsight.class, AdSetOperations.AD_SET_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAdSet(String accountId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
		data.set("campaign_group_id", adSet.getCampaignId());
		return graphApi.publish(getAdAccountId(accountId), "adcampaigns", data);
	}

	public boolean updateAdSet(String adSetId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
		return graphApi.update(adSetId, data);
	}

	public void deleteAdSet(String adSetId) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("campaign_status", "DELETED");
		graphApi.post(adSetId, data);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdSet adSet) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		if (adSet.getName() != null) {
			data.set("name", adSet.getName());
		}
		if (adSet.getStatus() != null) {
			data.set("campaign_status", adSet.getStatus().name());
		}
		data.set("is_autobid", String.valueOf(adSet.isAutobid()));
		if (adSet.getBidInfo() != null) {
			try {
				data.set("bid_info", mapper.writeValueAsString(adSet.getBidInfo()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getBidType() != null) {
			data.set("bid_type", adSet.getBidType().name());
		}
		data.set("daily_budget", String.valueOf(adSet.getDailyBudget()));
		data.set("lifetime_budget", String.valueOf(adSet.getLifetimeBudget()));
		if (adSet.getTargeting() != null) {
			try {
				data.set("targeting", mapper.writeValueAsString(adSet.getTargeting()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getStartTime() != null) {
			data.set("start_time", getUnixTime(adSet.getStartTime()));
		}
		if (adSet.getEndTime() != null) {
			data.set("end_time", getUnixTime(adSet.getEndTime()));
		}
		return data;
	}
}