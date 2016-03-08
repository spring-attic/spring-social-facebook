package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdUser.AdUserPermission;
import org.springframework.social.facebook.api.ads.AdUser.AdUserRole;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdUser.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdUserMixin extends FacebookObjectMixin {
	@JsonProperty("id")
	String id;

	@JsonProperty("name")
	String name;

	@JsonProperty("permissions")
	List<AdUserPermission> permissions;

	@JsonProperty("role")
	AdUserRole role;
}
