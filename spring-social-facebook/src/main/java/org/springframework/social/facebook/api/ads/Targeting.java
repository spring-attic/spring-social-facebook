package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

/**
 * @author Sebastian Górecki
 */
public class Targeting {

	// demographics
	private List<Gender> genders;
	private Integer ageMin;
	private Integer ageMax;
	private List<RelationshipStatus> relationshipStatuses;
	private List<InterestedIn> interestedIn;

	// location
	private TargetingLocation geoLocations;
	private TargetingLocation excludedGeoLocations;

	// placement
	private List<PageType> pageTypes;

	// connections
	private List<String> connections;
	private List<String> excludedConnections;
	private List<String> friendsOfConnections;

	// interests
	private List<TargetingEntry> interests;

	// behaviors
	private List<TargetingEntry> behaviors;

	// education and workplace
	private List<TargetingEntry> educationSchools;
	private List<EducationStatus> educationStatuses;
	private List<Integer> collegeYears;
	private List<TargetingEntry> educationMajors;
	private List<TargetingEntry> workEmployers;
	private List<TargetingEntry> workPositions;

	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public List<RelationshipStatus> getRelationshipStatuses() {
		return relationshipStatuses;
	}

	public void setRelationshipStatuses(List<RelationshipStatus> relationshipStatuses) {
		this.relationshipStatuses = relationshipStatuses;
	}

	public List<InterestedIn> getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(List<InterestedIn> interestedIn) {
		this.interestedIn = interestedIn;
	}

	public TargetingLocation getGeoLocations() {
		return geoLocations;
	}

	public void setGeoLocations(TargetingLocation geoLocations) {
		this.geoLocations = geoLocations;
	}

	public TargetingLocation getExcludedGeoLocations() {
		return excludedGeoLocations;
	}

	public void setExcludedGeoLocations(TargetingLocation excludedGeoLocations) {
		this.excludedGeoLocations = excludedGeoLocations;
	}

	public List<PageType> getPageTypes() {
		return pageTypes;
	}

	public void setPageTypes(List<PageType> pageTypes) {
		this.pageTypes = pageTypes;
	}

	public List<String> getConnections() {
		return connections;
	}

	public void setConnections(List<String> connections) {
		this.connections = connections;
	}

	public List<String> getExcludedConnections() {
		return excludedConnections;
	}

	public void setExcludedConnections(List<String> excludedConnections) {
		this.excludedConnections = excludedConnections;
	}

	public List<String> getFriendsOfConnections() {
		return friendsOfConnections;
	}

	public void setFriendsOfConnections(List<String> friendsOfConnections) {
		this.friendsOfConnections = friendsOfConnections;
	}

	public List<TargetingEntry> getInterests() {
		return interests;
	}

	public void setInterests(List<TargetingEntry> interests) {
		this.interests = interests;
	}

	public List<TargetingEntry> getBehaviors() {
		return behaviors;
	}

	public void setBehaviors(List<TargetingEntry> behaviors) {
		this.behaviors = behaviors;
	}

	public List<TargetingEntry> getEducationSchools() {
		return educationSchools;
	}

	public void setEducationSchools(List<TargetingEntry> educationSchools) {
		this.educationSchools = educationSchools;
	}

	public List<EducationStatus> getEducationStatuses() {
		return educationStatuses;
	}

	public void setEducationStatuses(List<EducationStatus> educationStatuses) {
		this.educationStatuses = educationStatuses;
	}

	public List<Integer> getCollegeYears() {
		return collegeYears;
	}

	public void setCollegeYears(List<Integer> collegeYears) {
		this.collegeYears = collegeYears;
	}

	public List<TargetingEntry> getEducationMajors() {
		return educationMajors;
	}

	public void setEducationMajors(List<TargetingEntry> educationMajors) {
		this.educationMajors = educationMajors;
	}

	public List<TargetingEntry> getWorkEmployers() {
		return workEmployers;
	}

