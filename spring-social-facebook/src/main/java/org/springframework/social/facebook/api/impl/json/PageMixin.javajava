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
package org.springframework.social.facebook.api.impl.json;

import java.io.IOException;
import java.util.Map;

import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Page. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PageMixin extends FacebookObjectMixin {

	@JsonCreator
	PageMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("name") String name, 
			@JsonProperty("link") String location,
			@JsonProperty("category") String category) {}
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("about")
	String about;
	
	@JsonProperty("location")
	@JsonDeserialize(using=LocationDeserializer.class)
	Location location;

	@JsonProperty("website")
	String website;
	
	@JsonProperty("picture")
	@JsonDeserialize(using=PictureDeserializer.class)
	String picture;
	
	@JsonProperty("cover")
	CoverPhoto cover;
	
	@JsonProperty("phone")
	String phone;

	@JsonProperty("affiliation")
	String affiliation;
	
	@JsonProperty("company_overview")
	String companyOverview;

	@JsonProperty("likes")
	int likes;
	
	@JsonProperty("talking_about_count")
	int talkingAboutCount;
	
	@JsonProperty("checkins")
	int checkins;
	
	@JsonProperty("can_post")
	boolean canPost;
	
	@JsonProperty("is_published")
	private boolean isPublished;
	
	@JsonProperty("is_community_page")
	private boolean isCommunityPage;
	
	@JsonProperty("has_added_app")
	private boolean hasAddedApp;

	@JsonProperty("hours")
	private Map<String, String> hours;
	
	private static class LocationDeserializer extends JsonDeserializer<Location> {
		@Override
		public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				return jp.readValueAs(Location.class);
			}
			return new Location(jp.getText());
		}
	}

}
