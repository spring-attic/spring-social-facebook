package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.facebook.api.ads.TargetingCityEntry;
import org.springframework.social.facebook.api.ads.TargetingLocation.LocationType;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingLocationSerializer.class)
public abstract class TargetingLocationMixin extends FacebookObjectMixin {
	@JsonProperty("countries")
	private List<String> countries;

	@JsonProperty("regions")
	@JsonDeserialize(using = RegionListDeserializer.class)
	private List<String> regions;

	@JsonProperty("cities")
	private List<TargetingCityEntry> cities;

	@JsonProperty("zips")
	@JsonDeserialize(using = ZipListDeserializer.class)
	private List<String> zips;

	@JsonProperty("location_types")
	private List<LocationType> locationTypes;

	private static class RegionListDeserializer extends JsonDeserializer<List<String>> {
		@Override
		public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				List<String> regions = new ArrayList<String>();
				try {
					while (jp.nextToken() != JsonToken.END_ARRAY) {
						HashMap<String, String> regionMap = jp.readValueAs(HashMap.class);
						regions.add(regionMap.get("key"));
					}
					return regions;
				} catch (IOException e) {
					return Collections.emptyList();
				}
			}
			return Collections.emptyList();
		}
	}

	private static class ZipListDeserializer extends JsonDeserializer<List<String>> {
		@Override
		public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				List<String> zips = new ArrayList<String>();
				try {
					while (jp.nextToken() != JsonToken.END_ARRAY) {
						HashMap<String, String> zipMap = jp.readValueAs(HashMap.class);
						zips.add(zipMap.get("key"));
					}
					return zips;
				} catch (IOException e) {
					return Collections.emptyList();
				}
			}
			return Collections.emptyList();
		}
	}
}
