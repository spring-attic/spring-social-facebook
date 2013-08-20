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
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.CheckinPost;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.LinkPost;
import org.springframework.social.facebook.api.ListAndCount;
import org.springframework.social.facebook.api.MusicPost;
import org.springframework.social.facebook.api.NotePost;
import org.springframework.social.facebook.api.PhotoPost;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Post.PostType;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.StatusPost;
import org.springframework.social.facebook.api.StoryTag;
import org.springframework.social.facebook.api.SwfPost;
import org.springframework.social.facebook.api.VideoPost;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Post.
 * Also defines Post subtypes to deserialize into based on the "type" attribute. 
 * @author Craig Walls
 */
@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="postType", visible=true)
@JsonSubTypes({
				@Type(name="checkin", value=CheckinPost.class),
				@Type(name="link", value=LinkPost.class),
				@Type(name="note", value=NotePost.class),
				@Type(name="photo", value=PhotoPost.class),
				@Type(name="status", value=StatusPost.class),
				@Type(name="video", value=VideoPost.class),
				@Type(name="post", value=Post.class),
				@Type(name="swf", value=SwfPost.class),
				@Type(name="music", value=MusicPost.class)
				})
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PostMixin extends FacebookObjectMixin {
	
	@JsonCreator
	PostMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("from") Reference from, 
			@JsonProperty("created_time") Date createdTime, 
			@JsonProperty("updated_time") Date updatedTime) {}

	@JsonProperty("to")
	@JsonDeserialize(using = ReferenceListDeserializer.class)
	List<Reference> to;
	
	@JsonProperty("message")
	String message;

	@JsonProperty("caption")
	String caption;
	
	@JsonProperty("picture")
	@JsonDeserialize(using=PictureDeserializer.class)
	String picture;
	
	@JsonProperty("link")
	String link;
	
	@JsonProperty("subject")
	String subject;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("icon")
	String icon;
	
	@JsonProperty("application")
	Reference application;
	
	@JsonProperty("type")
	@JsonDeserialize(using = TypeDeserializer.class)
	PostType type;

	// TODO: THIS IS BREAKING TYPE DESERIALIZATION...BUT WHY???
	@JsonProperty("shares")
	@JsonDeserialize(using = CountDeserializer.class)
	int sharesCount;

	@JsonProperty("likes")
	@JsonDeserialize(using = ReferenceListAndCountDeserializer.class)
	ListAndCount<Reference> likes;

	@JsonProperty("comments")
	@JsonDeserialize(using = CommentListAndCountDeserializer.class)
	ListAndCount<Comment> comments;

	@JsonProperty("story")
	String story;
	
	@JsonProperty("story_tags")
	@JsonDeserialize(using = StoryTagMapDeserializer.class)
	Map<Integer,List<StoryTag>> storyTags;

	private static class TypeDeserializer extends JsonDeserializer<PostType> {
		@Override
		public PostType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return PostType.valueOf(jp.getText().toUpperCase());
		}
	}
	
	private static class CountDeserializer extends JsonDeserializer<Integer> {
		@Override
		public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			Map map = jp.readValueAs(Map.class);
			return map.containsKey("count") ? Integer.valueOf(String.valueOf(map.get("count"))): 0; 
		}
	}
}
