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
package org.springframework.social.facebook.api.impl;

import static org.springframework.social.facebook.api.OpenGraphOperations.*;

import org.springframework.social.facebook.api.ActionMetadata;
import org.springframework.social.facebook.api.BookActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.util.MultiValueMap;

public class BookActionsTemplate implements BookActions {

	private OpenGraphOperations openGraphOperations;

	public BookActionsTemplate(OpenGraphOperations openGraphOperations) {
		this.openGraphOperations = openGraphOperations;
	}
	
	public String readBook(String bookUrl, long timestamp, float percentComplete) {
		return readBook(bookUrl, timestamp, percentComplete, EMPTY_ACTION_METADATA);
	}
	
	public String readBook(String bookUrl, long timestamp, float percentComplete, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("book", bookUrl);
		parameters.set("progress:timestamp", String.valueOf(timestamp));
		parameters.set("progress:percent_complete", String.valueOf(percentComplete));
		return openGraphOperations.publishAction("book.reads", parameters, true);
	}
	
	public String quoteBook(String bookUrl, String quote) {
		return quoteBook(bookUrl, quote, EMPTY_ACTION_METADATA);
	}
	
	public String quoteBook(String bookUrl, String quote, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("book", bookUrl);
		parameters.set("body", quote);
		return openGraphOperations.publishAction("books.quotes", parameters, true);
	}
	
	public String wantsToRead(String bookUrl) {
		return wantsToRead(bookUrl, EMPTY_ACTION_METADATA);
	}
	
	public String wantsToRead(String bookUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("book", bookUrl);
		return openGraphOperations.publishAction("books.wants_to_read", parameters, true);
	}
	
	public String rateBook(String bookUrl, float rating, int scale) {
		return rateBook(bookUrl, rating, scale, EMPTY_ACTION_METADATA);
	}
	
	public String rateBook(String bookUrl, float rating, int scale, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("book", bookUrl);
		parameters.set("rating:value", String.valueOf(rating));
		parameters.set("rating:scale", String.valueOf(scale));
		return openGraphOperations.publishAction("books.rates", parameters, true);
	}
	
}
