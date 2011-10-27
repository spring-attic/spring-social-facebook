/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api.ads;

/**
 * @author Karthick Sankarachary
 */
public class AdCreative {
	public enum AdCreativeType {
		NONE, DEFAULT_BASIC_LINK_AD, INLINE_LIKE, EVENT_RSVP, APP_AD, SPONSORED_STORY_FOR_AN_APP_SHARE, SPONSORED_STORY_FOR_A_PAGE_LIKE_EVENT, SPONSORED_STORY_FOR_A_PLACE_CHECKIN_EVENT, SPONSORED_STORY_FOR_AN_APP_USED, SPONSORED_STORY_FOR_A_PAGE_POST_LIKE, SPONSORED_STORY_FOR_A_DOMAIN, SPONSORED_STORY_FOR_A_FACEBOOK_PAGE_UPDATE
	}

	private String name;
	private AdCreativeType type;
	private int objectId;
	private String body;
	private String imageHash;
	private String imageUrl;
	private long creativeId;
	private String countCurrentAdGroups;
	private String title;
	private String runStatus;
	private String linkUrl;
	private String previewUrl;
	private String relatedFanPage;
	private boolean autoUpdate;
	private String storyId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdCreativeType getType() {
		return type;
	}

	public void setType(AdCreativeType type) {
		this.type = type;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public long getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(long creativeId) {
		this.creativeId = creativeId;
	}

	public String getCountCurrentAdGroups() {
		return countCurrentAdGroups;
	}

	public void setCountCurrentAdGroups(String countCurrentAdGroups) {
		this.countCurrentAdGroups = countCurrentAdGroups;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getRelatedFanPage() {
		return relatedFanPage;
	}

	public void setRelatedFanPage(String relatedFanPage) {
		this.relatedFanPage = relatedFanPage;
	}

	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	public void setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

}
