package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.AdCampaign;
import org.springframework.social.facebook.api.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.CampaignOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sebastian Górecki
 */
public class CampaignTemplate extends AbstractFacebookOperations implements CampaignOperations {

	private final GraphApi graphApi;
	private final RestTemplate restTemplate;


	public CampaignTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public AdCampaign getAdCampaign(String id) {
		requireAuthorization();
		return graphApi.fetchObject(id, AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public String createAdCampaign(String accountId, String name, CampaignStatus status) {
		return createAdCampaign(accountId, name, status, null, null);
	}

	public String createAdCampaign(String accountId, String name, CampaignStatus status, CampaignObjective objective, String spendCap) {
		return createAdCampaign(accountId, name, status, objective, spendCap, null);
	}

	public String createAdCampaign(String accountId, String name, CampaignStatus status, CampaignObjective objective, String spendCap, BuyingType buyingType) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("name", name);
		map.add("campaign_group_status", status.name());
		if (objective != null) {
			map.add("objective", objective.name());
		}
		if (spendCap != null) {
			map.add("spend_cap", spendCap);
		}
		if (buyingType != null) {
			map.add("buying_type", buyingType.name());
		}
		return graphApi.publish("act_" + accountId, "adcampaign_groups", map);
	}

	public void updateAdCampaignName(String campaignId, String name) {

	}

	public void updateAdCampaignStatus(String campaignId, AdCampaign.CampaignStatus status) {

	}

	public void updateAdCampaignObjective(String campaignId, AdCampaign.CampaignObjective objective) {

	}

	public void updateAdCampaignSpendCap(String campaignId, int spendCap) {

	}

	public void deleteAdCampaign(String id) {

	}
}
