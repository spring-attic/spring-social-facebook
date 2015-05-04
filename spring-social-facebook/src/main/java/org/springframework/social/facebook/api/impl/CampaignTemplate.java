package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.AdCampaign;
import org.springframework.social.facebook.api.CampaignOperations;
import org.springframework.social.facebook.api.GraphApi;
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

	public int createAdCampaign(String name, AdCampaign.CampaignStatus status) {
		return 0;
	}

	public int createAdCampaign(String name, AdCampaign.CampaignStatus status, AdCampaign.CampaignObjective objective, int spendCap) {
		return 0;
	}

	public int createAdCampaign(String name, AdCampaign.CampaignStatus status, AdCampaign.CampaignObjective objective, int spendCap, AdCampaign.BuyingType buyingType) {
		return 0;
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
