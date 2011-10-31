/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads;

import java.util.List;

import org.springframework.social.facebook.api.Identifier;

/**
 * The <code>Targeting</code> class specifies the type of audience one is
 * interested in reaching. It lets you narrow down your users by location,
 * interests, connections, demographics and education.
 * 
 * @author Karthick Sankarachary
 */
public class Targeting {
	// Location
	private List<String> countries;
	private List<Name> cities;
	private List<String> zips;
	private List<String> regions;
	private int radius;
	private List<String> locales;

	// Likes and interests
	private List<String> keywords;
	private List<Identifier> userAdclusters;
	private List<Gender> interestedIn;

	// Demographic and events
	private List<Gender> genders;
	private int ageMin;
	private int ageMax;
	private int broadAge;
	private List<RelationshipStatus> relationshipStatus;
	private List<UserEvent> userEvent;

	// Connections
	private List<Long> connections;
	private List<Long> excludedConnections;
	private List<Long> friendsOfConnections;

	// Education
	private List<String> collegeNetworks;
	private List<String> workNetworks;
	private List<EducationStatus> educationStatuses;
	private List<Integer> educationYears;
	private List<String> collegeMajors;

	public List<String> getCollegeNetworks() {
		return collegeNetworks;
	}

	public void setCollegeNetworks(List<String> collegeNetworks) {
		this.collegeNetworks = collegeNetworks;
	}

	public List<String> getWorkNetworks() {
		return workNetworks;
	}

	public void setWorkNetworks(List<String> workNetworks) {
		this.workNetworks = workNetworks;
	}

	public List<EducationStatus> getEducationStatuses() {
		return educationStatuses;
	}

	public void setEducationStatuses(List<EducationStatus> educationStatuses) {
		this.educationStatuses = educationStatuses;
	}

	public List<Integer> getEducationYears() {
		return educationYears;
	}

	public void setEducationYears(List<Integer> educationYears) {
		this.educationYears = educationYears;
	}

	public List<String> getCollegeMajors() {
		return collegeMajors;
	}

	public void setCollegeMajors(List<String> collegeMajors) {
		this.collegeMajors = collegeMajors;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<Name> getCities() {
		return cities;
	}

	public void setCities(List<Name> cities) {
		this.cities = cities;
	}

	public List<String> getZips() {
		return zips;
	}

	public void setZips(List<String> zips) {
		this.zips = zips;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public List<String> getLocales() {
		return locales;
	}

	public void setLocales(List<String> locales) {
		this.locales = locales;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<Identifier> getUserAdclusters() {
		return userAdclusters;
	}

	public void setUserAdclusters(List<Identifier> userAdclusters) {
		this.userAdclusters = userAdclusters;
	}

	public List<Gender> getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(List<Gender> interestedIn) {
		this.interestedIn = interestedIn;
	}

	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public int getBroadAge() {
		return broadAge;
	}

	public void setBroadAge(int broadAge) {
		this.broadAge = broadAge;
	}

	public List<RelationshipStatus> getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(
			List<RelationshipStatus> relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public List<UserEvent> getUserEvent() {
		return userEvent;
	}

	public void setUserEvent(List<UserEvent> userEvent) {
		this.userEvent = userEvent;
	}

	public List<Long> getConnections() {
		return connections;
	}

	public void setConnections(List<Long> connections) {
		this.connections = connections;
	}

	public List<Long> getExcludedConnections() {
		return excludedConnections;
	}

	public void setExcludedConnections(List<Long> excludedConnections) {
		this.excludedConnections = excludedConnections;
	}

	public List<Long> getFriendsOfConnections() {
		return friendsOfConnections;
	}

	public void setFriendsOfConnections(List<Long> friendsOfConnections) {
		this.friendsOfConnections = friendsOfConnections;
	}
	
	public static boolean isTopicKeyword(String keyword) {
		return keyword != null && keyword.startsWith("#");
	}

}
