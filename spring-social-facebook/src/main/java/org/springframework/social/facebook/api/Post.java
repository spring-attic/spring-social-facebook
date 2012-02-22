/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.facebook.api;

import java.util.Date;
import java.util.List;



/**
 * Model class representing an entry in a feed. 
 * @author Craig Walls
 */
public class Post {
	
	private final String id;

	private final Reference from;

	private final Date createdTime;

	private final Date updatedTime;

	private List<Reference> to;
	
	private String message;
	
	private String picture;
	
	private String link;
		
	private String name;
	
	private String caption;
	
	private String description;
	
	private String icon;
	
	private Reference application;
	
	private PostType type;
	
	private ListAndCount<Reference> likes;
	
	private ListAndCount<Comment> comments;
	
	private int sharesCount;

	public Post(String id, Reference from, Date createdTime, Date updatedTime) {
		this.id = id;
		this.from = from;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public String getId() {
		return id;
	}

	public Reference getFrom() {
		return from;
	}

	public List<Reference> getTo() {
		return to;
	}

	public String getCaption() {
		return caption;
	}

	public String getMessage() {
		return message;
	}

	public String getPicture() {
		return picture;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public Reference getApplication() {
		return application;
	}

	public PostType getType() {
		return type;
	}
	
	/**
	 * Reference for users who have liked this Post. 
	 * May not be a complete list and the size may be different than the value returned from getLikeCount().
	 * For a complete list of likes, use {@link LikeOperations#getLikes(String)}.
	 */
	public List<Reference> getLikes() {
		return likes.getList();
	}
	
	/**
	 * The number of likes for this Post. May be different than the size of the list returned from getLikes().
	 */
	public int getLikeCount() {
		return likes.getCount();
	}
	
	public int getSharesCount() {
		return sharesCount;
	}

	/**
	 * The most recent comments for the post.
	 */
	public List<Comment> getComments() {
		return comments.getList();
	}

	public int getCommentCount() {
		return comments.getCount();
	}
	
	public static enum PostType { POST, CHECKIN, LINK, NOTE, PHOTO, STATUS, VIDEO, SWF, MUSIC }
}
