package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TargetingCityEntryMixin extends FacebookObjectMixin {
	@JsonProperty("key")
	private String key;

	@JsonProperty("radius")
	private int radius;

	@JsonProperty("distance_unit")
	private String distanceUnit;

}
