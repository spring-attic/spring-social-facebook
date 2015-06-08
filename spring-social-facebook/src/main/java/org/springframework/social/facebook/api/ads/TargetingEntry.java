package org.springframework.social.facebook.api.ads;

/**
 * @author Sebastian Górecki
 */
public class TargetingEntry {
	private long id;
	private String name;

	public TargetingEntry() {
	}

	public TargetingEntry(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
