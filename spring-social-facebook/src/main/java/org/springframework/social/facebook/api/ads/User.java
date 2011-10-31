/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.facebook.api.ads.impl.json.UserRoleDeserializer;

/**
 * The <code>User</code> class contains the id, permissions and roles of a given
 * ad account user.
 * 
 * @author Karthick Sankarachary
 */
public class User {
	private String uid;
	private List<Permission> permissions;
	private UserRole role;
	private AccountStatus status;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public UserRole getRole() {
		return role;
	}

	@JsonDeserialize(using = UserRoleDeserializer.class)
	public void setRole(UserRole role) {
		this.role = role;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}
