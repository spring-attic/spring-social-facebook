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

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdAccount;
import org.springframework.social.facebook.api.ads.ReachEstimate;
import org.springframework.social.facebook.api.ads.Stats;
import org.springframework.social.facebook.api.ads.Targeting;
import org.springframework.social.facebook.api.ads.User;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Karthick Sankarachary
 */
class AccountTemplate extends AbstractAdsOperations implements
		AccountOperations {

	private RestTemplate restTemplate;

	public AccountTemplate(GraphApi graphApi, RestTemplate restTemplate,
			boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
		this.restTemplate = restTemplate;
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adcampaign", "adcreative", "adgroup", "adimages" };
	}

	@SuppressWarnings("unchecked")
	public List<AdAccount> getAccounts(String userId) {
		requireAuthorization();
		return graphApi.fetchObject(userId + "/adaccounts", List.class);
	}

	public AdAccount getAccount(String accountId) {
		requireAuthorization();
		return graphApi.fetchObject(getAccountId(accountId), AdAccount.class);
	}

	public List<User> getAccountUsers(String accountId) {
		requireAuthorization();
		List<User> users = graphApi.fetchConnections(accountId, "users",
				User.class);
		return users;
	}

	public Stats getAccountStats(String accountId) {
		requireAuthorization();
		return graphApi.fetchObject(getPath(getAccountId(accountId), "stats"),
				Stats.class);
	}

	public <T> List<Stats> getAccountConnectionStats(String accountId,
			Class<T> connectionType) {
		requireAuthorization();
		return graphApi.fetchConnections(getAccountId(accountId),
				getConnectionType(connectionType), Stats.class);
	}

	public ReachEstimate getReachEstimate(String accountId, String currency,
			Targeting targetingSpec) {
		requireAuthorization();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("currency", currency);
		if (targetingSpec != null) {
			parameters.set("targeting_spec", targetingSpec.toString());
		}
		if (targetingSpec == null) {
			targetingSpec = new Targeting();
			targetingSpec.setCountries(Arrays.asList("US"));
		}
		parameters.set("countries", "US");
		ObjectMapper mapper = new ObjectMapper();
		String targeting = null;
		try {
			targeting = mapper.writeValueAsString(targetingSpec);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameters.set("targeting_spec", targeting);
		URI uri = URIBuilder.fromUri(GraphApi.GRAPH_API_URL + getAccountId(accountId) + "/reachestimate")
				.queryParams(parameters).build();
		return restTemplate.getForObject(uri, ReachEstimate.class);
	}

}
