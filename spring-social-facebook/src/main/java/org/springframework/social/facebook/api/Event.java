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
 * Model class representing an event.
 * @author Craig Walls
 */
public class Event extends FacebookObject {
	
	private String id;

	private CoverPhoto cover;
	
	private String description;
	
	private Date endTime;

	private boolean isDateOnly;

	private String name;
	
	private Reference owner;

	private Group parentGroup;
	
	private Privacy privacy;

	private Date startTime;

	private String ticketUri;
	
	private String timeZone;
	
	private Date updatedTime;
	
	private Place place;
		
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public CoverPhoto getCover() {
		return cover;
	}
	
	public Reference getOwner() {
		return owner;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getTicketUri() {
		return ticketUri;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public boolean isDateOnly() {
		return isDateOnly;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	
	public Group getParentGroup() {
		return parentGroup;
	}
	
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public static enum Privacy { OPEN, SECRET, CLOSED, FRIENDS }
}
