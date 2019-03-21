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
 * Model class representing a photo.
 * @author Craig Walls
 */
public class Photo extends FacebookObject {

	private String id;
	
	private Reference album;
	
	private Date backdatedTime;
	
	private TimeGranularity backdatedTimeGranularity;
	
	private Date createdTime;
	
	private Reference from;
	
	private int height;
	
	private String icon;
	
	private List<Image> images;
	
	private String link;
	
	private String name;

	private String pageStoryId;
	
	private Location place; 
	
	private String picture;
	
	private String source;
	
	private List<Tag> tags;
	
	private Date updatedTime;
	
	private int width;
	
	public String getId() {
		return id;
	}
	
	public Reference getAlbum() {
		return album;
	}

	public String getName() {
		return name;
	}

	public String getPageStoryId() {
		return pageStoryId;
	}
	
	public Reference getFrom() {
		return from;
	}
	
	public int getHeight() {
		return height;
	}

	public String getPicture() {
		return picture;
	}

	public String getSource() {
		return source;
	}

	public String getLink() {
		return link;
	}

	public String getIcon() {
		return icon;
	}

	public Location getPlace() {
		return place;
	}
	
	public Date getBackdatedTime() {
		return backdatedTime;
	}

	public TimeGranularity getBackdatedTimeGranularity() {
		return backdatedTimeGranularity;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	/**
	 * @return A list of all Image objects for this photo, ordered largest to smallest.
	 */
	public List<Image> getImages() {
		return images;
	}
	
	public int getWidth() {
		return width;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public static class Image {
		
		private final int width;
		
		private final int height;
		
		private final String source;
		
		public Image(String source, int width, int height) {
			this.source = source;
			this.width = width;
			this.height = height;
			
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public String getSource() {
			return source;
		}
	}
	
	public static enum TimeGranularity {
		YEAR, MONTH, DAY, HOUR, MIN, NONE
	}

}
