package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.facebook.api.ads.Targeting;
import org.springframework.social.facebook.api.ads.Targeting.*;
import org.springframework.social.facebook.api.ads.TargetingEntry;
import org.springframework.social.facebook.api.ads.TargetingLocation;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingSerializer.class)
public abstract class TargetingMixin extends FacebookObjectMixin {
	// demographics
	@JsonProperty("genders")
	private List<Gender> genders;

	@JsonProperty("age_min")
	private Integer ageMin;

	@JsonProperty("age_max")
	private Integer ageMax;

	@JsonProperty("relationship_statuses")
	private List<RelationshipStatus> relationshipStatuses;

	@JsonProperty("interested_in")
	private List<InterestedIn> interestedIn;

	// location
	@JsonProperty("geo_locations")
	private TargetingLocation geoLocations;

	@JsonProperty("excluded_geo_locations")
	private TargetingLocation excludedGeoLocations;

	// placement
	@JsonProperty("page_types")
	private List<PageType> pageTypes;

	// connections
	@JsonProperty("connections")
	private List<String> connections;

	@JsonProperty("excluded_connections")
	private List<String> excludedConnections;

	@JsonProperty("friends_of_connections")
	private List<String> friendsOfConnections;

	// interests
	@JsonProperty("interests")
	private List<TargetingEntry> interests;

	// behaviors
	@JsonProperty("behaviors")
	private List<TargetingEntry> behaviors;

	// education and workplace
	@JsonProperty("education_schools")
	private List<TargetingEntry> educationSchools;

	@JsonProperty("education_statuses")
	private List<EducationStatus> educationStatuses;

	@JsonProperty("college_years")
	private List<Integer> collegeYears;

	@JsonProperty("education_majors")
	private List<TargetingEntry> educationMajors;

	@JsonProperty("work_employers")
	private List<TargetingEntry> workEmployers;

	@JsonProperty("work_positions")
	private List<TargetingEntry> workPositions;
}
