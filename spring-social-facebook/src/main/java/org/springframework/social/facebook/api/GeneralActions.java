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
 * Defines operations for publishing general OpenGraph actions such as "og.likes" and "og.follows". 
 * Many OpenGraph operations require approval from Facebook.
 * See https://developers.facebook.com/docs/opengraph/submission-process for details.
 * @author Craig Walls
 */
public interface GeneralActions {

	/**
	 * Publishes a "og.likes" action for the OpenGraph object defined at the given URL.
	 * @param objectUrl The URL of the OpenGraph object that is to be liked.
	 * @return The ID of the action. 
	 */
	String like(String objectUrl);
	
	/**
	 * Publishes a "og.likes" action for the OpenGraph object defined at the given URL.
	 * @param objectUrl The URL of the OpenGraph object that is to be liked.
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID of the action. 
	 */
	String like(String objectUrl, ActionMetadata metadata);
	
	/**
	 * Publishes a "og.follows" action for the OpenGraph object defined at the given URL.
	 * @param profileUrl The URL of the OpenGraph object that is to be followed.
	 * @return The ID of the action. 
	 */
	String follow(String profileUrl);

	/**
	 * Publishes a "og.follows" action for the OpenGraph object defined at the given URL.
	 * @param profileUrl The URL of the OpenGraph object that is to be followed.
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID of the action. 
	 */
	String follow(String profileUrl, ActionMetadata metadata);

}
