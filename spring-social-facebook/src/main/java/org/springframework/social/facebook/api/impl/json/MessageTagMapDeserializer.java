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

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.MessageTag;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class MessageTagMapDeserializer extends JsonDeserializer<Map<Integer,List<MessageTag>>> {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Map<Integer,List<MessageTag>> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new FacebookModule());
		jp.setCodec(mapper);
		if (jp.hasCurrentToken()) {
			JsonNode dataNode = jp.readValueAs(JsonNode.class);
			if (dataNode != null) {
				if(dataNode.getNodeType().equals(JsonNodeType.OBJECT)) { // OLD STYLE, SUPPORTED IN GRAPH API 2.3
					return (Map<Integer,List<MessageTag>>) mapper.reader(new TypeReference<Map<Integer,List<MessageTag>>>() {}).readValue(dataNode);
				} else if (dataNode.getNodeType().equals(JsonNodeType.ARRAY)) { // NEW STYLE 2.4/2.5-ish
					List<MessageTag> tagList = (List<MessageTag>) mapper.reader(new TypeReference<List<MessageTag>>() {}).readValue(dataNode);
					Map<Integer, List<MessageTag>> messageTagMap = new HashMap<Integer, List<MessageTag>>();
					for (MessageTag messageTag : tagList) {
						messageTagMap.put(messageTag.getOffset(), Arrays.asList(messageTag));
					}
				}
			} 
		}
		
		return Collections.emptyMap();
	}

}
