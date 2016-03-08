package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * Annotated mixin to add Jackson annotations to TargetingCityEntry.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TargetingCityEntryMixin extends FacebookObjectMixin {
	@JsonProperty("key")
	String key;

	@JsonProperty("radius")
	int radius;

	@JsonProperty("distance_unit")
	String distanceUnit;
}
