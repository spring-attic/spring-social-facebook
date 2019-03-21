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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * Model class representing an entry in a feed. 
 * @author Craig Walls
 */
public class Post extends FacebookObject {
	
	private String id;
	
	private List<Action> actions;
	
	private AdminCreator adminCreator;

	private Reference application;

	private String caption;

	private Date createdTime;

	private String description;

	private Reference from;

	private String icon;

	private boolean isHidden;
	
	private boolean isPublished;
	
	private String link;
	
	private String message;
	
	private Map<Integer,List<MessageTag>> messageTags;
	
	private String name;
	
	private String objectId;
	
	private String picture;

	private Page place;
	
	private Privacy privacy;
	
	private List<PostProperty> properties = new ArrayList<PostProperty>();
	
	private int sharesCount;

	private String source;
	
	private StatusType statusType;

	private String story;

	private List<Reference> to;
	
	private PostType type = PostType.UNKNOWN;
	
	private Date updatedTime;

	private List<Reference> withTags;
			
	public String getId() {
		return id;
	}

	public List<Action> getActions() {
		return actions;
	}
	
	public AdminCreator getAdminCreator() {
		return adminCreator;
	}
	
	public Reference getApplication() {
		return application;
	}

	// TODO: public ? getCallToAction() { ... }
	
	public String getCaption() {
		return caption;
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

	public String getIcon() {
		return icon;
	}

	public boolean isHidden() {
		return isHidden;
	}
	
	public boolean isPublished() {
		return isPublished;
	}
	
	public String getLink() {
		return link;
	}

	public String getMessage() {
		return message;
	}

	public Map<Integer,List<MessageTag>> getMessageTags() {
		return messageTags;
	}

	public String getName() {
		return name;
	}

	public String getObjectId() {
		return objectId;
	}
	
	public String getPicture() {
		return picture;
	}

	public Page getPlace() {
		return place;
	}
	
	public Privacy getPrivacy() {
		return privacy;
	}
	
	public List<PostProperty> getProperties() {
		return properties;
	}
	
	public String getSource() {
		return source;
	}
	
	public StatusType getStatusType() {
		return statusType;
	}
	
	public String getStory() {
		return story;
	}
	
	public List<Reference> getTo() {
		return to;
	}
	
	public PostType getType() {
		return type;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public List<Reference> getWithTags() {
		return withTags;
	}
	
	public int getShares() {
		return sharesCount;
	}
	
	public static class AdminCreator {
		
		private String id;
		
		private String name;
		
		private String namespace;
		
		public String getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public String getNamespace() {
			return namespace;
		}
		
	}
	
	public static class Privacy {
		
		private String description;
		
		private PrivacyType value;
		
		private FriendsPrivacyType friends;
		
		private String networks;
		
		private String allow;
		
		private String deny;
		
		public String getDescription() {
			return description;
		}
		
		public PrivacyType getValue() {
			return value;
		}
		
		public FriendsPrivacyType getFriends() {
			return friends;
		}
		
		public String getNetworks() {
			return networks;
		}
		
		public String getAllow() {
			return allow;
		}
		
		public String getDeny() {
			return deny;
		}
		
	}
	
	public static enum PostType { LINK, STATUS, PHOTO, VIDEO, UNKNOWN }
	
	public static enum StatusType { MOBILE_STATUS_UPDATE, CREATED_NOTE, ADDED_PHOTOS, ADDED_VIDEO, SHARED_STORY, CREATED_GROUP, 
		CREATED_EVENT, WALL_POST, APP_CREATED_STORY, PUBLISHED_STORY, TAGGED_IN_PHOTO, APPROVED_FRIEND, UNKNOWN }
	
	public static enum PrivacyType { EVERYONE, ALL_FRIENDS, FRIENDS_OF_FRIENDS, SELF, CUSTOM, UNKNOWN }

	public static enum FriendsPrivacyType { ALL_FRIENDS, FRIENDS_OF_FRIENDS, SOME_FRIENDS, UNKNOWN }

}
