package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

/**
 * @author Sebastian Górecki
 */
public class TargetingLocation {
	private List<String> countries;
	private List<String> regions;
	private List<TargetingCityEntry> cities;
	private List<String> zips;
	private List<LocationType> locationTypes;

	public List<LocationType> getLocationTypes() {
		return locationTypes;
	}

	public void setLocationTypes(List<LocationType> locationTypes) {
		this.locationTypes = locationTypes;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public List<TargetingCityEntry> getCities() {
		return cities;
	}

	public void setCities(List<TargetingCityEntry> cities) {
		this.cities = cities;
	}

	public List<String> getZips() {
		return zips;
	}

	public void setZips(List<String> zips) {
		this.zips = zips;
	}

	public enum LocationType {
		UNKNOWN("unknown"), RECENT("recent"), HOME("home"), TRAVEL_IN("travel_in");

		private final String value;

		LocationType(String value) {
			this.value = value;
		}

		@JsonCreator
		public static LocationType forValue(String value) {
			for (LocationType type : LocationType.values()) {
				if (type.getValue().equals(value)) return type;
			}
			return UNKNOWN;
		}

		@JsonValue
		public String getValue() {
			return value;
		}
	}
}
