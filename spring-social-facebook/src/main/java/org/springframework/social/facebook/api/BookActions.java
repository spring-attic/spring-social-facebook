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

/**
 * Defines operations for publishing OpenGraph actions pertaining to books.
 * Many OpenGraph operations require approval from Facebook.
 * See https://developers.facebook.com/docs/opengraph/submission-process for details.
 * @author Craig Walls
 */
public interface BookActions {

	/**
	 * Publishes a "books.reads" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being read. Must reference an OpenGraph object of type "books.book".
	 * @param timestamp The time that the book was read.
	 * @param percentComplete The percentage of the book that has been read.
	 * @return The ID for the action created.
	 */
	String readBook(String bookUrl, long timestamp, float percentComplete);

	/**
	 * Publishes a "books.reads" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being read. Must reference an OpenGraph object of type "books.book".
	 * @param timestamp The time that the book was read.
	 * @param percentComplete The percentage of the book that has been read.
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String readBook(String bookUrl, long timestamp, float percentComplete, ActionMetadata metadata);

	/**
	 * Publishes a "books.quotes" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being quoted. Must reference an OpenGraph object of type "books.book".
	 * @param quote The quote from the book.
	 * @return The ID for the action created.
	 */
	String quoteBook(String bookUrl, String quote);

	/**
	 * Publishes a "books.quotes" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being quoted. Must reference an OpenGraph object of type "books.book".
	 * @param quote The quote from the book.
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String quoteBook(String bookUrl, String quote, ActionMetadata metadata);
	
	/**
	 * Publishes a "books.wants_to_read" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is wanted to be read. Must reference an OpenGraph object of type "books.book".
	 * @return The ID for the action created.
	 */
	String wantsToRead(String bookUrl);

	/**
	 * Publishes a "books.wants_to_read" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is wanted to be read. Must reference an OpenGraph object of type "books.book".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String wantsToRead(String bookUrl, ActionMetadata metadata);
	
	/**
	 * Publishes a "books.rates" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being rated. Must reference an OpenGraph object of type "books.book".
	 * @param rating The rating given to the book, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @return The ID for the action created.
	 */
	String rateBook(String bookUrl, float rating, int scale);

	/**
	 * Publishes a "books.rates" action for the OpenGraph book object at the given URL.
	 * @param bookUrl The URL of the book that is being rated. Must reference an OpenGraph object of type "books.book".
	 * @param rating The rating given to the book, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateBook(String bookUrl, float rating, int scale, ActionMetadata metadata);
	
}
