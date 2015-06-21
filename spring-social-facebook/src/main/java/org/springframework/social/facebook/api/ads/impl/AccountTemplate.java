package org.springframework.social.facebook.api.ads.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.ads.*;
import org.springframework.social.facebook.api.ads.AdUser.AdUserRole;
import org.springframework.social.facebook.api.impl.AbstractFacebookOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sebastian Górecki
 */
public class AccountTemplate extends AbstractFacebookOperations implements AccountOperations {

	private final GraphApi graphApi;

	private final RestTemplate restTemplate;

	public AccountTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public PagedList<AdAccount> getAdAccounts(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "adaccounts", AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public AdAccount getAdAccount(String accountId) {
		requireAuthorization();
		return graphApi.fetchObject(getAdAccountId(accountId), AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public PagedList<AdCampaign> getAdAccountCampaigns(String accountId) {
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcampaign_groups", AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public PagedList<AdUser> getAdAccountUsers(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "users", AdUser.class);
	}

	public void addUserToAdAccount(String accountId, String userId, AdUserRole role) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("uid", userId);
		map.set("role", String.valueOf(role.getValue()));
		graphApi.post(getAdAccountId(accountId) + "/users", "", map);
	}

	public void deleteUserFromAdAccount(String accountId, String userId) {
		requireAuthorization();
		graphApi.delete(getAdAccountId(accountId) + "/users/" + userId);
	}

	public AdInsight getAdAccountInsight(String accountId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(getAdAccountId(accountId), "insights", AdInsight.class, AD_ACCOUNT_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public boolean updateAdAccount(String accountId, AdAccount adAccount) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		if (adAccount.getName() != null) {
			map.set("name", adAccount.getName());
		}
		if (adAccount.getSpendCap() != null) {
			map.set("spend_cap", adAccount.getSpendCap());
		}
		return graphApi.update(getAdAccountId(accountId), map);
	}
}
