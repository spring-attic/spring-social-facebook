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

/**
 * @author Karthick Sankarachary
 */
public class Targeting {
	private List<String> countries;
	private List<Integer> genders;
	private List<Integer> relationshipStatus;
	private int ageMin;
	private int ageMax;
	private List<Name> cities;
	private int radius;
	private List<String> keywords;

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<Integer> getGenders() {
		return genders;
	}

	public void setGenders(List<Integer> genders) {
		this.genders = genders;
	}

	public List<Integer> getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(List<Integer> relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public List<Name> getCities() {
		return cities;
	}

	public void setCities(List<Name> cities) {
		this.cities = cities;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

}
