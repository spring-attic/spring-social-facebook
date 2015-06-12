package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdCreativeMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	private String id;

	@JsonProperty("object_type")
	private AdCreative.AdCreativeType type;

	@JsonProperty("name")
	private String name;

	@JsonProperty("title")
	private String title;

	@JsonProperty("run_status")
	private AdCreative.AdCreativeStatus status;

	@JsonProperty("body")
	private String body;

	@JsonProperty("object_id")
	private String objectId;

	@JsonProperty("image_hash")
	private String imageHash;

	@JsonProperty("image_url")
	private String imageUrl;

	@JsonProperty("link_url")
	private String linkUrl;

	@JsonProperty("object_story_id")
	private String objectStoryId;

	@JsonProperty("object_url")
	private String objectUrl;

	@JsonProperty("url_tags")
	private String urlTags;

	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;

}
