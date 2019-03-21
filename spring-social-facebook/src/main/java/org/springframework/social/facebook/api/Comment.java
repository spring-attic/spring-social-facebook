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

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Model class representing a comment.
 * @author Craig Walls
 */
public class Comment extends FacebookObject {
	
	private static final List<MessageTag> EMPTY_TAG_LIST = Collections.emptyList();
	
	private String id;
	
	private StoryAttachment attachment;
	
	private boolean canComment;
	
	private boolean canRemove;
	
	private Integer commentCount;
	
	private Date createdTime;
	
	private Reference from;
	
	private Integer likeCount;
	
	private String message;
	
	private List<MessageTag> messageTags;

	private Comment parent;

	private boolean userLikes;
	
	/**
	 * @return the comment's Graph API object ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the text of the comment
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the time the comment was created.
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @return a reference to the user who posted the comment.
	 */
	public Reference getFrom() {
		return from;
	}

	/**
	 * @return the number of users who like this comment.
	 */
	public Integer getLikeCount() {
		return likeCount;
	}
	
	/**
	 * @return the number of comments made on this comment or null if that information is unknown
	 */
	public Integer getCommentCount() {
		return commentCount;
	}
	
	/**
	 * @return the parent comment if this comment is a comment to another comment
	 */
	public Comment getParent() {
		return parent;
	}
	
	/**
	 * @return true if the authenticated user is able to comment on this comment
	 */
	public boolean canComment() {
		return canComment;
	}
	
	/**
	 * @return true if the authenticated user is able to remove this comment
	 */
	public boolean canRemove() {
		return canRemove;
	}
	
	/**
	 * @return true if the authenticated user likes this comment
	 */
	public boolean userLikes() {
		return userLikes;
	}
	
	/**
	 * @return an attachment (link, photo, etc) associated with the comment or null if no attachment
	 */
	public StoryAttachment getAttachment() {
		return attachment;
	}

	/**
	 * @return a list of tags in the comment's message
	 */
	public List<MessageTag> getMessageTags() {
		return messageTags != null ? messageTags : EMPTY_TAG_LIST;
	}

}
