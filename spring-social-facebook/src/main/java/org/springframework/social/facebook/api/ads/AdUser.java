package org.springframework.social.facebook.api.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
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
		ACCOUNT_ADMIN(1), ADMANAGER_READ(2), ADMANAGER_WRITE(3), BILLING_READ(4), BILLING_WRITE(5), REPORTS(7), UNKNOWN(0);

		private final int value;

		AdUserPermission(int value) {
			this.value = value;
		}

		@JsonCreator
		public static AdUserPermission forValue(int value) {
			for (AdUserPermission permission : AdUserPermission.values()) {
				if (permission.getValue() == value) {
					return permission;
				}
			}
			return UNKNOWN;
		}

		@JsonGetter
		public int getValue() {
			return value;
		}
	}

	public enum AdUserRole {
		ADMINISTRATOR(1001), ADVERTISER(1002), ANALYST(1003), SALES(1004), UNKNOWN(0);

		private final int value;

		AdUserRole(int value) {
			this.value = value;
		}

		@JsonCreator
		public static AdUserRole forValue(int value) {
			for (AdUserRole role : AdUserRole.values()) {
				if (role.getValue() == value) {
					return role;
				}
			}
			return UNKNOWN;
		}

		@JsonGetter
		public int getValue() {
			return value;
		}
	}
}
