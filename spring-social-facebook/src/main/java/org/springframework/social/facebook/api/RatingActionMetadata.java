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

import org.springframework.util.MultiValueMap;

/**
 * Carries metadata related to a rating action (such as rating a book or a movie).
 */
public class RatingActionMetadata extends ActionMetadata {

	private String reviewText;
	
	private String reviewLink;
	
	public RatingActionMetadata() {
		super();
	}
	
	/**
	 * Sets review text to accompany a rating.
	 * @param reviewText Review text to go along with a rating.
	 * @return the RatingActionMetadata for additional configuration
	 */
	public RatingActionMetadata reviewText(String reviewText) {
		this.reviewText = reviewText;
		return this;
	}
	
	/**
	 * Sets a link to a review that accompanies a rating.
	 * @param reviewLink A link to a review that accompanies a rating.
	 * @return the RatingActionMetadata for additional configuration
	 */
	public RatingActionMetadata reviewLink(String reviewLink) {
		this.reviewLink = reviewLink;
		return this;
	}
	
	@Override
	public MultiValueMap<String, Object> toParameters() {
		MultiValueMap<String, Object> params = super.toParameters();
		setIfNotNull(params, "review_text", reviewText);
		setIfNotNull(params, "review_link", reviewLink);
		return params;
	}
}
