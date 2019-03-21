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
 * Defines operations for publishing OpenGraph actions pertaining to fitness.
 * Many OpenGraph operations require approval from Facebook.
 * See https://developers.facebook.com/docs/opengraph/submission-process for details.
 * @author Craig Walls
 */
public interface FitnessActions {

	/**
	 * Publishes a "fitness.runs" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was run. Must reference an OpenGraph object of type "fitness.course".
	 * @return The ID for the action created.
	 */
	String runs(String courseUrl);

	/**
	 * Publishes a "fitness.runs" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was run. Must reference an OpenGraph object of type "fitness.course".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String runs(String courseUrl, ActionMetadata metadata);

	/**
	 * Publishes a "fitness.walks" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was walked. Must reference an OpenGraph object of type "fitness.course".
	 * @return The ID for the action created.
	 */
	String walks(String courseUrl);

	/**
	 * Publishes a "fitness.walks" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was walked. Must reference an OpenGraph object of type "fitness.course".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String walks(String courseUrl, ActionMetadata metadata);

	/**
	 * Publishes a "fitness.bikes" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was biked. Must reference an OpenGraph object of type "fitness.course".
	 * @return The ID for the action created.
	 */
	String bikes(String courseUrl);

	/**
	 * Publishes a "fitness.bikes" action for the OpenGraph course object at the given URL.
	 * @param courseUrl The URL of the course that was biked. Must reference an OpenGraph object of type "fitness.course".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String bikes(String courseUrl, ActionMetadata metadata);

}
