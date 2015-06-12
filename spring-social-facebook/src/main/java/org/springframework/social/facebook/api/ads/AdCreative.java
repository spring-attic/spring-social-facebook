package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Sebastian Górecki
 */
public class AdCreative {
	private String id;
	private AdCreativeType type;
	private String name;
	private String title;
	private AdCreativeStatus status;

	private String body;
	private String objectId;
	private String imageHash;
	private String imageUrl;
	private String linkUrl;
	private String objectStoryId;
	private String objectUrl;
	private String urlTags;
	private String thumbnailUrl;

	public AdCreativeType getType() {
		return type;
	}

	public void setType(AdCreativeType type) {
		this.type = type;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdCreativeStatus getStatus() {
		return status;
	}

	public void setStatus(AdCreativeStatus status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getImageHash() {
		return imageHash;
	}

	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getObjectStoryId() {
		return objectStoryId;
	}

	public void setObjectStoryId(String objectStoryId) {
		this.objectStoryId = objectStoryId;
	}

	public String getObjectUrl() {
		return objectUrl;
	}

	public void setObjectUrl(String objectUrl) {
		this.objectUrl = objectUrl;
	}

	public String getUrlTags() {
		return urlTags;
	}

	public void setUrlTags(String urlTags) {
		this.urlTags = urlTags;
	}

	public enum AdCreativeType {
		PAGE, DOMAIN, EVENT, STORE_ITEM, OFFER, SHARE, PHOTO, STATUS, VIDEO, APPLICATION, INVALID, UNKNOWN;

		@JsonCreator
		public static AdCreativeType forValue(String value) {
			for (AdCreativeType type : AdCreativeType.values()) {
				if (type.name().equals(value)) return type;
			}
			return UNKNOWN;
		}
	}

	public enum AdCreativeStatus {
		PENDING, ACTIVE, PAUSED, DELETED, PENDING_REVIEW, DISAPPROVED, PREAPPROVED, PENDING_BILLING_INFO,
		CAMPAIGN_PAUSED, ADGROUP_PAUSED, CAMPAIGN_GROUP_PAUSED, ARCHIVED, UNKNOWN;

		@JsonCreator
		public static AdCreativeStatus forValue(String value) {
			for (AdCreativeStatus status : AdCreativeStatus.values()) {
				if (status.name().equals(value)) return status;
			}
			return UNKNOWN;
		}
	}
}
