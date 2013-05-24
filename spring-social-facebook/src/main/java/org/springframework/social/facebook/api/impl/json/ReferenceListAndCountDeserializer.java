/*
 * Copyright 2013 the original author or authors.
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
import java.util.Collections;
import java.util.List;

import org.springframework.social.facebook.api.ListAndCount;
import org.springframework.social.facebook.api.Reference;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class ReferenceListAndCountDeserializer extends JsonDeserializer<ListAndCount<Reference>> {

	@SuppressWarnings("unchecked")
	@Override
	public ListAndCount<Reference> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new FacebookModule());
		jp.setCodec(mapper);
		if(jp.hasCurrentToken()) {
			JsonNode node = jp.readValueAsTree();
			JsonNode dataNode = node.get("data");
			List<Reference> commentsList = dataNode != null ? 
					(List<Reference>) mapper.reader(new TypeReference<List<Reference>>() {}).readValue(dataNode) : 
					Collections.<Reference>emptyList();
			JsonNode countNode = node.get("count");
			int referenceCount = countNode != null ? countNode.intValue() : 0;
			return new ListAndCount<Reference>(commentsList, referenceCount);
		}
		
		return null;
	}
}
