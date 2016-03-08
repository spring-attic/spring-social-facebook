package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdSet.AdSetStatus;
import org.springframework.social.facebook.api.ads.BidInfo;
import org.springframework.social.facebook.api.ads.BidType;
import org.springframework.social.facebook.api.ads.PromotedObject;
import org.springframework.social.facebook.api.ads.Targeting;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdSet.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdSetMixin extends FacebookObjectMixin {
	@JsonProperty("id")
	String id;

	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("campaign_group_id")
	String campaignId;

	@JsonProperty("name")
	String name;

	@JsonProperty("campaign_status")
	AdSetStatus status;

	@JsonProperty("is_autobid")
	boolean autobid;

	@JsonProperty("bid_info")
	BidInfo bidInfo;

	@JsonProperty("bid_type")
	BidType bidType;

	@JsonProperty("budget_remaining")
	int budgetRemaining;

	@JsonProperty("daily_budget")
	int dailyBudget;

	@JsonProperty("lifetime_budget")
	int lifetimeBudget;

	@JsonProperty("creative_sequence")
	List<String> creativeSequence;

	@JsonProperty("promoted_object")
	PromotedObject promotedObject;

	@JsonProperty("targeting")
	Targeting targeting;

	@JsonProperty("start_time")
	Date startTime;

	@JsonProperty("end_time")
	Date endTime;

	@JsonProperty("created_time")
	Date createdTime;

	@JsonProperty("updated_time")
	Date updatedTime;
}
