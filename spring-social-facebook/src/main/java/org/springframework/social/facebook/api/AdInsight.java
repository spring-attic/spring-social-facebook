package org.springframework.social.facebook.api;

import java.util.Date;
import java.util.List;

/**
 * Class representing response object given by Ad Insights API for request
 * about AdAccount, AdCampaign, AdSet or Ad.
 *
 * @author Sebastian Górecki
 */
public class AdInsight {
	// id fields
	private String accountId;
	private String adGroupId;
	private String campaignId;
	private String campaignGroupId;

	// name fields
	private String accountName;
	private String adGroupName;
	private String campaignGroupName;
	private String camapignName;

	// date fields
	private Date dateStart;
	private Date dateStop;
	private Date campaignStart;
	private Date campaignEnd;
	private Date campaignGroupEnd;

	// general fields
	private double actionsPerImpression;
	private int clicks;
	private int uniqueClicks;
	private double costPerResult;
	private double costPerTotalAction;
	private double costPerClick;
	private double costPerUniqueClick;
	private double cpm;
	private double cpp;
	private double ctr;
	private double uniqueCtr;
	private double frequency;
	private int impressions;
	private int uniqueImpressions;
	private String objective;
	private int reach;
	private double resultRate;
	private int results;
	private int roas;
	private int socialClicks;
	private int uniqueSocialClicks;
	private int socialImpressions;
	private int uniqueSocialImpressions;
	private int socialReach;
	private int spend;
	private int todaySpend;
	private int totalActionValue;
	private int totalActions;
	private int totalUniqueActions;

	// action and video fields
	private List<AdInsightAction> actions;
	private List<AdInsightAction> uniqueActions;
	private List<AdInsightAction> costPerActionType;
	private List<AdInsightAction> videoStartActions;

	public String getAccountId() {
		return accountId;
	}

	public String getAdGroupId() {
		return adGroupId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public String getCampaignGroupId() {
		return campaignGroupId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAdGroupName() {
		return adGroupName;
	}

	public String getCampaignGroupName() {
		return campaignGroupName;
	}

	public String getCamapignName() {
		return camapignName;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateStop() {
		return dateStop;
	}

	public Date getCampaignStart() {
		return campaignStart;
	}

	public Date getCampaignEnd() {
		return campaignEnd;
	}

	public Date getCampaignGroupEnd() {
		return campaignGroupEnd;
	}

	public double getActionsPerImpression() {
		return actionsPerImpression;
	}

	public int getClicks() {
		return clicks;
	}

	public int getUniqueClicks() {
		return uniqueClicks;
	}

	public double getCostPerResult() {
		return costPerResult;
	}

	public double getCostPerTotalAction() {
		return costPerTotalAction;
	}

	public double getCostPerClick() {
		return costPerClick;
	}

	public double getCostPerUniqueClick() {
		return costPerUniqueClick;
	}

	public double getCpm() {
		return cpm;
	}

	public double getCpp() {
		return cpp;
	}

	public double getCtr() {
		return ctr;
	}

	public double getUniqueCtr() {
		return uniqueCtr;
	}

	public double getFrequency() {
		return frequency;
	}

	public int getImpressions() {
		return impressions;
	}

	public int getUniqueImpressions() {
		return uniqueImpressions;
	}

	public String getObjective() {
		return objective;
	}

	public int getReach() {
		return reach;
	}

	public double getResultRate() {
		return resultRate;
	}

	public int getResults() {
		return results;
	}

	public int getRoas() {
		return roas;
	}

	public int getSocialClicks() {
		return socialClicks;
	}

	public int getUniqueSocialClicks() {
		return uniqueSocialClicks;
	}

	public int getSocialImpressions() {
		return socialImpressions;
	}

	public int getUniqueSocialImpressions() {
		return uniqueSocialImpressions;
	}

	public int getSocialReach() {
		return socialReach;
	}

	public int getSpend() {
		return spend;
	}

	public int getTodaySpend() {
		return todaySpend;
	}

	public int getTotalActionValue() {
		return totalActionValue;
	}

	public int getTotalActions() {
		return totalActions;
	}

	public int getTotalUniqueActions() {
		return totalUniqueActions;
	}

	public List<AdInsightAction> getActions() {
		return actions;
	}

	public List<AdInsightAction> getUniqueActions() {
		return uniqueActions;
	}

	public List<AdInsightAction> getCostPerActionType() {
		return costPerActionType;
	}

	public List<AdInsightAction> getVideoStartActions() {
		return videoStartActions;
	}
}
