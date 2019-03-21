/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

/**
 * Represents a user permission, whether granted or declined.
 * @author Craig Walls
 */
public class Permission {

	private final String name;
	
	private final String status;

	/**
	 * Creates the permission.
	 * @param name The permission name
	 * @param status The permission status
	 */
	public Permission(String name, String status) {
		this.name = name;
		this.status = status;
	}
	
	/**
	 * @return the name of the permission (e.g., "publish_actions")
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the status of the permission (e.g., "granted")
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Convenience method for checking granted state of the permission.
	 * @return true if status is equal to "granted"
	 */
	public boolean isGranted() {
		return "granted".equals(status);
	}
	
	/**
	 * Convenience method for checking declined state of the permission.
	 * @return true if status is equal to "declined"
	 */
	public boolean isDeclined() {
		return "declined".equals(status);
	}
	
}
