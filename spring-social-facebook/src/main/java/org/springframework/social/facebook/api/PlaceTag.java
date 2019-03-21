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

import java.util.Date;

public class PlaceTag {

	private final String id;
	
	private final Date createdTime;
	
	private final Page place;

	public PlaceTag(String id, Date createdTime, Page place) {
		this.id = id;
		this.createdTime = createdTime;
		this.place = place;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public Page getPlace() {
		return place;
	}
	
}
