package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * Annotated mixin to add Jackson annotations to AdCreative.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdCreativeMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("object_type")
	AdCreative.AdCreativeType type;

	@JsonProperty("name")
	String name;

	@JsonProperty("title")
	String title;

	@JsonProperty("run_status")
	AdCreative.AdCreativeStatus status;

	@JsonProperty("body")
	String body;

	@JsonProperty("object_id")
	String objectId;

	@JsonProperty("image_hash")
	String imageHash;

	@JsonProperty("image_url")
	String imageUrl;

	@JsonProperty("link_url")
	String linkUrl;

	@JsonProperty("object_story_id")
	String objectStoryId;

	@JsonProperty("object_url")
	String objectUrl;

	@JsonProperty("url_tags")
	String urlTags;

	@JsonProperty("thumbnail_url")
	String thumbnailUrl;
}
