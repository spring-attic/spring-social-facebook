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

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.Photo.Image;
import org.springframework.social.facebook.api.Photo.TimeGranularity;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.Tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Photo. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PhotoMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	String id;
	
	@JsonProperty("album")
	Album album;
	
	@JsonProperty("backdated_time")
	Date backdatedTime;
	
	@JsonProperty("backdated_time_granularity")
	@JsonDeserialize(using=TimeGranularityDeserializer.class)
	TimeGranularity backdatedTimeGranularity;
	
	@JsonProperty("created_time")
	Date createdTime;
	
	@JsonProperty("from")
	Reference from;
	
	@JsonProperty("height")
	int height;
	
	@JsonProperty("picture")
	String picture;
	
	@JsonProperty("source")
	String source;
	
	@JsonProperty("link")
	String link;
	
	@JsonProperty("icon")
	String icon;
	
	@JsonProperty("images")
	List<Image> images;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("page_story_id")
	String pageStoryId;
	
	@JsonProperty("place")
	Location place;
	
	@JsonProperty("updated_time")
	Date updatedTime;
	
	@JsonProperty("tags")
	@JsonDeserialize(using=TagListDeserializer.class)
	List<Tag> tags;

	public static abstract class ImageMixin {
		@JsonCreator
		public ImageMixin(
				@JsonProperty("source") String source,
				@JsonProperty("width") int width,
				@JsonProperty("height") int height
				) {}
	}
	
	public static class TimeGranularityDeserializer extends JsonDeserializer<TimeGranularity> {
		@Override
		public TimeGranularity deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return TimeGranularity.valueOf(jp.getText().toUpperCase());
		}
	}
	
}
