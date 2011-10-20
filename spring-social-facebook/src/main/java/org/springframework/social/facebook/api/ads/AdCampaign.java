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

import java.util.Date;

/**
 * @author Karthick Sankarachary
 */
public class AdCampaign {
	private long id;
	private long accountId;
	private String name;
	private Date startTime;
	private Date endTime;
	private int dailyBudget;
	private int campaignStatus;
	private int lifetimeBudget;
	private int remainingBudget;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(int dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public int getCampaignStatus() {
		return campaignStatus;
	}

	public void setCampaignStatus(int campaignStatus) {
		this.campaignStatus = campaignStatus;
	}

	public int getLifetimeBudget() {
		return lifetimeBudget;
	}

	public void setLifetimeBudget(int lifetimeBudget) {
		this.lifetimeBudget = lifetimeBudget;
	}

	public int getRemainingBudget() {
		return remainingBudget;
	}

	public void setRemainingBudget(int remainingBudget) {
		this.remainingBudget = remainingBudget;
	}

}
