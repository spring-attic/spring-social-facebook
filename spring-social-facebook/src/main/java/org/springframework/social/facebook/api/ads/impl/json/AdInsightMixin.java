package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdInsightAction;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdInsight.
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdInsightMixin extends FacebookObjectMixin {
	// id fields
	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("adgroup_id")
	private String adGroupId;

	@JsonProperty("campaign_id")
	private String campaignId;

	@JsonProperty("campaign_group_id")
	private String campaignGroupId;

	// name fields
	@JsonProperty("account_name")
	private String accountName;

	@JsonProperty("adgroup_name")
	private String adGroupName;

	@JsonProperty("campaign_group_name")
	private String campaignGroupName;

	@JsonProperty("campaign_name")
	private String camapignName;

	// date fields
	@JsonProperty("date_start")
	private Date dateStart;

	@JsonProperty("date_stop")
	private Date dateStop;

	@JsonProperty("campaign_start")
	private Date campaignStart;

	@JsonProperty("campaign_end")
	private Date campaignEnd;

	@JsonProperty("campaign_group_end")
	private Date campaignGroupEnd;

	// general fields
	@JsonProperty("actions_per_impression")
	private double actionsPerImpression;

	@JsonProperty("clicks")
	private int clicks;

	@JsonProperty("unique_clicks")
	private int uniqueClicks;

	@JsonProperty("cost_per_result")
	private double costPerResult;

	@JsonProperty("cost_per_total_action")
	private double costPerTotalAction;

	@JsonProperty("cpc")
	private double costPerClick;

	@JsonProperty("cost_per_unique_click")
	private double costPerUniqueClick;

	@JsonProperty("cpm")
	private double cpm;

	@JsonProperty("cpp")
	private double cpp;

	@JsonProperty("ctr")
	private double ctr;

	@JsonProperty("unique_ctr")
	private double uniqueCtr;

	@JsonProperty("frequency")
	private double frequency;

	@JsonProperty("impressions")
	private int impressions;

	@JsonProperty("unique_impressions")
	private int uniqueImpressions;

	@JsonProperty("objective")
	private String objective;

	@JsonProperty("reach")
	private int reach;

	@JsonProperty("result_rate")
	private double resultRate;

	@JsonProperty("results")
	private int results;

	@JsonProperty("roas")
	private int roas;

	@JsonProperty("social_clicks")
	private int socialClicks;

	@JsonProperty("unique_social_clicks")
	private int uniqueSocialClicks;

	@JsonProperty("social_impressions")
	private int socialImpressions;

	@JsonProperty("unique_social_impressions")
	private int uniqueSocialImpressions;

	@JsonProperty("social_reach")
	private int socialReach;

	@JsonProperty("spend")
	private int spend;

	@JsonProperty("today_spend")
	private int todaySpend;

	@JsonProperty("total_action_value")
	private int totalActionValue;

	@JsonProperty("total_actions")
	private int totalActions;

	@JsonProperty("total_unique_actions")
	private int totalUniqueActions;

	// action and video fields
	@JsonProperty("actions")
	private List<AdInsightAction> actions;

	@JsonProperty("unique_actions")
	private List<AdInsightAction> uniqueActions;

	@JsonProperty("cost_per_action_type")
	private List<AdInsightAction> costPerActionType;

	@JsonProperty("video_start_actions")
	private List<AdInsightAction> videoStartActions;

}
