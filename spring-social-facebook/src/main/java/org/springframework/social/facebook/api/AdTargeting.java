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
package org.springframework.social.facebook.api;

import java.util.List;

/**
 * Ad Targeting object on Facebook {@link http://developers.facebook.com/docs/reference/ads-api/adgroup/}
 *
 * @author Will Taylor
 *
 */
public class AdTargeting {

    // Location Targets
    private final List<String> countries;
    private final List<Reference> cities;
    private final List<String> zips;
    private final List<Reference> regions;
    private final String radius;
    private final List<String> locales;

    // Likes / Interest Targets
    private final List<String> keywords;
    private final List<Reference> userAdClusters;
    private final List<Gender> interestedIn;

    // Demographics / Events
    private final List<Gender> genders;
    private final Integer ageMin;
    private final Integer ageMax;
    private final BroadAge broadAge;
    private final List<RelationshipStatus> relationshipStatuses;
    private final UserEvent userEvent;

    // Facebook Connections
    private final List<Reference> connections;
    private final List<Reference> excludedConnections;
    private final List<Reference> friendsOfConnections;

    // Education and Work
    private final List<Reference> collegeNetworks;
    private final List<Reference> workNetworks;
    private final List<EducationStatus> educationStatuses;
    private final List<Integer> collegeYears;
    private final List<String> collegeMajors;

    public AdTargeting(List<String> countries, List<Reference> cities, List<String> zips, List<Reference> regions, String radius, List<String> locales, List<String> keywords, List<Reference> userAdClusters, List<Gender> interestedIn, List<Gender> genders, Integer ageMin, Integer ageMax, BroadAge broadAge, List<RelationshipStatus> relationshipStatuses, UserEvent userEvent, List<Reference> connections, List<Reference> excludedConnections, List<Reference> friendsOfConnections, List<Reference> collegeNetworks, List<Reference> workNetworks, List<EducationStatus> educationStatuses, List<Integer> collegeYears, List<String> collegeMajors) {
        this.countries = Collections2.immutable(countries);
        this.cities = Collections2.immutable(cities);
        this.zips = Collections2.immutable(zips);
        this.regions = Collections2.immutable(regions);
        this.radius = radius;
        this.locales = Collections2.immutable(locales);
        this.keywords = Collections2.immutable(keywords);
        this.userAdClusters = Collections2.immutable(userAdClusters);
        this.interestedIn = Collections2.immutable(interestedIn);
        this.genders = Collections2.immutable(genders);
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.broadAge = broadAge;
        this.relationshipStatuses = Collections2.immutable(relationshipStatuses);
        this.userEvent = userEvent;
        this.connections = Collections2.immutable(connections);
        this.excludedConnections = Collections2.immutable(excludedConnections);
        this.friendsOfConnections = Collections2.immutable(friendsOfConnections);
        this.collegeNetworks = Collections2.immutable(collegeNetworks);
        this.workNetworks = Collections2.immutable(workNetworks);
        this.educationStatuses = Collections2.immutable(educationStatuses);
        this.collegeYears = Collections2.immutable(collegeYears);
        this.collegeMajors = Collections2.immutable(collegeMajors);
    }

    public List<Gender> getGenders( ) {
        return Collections2.copy(genders);
    }

    public List<String> getCountries() {
        return Collections2.copy(countries);
    }

    public List<Reference> getCities() {
        return Collections2.copy(cities);
    }

    public List<String> getZips() {
        return Collections2.copy(zips);
    }

    public List<Reference> getRegions() {
        return Collections2.copy(regions);
    }

    public String getRadius() {
        return radius;
    }

    public List<String> getLocales() {
        return Collections2.copy(locales);
    }

    public List<String> getKeywords() {
        return Collections2.copy(keywords);
    }

    public List<Reference> getUserAdClusters() {
        return Collections2.copy(userAdClusters);
    }

