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
package org.springframework.social.facebook.api.impl.json;

import java.util.Date;

import org.springframework.social.facebook.api.Achievement.AchievementData;
import org.springframework.social.facebook.api.ApplicationReference;
import org.springframework.social.facebook.api.Reference;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

abstract class AchievementMixin extends FacebookObjectMixin {

	@JsonCreator
	AchievementMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("from") Reference from, 
			@JsonProperty("publish_time") Date publishTime, 
			@JsonProperty("application") ApplicationReference application, 
			@JsonProperty("data") AchievementData data,
			@JsonProperty("type") String type, 
			@JsonProperty("no_feed_story") boolean noFeedStory) {}
	
}
