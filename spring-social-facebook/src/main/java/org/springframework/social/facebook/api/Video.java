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

import java.util.Date;
import java.util.List;


/**
 * Model class representing a video.
 * @author Craig Walls
 */
public class Video extends FacebookObject {

	private String id;
	
	private Date createdTime;
	
	private String description;
	
	private String embedHtml;
	
	private List<VideoFormat> format;
	
	private Reference from;
	
	private String icon;
	
	private String name;
	
	private String picture;
	
	private String source;
	
	private List<Tag> tags;
	
	private Date updatedTime;
	
	public String getId() {
		return id;
	}

	public Reference getFrom() {
		return from;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the video's picture.
	 */
	public String getPicture() {
		return picture;
	}
	
	public String getEmbedHtml() {
		return embedHtml;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getSource() {
		return source;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	public List<VideoFormat> getFormat() {
		return format;
	}
	
	public static class VideoFormat {
		
		private String embedHtml;
		
		private String filter;
		
		private int height;
		
		private String picture;
		
		private int width;
		
		public String getEmbedHtml() {
			return embedHtml;
		}
		
		public String getFilter() {
			return filter;
		}
		
		public int getHeight() {
			return height;
		}
		
		public String getPicture() {
			return picture;
		}
		
		public int getWidth() {
			return width;
		}
		
	}
	
}
