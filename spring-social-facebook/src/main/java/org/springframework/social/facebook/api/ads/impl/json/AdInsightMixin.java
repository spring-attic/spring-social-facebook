package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdInsightAction;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdInsight.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdInsightMixin extends FacebookObjectMixin {
	// id fields
	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("adgroup_id")
	String adGroupId;

	@JsonProperty("campaign_id")
	String campaignId;

	@JsonProperty("campaign_group_id")
	String campaignGroupId;

	// name fields
	@JsonProperty("account_name")
	String accountName;

	@JsonProperty("adgroup_name")
	String adGroupName;

	@JsonProperty("campaign_group_name")
	String campaignGroupName;

	@JsonProperty("campaign_name")
	String camapignName;

	// date fields
	@JsonProperty("date_start")
	Date dateStart;

	@JsonProperty("date_stop")
	Date dateStop;

	@JsonProperty("campaign_start")
	Date campaignStart;

	@JsonProperty("campaign_end")
	Date campaignEnd;

	@JsonProperty("campaign_group_end")
	Date campaignGroupEnd;

	// general fields
	@JsonProperty("actions_per_impression")
	double actionsPerImpression;

	@JsonProperty("clicks")
	int clicks;

	@JsonProperty("unique_clicks")
	int uniqueClicks;

	@JsonProperty("cost_per_result")
	double costPerResult;

	@JsonProperty("cost_per_total_action")
	double costPerTotalAction;

	@JsonProperty("cpc")
	double costPerClick;

	@JsonProperty("cost_per_unique_click")
	double costPerUniqueClick;

	@JsonProperty("cpm")
	double cpm;

	@JsonProperty("cpp")
	double cpp;

	@JsonProperty("ctr")
	double ctr;

	@JsonProperty("unique_ctr")
	double uniqueCtr;

	@JsonProperty("frequency")
	double frequency;

	@JsonProperty("impressions")
	int impressions;

	@JsonProperty("unique_impressions")
	int uniqueImpressions;

	@JsonProperty("objective")
	String objective;

	@JsonProperty("reach")
	int reach;

	@JsonProperty("result_rate")
	double resultRate;

	@JsonProperty("results")
	int results;

	@JsonProperty("roas")
	int roas;

	@JsonProperty("social_clicks")
	int socialClicks;

	@JsonProperty("unique_social_clicks")
	int uniqueSocialClicks;

	@JsonProperty("social_impressions")
	int socialImpressions;

	@JsonProperty("unique_social_impressions")
	int uniqueSocialImpressions;

	@JsonProperty("social_reach")
	int socialReach;

	@JsonProperty("spend")
	int spend;

	@JsonProperty("today_spend")
	int todaySpend;

	@JsonProperty("total_action_value")
	int totalActionValue;

	@JsonProperty("total_actions")
	int totalActions;

	@JsonProperty("total_unique_actions")
	int totalUniqueActions;

	// action and video fields
	@JsonProperty("actions")
	List<AdInsightAction> actions;

	@JsonProperty("unique_actions")
	List<AdInsightAction> uniqueActions;

	@JsonProperty("cost_per_action_type")
	List<AdInsightAction> costPerActionType;

	@JsonProperty("video_start_actions")
	List<AdInsightAction> videoStartActions;
}