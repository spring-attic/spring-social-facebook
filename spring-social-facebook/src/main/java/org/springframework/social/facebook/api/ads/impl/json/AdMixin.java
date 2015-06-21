package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.facebook.api.ads.Ad;
import org.springframework.social.facebook.api.ads.BidInfo;
import org.springframework.social.facebook.api.ads.BidType;
import org.springframework.social.facebook.api.ads.Targeting;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdMixin {

	@JsonProperty("id")
	private String id;

	@JsonProperty("adgroup_status")
	private Ad.AdStatus status;

	@JsonProperty("name")
	private String name;

	@JsonProperty("bid_type")
	private BidType bidType;

	@JsonProperty("bid_info")
	private BidInfo bidInfo;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("campaign_id")
	private String adSetId;

	@JsonProperty("campaign_group_id")
	private String campaignId;

	@JsonProperty("creative")
	@JsonDeserialize(using = CreativeIdDeserializer.class)
	private String creativeId;

	@JsonProperty("targeting")
	private Targeting targeting;

	@JsonProperty("created_time")
	private Date createdTime;

	@JsonProperty("updated_time")
	private Date updatedTime;

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
