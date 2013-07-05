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

import org.springframework.social.facebook.api.Post.Privacy;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * An object that represents a new post to be created. 
 * Offers a builder-like way of creating a new post.
 * Given to {@link FeedOperations#post(PostData)}.
 * @author Craig Walls
 */
public class PostData {

	private final String targetFeedId;
	
	private String message;
	
	private String linkUrl;
	
	private String name;
	
	private String caption;
	
	private String description;
	
	private String[] tags;
	
	private String placeId;
	
	private String picture;
	
	private Post.Privacy privacy;
	
	private String[] allow;
	
	private String[] deny;

	/**
	 * Creates a new {@link PostData}.
	 * @param targetFeedId The ID of the owner of the post.
	 */
	public PostData(String targetFeedId) {
		this.targetFeedId = targetFeedId;
	}
	
	public String getTargetFeedId() {
		return targetFeedId;
	}
	
	/**
	 * @param message A message for the post.
	 */
	public PostData message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @param linkUrl A link to include in the post.
	 */
	public PostData link(String linkUrl) {
		this.linkUrl = linkUrl;
		return this;
	}
	
	/**
	 * @param name A name (e.g., title) for the post.
	 */
	public PostData name(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * @param caption A caption for the post.
	 */
	public PostData caption(String caption) {
		this.caption = caption;
		return this;
	}
	
	/**
	 * @param description A description of the post.
	 */
	public PostData description(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * @param placeId The ID of a place to associate with the post.
	 */
	public PostData place(String placeId) {
		this.placeId = placeId;
		return this;
	}
	
	/**
	 * @param tags One or more Facebook user IDs to tag in the post. Will be ignored unless a place is specified.
	 */
	public PostData tags(String... tags) {
		this.tags = tags;
		return this;
	}

	/**
	 * @param picture The URL to a picture to embed in the post
	 */
	public PostData picture(String picture) {
		this.picture = picture;
		return this;
	}
	
	/**
	 * @param privacy The privacy setting for the post. If CUSTOM, then you must also set at least one of allow() or deny().
	 */
	public PostData privacy(Post.Privacy privacy) {
		this.privacy = privacy;
		return this;
	}
	
	/**
	 * @param allow One or more Facebook User IDs and friend list IDs that can see the post. Ignored unless privacy is CUSTOM.
	 */
	public PostData allow(String... allow) {
		this.allow = allow;
		return this;
	}

	/**
	 * @param deny One or more Facebook User IDs and friend list IDs that cannot see the post. Ignored unless privacy is CUSTOM.
	 */
	public PostData deny(String... deny) {
		this.deny = deny;
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
		if (privacy != null) {
			StringBuffer privacyBuffer = new StringBuffer();
			privacyBuffer.append("{'value': '").append(privacy.toString()).append("'");
			if (privacy == Privacy.CUSTOM) {
				if (allow == null && deny == null) {
					throw new IllegalArgumentException("At least one of 'deny' or 'allow' must be specified when privacy is CUSTOM.");
				}
				if (allow != null) {
					privacyBuffer.append(",'allow': '").append(join(allow)).append("'");
				}
				if (deny != null) {
					privacyBuffer.append(",'deny': '").append(join(deny)).append("'");
				}
			}
			privacyBuffer.append("}");
			parameters.add("privacy", privacyBuffer.toString());
		}

		return parameters;
	}

	// TODO: Extract this into some utility, as it comes in handy in several places
	private String join(String... strings) {
		Assert.notEmpty(strings);
		StringBuilder builder = new StringBuilder();
		builder.append(strings[0]);
		if (strings.length > 1) {
			for (int i=1; i<strings.length; i++) {
				builder.append(",").append(strings[i]);
			}
		}
		return builder.toString();
	}

}
