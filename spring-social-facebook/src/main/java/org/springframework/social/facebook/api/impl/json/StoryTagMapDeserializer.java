package org.springframework.social.facebook.api.impl.json;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.social.facebook.api.StoryTag;

public class StoryTagMapDeserializer extends JsonDeserializer<Map<Integer,List<StoryTag>>> {

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer,List<StoryTag>> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDeserializationConfig(ctxt.getConfig());
		jp.setCodec(mapper);
		if (jp.hasCurrentToken()) {
			JsonNode dataNode = jp.readValueAsTree();
			if (dataNode != null) {
				return (Map<Integer,List<StoryTag>>) mapper.readValue(dataNode, new TypeReference<Map<Integer,List<StoryTag>>>() {});
			}
		}
		
		return Collections.emptyMap();
	}

}
