/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.facebook.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * Strategy interface for deserializing lists of data returned from Facebook as
 * JSON.
 * 
 * @author Craig Walls
 */
public class ListDeserializer {
	private ObjectMapper objectMapper;

	public ListDeserializer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public <T> List<T> deserializeList(JsonNode jsonNode, Class<T> elementType)
			throws Exception {
		Object value = objectMapper.readValue(jsonNode,
				TypeFactory.arrayType(elementType));
		List<T> returnValue = new ArrayList<T>();
		if (value != null) {
			if (value.getClass().isArray()) {
				for (T element : (T[]) value) {
					returnValue.add(element);
				}
			} else if (value instanceof List) {
				return (List<T>) value;
			}
		}
		return returnValue;
	}

	public <T> List<T> deserializeList(Map<String, ?> map, Class<T> elementType) {
		return deserializeList(map, elementType, "data");
	}

	public <T> List<T> deserializeList(Map<String, ?> map,
			Class<T> elementType, String listPropertyName) {
		try {
			return deserializeList(
					objectMapper.valueToTree(map.get(listPropertyName)),
					elementType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}