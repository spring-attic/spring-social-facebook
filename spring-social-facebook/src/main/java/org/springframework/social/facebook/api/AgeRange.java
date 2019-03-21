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
 * Enum representing a user's age range.
 * @author Craig Walls
 */
public enum AgeRange {

	UNKNOWN(null, null),
	AGE_13_17(13, 17),
	AGE_18_20(18, 20),
	AGE_21_PLUS(21, null);
	
	private Integer min;
	private Integer max;

	private AgeRange(Integer min, Integer max) {
		this.min = min;
		this.max = max;
	}
	
	/**
	 * @return The minimum integer value for the range (possibly null).
	 */
	public Integer getMin() {
		return min;
	}
	
	/**
	 * @return The maximum integer value for the range (possibly null).
	 */
	public Integer getMax() {
		return max;
	}
	
	/**
	 * Constructs an AgeRange from the min/max age values.
	 * @param min The minimum age
	 * @param max The maximum age
	 * @return an AgeRange
	 */
	public static AgeRange fromMinMax(Integer min, Integer max) {
		if (min == 13 && max == 17) {
			return AGE_13_17;
		} else if (min == 18 && max == 20) {
			return AGE_18_20;
		} else if (min == 21 && max == null) {
			return AGE_21_PLUS;
		}
		
		AgeRange unknown = AgeRange.UNKNOWN;
		unknown.min = min;
		unknown.max = max;
		return unknown;
	}

}
