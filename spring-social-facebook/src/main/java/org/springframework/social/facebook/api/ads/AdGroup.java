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
import java.util.List;

/**
 * @author Karthick Sankarachary
 */
public class AdGroup {
	private long adId;
	private long campaignId;
	private String name;
	private AdGroupStatus adGroupStatus;
	private BidType bidType;
	private String maxBid;
	private Targeting targeting;
	private AdCreative creative;
	private long adGroupId;
	private Date endTime;
	private Date startTime;
	private Date updatedTime;
	private BidInfo bidInfo;
	private List<String> disapproveReasonDescriptions;

	public long getAdId() {
		return adId;
	}

	public void setAdId(long adId) {
		this.adId = adId;
	}

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdGroupStatus getAdGroupStatus() {
		return adGroupStatus;
	}

	public void setAdGroupStatus(AdGroupStatus adGroupStatus) {
		this.adGroupStatus = adGroupStatus;
	}

	public BidType getBidType() {
		return bidType;
	}

	public void setBidType(BidType bidType) {
		this.bidType = bidType;
	}

	public String getMaxBid() {
		return maxBid;
	}

	public void setMaxBid(String maxBid) {
		this.maxBid = maxBid;
	}

	public Targeting getTargeting() {
		return targeting;
	}

	public void setTargeting(Targeting targeting) {
		this.targeting = targeting;
	}

	public AdCreative getCreative() {
		return creative;
	}

	public void setCreative(AdCreative creative) {
		this.creative = creative;
	}

	public long getAdGroupId() {
		return adGroupId;
	}

	public void setAdGroupId(long adGroupId) {
		this.adGroupId = adGroupId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public BidInfo getBidInfo() {
		return bidInfo;
	}

	public void setBidInfo(BidInfo bidInfo) {
		this.bidInfo = bidInfo;
	}

	public List<String> getDisapproveReasonDescriptions() {
		return disapproveReasonDescriptions;
	}

	public void setDisapproveReasonDescriptions(
			List<String> disapproveReasonDescriptions) {
		this.disapproveReasonDescriptions = disapproveReasonDescriptions;
	}

}
