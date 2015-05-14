package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * Annotated mixin to add Jackson annotations to AdAccountGroup.
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdAccountGroupMixin extends FacebookObjectMixin {

	@JsonProperty("account_group_id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("status")
	private int status;
}
