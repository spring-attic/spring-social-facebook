package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * Annotated mixin to add Jackson annotations to TargetingEntry.
 *
 * @author Sebastian Górecki
 */
public abstract class TargetingEntryMixin extends FacebookObjectMixin {
	@JsonProperty("id")
	long id;

	@JsonProperty("name")
	String name;
}
