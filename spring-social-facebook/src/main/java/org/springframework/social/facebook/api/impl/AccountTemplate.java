package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.AdUser.AdUserRole;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

	public List<AdAccount> getAdAccounts(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "adaccounts", AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public AdAccount getAdAccount(String id) {
		requireAuthorization();
		return graphApi.fetchObject(id, AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public PagedList<AdCampaign> getAdAccountCampaigns(String id) {
		return graphApi.fetchConnections(id, "adcampaign_groups", AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public List<AdUser> getAdAccountUsers(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(accountId, "users", AdUser.class);
	}

//	problem with Facebook Marketing API
//	public void addUserToAdAccount(String accountId, String userId, AdUserRole role) {
//		requireAuthorization();
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//		map.set("uid", userId);
//		map.set("role", String.valueOf(serializeRole(role)));
//		graphApi.post(accountId + "/users", "", map);
//	}
//
//	public void deleteUserFromAdAccount(String accountId, String userId) {
//		requireAuthorization();
//		graphApi.delete(accountId + "/users/" + userId);
//	}
//
	public AdInsight getAdAccountInsight(String accountId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(accountId, "insights", AdInsight.class, AD_ACCOUNT_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public void updateAdAccountName(String accountId, String name) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("name", name);
		graphApi.post(accountId, "", map);
	}

	public void updateAdAccountSpendCap(String accountId, int spendCap) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("spend_cap", String.valueOf(spendCap));
		graphApi.post(accountId, "", map);
	}

	private int serializeRole(AdUserRole role) {
		switch (role) {
			case ADMINISTRATOR:
				return 1001;
			case ADVERTISER:
				return 1002;
			case ANALYST:
				return 1003;
			case SALES:
				return 1004;
			case UNKNOWN:
			default:
				return 0;
		}
	}

	private String join(String[] strings) {
		StringBuilder builder = new StringBuilder();
		if (strings.length > 0) {
			builder.append(strings[0]);
			for (int i = 1; i < strings.length; i++) {
				builder.append("," + strings[i]);
			}
		}
		return builder.toString();
	}

}
