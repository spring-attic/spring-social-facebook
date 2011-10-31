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
package org.springframework.social.facebook.api.ads;

import java.util.List;

/**
 * The <code>AdAccount</code> represents an ad account for managing ads on
 * Facebook.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adaccount/">Ad
 *      Account</a>
 * 
 * @author Karthick Sankarachary
 */
public class AdAccount {

	private long accountId;
	private String name;
	private AccountStatus accountStatus;
	private int dailySpendLimit;
	private List<User> users;
	private String currency;
	private int timezoneId;
	private String timezoneName;
	private List<Object> capabilities;
	private List<AdAccountGroup> accountGroups;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getDailySpendLimit() {
		return dailySpendLimit;
	}

	public void setDailySpendLimit(int dailySpendLimit) {
		this.dailySpendLimit = dailySpendLimit;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(int timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public List<Object> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Object> capabilities) {
		this.capabilities = capabilities;
	}

	public List<AdAccountGroup> getAccountGroups() {
		return accountGroups;
	}

	public void setAccountGroups(List<AdAccountGroup> accountGroups) {
		this.accountGroups = accountGroups;
	}

}
