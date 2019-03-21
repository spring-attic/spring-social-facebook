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
import java.util.Date;

import org.springframework.social.facebook.api.Album.Privacy;
import org.springframework.social.facebook.api.Album.Type;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Album. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AlbumMixin extends FacebookObjectMixin {

	@JsonProperty("id")
	String id;
	
	@JsonProperty("backdated_time")
	Date backdatedTime;

	@JsonProperty("can_upload")
	boolean canUpload;

	@JsonProperty("count")
	int count;
	
	@JsonProperty("cover_photo")
	String coverPhoto;
		
	@JsonProperty("created_time") 
	Date createdTime;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("from")
	Reference from;
	
	@JsonProperty("link")
	String link; 
	
	@JsonProperty("location")
	String location;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("place")
	Page place;
	
	@JsonProperty("privacy") 
	@JsonDeserialize(using=PrivacyDeserializer.class) 
	Privacy privacy; 
	
	@JsonProperty("type")
	@JsonDeserialize(using=TypeDeserializer.class) Type type; 
	
	@JsonProperty("updated_time")
	Date updatedTime;
	
	private static class TypeDeserializer extends JsonDeserializer<Type> {
		@Override
		public Type deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			try {
				return Type.valueOf(jp.getText().toUpperCase());
			} catch (IllegalArgumentException e) {
				return Type.UNKNOWN;
			}				
		}		
	}
			
	private static class PrivacyDeserializer extends JsonDeserializer<Privacy> {
		@Override
		public Privacy deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			try {
				return Privacy.valueOf(jp.getText().replace("-", "_").toUpperCase());
			} catch (IllegalArgumentException e) {
				return Privacy.CUSTOM;
			}
		}
	}
}
