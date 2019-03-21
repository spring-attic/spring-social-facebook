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
 * Model class representing an entry in a user's education history.
 * @author Craig Walls
 */
@SuppressWarnings("serial")
public class EducationExperience extends FacebookObject implements Serializable {

	private List<Experience> classes;
	
	private List<Reference> concentration;
	
	private Reference degree;
	
	private Reference school;

	private String type;
	
	private List<Reference> with;

	private Reference year;

	public List<Experience> getClasses() {
		return classes;
	}
	
	public List<Reference> getConcentration() {
		return concentration;
	}

	public Reference getDegree() {
		return degree;
	}
	
	public Reference getSchool() {
		return school;
	}

	public String getType() {
		return type;
	}
	
	public List<Reference> getWith() {
		return with;
	}
	
	public Reference getYear() {
		return year;
	}

}
