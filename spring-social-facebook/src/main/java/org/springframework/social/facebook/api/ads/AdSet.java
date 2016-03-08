package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.social.facebook.api.FacebookObject;

import java.util.Date;
import java.util.List;

/**
 * Model class representing an ad set.
 *
 * @author Sebastian Górecki
 */
public class AdSet extends FacebookObject {
	private String id;
	private String accountId;
	private String campaignId;
	private String name;
	private AdSetStatus status;

	private boolean autobid;
	private BidInfo bidInfo;
	private BidType bidType;

	private int budgetRemaining;
	private int dailyBudget;
	private int lifetimeBudget;

	private List<String> creativeSequence;
	private PromotedObject promotedObject;
	private Targeting targeting;

	private Date startTime;
	private Date endTime;
	private Date createdTime;
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdSetStatus getStatus() {
		return status;
	}

	public void setStatus(AdSetStatus status) {
		this.status = status;
	}

	public boolean isAutobid() {
		return autobid;
	}

	public void setAutobid(boolean autobid) {
		this.autobid = autobid;
	}

	public BidInfo getBidInfo() {
		return bidInfo;
	}

	public void setBidInfo(BidInfo bidInfo) {
		this.bidInfo = bidInfo;
	}

	public BidType getBidType() {
		return bidType;
	}

	public void setBidType(BidType bidType) {
		this.bidType = bidType;
	}

	public int getBudgetRemaining() {
		return budgetRemaining;
	}

	public int getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(int dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public int getLifetimeBudget() {
		return lifetimeBudget;
	}

	public void setLifetimeBudget(int lifetimeBudget) {
		this.lifetimeBudget = lifetimeBudget;
	}

	public List<String> getCreativeSequence() {
		return creativeSequence;
	}

	public PromotedObject getPromotedObject() {
		return promotedObject;
	}

	public void setPromotedObject(PromotedObject promotedObject) {
		this.promotedObject = promotedObject;
	}

	public Targeting getTargeting() {
		return targeting;
	}

	public void setTargeting(Targeting targeting) {
		this.targeting = targeting;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public enum AdSetStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, CAMPAIGN_GROUP_PAUSED, UNKNOWN;

		@JsonCreator
		public static AdSetStatus fromValue(String value) {
			for (AdSetStatus status : AdSetStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}
}
