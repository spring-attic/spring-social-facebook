package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.facebook.api.ads.Ad;
import org.springframework.social.facebook.api.ads.BidInfo;
import org.springframework.social.facebook.api.ads.BidType;
import org.springframework.social.facebook.api.ads.Targeting;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Annotated mixin to add Jackson annotations to Ad.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("adgroup_status")
	Ad.AdStatus status;

	@JsonProperty("name")
	String name;

	@JsonProperty("bid_type")
	BidType bidType;

	@JsonProperty("bid_info")
	BidInfo bidInfo;

	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("campaign_id")
	String adSetId;

	@JsonProperty("campaign_group_id")
	String campaignId;

	@JsonProperty("creative")
	@JsonDeserialize(using = CreativeIdDeserializer.class)
	String creativeId;

	@JsonProperty("targeting")
	Targeting targeting;

	@JsonProperty("created_time")
	Date createdTime;

	@JsonProperty("updated_time")
	Date updatedTime;

	private static class CreativeIdDeserializer extends JsonDeserializer<String> {
		@Override
		public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				try {
					Map<String, String> map = jp.readValueAs(HashMap.class);
					return map.get("id");
				} catch (IOException e) {
					return null;
				}
			}
			return null;
		}
	}
}
