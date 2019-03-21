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

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * An object that represents a new post to be created. 
 * Offers a builder-like way of creating a new post.
 * Given to {@link PageOperations#post(PagePostData)}.
 * @author Craig Walls
 */
public class PagePostData {

	private final String pageId;
	
	private String message;
	
	private String linkUrl;
	
	private String name;
	
	private String caption;
	
	private String description;
	
	private String[] tags;
	
	private String placeId;
	
	private String picture;
	
	/**
	 * Creates a new {@link PagePostData}.
	 * @param pageId The ID of the owner of the post.
	 */
	public PagePostData(String pageId) {
		this.pageId = pageId;
	}
	
	public String getPageId() {
		return pageId;
	}
	
	/**
	 * @param message A message for the post.
	 * @return the PagePostData object for additional configuration
	 */
	public PagePostData message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @param linkUrl A link to include in the post.
	 * @param picture A preview image associated with the link. May be null.
	 * @param name Overwrites the title of the link preview. May be null.
	 * @param caption Overwrites the caption of the link preview. May be null.
	 * @param description Overwrites the caption of hte link preview. May be null.
	 * @return the PagePostData object for additional configuration
	 */
	public PagePostData link(String linkUrl, String picture, String name, String caption, String description) {
		this.linkUrl = linkUrl;
		this.picture = picture;
		this.name = name;
		this.caption = caption;
		this.description = description;
		return this;
	}

	/**
	 * @param placeId The ID of a place to associate with the post.
	 * @return the PagePostData object for additional configuration
	 */
	public PagePostData place(String placeId) {
		this.placeId = placeId;
		return this;
	}
	
	/**
	 * @param tags One or more Facebook user IDs to tag in the post. Will be ignored unless a place is specified.
	 * @return the PagePostData object for additional configuration
	 */
	public PagePostData tags(String... tags) {
		this.tags = tags;
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
