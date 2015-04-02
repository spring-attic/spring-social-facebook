/*
 * Copyright 2015 the original author or authors.
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
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Page.PriceRange;
import org.springframework.social.facebook.api.ParkingInfo;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.RestaurantSpecialties;

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

	@JsonProperty("id")
	String id;
	
	@JsonProperty("about")
	String about;
	
	@JsonProperty("affiliation")
	String affiliation;
	
	@JsonProperty("attire")
	String attire;
	
	@JsonProperty("band_members")
	String bandMembers;
	
	@JsonProperty("best_page")
	Page bestPage;
	
	@JsonProperty("birthday")
	String birthday;
	
	@JsonProperty("booking_agent")
	String bookingAgent;
	
	@JsonProperty("can_post")
	boolean canPost;
	
	@JsonProperty("category")
	String category;
	
	@JsonProperty("category_list")
	List<Reference> categoryList;
	
	@JsonProperty("checkins")
	int checkins;
	
	@JsonProperty("company_overview")
	String companyOverview;

	@JsonProperty("cover")
	CoverPhoto cover;
	
	@JsonProperty("current_location")
	String currentLocation;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("directed_by")
	String directedBy;
	
	@JsonProperty("founded")
	String founded;
	
	@JsonProperty("general_info")
	String generalInfo;
	
	@JsonProperty("general_manager")
	String generalManager;
	
	@JsonProperty("global_brand_page_name")
	String globalBrandPageName;

	@JsonProperty("has_added_app")
	boolean hasAddedApp;

	@JsonProperty("hometown")
	String hometown;
	
	@JsonProperty("hours")
	Map<String, String> hours;
	
	@JsonProperty("is_community_page")
	boolean isCommunityPage;
	
	@JsonProperty("is_permanently_closed")
	boolean isPermanentlyClosed;
	
	@JsonProperty("is_published")
	boolean isPublished;
	
	@JsonProperty("is_unclaimed")
	boolean isUnclaimed;

	@JsonProperty("likes")
	int likes;
	
	@JsonProperty("link")
	String link;
	
	@JsonProperty("location")
	@JsonDeserialize(using=LocationDeserializer.class)
	Location location;

	@JsonProperty("mission")
	String mission;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("parking")
	ParkingInfo parking;
	
	@JsonProperty("phone")
	String phone;

	@JsonProperty("picture")
	@JsonDeserialize(using=PictureDeserializer.class)
	String picture;
	
	@JsonProperty("press_contact")
	String pressContact;
	
	@JsonProperty("price_range")
	@JsonDeserialize(using=PriceRangeDeserializer.class)
	PriceRange priceRange;
	
	@JsonProperty("products")
	String products;
	
	@JsonProperty("restaurant_specialties")
	RestaurantSpecialties restaurantSpecialties;
	
	@JsonProperty("talking_about_count")
	int talkingAboutCount;
	
	@JsonProperty("website")
	String website;
	
	@JsonProperty("were_here_count")
	int wereHereCount;
	
	private static class LocationDeserializer extends JsonDeserializer<Location> {
		@Override
		public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				return jp.readValueAs(Location.class);
			}
			return new Location(jp.getText());
		}
	}
	
	private static class PriceRangeDeserializer extends JsonDeserializer<PriceRange> {
		@Override
		public PriceRange deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			String priceRangeText = jp.getText();
			if (priceRangeText.startsWith("$")) {
				String[] split = priceRangeText.split("\\s");
				return PriceRange.valueOf(split[0]);
			}
			
			return PriceRange.UNSPECIFIED;
		}
	}

}
