package org.springframework.social.facebook.api.ads.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.Ad;
import org.springframework.social.facebook.api.ads.AdInsight;
import org.springframework.social.facebook.api.ads.AdOperations;
import org.springframework.social.facebook.api.impl.AbstractFacebookOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sebastian Górecki
 */
public class AdTemplate extends AbstractFacebookOperations implements AdOperations {

	private final GraphApi graphApi;
	private final RestTemplate restTemplate;
	private ObjectMapper mapper;

	public AdTemplate(GraphApi graphApi, RestTemplate restTemplate, ObjectMapper mapper, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}

	public PagedList<Ad> getAccountAds(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public PagedList<Ad> getCampaignAds(String campaignId) {
		requireAuthorization();
		return graphApi.fetchConnections(campaignId, "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public PagedList<Ad> getAdSetAds(String adSetId) {
		requireAuthorization();
		return graphApi.fetchConnections(adSetId, "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public Ad getAd(String adId) {
		requireAuthorization();
		return graphApi.fetchObject(adId, Ad.class, AdOperations.AD_FIELDS);
	}

	public AdInsight getAdInsight(String adId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(adId, "insights", AdInsight.class, AdOperations.AD_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAd(String accountId, Ad ad) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(ad);
		data.add("campaign_id", ad.getAdSetId());
		return graphApi.publish(getAdAccountId(accountId), "adgroups", data);
	}

	public boolean updateAd(String adId, Ad ad) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(ad);
		return graphApi.update(adId, data);
	}

	public void deleteAd(String adId) {
		requireAuthorization();
		restTemplate.delete(GraphApi.GRAPH_API_URL + adId);
	}

	private MultiValueMap<String, Object> mapCommonFields(Ad ad) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		if (ad.getName() != null) {
			data.add("name", ad.getName());
		}
		if (ad.getStatus() != null) {
			data.add("adgroup_status", ad.getStatus().name());
		}
		if (ad.getBidInfo() != null) {
			try {
				data.add("bid_info", mapper.writeValueAsString(ad.getBidInfo()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (ad.getCreativeId() != null) {
			data.add("creative", "{\"creative_id\": \"" + ad.getCreativeId() + "\"}");
		}
		return data;
	}
}
