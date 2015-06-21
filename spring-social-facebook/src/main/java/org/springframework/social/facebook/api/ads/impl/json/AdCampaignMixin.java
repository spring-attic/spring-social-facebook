package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * Annotated mixin to add Jackson annotations to AdCampaign.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdCampaignMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("buying_type")
	BuyingType buyingType;

	@JsonProperty("campaign_group_status")
	CampaignStatus status;

	@JsonProperty("name")
	String name;

	@JsonProperty("objective")
	CampaignObjective objective;

	@JsonProperty("spend_cap")
	int spendCap;
}