	public void setWorkEmployers(List<TargetingEntry> workEmployers) {
		this.workEmployers = workEmployers;
	}

	public List<TargetingEntry> getWorkPositions() {
		return workPositions;
	}

	public void setWorkPositions(List<TargetingEntry> workPositions) {
		this.workPositions = workPositions;
	}

	public enum Gender {
		UNKNOWN(0), MALE(1), FEMALE(2);

		private final int value;

		Gender(int value) {
			this.value = value;
		}

		@JsonCreator
		public static Gender forValue(int value) {
			for (Gender gender : Gender.values()) {
				if (gender.getValue() == value) return gender;
			}
			return UNKNOWN;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
	}

	public enum RelationshipStatus {
		UNKNOWN(0), SINGLE(1), IN_RELATIONSHIP(2), MARRIED(3), ENGAGED(4), NOT_SPECIFIED(6), IN_CIVIL_UNION(7),
		IN_DOMESTIC_PARTNERSHIP(8), IN_OPEN_RELATIONSHIP(9), ITS_COMPLICATED(10), SEPARATED(11), DIVORCED(12),
		WIDOWED(13);

		private final int value;

		RelationshipStatus(int value) {
			this.value = value;
		}

		@JsonCreator
		public static RelationshipStatus forValue(int value) {
			for (RelationshipStatus status : RelationshipStatus.values()) {
				if (status.getValue() == value) return status;
			}
			return UNKNOWN;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
	}

	public enum InterestedIn {
		UNKNOWN(0), MEN(1), WOMEN(2), MAN_AND_WOMAN(3), NOT_SPECIFIED(4);

		private final int value;

		InterestedIn(int value) {
			this.value = value;
		}

		@JsonCreator
		public static InterestedIn forValue(int value) {
			for (InterestedIn interestedIn : InterestedIn.values()) {
				if (interestedIn.getValue() == value) return interestedIn;
			}
			return UNKNOWN;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
	}

	public enum PageType {
		UNKNOWN("unknown"), DESKTOP("desktop"), FEED("feed"), DESKTOP_FEED("desktopfeed"), MOBILE("mobile"),
		MOBILE_FEED_AND_EXTERNAL("mobilefeed-and-external"), MOBILE_FEED("mobilefeed"), RIGHTCOLUMN("rightcolumn"),
		RIGHTCOLUMN_AND_MOBILE("rightcolumn-and-mobile"), HOME("home"), DESKTOP_AND_MOBILE_AND_EXTERNAL("desktop-and-mobile-and-external"),
		FEED_AND_EXTERNAL("feed-and-external"), RIGHTCOLUMN_AND_MOBILE_AND_EXTERNAL("rightcolumn-and-mobile-and-external");

		private final String value;

		PageType(String value) {
			this.value = value;
		}

		@JsonCreator
		public static PageType forValue(String value) {
			for (PageType type : PageType.values()) {
				if (type.getValue().equals(value)) return type;
			}
			return UNKNOWN;
		}

		@JsonValue
		public String getValue() {
			return value;
		}
	}

	public enum EducationStatus {
		UNKNOWN(0), HIGH_SCHOOL(1), UNDERGRAD(2), ALUM(3), HIGH_SCHOOL_GRAD(4), SOME_COLLEGE(5), ASSOCIATE_DEGREE(6),
		IN_GRAD_SCHOOL(7), SOME_GRAD_SCHOOL(8), MASTER_DEGREE(9), PROFESSIONAL_DEGREE(10), DOCTORATE_DEGREE(11),
		UNSPECIFIED(12), SOME_HIGH_SCHOOL(13);

		private final int value;

		EducationStatus(int value) {
			this.value = value;
		}

		@JsonCreator
		public static EducationStatus forValue(int value) {
			for (EducationStatus status : EducationStatus.values()) {
				if (status.getValue() == value) return status;
			}
			return UNKNOWN;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
	}
}
