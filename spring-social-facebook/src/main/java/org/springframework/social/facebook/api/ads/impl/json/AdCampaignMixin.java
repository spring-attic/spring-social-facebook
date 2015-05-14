package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.facebook.api.ads.AdCampaign.BuyingType;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignObjective;
import org.springframework.social.facebook.api.ads.AdCampaign.CampaignStatus;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.io.IOException;

/**
 * Annotated mixin to add Jackson annotations to AdCampaign.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdCampaignMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	private String id;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("buying_type")
	@JsonDeserialize(using = BuyingTypeDeserializer.class)
	private BuyingType buyingType;

	@JsonProperty("campaign_group_status")
	@JsonDeserialize(using = CampaignStatusDeserializer.class)
	private CampaignStatus status;

	@JsonProperty("name")
	private String name;

	@JsonProperty("objective")
	@JsonDeserialize(using = CampaignObjectiveDeserializer.class)
	private CampaignObjective objective;

	@JsonProperty("spend_cap")
	private int spendCap;


	private static class BuyingTypeDeserializer extends JsonDeserializer<BuyingType> {
		@Override
		public BuyingType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return BuyingType.valueOf(jp.getValueAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				return BuyingType.UNKNOWN;
			}
		}
	}

	private static class CampaignStatusDeserializer extends JsonDeserializer<CampaignStatus> {
		@Override
		public CampaignStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return CampaignStatus.valueOf(jp.getValueAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				return CampaignStatus.UNKNOWN;
			}
		}
	}

	private static class CampaignObjectiveDeserializer extends JsonDeserializer<CampaignObjective> {
		@Override
		public CampaignObjective deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return CampaignObjective.valueOf(jp.getValueAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				return CampaignObjective.UNKNOWN;
			}
		}
	}
}
