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
import java.util.List;


/**
 * Model class representing an entry in the user's work history.
 * @author Craig Walls
 */
@SuppressWarnings("serial")
public class WorkEntry extends FacebookObject implements Serializable {

	private Reference employer;

	private String endDate;
	
	private Page location;
	
	private Page position;

	private String startDate;
	
	private List<Project> projects;

	public Reference getEmployer() {
		return employer;
	}

	public String getEndDate() {
		return endDate;
	}

	public Page getLocation() {
		return location;
	}
	
	public Page getPosition() {
		return position;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public String getStartDate() {
		return startDate;
	}
	
	public static class Project {

		private String id;

		private String name;

		private String description;

		private String endDate;
		
		private String startDate;

		private Reference from;

		private List<Reference> with;

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		
		public String getEndDate() {
			return endDate;
		}
		
		public String getStartDate() {
			return startDate;
		}

		public Reference getFrom() {
			return from;
		}

		public List<Reference> getWith() {
			return with;
		}
		
	}
	
}
