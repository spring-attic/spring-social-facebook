package org.springframework.social.facebook.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.facebook.api.AdUser.AdUserPermission;
import org.springframework.social.facebook.api.AdUser.AdUserRole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdUser.
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdUserMixin extends FacebookObjectMixin {
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("permissions")
	@JsonDeserialize(using = AdUserPermissionListDeserializer.class)
	private List<AdUserPermission> permissions;

	@JsonProperty("role")
	@JsonDeserialize(using = AdUserRoleDeserializer.class)
	private AdUserRole role;

	private static class AdUserRoleDeserializer extends JsonDeserializer<AdUserRole> {
		@Override
		public AdUserRole deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				int status = jp.getIntValue();
				switch (status) {
					case 1001:
						return AdUserRole.ADMINISTRATOR;
					case 1002:
						return AdUserRole.ADVERTISER;
					case 1003:
						return AdUserRole.ANALYST;
					case 1004:
						return AdUserRole.SALES;
					default:
						return AdUserRole.UNKNOWN;
				}
			} catch (IOException e) {
				return AdUserRole.UNKNOWN;
			}
		}
	}

	private static class AdUserPermissionListDeserializer extends JsonDeserializer<List<AdUserPermission>> {
		@Override
		public List<AdUserPermission> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				List<AdUserPermission> permissions = new ArrayList<AdUserPermission>();
				try {
					while (jp.nextToken() != JsonToken.END_ARRAY) {
						int permissionValue = jp.getIntValue();
						switch (permissionValue) {
							case 1:
								permissions.add(AdUserPermission.ACCOUNT_ADMIN);
								break;
							case 2:
								permissions.add(AdUserPermission.ADMANAGER_READ);
								break;
							case 3:
								permissions.add(AdUserPermission.ADMANAGER_WRITE);
								break;
							case 4:
								permissions.add(AdUserPermission.BILLING_READ);
								break;
							case 5:
								permissions.add(AdUserPermission.BILLING_WRITE);
								break;
							case 7:
								permissions.add(AdUserPermission.REPORTS);
								break;
							default:
								permissions.add(AdUserPermission.UNKNOWN);
								break;
						}
					}
					return permissions;
				} catch (IOException e) {
					return Collections.emptyList();
				}
			}
			return Collections.emptyList();
		}
	}

}
