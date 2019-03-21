/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class ActionMetadata {

	private Date startTime;
	private Date endTime;
	private Date createdTime;
	private Integer expiresIn;
	private String place;
	private List<String> tags;
	private String message;
	private Boolean noFeedStory;
	private Boolean explicitlyShared;
	private String privacy;
	
	public ActionMetadata() {
		tags = new ArrayList<String>();
	}
	
	public ActionMetadata startTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public ActionMetadata endTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

	public ActionMetadata createdTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public ActionMetadata expiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}
	
	public ActionMetadata place(String place) {
		this.place = place;
		return this;
	}
	
	public ActionMetadata tag(String user) {
		tags.add(user);
		return this;
	}
	
	public ActionMetadata message(String message) {
		this.message = message;
		return this;
	}
	
	public ActionMetadata noFeedStory(boolean noFeedStory) {
		this.noFeedStory = noFeedStory;
		return this;
	}
	
	public ActionMetadata explicitlyShared(boolean explicitlyShared) {
		this.explicitlyShared = explicitlyShared;
		return this;
	}
	
	public ActionMetadata privacy(String privacy) {
		this.privacy = privacy;
		return this;
	}
	
	public MultiValueMap<String, Object> toParameters() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		setDateIfNotNull(params, "start_time", startTime);
		setDateIfNotNull(params, "end_time", endTime);
		setDateIfNotNull(params, "created_time", createdTime);
		if (expiresIn != null) { params.set("expires_in", String.valueOf(expiresIn)); } 
		setIfNotNull(params, "place", place);
		setTagsIfNotNull(params);
		setIfNotNull(params, "message", message);
		setIfBooleanNotNull(params, "no_feed_story", noFeedStory);
		setIfBooleanNotNull(params, "fb:explicitly_shared", explicitlyShared);
		if (privacy != null) { params.set("privacy", "{\"value\":\"" + privacy + "\"}"); }
		return params;
	}
	
	protected void setIfNotNull(MultiValueMap<String, Object> params, String key, Object value) {
		if (value != null) { params.set(key, value); }
	}

	protected void setIfBooleanNotNull(MultiValueMap<String, Object> params, String key, Boolean value) {
		setIfNotNull(params, key, value != null ? value.toString() : null);
	}
	
	protected void setDateIfNotNull(MultiValueMap<String, Object> params, String key, Date value) {
		if (value != null) {
			String formattedDate = DATE_FORMAT.format(value);
			params.set(key, formattedDate);
		}
	}

	private void setTagsIfNotNull(MultiValueMap<String, Object> params) {
		if (tags.size() > 0) {
			params.set("tags", StringUtils.collectionToCommaDelimitedString(tags));
		}
	}
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
}
