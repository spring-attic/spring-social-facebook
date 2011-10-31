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
package org.springframework.social.facebook.api.impl.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GenericListDeserializer<T> extends JsonDeserializer<List<T>> {
	private String dataProperty;
	
	public GenericListDeserializer() {
		this(null);
	}
	
	public GenericListDeserializer(String dataProperty) {
		this.dataProperty = dataProperty;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDeserializationConfig(ctxt.getConfig());
		jp.setCodec(mapper);
		if(jp.hasCurrentToken()) {
			JsonNode tree = jp.readValueAsTree();
			JsonNode dataNode = dataProperty != null ? tree.get(dataProperty) : tree;
			return (List<T>) mapper.readValue(dataNode, new TypeReference<List<T>>() {});
		}
		
		return null;
	}
}
