/*
 * Copyright 2011 the original author or authors.
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

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.facebook.api.AdTargeting.BroadAge;
import org.springframework.social.facebook.api.AdTargeting.EducationStatus;
import org.springframework.social.facebook.api.AdTargeting.Gender;
import org.springframework.social.facebook.api.AdTargeting.RelationshipStatus;
import org.springframework.social.facebook.api.AdTargeting.UserEvent;
import org.springframework.social.facebook.api.Reference;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class AdTargetingMixin {

    @JsonCreator
    AdTargetingMixin(@JsonProperty("countries") List<String> countries,
                     @JsonProperty("cities") List<Reference> cities,
                     @JsonProperty("zips") List<String> zips,
                     @JsonProperty("regions") List<Reference> regions,
                     @JsonProperty("radius") String radius,
                     @JsonProperty("locales") List<String> locales,
                     @JsonProperty("keywords") List<String> keywords,
                     @JsonProperty("user_adclusters") List<Reference> userAdClusters,
                     @JsonProperty("interested_in") List<Gender> interestedIn,
                     @JsonProperty("genders") List<Gender> genders,
                     @JsonProperty("age_min") Integer ageMin,
                     @JsonProperty("age_max") Integer ageMax,
                     @JsonProperty("broad_age") BroadAge broadAge,
                     @JsonProperty("relationship_statuses") List<RelationshipStatus> relationshipStatuses,
                     @JsonProperty("user_event") UserEvent userEvent,
                     @JsonProperty("connections") List<Reference> connections,
                     @JsonProperty("excluded_connections") List<Reference> excludedConnections,
                     @JsonProperty("friends_of_connections") List<Reference> friendsOfConnections,
                     @JsonProperty("college_networks") List<Reference> collegeNetworks,
                     @JsonProperty("work_networks") List<Reference> workNetworks,
                     @JsonProperty("education_statuses") List<EducationStatus> educationStatuses,
                     @JsonProperty("college_years") List<Integer> collegeYears,
                     @JsonProperty("college_majors") List<String> collegeMajors) {}
}
