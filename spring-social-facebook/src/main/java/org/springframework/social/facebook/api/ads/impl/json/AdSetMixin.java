package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.facebook.api.ads.AdSet.AdSetStatus;
import org.springframework.social.facebook.api.ads.BidType;
import org.springframework.social.facebook.api.ads.BidInfo;
import org.springframework.social.facebook.api.ads.Targeting;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * * Annotated mixin to add Jackson annotations to AdSet.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdSetMixin extends FacebookObjectMixin {
	@JsonProperty("id")
	private String id;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("campaign_group_id")
	private String campaignId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("campaign_status")
	@JsonDeserialize(using = AdSetStatusDeserializer.class)
	private AdSetStatus status;

	@JsonProperty("is_autobid")
	private boolean autobid;

	@JsonProperty("bid_info")
	private BidInfo bidInfo;

	@JsonProperty("bid_type")
	@JsonDeserialize(using = BidTypeDeserializer.class)
	private BidType bidType;

	@JsonProperty("budget_remaining")
	private int budgetRemaining;

	@JsonProperty("daily_budget")
	private int dailyBudget;

	@JsonProperty("lifetime_budget")
	private int lifetimeBudget;

	@JsonProperty("creative_sequence")
	private List<String> creativeSequence;

	@JsonProperty("targeting")
	private Targeting targeting;

	@JsonProperty("start_time")
	private Date startTime;

	@JsonProperty("end_time")
	private Date endTime;

	@JsonProperty("created_time")
	private Date createdTime;

	@JsonProperty("updated_time")
	private Date updatedTime;

	private static class AdSetStatusDeserializer extends JsonDeserializer<AdSetStatus> {
		@Override
		public AdSetStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return AdSetStatus.valueOf(jp.getValueAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				return AdSetStatus.UNKNOWN;
			}
		}
	}

	private static class BidTypeDeserializer extends JsonDeserializer<BidType> {
		@Override
		public BidType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return BidType.valueOf(jp.getValueAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				return BidType.UNKNOWN;
			}
		}
	}
}
