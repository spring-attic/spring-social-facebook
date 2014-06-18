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
