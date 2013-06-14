/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.facebook.api;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * An object that represents a new post to be created. 
 * Offers a builder-like way of creating a new post.
 * Given to {@link FeedOperations#post(NewPost)}.
 * @author Craig Walls
 */
public class NewPost {

	private final String targetFeedId;
	
	private String message;
	
	private String linkUrl;
	
	private String name;
	
	private String caption;
	
	private String description;
	
	private String[] tags;
	
	private String placeId;
	
	private String picture;

	/**
	 * Creates a new {@link NewPost}.
	 * @param targetFeedId The ID of the owner of the post.
	 */
	public NewPost(String targetFeedId) {
		this.targetFeedId = targetFeedId;
	}
	
	public String getTargetFeedId() {
		return targetFeedId;
	}
	
	/**
	 * @param picture A message for the post.
	 */
	public NewPost message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @param picture A link to include in the post.
	 */
	public NewPost link(String linkUrl) {
		this.linkUrl = linkUrl;
		return this;
	}
	
	/**
	 * @param picture A name (e.g., title) for the post.
	 */
	public NewPost name(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * @param picture A caption for the post.
	 */
	public NewPost caption(String caption) {
		this.caption = caption;
		return this;
	}
	
	/**
	 * @param picture A description of the post.
	 */
	public NewPost description(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * @param picture The ID of a place to associate with the post.
	 */
	public NewPost place(String placeId) {
		this.placeId = placeId;
		return this;
	}
	
	/**
	 * @param picture One or more Facebook user IDs to tag in the post. Will be ignored unless a place is specified.
	 */
	public NewPost tags(String... tags) {
		this.tags = tags;
		return this;
	}

	/**
	 * @param picture The URL to a picture to embed in the post
	 */
	public NewPost picture(String picture) {
		this.picture = picture;
		return this;
	}

	public MultiValueMap<String, Object> toRequestParameters() {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		if (message != null) { parameters.add("message", message); }
		if (linkUrl != null) { parameters.add("link", linkUrl); }
		if (name != null) { parameters.add("name", name); }
		if (caption != null) { parameters.add("caption", caption); }
		if (description != null) { parameters.add("description", description); }
		if (picture != null) { parameters.add("picture", picture); }
		if (placeId != null) { 
			parameters.add("place", placeId);
			// tags are only allowed if a place is given
			if (tags != null) { parameters.add("tags", StringUtils.arrayToCommaDelimitedString(tags)); }
		}
		return parameters;
	}

}
