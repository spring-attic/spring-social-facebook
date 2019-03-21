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
 * Model class representing an achievement type.
 * @author Craig Walls
 * @since 2.0
 */
public class AchievementType extends FacebookObject {

	private final String id;
	
	private final String type;
	
	private final String title;
	
	private final String url;
	
	private final String description;
	
	private final List<Image> images;
	
	private final int points;
	
	private final Date createdTime;
	
	private final Date updatedTime;
	
	private final ApplicationReference application;
				
	private final boolean isScraped;

	public AchievementType(String id, String type, String title, String url, String description, List<Image> images, int points, Date createdTime, Date updatedTime, ApplicationReference application, boolean isScraped) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.url = url;
		this.description = description;
		this.images = images;
		this.points = points;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.application = application;
		this.isScraped = isScraped;
	}
	
	public Image getImage() {
		return images.get(0);
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public int getPoints() {
		return points;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public ApplicationReference getApplication() {
		return application;
	}

	public boolean isScraped() {
		return isScraped;
	}

	
	
	public static class Image {
		
		private final String url;
		
		private final int width;
		
		private final int height;
		
		public Image(String url, int width, int height) {
			this.url = url;
			this.width = width;
			this.height = height;
		}
		
		public String getUrl() {
			return url;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
		
	}
}
