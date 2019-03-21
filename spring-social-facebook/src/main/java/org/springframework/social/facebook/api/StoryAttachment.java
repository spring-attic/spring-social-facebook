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

import java.util.List;

/**
 * Domain type that represents an attachment to a story, post, comment, etc.
 * @author Craig Walls
 * @since 2.0
 */
public class StoryAttachment {

	private String title;

	private String description;
	
	private List<String> descriptionTags;
	
	private String type;
	
	private String url;
	
	private StoryAttachmentTarget target;
	
	private StoryAttachmentMedia media;
	
	
	/**
	 * @return the attachment title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return the attachment description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return a list of profiles tagged in the text accompanying the attachment
	 */
	public List<String> getDescriptionTags() {
		return descriptionTags;
	}
	
	/**
	 * @return the attachment type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the attachment url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @return the object the attachment is attached to
	 */
	public StoryAttachmentTarget getTarget() {
		return target;
	}
	
	/**
	 * @return the attachment's media object
	 */
	public StoryAttachmentMedia getMedia() {
		return media;
	}
	
	/**
	 * Domain type representing the media of an attachment.
	 * @author Craig Walls
	 */
	public static class StoryAttachmentMedia {
		private final ImageSource image; 
		
		public StoryAttachmentMedia(ImageSource image) {
			this.image = image;
		}
		
		public ImageSource getImage() {
			return image;
		}

	}
	
	/**
	 * Domain type representing the target of an attachment.
	 * @author Craig Walls
	 */
	public static class StoryAttachmentTarget {

		private final String id;
		
		private final String url;
		
		public StoryAttachmentTarget(String id, String url) {
			this.id = id;
			this.url = url;
		}
		
		public String getId() {
			return id;
		}
		
		public String getUrl() {
			return url;
		}
		
	}
}
