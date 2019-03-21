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
package org.springframework.social.facebook.api.impl.json;

import java.util.List;

import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.WorkEntry.Project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to WorkEntry. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class WorkEntryMixin extends FacebookObjectMixin {
	
	@JsonProperty("employer")
	Reference employer;
	
	@JsonProperty("end_date")
	String endDate;
	
	@JsonProperty("location")
	Page location;
	
	@JsonProperty("position")
	Page position;
	
	@JsonProperty("projects")
	List<Project> projects;

	@JsonProperty("start_date")
	String startDate;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static abstract class ProjectMixin {
		
		@JsonProperty("description")
		private String description;

		@JsonProperty("end_date")
		private String endDate;
		
		@JsonProperty("start_date")
		private String startDate;
		
		@JsonProperty("with")
		private List<Reference> with;
		
	}
}
