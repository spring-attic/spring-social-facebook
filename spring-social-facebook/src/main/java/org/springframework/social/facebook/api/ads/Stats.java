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
public class Stats {
	private String id;
	private long accountId;
	private Date startTime;
	private Date endTime;
	private int impressions;
	private int clicks;
	private int spent;
	private int socialImpressions;
	private int socialClicks;
	private int uniqueImpressions;
	private int socialUniqueImpressions;
	private int uniqueClicks;
	private int socialUniqueClicks;
	private int connections;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getImpressions() {
		return impressions;
	}

	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getSpent() {
		return spent;
	}

	public void setSpent(int spent) {
		this.spent = spent;
	}

	public int getSocialImpressions() {
		return socialImpressions;
	}

	public void setSocialImpressions(int socialImpressions) {
		this.socialImpressions = socialImpressions;
	}

	public int getSocialClicks() {
		return socialClicks;
	}

	public void setSocialClicks(int socialClicks) {
		this.socialClicks = socialClicks;
	}

	public int getUniqueImpressions() {
		return uniqueImpressions;
	}

	public void setUniqueImpressions(int uniqueImpressions) {
		this.uniqueImpressions = uniqueImpressions;
	}

	public int getSocialUniqueImpressions() {
		return socialUniqueImpressions;
	}

	public void setSocialUniqueImpressions(int socialUniqueImpressions) {
		this.socialUniqueImpressions = socialUniqueImpressions;
	}

	public int getUniqueClicks() {
		return uniqueClicks;
	}

	public void setUniqueClicks(int uniqueClicks) {
		this.uniqueClicks = uniqueClicks;
	}

	public int getSocialUniqueClicks() {
		return socialUniqueClicks;
	}

	public void setSocialUniqueClicks(int socialUniqueClicks) {
		this.socialUniqueClicks = socialUniqueClicks;
	}

	public int getConnections() {
		return connections;
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
