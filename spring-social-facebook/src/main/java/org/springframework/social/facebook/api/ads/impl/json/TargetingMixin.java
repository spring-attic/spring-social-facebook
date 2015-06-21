package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.facebook.api.ads.Targeting.*;
import org.springframework.social.facebook.api.ads.TargetingEntry;
import org.springframework.social.facebook.api.ads.TargetingLocation;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to Targeting.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingSerializer.class)
public abstract class TargetingMixin extends FacebookObjectMixin {
	// demographics
	@JsonProperty("genders")
	List<Gender> genders;

	@JsonProperty("age_min")
	Integer ageMin;

	@JsonProperty("age_max")
	Integer ageMax;

	@JsonProperty("relationship_statuses")
	List<RelationshipStatus> relationshipStatuses;

	@JsonProperty("interested_in")
	List<InterestedIn> interestedIn;

	// location
	@JsonProperty("geo_locations")
	TargetingLocation geoLocations;

	@JsonProperty("excluded_geo_locations")
	TargetingLocation excludedGeoLocations;

	// placement
	@JsonProperty("page_types")
	List<PageType> pageTypes;

	// connections
	@JsonProperty("connections")
	List<String> connections;

	@JsonProperty("excluded_connections")
	List<String> excludedConnections;

	@JsonProperty("friends_of_connections")
	List<String> friendsOfConnections;

	// interests
	@JsonProperty("interests")
	List<TargetingEntry> interests;

	// behaviors
	@JsonProperty("behaviors")
	List<TargetingEntry> behaviors;

	// education and workplace
	@JsonProperty("education_schools")
	List<TargetingEntry> educationSchools;

	@JsonProperty("education_statuses")
	List<EducationStatus> educationStatuses;

	@JsonProperty("college_years")
	List<Integer> collegeYears;

	@JsonProperty("education_majors")
	List<TargetingEntry> educationMajors;

	@JsonProperty("work_employers")
	List<TargetingEntry> workEmployers;

	@JsonProperty("work_positions")
	List<TargetingEntry> workPositions;
}
