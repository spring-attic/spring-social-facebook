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

import java.util.List;
import java.util.Map;

/**
 * Model class representing a Facebook page.
 * A Facebook page could represent any number of things, including businesses, government agencies, people, organizations, etc.
 * A page may even represent a place that a user may check into using Facebook Places, if the page has location data.
 * The data available for a page will vary depending on the category it belongs to and what data the page administrator has entered.
 * @author Craig Walls
 */
public class Page extends FacebookObject {

	private String id;

	private String about;
	
	private String accessToken;
	
	private String affiliation;
	
	private String appId;
	
	private String artistsWeLike;
	
	private String attire;
	
	private String awards;
	
	private String bandInterests;
	
	private String bandMembers;
	
	private Page bestPage;
	
	private String birthday;
	
	private String bookingAgent;
	
	private String built;
	
	private Reference business;
	
	private boolean canPost;
	
	private String category;
	
	private List<Reference> categoryList;

	private int checkins;
	
	private String companyOverview;
	
	private MailingAddress contactAddress;
	
	private int countryPageLikes;
	
	private CoverPhoto cover;
	
	private String culinaryTeam;

	private String currentLocation;
	
	private String description;
	
	private String descriptionHtml;

	private String directedBy;
	
	private List<String> emails;
	
	private Engagement engagement;
	
	private String features;
	
	private String foodStyles;
	
	private String founded;
	
	private String generalInfo;
	
	private String generalManager;
	
	private String genre;
	
	private String globalBrandPageName;

	private Reference globalBrandParentPage;

	private String globalBrandRootId;
	
	private boolean hasAddedApp;
	
	private String hometown;
	
	private Map<String, String> hours;
	
	private String influences;
	
	private boolean isCommunityPage;
	
	private boolean isPermanentlyClosed;
	
	private boolean isPublished;
	
	private boolean isUnclaimed;
	
	private boolean isVerified;
	
	private int likes;
	
	private String link;

	private Location location;
	
	private String members;
	
	private String mission;
	
	private String mpg;
	
	private String name;
	
	private String network;
	
	private int newLikeCount;
	
	private boolean offerEligible;
	
	private Reference parentPage;
	
	private PageParking parking;
	
	private PagePaymentOptions paymentOptions;
	
	private String personalInfo;
	
	private String personalInterests;
	
	private String pharmaSafetyInfo;
	
	private String phone;
	
	private String plotOutline;
	
	private String pressContact;
	
	private PriceRange priceRange;
	
	private String producedBy;
	
	private String products;
	
	private boolean promotionEligible;
	
	private String promotionIneligibleReason;
	
	private String publicTransit;
	
	private String recordLabel;
	
	private String releaseDate;
	
	private PageRestaurantServices restaurantServices;
	
	private PageRestaurantSpecialties restaurantSpecialties;
	
	private String schedule;
	
	private String screenplayBy;
	
	private String season;
	
	private String starring;
	
	private int storeNumber;
	
	private String studio;
	
	private int talkingAboutCount;
	
	private int unreadMessageCount;
	
	private int unreadNotifCount;
	
	private int unseenMessageCount;
	
	private String username;
	
	private VoipInfo voipInfo;
	
	private String website;
	
	private int wereHereCount;
	
	private String writtenBy;

	/**
	 * @return The page's ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Access token that can be used to act as the Page. Only visible to page admins.
	 * @return a page access token.
	 */
	public String getAccessToken() {
		return accessToken;
	}
	
	/**
	 * @return The page's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Link to the page on Facebook.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @return The page's category.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @return A description of this page.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return Text telling what this page is about.
	 */
	public String getAbout() {
		return about;
	}
	
	/**
	 * @return The page's street address and longitude/latitude coordinates (if available).
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return Link to the external website for the page.
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @return the page's cover photo.
	 */
	public CoverPhoto getCover() {
		return cover;
	}

	/**
	 * @return The phone number for the page.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return The name of an organization that this page is affiliated with.
	 */
	public String getAffiliation() {
		return affiliation;
	}

	/**
	 * @return Text giving a brief description of the company this page serves.
	 */
	public String getCompanyOverview() {
		return companyOverview;
	}
	
	/**
	 * @return The number of users who like this page. For Global Brand pages, this count is fall all pages across the brand.
	 */
	public int getLikes() {
		return likes;
	}
	
	
	public int getTalkingAboutCount() {
		return talkingAboutCount;
	}

	/**
	 * @return The total number of users that have checked in to the page.
	 */
	public int getCheckins() {
		return checkins;
	}

	/**
	 * Indicates whether or not the authenticated user can post on this page.
	 * @return true if the user can post to the page; false otherwise
	 */
	public boolean canPost() {
		return canPost;
	}
	
	/**
	 * @return true if the page has been published; false otherwise.
	 */
	public boolean isPublished() {
		return isPublished;
	}
	
