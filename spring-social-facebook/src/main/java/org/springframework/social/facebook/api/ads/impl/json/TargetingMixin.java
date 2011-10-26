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
package org.springframework.social.facebook.api.ads.impl.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ads.Gender;
import org.springframework.social.facebook.api.ads.Name;
import org.springframework.social.facebook.api.ads.RelationshipStatus;
import org.springframework.social.facebook.api.ads.UserEvent;

/**
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class TargetingMixin {

	@JsonCreator
	TargetingMixin() {
	}

	@JsonProperty("cities")
	@JsonDeserialize(using = CitiesListDeserializer.class)
	List<Name> cities;

	@JsonProperty("regions")
	@JsonDeserialize(using = RegionsListDeserializer.class)
	List<Name> regions;

	@JsonProperty("user_adclusters")
	List<Identifier> userAdclusters;

	@JsonProperty("interested_in")
	List<Gender> interestedIn;

	@JsonProperty("age_min")
	int ageMin;

	@JsonProperty("age_max")
	int ageMax;

	@JsonProperty("broad_age")
	int broadAge;

	@JsonProperty("relationship_statuses")
	List<RelationshipStatus> relationshipStatus;

	@JsonProperty("user_event")
	List<UserEvent> userEvent;

	@JsonProperty("excluded_connections")
	List<Long> excludedConnections;

	@JsonProperty("friends_of_connections")
	List<Long> friendsOfConnections;
}
