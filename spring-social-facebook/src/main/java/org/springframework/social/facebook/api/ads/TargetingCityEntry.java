package org.springframework.social.facebook.api.ads;

/**
 * @author Sebastian Górecki
 */
public class TargetingCityEntry {
	private String key;
	private int radius;
	private String distanceUnit;

	TargetingCityEntry() {
	}

	public TargetingCityEntry(String key, int radius, String distanceUnit) {
		this.key = key;
		this.radius = radius;
		this.distanceUnit = distanceUnit;
	}

	public String getKey() {
		return key;
	}

	public int getRadius() {
		return radius;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}
}