	/**
	 * @return true if the page is a community page; false otherwise.
	 */
	public boolean isCommunityPage() {
		return isCommunityPage;
	}
	
	/**
	 * @return true if the page has added the app making the query in a Page tab; false otherwise.
	 */
	public boolean hasAddedApp() {
		return hasAddedApp;
	}

	public Map<String, String> getHours() {
		return hours;
	}
	
	public String getAttire() {
		return attire;
	}
	
	public String getBandMembers() {
		return bandMembers;
	}
	
	public Page getBestPage() {
		return bestPage;
	}
	
	public String getBookingAgent() {
		return bookingAgent;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public List<Reference> getCategoryList() {
		return categoryList;
	}
	
	public String getCurrentLocation() {
		return currentLocation;
	}
	
	public String getDirectedBy() {
		return directedBy;
	}
	
	public String getFounded() {
		return founded;
	}
	
	public String getGeneralInfo() {
		return generalInfo;
	}
	
	public String getGeneralManager() {
		return generalManager;
	}
	
	public String getGlobalBrandPageName() {
		return globalBrandPageName;
	}
	
	public String getHometown() {
		return hometown;
	}
	
	public boolean isPermanentlyClosed() {
		return isPermanentlyClosed;
	}
	
	public boolean isUnclaimed() {
		return isUnclaimed;
	}
	
	public String getMission() {
		return mission;
	}
	
	public PageParking getParking() {
		return parking;
	}
	
	public PriceRange getPriceRange() {
		return priceRange;
	}
	
	public String getPressContact() {
		return pressContact;
	}
	
	public String getProducts() {
		return products;
	}
	
	public PageRestaurantSpecialties getRestaurantSpecialties() {
		return restaurantSpecialties;
	}
	
	public int getWereHereCount() {
		return wereHereCount;
	}
	
	public String getAppId() {
		return appId;
	}

	public String getArtistsWeLike() {
		return artistsWeLike;
	}

	public String getAwards() {
		return awards;
	}

	public String getBandInterests() {
		return bandInterests;
	}

	public String getBuilt() {
		return built;
	}

	public Reference getBusiness() {
		return business;
	}

	public boolean isCanPost() {
		return canPost;
	}

	public MailingAddress getContactAddress() {
		return contactAddress;
	}

	public int getCountryPageLikes() {
		return countryPageLikes;
	}

	public String getCulinaryTeam() {
		return culinaryTeam;
	}

	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	public List<String> getEmails() {
		return emails;
	}

	public Engagement getEngagement() {
		return engagement;
	}

	public String getFeatures() {
		return features;
	}

	public String getFoodStyles() {
		return foodStyles;
	}

	public String getGenre() {
		return genre;
	}

	public Reference getGlobalBrandParentPage() {
		return globalBrandParentPage;
	}

	public String getGlobalBrandRootId() {
		return globalBrandRootId;
	}

	public boolean isHasAddedApp() {
		return hasAddedApp;
	}

	public String getInfluences() {
		return influences;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public String getMembers() {
		return members;
	}

	public String getMpg() {
		return mpg;
	}

	public String getNetwork() {
		return network;
	}

	public int getNewLikeCount() {
		return newLikeCount;
	}

	public boolean isOfferEligible() {
		return offerEligible;
	}

	public Reference getParentPage() {
		return parentPage;
	}

	public PagePaymentOptions getPaymentOptions() {
		return paymentOptions;
	}

	public String getPersonalInfo() {
		return personalInfo;
	}

	public String getPersonalInterests() {
		return personalInterests;
	}

	public String getPharmaSafetyInfo() {
		return pharmaSafetyInfo;
	}

	public String getPlotOutline() {
		return plotOutline;
	}

	public String getProducedBy() {
		return producedBy;
	}

	public boolean isPromotionEligible() {
		return promotionEligible;
	}

	public String getPromotionIneligibleReason() {
		return promotionIneligibleReason;
	}

	public String getPublicTransit() {
		return publicTransit;
	}

	public String getRecordLabel() {
		return recordLabel;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public PageRestaurantServices getRestaurantServices() {
		return restaurantServices;
	}

	public String getSchedule() {
		return schedule;
	}

	public String getScreenplayBy() {
		return screenplayBy;
	}

	public String getSeason() {
		return season;
	}

	public String getStarring() {
		return starring;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public String getStudio() {
		return studio;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public int getUnreadNotificationCount() {
		return unreadNotifCount;
	}

	public int getUnseenMessageCount() {
		return unseenMessageCount;
	}

	public String getUsername() {
		return username;
	}

	public VoipInfo getVoipInfo() {
		return voipInfo;
	}

	public String getWrittenBy() {
		return writtenBy;
	}

	
	public static enum PriceRange {
		$,$$, $$$, $$$$, UNSPECIFIED;
	}
	
}
