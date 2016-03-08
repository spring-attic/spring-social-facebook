package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.social.facebook.api.FacebookObject;

/**
 * Model class representing an ad campaign.
 *
 * @author Sebastian Górecki
 */
public class AdCampaign extends FacebookObject {
	private String id;
	private String accountId;
	private BuyingType buyingType;
	private CampaignStatus status;
	private String name;
	private CampaignObjective objective;
	private int spendCap;

	public CampaignStatus getStatus() {
		return status;
	}

	public void setStatus(CampaignStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public BuyingType getBuyingType() {
		return buyingType;
	}

	public void setBuyingType(BuyingType buyingType) {
		this.buyingType = buyingType;
	}

	public CampaignObjective getObjective() {
		return objective;
	}

	public void setObjective(CampaignObjective objective) {
		this.objective = objective;
	}

	public int getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(int spendCap) {
		this.spendCap = spendCap;
	}

	public enum BuyingType {
		AUCTION, FIXED_CPM, RESERVED, MIXED, UNKNOWN;

		@JsonCreator
		public static BuyingType fromValue(String value) {
			for (BuyingType type : BuyingType.values()) {
				if (type.name().equals(value)) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}

	public enum CampaignStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, UNKNOWN;

		@JsonCreator
		public static CampaignStatus fromValue(String value) {
			for (CampaignStatus status : CampaignStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}

	public enum CampaignObjective {
		CANVAS_APP_ENGAGEMENT, CANVAS_APP_INSTALLS, EVENT_RESPONSES, LOCAL_AWARENESS, MOBILE_APP_ENGAGEMENT,
		MOBILE_APP_INSTALLS, NONE, OFFER_CLAIMS, PAGE_LIKES, POST_ENGAGEMENT, VIDEO_VIEWS, WEBSITE_CLICKS,
		WEBSITE_CONVERSIONS, UNKNOWN;

		@JsonCreator
		public static CampaignObjective fromValue(String value) {
			for (CampaignObjective objective : CampaignObjective.values()) {
				if (objective.name().equals(value)) {
					return objective;
				}
			}
			return UNKNOWN;
		}
	}
}
