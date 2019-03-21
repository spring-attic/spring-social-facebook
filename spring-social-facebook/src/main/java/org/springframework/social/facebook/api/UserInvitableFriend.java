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

import java.io.Serializable;

/**
 * Domain type representing an invitable friend.
 * @author Craig Walls
 */
@SuppressWarnings("serial")
public class UserInvitableFriend extends FacebookObject implements Serializable {

	private final String id;

	private final String name;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String lastName;

	public UserInvitableFriend(String id, String name, String firstName, String middleName, String lastName) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
}
