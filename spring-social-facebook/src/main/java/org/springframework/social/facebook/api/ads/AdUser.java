package org.springframework.social.facebook.api.ads;

import org.springframework.social.facebook.api.FacebookObject;

import java.util.List;

/**
 * Model class representing an ad user.
 *
 * @author Sebastian Górecki
 */
public class AdUser extends FacebookObject {
	private String id;
	private String name;
	private List<AdUserPermission> permissions;
	private AdUserRole role;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<AdUserPermission> getPermissions() {
		return permissions;
	}

	public AdUserRole getRole() {
		return role;
	}

	public enum AdUserPermission {
		ACCOUNT_ADMIN, ADMANAGER_READ, ADMANAGER_WRITE, BILLING_READ, BILLING_WRITE, REPORTS, UNKNOWN
	}

	public enum AdUserRole {
		ADMINISTRATOR, ADVERTISER, ANALYST, SALES, UNKNOWN
	}
}
