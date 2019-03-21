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
 * Model class representing a Group.
 * @author Craig Walls
 */
public class Group extends FacebookObject {
	
	private String id;
	
	private CoverPhoto cover;
	
	private String description;

	private String email;

	private String icon;
	
	private String link;
	
	private String name;
	
	private Reference owner;
	
	private Privacy privacy;
	
	private Date updatedTime;
	
	public String getId() {
		return id;
	}
	
	public CoverPhoto getCover() {
		return cover;
	}

	public Reference getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public String getIcon() {
		return icon;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public String getEmail() {
		return email;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getLink() {
		return link;
	}

	public static enum Privacy { OPEN, SECRET, CLOSED }

}
