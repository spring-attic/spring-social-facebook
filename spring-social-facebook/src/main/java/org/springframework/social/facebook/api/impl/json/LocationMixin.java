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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to Location. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class LocationMixin extends FacebookObjectMixin {
	
	@JsonCreator
	LocationMixin(
			@JsonProperty("latitude") double latitude, 
			@JsonProperty("longitude") double longitude) {}
	
	@JsonProperty("id")
	String id;
	
	@JsonProperty("street")
	String street;

	@JsonProperty("city")
	String city;

	@JsonProperty("state")
	String state;

	@JsonProperty("country")
	String country;

	@JsonProperty("zip")
	String zip;

	@JsonProperty("description")
	String description;
	
	@JsonProperty("located_in")
	String locatedIn;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("region")
	String region;

}
