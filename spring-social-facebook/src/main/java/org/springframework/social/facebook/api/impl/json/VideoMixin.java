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
import java.util.List;

import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.Tag;
import org.springframework.social.facebook.api.Video.VideoFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Video. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class VideoMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("created_time")
	Date createdTime;
	
	@JsonProperty("description")
	String description;

	@JsonProperty("embed_html")
	String embedHtml;
	
	@JsonProperty("format")
	List<VideoFormat> format;
	
	@JsonProperty("from")
	Reference from;
	
	@JsonProperty("icon")
	String icon;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("picture")
	@JsonDeserialize(using=PictureDeserializer.class)
	String picture;
	
	@JsonProperty("source")
	String source;
	
	@JsonProperty("tags")
	@JsonDeserialize(using=TagListDeserializer.class)
	List<Tag> tags;
	
	@JsonProperty("updated_time")
	Date updatedTime;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static abstract class VideoFormatMixin {
		
		@JsonProperty("embed_html")
		String embedHtml;
		
		@JsonProperty("filter")
		String filter;
		
		@JsonProperty("height")
		int height;
		
		@JsonProperty("picture")
		String picture;
		
		@JsonProperty("width")
		int width;

	}
	
}
