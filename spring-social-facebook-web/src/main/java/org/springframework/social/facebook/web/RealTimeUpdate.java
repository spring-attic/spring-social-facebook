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
package org.springframework.social.facebook.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a real-time update from Facebook.
 * Note that per Facebook's Real-Time Update API, only essential information on what changed is given; no actual changed data is provided.
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class RealTimeUpdate {

	@JsonProperty("object")
	private String object;
	
	@JsonProperty("entry")
	private List<Entry> entries;
		
	/**
	 * @return the object type that changed (e.g., "user", "page", etc).
	 */
	public String getObject() {
		return object;
	}
	
	/**
	 * @return the entries that changed. Multiple change entries for multiple objects may be given in a single update.
	 */
	public List<Entry> getEntries() {
		return entries;
	}
	
	/**
	 * Represents a single change entry.
	 */
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Entry {
		@JsonProperty("id")
		private long id;

		@JsonProperty("time")
		private long time;
		
		@JsonProperty("changed_fields")
		private List<String> changedFields;
		
		/**
		 * @return the ID of the object that changed (e.g., if the object is a "user", then this is the user's Facebook ID).
		 */
		public long getId() {
			return id;
		}

		/**
		 * @return the time of the change in seconds since Jan 1, 1970.
		 */
		public long getTime() {
			return time;
		}
		
		/**
		 * @return a list of the fields that changed on the object. {@link UpdateHandler} implementations may use this as a clue to know what data to fetch to see the details of the change.
		 */
		public List<String> getChangedFields() {
			return changedFields;
		}

	}
}
