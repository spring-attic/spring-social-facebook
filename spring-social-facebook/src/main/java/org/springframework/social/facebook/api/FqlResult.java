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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Represents a single result item from an FQL query.
 * Given to an {@link FqlResultMapper}, in a way that is analogous to how a ResultSet is given to a RowMapper in Spring's JdbcTemplate.
 * @author habuma
 */
public class FqlResult {

	private final Map<String, Object> resultMap;

	/**
	 * Constructs an FqlResult instance from a map.
	 * @param resultMap a Map representing the query results
	 */
	public FqlResult(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * Returns the value of the identified field as a String.
	 * @param fieldName the name of the field
	 * @return the value of the field as a String
	 */
	public String getString(String fieldName) {
		return hasValue(fieldName) ? String.valueOf(resultMap.get(fieldName)) : null;
	}

	/**
	 * Returns the value of the identified field as an Integer.
	 * @param fieldName the name of the field
	 * @return the value of the field as an Integer
	 * @throws FqlException if the field cannot be expressed as an Integer
	 */
	public Integer getInteger(String fieldName) {
		try {
			return hasValue(fieldName) ? Integer.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
		} catch (NumberFormatException e) {
			throw new FqlException("Field '" + fieldName +"' is not a number.", e);
		}
	}

	/**
	 * Returns the value of the identified field as a Long.
	 * @param fieldName the name of the field
	 * @return the value of the field as a Long
	 * @throws FqlException if the field cannot be expressed as an Long
	 */
	public Long getLong(String fieldName) {
		try {
			return hasValue(fieldName) ? Long.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
		} catch (NumberFormatException e) {
			throw new FqlException("Field '" + fieldName +"' is not a number.", e);
		}
	}
	
	/**
	 * Returns the value of the identified field as a Float.
	 * @param fieldName the name of the field
	 * @return the value of the field as a Float
	 * @throws FqlException if the field cannot be expressed as an Float
	 */
	public Float getFloat(String fieldName) {
		try {
			return hasValue(fieldName) ? Float.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
		} catch (NumberFormatException e) {
			throw new FqlException("Field '" + fieldName +"' is not a number.", e);
		}
	}

	/**
	 * Returns the value of the identified field as a Boolean.
	 * @param fieldName the name of the field
	 * @return the value of the field as a Boolean
	 */
	public Boolean getBoolean(String fieldName) {
		return hasValue(fieldName) ? Boolean.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
	}
	
	/**
	 * Returns the value of the identified field as a Date.
	 * Time fields returned from an FQL query are expressed in terms of seconds since midnight, January 1, 1970 UTC.
	 * @param fieldName the name of the field
	 * @return the value of the field as a Date
	 * @throws FqlException if the field's value cannot be expressed as a long value from which a Date object can be constructed.
	 */
	public Date getTime(String fieldName) {
		try {
			if (hasValue(fieldName)) {
				return new Date(Long.valueOf(String.valueOf(resultMap.get(fieldName))) * 1000);
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			throw new FqlException("Field '" + fieldName +"' is not a time.", e);
		}
	}
	
	/**
	 * Returns the value of the identified field as a simple Object.
	 * @param fieldName the name of the field
	 * @return the value of the field as an Object
	 */
	public Object getObject(String fieldName) {
		return resultMap.get(fieldName);
	}

	/**
	 * Returns the value of the identified field as an object mapped by a given {@link FqlResultMapper}.
	 * @param fieldName the name of the field
	 * @param mapper an {@link FqlResultMapper} used to map the object date into a specific type.
	 * @param <T> the Java type to bind the Facebook object to
	 * @return the value of the field as an object of a type the same as the parameterized type of the given {@link FqlResultMapper}.
	 * @throws FqlException if the value of the field is not a nested object.
	 */
	public <T> T getObject(String fieldName, FqlResultMapper<T> mapper) {
		if (!hasValue(fieldName)) {
			return null;
		}
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> value = (Map<String, Object>) resultMap.get(fieldName);
			return mapper.mapObject(new FqlResult(value));
		} catch (ClassCastException e) {
			throw new FqlException("Field '" + fieldName +"' is not an object.", e);
		}
	}

	/**
	 * Returns the value of the identified field as an object mapped by a given {@link FqlResultMapper}.
	 * @param fieldName the name of the field
	 * @param mapper an {@link FqlResultMapper} used to map the object date into a specific type.
	 * @param <T> the Java type to bind the Facebook object to
	 * @return the value of the field as list of objects whose type is the same as the parameterized type of the given {@link FqlResultMapper}.
	 * @throws FqlException if the value of the field is not a list.
	 */
	public <T> List<T> getList(String fieldName, FqlResultMapper<T> mapper) {
		if (!hasValue(fieldName)) {
			return null;
		}		
		try {
			List<T> response = new ArrayList<T>();
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> arrayItems = (List<Map<String, Object>>) resultMap.get(fieldName);
			for (Map<String, Object> arrayItem : arrayItems) {
				response.add(mapper.mapObject(new FqlResult(arrayItem)));
			}		
			return response;
		} catch (ClassCastException e) {
			throw new FqlException("Field '" + fieldName +"' is not a list.", e);
		}
	}

	/**
	 * Checks for the existence of a field in the result set, whether null or non-null.
	 * @param fieldName the name of the field to check existence of.
	 * @return true if the field exists in the result set, even if the value is null; false if the field is not in the result set.
	 */
	public boolean hasField(String fieldName) {
		return resultMap.containsKey(fieldName);
	}

	/**
	 * Checks that a field exists and contains a non-null value.
	 * @param fieldName the name of the field to check existence/value of.
	 * @return true if the field exists in the result set and has a non-null value; false otherwise.
	 */
	public boolean hasValue(String fieldName) {
		return resultMap.containsKey(fieldName) && resultMap.get(fieldName) != null;
	}

}
