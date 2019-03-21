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
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Engagement;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.MailingAddress;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Page.PriceRange;
import org.springframework.social.facebook.api.PageParking;
import org.springframework.social.facebook.api.PagePaymentOptions;
import org.springframework.social.facebook.api.PageRestaurantServices;
import org.springframework.social.facebook.api.PageRestaurantSpecialties;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.VoipInfo;

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
	
	@JsonProperty("access_token")
	String accessToken;
	
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
	PageParking parking;
	
	@JsonProperty("phone")
	String phone;

	@JsonProperty("press_contact")
	String pressContact;
	
	@JsonProperty("price_range")
	@JsonDeserialize(using=PriceRangeDeserializer.class)
	PriceRange priceRange;
	
	@JsonProperty("products")
	String products;
	
	@JsonProperty("restaurant_specialties")
	PageRestaurantSpecialties restaurantSpecialties;
	
	@JsonProperty("talking_about_count")
	int talkingAboutCount;
	
	@JsonProperty("website")
	String website;
	
	@JsonProperty("were_here_count")
	int wereHereCount;
	
	@JsonProperty("app_id")
	String appId;
	
	@JsonProperty("artists_we_like")
	String artistsWeLike;
	
	@JsonProperty("awards")
	String awards;
	
	@JsonProperty("band_interests")
	String bandInterests;
	
	@JsonProperty("built")
	String built;
	
	@JsonProperty("business")
	Reference business;
	
	@JsonProperty("contact_adress")
	MailingAddress contactAddress;
	
	@JsonProperty("country_page_likes")
	int countryPageLikes;
	
	@JsonProperty("culinary_team")
	String culinaryTeam;

	@JsonProperty("description_html")
	String descriptionHtml;

	@JsonProperty("emails")
	List<String> emails;
	
	@JsonProperty("engagement")
	Engagement engagement;
	
	@JsonProperty("features")
	String features;
	
	@JsonProperty("food_styles")
	String foodStyles;
	
	@JsonProperty("genre")
	String genre;
	
	@JsonProperty("global_brand_parent_page")
	Reference globalBrandParentPage;

	@JsonProperty("global_brand_root_id")
	String globalBrandRootId;
	
	@JsonProperty("influences")
	String influences;
	
	@JsonProperty("is_verified")
	boolean isVerified;
	
	@JsonProperty("members")
	String members;
	
	@JsonProperty("mpg")
	String mpg;
	
	@JsonProperty("network")
	String network;
	
	@JsonProperty("new_like_count")
	int newLikeCount;
	
	@JsonProperty("offer_eligible")
	boolean offerEligible;
	
	@JsonProperty("parent_page")
	Reference parentPage;
	
	@JsonProperty("payment_options")
	PagePaymentOptions paymentOptions;
	
	@JsonProperty("personal_info")
	String personalInfo;
	
	@JsonProperty("personal_interests")
	String personalInterests;
	
	@JsonProperty("pharma_safety_info")
	String pharmaSafetyInfo;
	
	@JsonProperty("plot_outline")
	String plotOutline;
	
	@JsonProperty("produced_by")
	String producedBy;
	
	@JsonProperty("promotion_eligible")
	boolean promotionEligible;
	
	@JsonProperty("promotion_ineligible_reason")
	String promotionIneligibleReason;
	
	@JsonProperty("public_transit")
	String publicTransit;
	
	@JsonProperty("record_label")
	String recordLabel;
	
	@JsonProperty("release_date")
	String releaseDate;
	
	@JsonProperty("restaurant_services")
	PageRestaurantServices restaurantServices;
	
	@JsonProperty("schedule")
	String schedule;
	
	@JsonProperty("screenplay_by")
	String screenplayBy;
	
	@JsonProperty("season")
	String season;
	
	@JsonProperty("starring")
	String starring;
	
	@JsonProperty("store_number")
	int storeNumber;
	
	@JsonProperty("studio")
	String studio;
	
	@JsonProperty("unread_message_count")
	int unreadMessageCount;
	
	@JsonProperty("unread_notif_count")
	int unreadNotifCount;
	
	@JsonProperty("unseen_message_count")
	int unseenMessageCount;
	
	@JsonProperty("username")
	String username;
	
	@JsonProperty("voip_info")
	VoipInfo voipInfo;
	
	@JsonProperty("written_by")
	String writtenBy;

	
	static class LocationDeserializer extends JsonDeserializer<Location> {
		@Override
		public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				return jp.readValueAs(Location.class);
			}
			return new Location(jp.getText());
		}
	}
	
	static class PriceRangeDeserializer extends JsonDeserializer<PriceRange> {
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
