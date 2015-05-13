package org.springframework.social.facebook.api;

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
	private Object bidInfo;
	private BidType bidType;

	private int budgetRemaining;
	private int dailyBudget;
	private int lifetimeBudget;

	private List<String> creativeSequence;
	private Object promotedObject;
	private Object targeting;

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

	public String getName() {
		return name;
	}

	public AdSetStatus getStatus() {
		return status;
	}

	public boolean isAutobid() {
		return autobid;
	}

	public Object getBidInfo() {
		return bidInfo;
	}

	public BidType getBidType() {
		return bidType;
	}

	public int getBudgetRemaining() {
		return budgetRemaining;
	}

	public int getDailyBudget() {
		return dailyBudget;
	}

	public int getLifetimeBudget() {
		return lifetimeBudget;
	}

	public List<String> getCreativeSequence() {
		return creativeSequence;
	}

	public Object getPromotedObject() {
		return promotedObject;
	}

	public Object getTargeting() {
		return targeting;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public enum BidType {
		CPM, CPC, MULTI_PREMIUM, ABSOLUTE_OCPM, CPA, UNKNOWN
	}

	public enum AdSetStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, CAMPAIGN_GROUP_PAUSED, UNKNOWN
	}
}
