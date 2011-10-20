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

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AdAccountGroup;
import org.springframework.social.facebook.api.ads.Id;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Karthick Sankarachary
 */
class AccountGroupTemplate extends AbstractAdsOperations implements
		AccountGroupOperations {

	public AccountGroupTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adaccount", "user" };
	}

	public AdAccountGroup getAccountGroup(String accountGroupId) {
		requireAuthorization();
		return graphApi.fetchObject(accountGroupId, AdAccountGroup.class);
	}

	public Id createAccountGroup(AdAccountGroup accountGroup) {
		requireAuthorization();
		String id = graphApi.publish("me", "adaccountgroups",
				getAccountGroupData(accountGroup));
		return new Id(id);
	}

	public boolean updateAccountGroup(String accountGroupId,
			AdAccountGroup accountGroup) {
		requireAuthorization();
		return graphApi.update(accountGroupId,
				getAccountGroupData(accountGroup));
	}

	public boolean deleteAccountGroup(String accountGroupId) {
		requireAuthorization();
		String status = graphApi.delete(accountGroupId);
		return Boolean.valueOf(status);
	}

	private MultiValueMap<String, Object> getAccountGroupData(
			AdAccountGroup accountGroup) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("id", accountGroup.getId());
		data.set("name", accountGroup.getName());
		data.set("status", accountGroup.getStatus());
		data.set("users", accountGroup.getUsers());
		data.set("accounts", accountGroup.getAccounts());
		return data;
	}
}
