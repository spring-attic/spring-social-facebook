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


/**
 * Model class representing a Facebook photo album.
 * @author Craig Walls
 */
public class Album extends FacebookObject {

	private String id;
	
	private Date backdatedTime;

	private boolean canUpload;

	private int count;

	private String coverPhoto;

	private Date createdTime;

	private String description;

	private Reference from;

	private String link;

	private String location;

	private String name;
	
	private Page place;

	private Privacy privacy;

	private Type type;

	private Date updatedTime;
	
	public String getId() {
		return id;
	}
	
	public Date getBackdatedTime() {
		return backdatedTime;
	}

	public boolean canUpload() {
		return canUpload;
	}

	public int getCount() {
		return count;
	}

	/**
	 * The ID of the Photo object that is the cover photo for the album.
	 * @return A Photo object ID or null if the album does not have a cover photo
	 */
	public String getCoverPhoto() {
		return coverPhoto;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getDescription() {
		return description;
	}

	public Reference getFrom() {
		return from;
	}

	public String getLink() {
		return link;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public Page getPlace() {
		return place;
	}
	
	public Privacy getPrivacy() {
		return privacy;
	}

	public Type getType() {
		return type;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	public static enum Type { APP, COVER, PROFILE, MOBILE, WALL, NORMAL, ALBUM, UNKNOWN }
	
	public static enum Privacy { EVERYONE, FRIENDS_OF_FRIENDS, FRIENDS, CUSTOM } 

}
