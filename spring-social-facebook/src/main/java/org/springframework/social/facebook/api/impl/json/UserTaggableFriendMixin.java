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

import org.springframework.social.facebook.api.FacebookObject;
import org.springframework.social.facebook.api.ProfilePictureSource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to UserTaggableFriend. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class UserTaggableFriendMixin extends FacebookObject {

	@JsonCreator
	UserTaggableFriendMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("name") String name, 
			@JsonProperty("first_name") String firstName, 
			@JsonProperty("middle_name") String middleName, 
			@JsonProperty("last_name") String lastName, 
			@JsonProperty("picture") @JsonDeserialize(using=PictureDeserializer.class) ProfilePictureSource picture) {}

	private static class PictureDeserializer extends JsonDeserializer<ProfilePictureSource> {
		@Override
		public ProfilePictureSource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new FacebookModule());
			JsonNode dataNode = (JsonNode) jp.readValueAs(JsonNode.class).get("data");
			return (ProfilePictureSource) mapper.readerFor(ProfilePictureSource.class).readValue(dataNode);
		}
	}

	
}
