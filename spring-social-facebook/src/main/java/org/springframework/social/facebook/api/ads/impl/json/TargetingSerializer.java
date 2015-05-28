package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.social.facebook.api.ads.Targeting;

import java.io.IOException;

/**
 * @author Sebastian Górecki
 */
public class TargetingSerializer extends JsonSerializer<Targeting> {
	@Override
	public void serialize(Targeting targeting, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		if (targeting.getGenders() != null) {
			jgen.writeObjectField("genders", targeting.getGenders());
		}
		if (targeting.getAgeMin() != null) {
			jgen.writeObjectField("age_min", targeting.getAgeMin());
		}
		if (targeting.getAgeMax() != null) {
			jgen.writeObjectField("age_max", targeting.getAgeMax());
		}
		if (targeting.getRelationshipStatuses() != null) {
			jgen.writeObjectField("relationship_statuses", targeting.getRelationshipStatuses());
		}
		if (targeting.getInterestedIn() != null) {
			jgen.writeObjectField("interested_in", targeting.getInterestedIn());
		}
		if (targeting.getGeoLocations() != null) {
			jgen.writeObjectField("geo_locations", targeting.getGeoLocations());
		}
		if (targeting.getExcludedGeoLocations() != null) {
			jgen.writeObjectField("excluded_geo_locations", targeting.getExcludedGeoLocations());
		}
		if (targeting.getPageTypes() != null) {
			jgen.writeObjectField("page_types", targeting.getPageTypes());
		}
		if (targeting.getConnections() != null) {
			jgen.writeObjectField("connections", targeting.getConnections());
		}
		if (targeting.getExcludedConnections() != null) {
			jgen.writeObjectField("excluded_connections", targeting.getExcludedConnections());
		}
		if (targeting.getFriendsOfConnections() != null) {
			jgen.writeObjectField("friends_of_connections", targeting.getFriendsOfConnections());
		}
		if (targeting.getInterests() != null) {
			jgen.writeObjectField("interests", targeting.getInterests());
		}
		if (targeting.getBehaviors() != null) {
			jgen.writeObjectField("behaviors", targeting.getBehaviors());
		}
		if (targeting.getEducationSchools() != null) {
			jgen.writeObjectField("education_schools", targeting.getEducationSchools());
		}
		if (targeting.getEducationStatuses() != null) {
			jgen.writeObjectField("education_statuses", targeting.getEducationStatuses());
		}
		if (targeting.getCollegeYears() != null) {
			jgen.writeObjectField("college_years", targeting.getCollegeYears());
		}
		if (targeting.getEducationMajors() != null) {
			jgen.writeObjectField("education_majors", targeting.getEducationMajors());
		}
		if (targeting.getWorkEmployers() != null) {
			jgen.writeObjectField("work_employers", targeting.getWorkEmployers());
		}
		if (targeting.getWorkPositions() != null) {
			jgen.writeObjectField("work_positions", targeting.getWorkPositions());
		}

		jgen.writeEndObject();
	}
}
