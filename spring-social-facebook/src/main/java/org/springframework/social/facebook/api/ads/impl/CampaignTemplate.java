package org.springframework.social.facebook.api.ads.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.*;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.impl.AbstractFacebookOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Sebastian Górecki
 */
public class CampaignTemplate extends AbstractFacebookOperations implements CampaignOperations {

	private final GraphApi graphApi;

	public CampaignTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public PagedList<AdCampaign> getAdCampaigns(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcampaign_groups", AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public AdCampaign getAdCampaign(String id) {
		requireAuthorization();
		return graphApi.fetchObject(id, AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public PagedList<AdSet> getAdCampaignSets(String campaignId) {
		requireAuthorization();
		return graphApi.fetchConnections(campaignId, "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdInsight getAdCampaignInsight(String campaignId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(campaignId, "insights", AdInsight.class, CampaignOperations.AD_CAMPAIGN_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAdCampaign(String accountId, AdCampaign adCampaign) {
		requireAuthorization();
		MultiValueMap<String, Object> map = mapCommonFields(adCampaign);
		if (adCampaign.getBuyingType() != null) {
			map.add("buying_type", adCampaign.getBuyingType().name());
		}
		return graphApi.publish(getAdAccountId(accountId), "adcampaign_groups", map);
	}

	public boolean updateAdCampaign(String campaignId, AdCampaign adCampaign) {
		requireAuthorization();
		MultiValueMap<String, Object> map = mapCommonFields(adCampaign);
		return graphApi.update(campaignId, map);
	}

	public void deleteAdCampaign(String campaignId) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("campaign_group_status", CampaignStatus.DELETED.name());
		graphApi.post(campaignId, map);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdCampaign adCampaign) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		if (adCampaign.getName() != null) {
			map.add("name", adCampaign.getName());
		}
		if (adCampaign.getStatus() != null) {
			map.add("campaign_group_status", adCampaign.getStatus().name());
		}
		if (adCampaign.getObjective() != null) {
			map.add("objective", adCampaign.getObjective().name());
		}
		if (adCampaign.getSpendCap() != 0) {
			map.add("spend_cap", String.valueOf(adCampaign.getSpendCap()));
		}
		return map;
	}
}