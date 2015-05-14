package org.springframework.social.facebook.api.ads;

import org.springframework.social.facebook.api.FacebookObject;

/**
 * Model class representing an ad account group.
 *
 * @author Sebastian Górecki
 */
public class AdAccountGroup extends FacebookObject {
	private String id;
	private String name;
	private int status;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}
}