    public List<Gender> getInterestedIn() {
        return Collections2.copy(interestedIn);
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public BroadAge getBroadAge() {
        return broadAge;
    }

    public List<RelationshipStatus> getRelationshipStatuses() {
        return Collections2.copy(relationshipStatuses);
    }

    public UserEvent getUserEvent() {
        return userEvent;
    }

    public List<Reference> getConnections() {
        return Collections2.copy(connections);
    }

    public List<Reference> getExcludedConnections() {
        return Collections2.copy(excludedConnections);
    }

    public List<Reference> getFriendsOfConnections() {
        return Collections2.copy(friendsOfConnections);
    }

    public List<Reference> getCollegeNetworks() {
        return Collections2.copy(collegeNetworks);
    }

    public List<Reference> getWorkNetworks() {
        return Collections2.copy(workNetworks);
    }

    public List<EducationStatus> getEducationStatuses() {
        return Collections2.copy(educationStatuses);
    }

    public List<Integer> getCollegeYears() {
        return Collections2.copy(collegeYears);
    }

    public List<String> getCollegeMajors() {
        return Collections2.copy(collegeMajors);
    }

    public enum Gender {
        UNUSED_0(0), MALE(1), FEMALE(2);
        private final int gender;
        private Gender(int gender){ this.gender = gender; }
        public int getGender() { return gender; }
        public static Gender getValue(int gender) {
            Gender result = null;

            switch ( gender ) {
            case 1:
                result = MALE;
                break;
            case 2:
                result = FEMALE;
                break;
            default:
                throw new EnumConstantNotPresentException(Gender.class, String.valueOf(gender));
            }

            return result;
        }
    }

    public enum BroadAge {
        UNUSED_0(0), ENABLED(1);
        private final int broadAge;
        private BroadAge(int broadAge){ this.broadAge = broadAge; }
        public int getBroadAge() { return broadAge; }
        public static BroadAge getValue(int broadAge) {
            BroadAge result = null;

            switch ( broadAge ) {
            case 1:
                result = ENABLED;
                break;
            default:
                throw new EnumConstantNotPresentException(BroadAge.class, String.valueOf(broadAge));
            }

            return result;
        }
    }

    public enum RelationshipStatus {
        UNUSED_0(0), SINGLE(1), IN_RELATIONSHIP(2), MARRIED(3), ENGAGED(4);
        private final int relationshipStatus;
        private RelationshipStatus(int relationshipStatus){ this.relationshipStatus = relationshipStatus; }
        public int getRelationshipStatus() { return relationshipStatus; }
        public static RelationshipStatus getValue(int relationshipStatus) {
            RelationshipStatus result = null;

            switch ( relationshipStatus ) {
            case 1:
                result = SINGLE;
                break;
            case 2:
                result = IN_RELATIONSHIP;
                break;
            case 3:
                result = MARRIED;
                break;
            case 4:
                result = ENGAGED;
                break;
            default:
                throw new EnumConstantNotPresentException(RelationshipStatus.class, String.valueOf(relationshipStatus));
            }

            return result;
        }
    }

    public enum UserEvent {
        UNUSED_0(0), BIRTHDAY(1);
        private final int userEvent;
        private UserEvent(int userEvent){ this.userEvent = userEvent; }
        public int getUserEvent() { return userEvent; }
        public static UserEvent getValue(int userEvent) {
            UserEvent result = null;

            switch ( userEvent ) {
            case 1:
                result = BIRTHDAY;
                break;
            default:
                throw new EnumConstantNotPresentException(UserEvent.class, String.valueOf(userEvent));
            }

            return result;
        }
    }

    public enum EducationStatus {
        UNUSED_0(0), HIGH_SCHOOL(1), UNDERGRAD(2), ALUM(3);
        private final int educationStatus;
        private EducationStatus(int educationStatus){ this.educationStatus = educationStatus; }
        public int getEducationStatus() { return educationStatus; }
        public static EducationStatus getValue(int educationStatus) {
            EducationStatus result = null;

            switch ( educationStatus ) {
            case 1:
                result = HIGH_SCHOOL;
                break;
            case 2:
                result = UNDERGRAD;
                break;
            case 3:
                result = ALUM;
                break;
            default:
                throw new EnumConstantNotPresentException(EducationStatus.class, String.valueOf(educationStatus));
            }

            return result;
        }
    }


}
