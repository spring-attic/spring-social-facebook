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
 * Model class representing an achievement.
 * @author Craig Walls
 * @since 2.0
 */
public class Achievement extends FacebookObject {

	private final String id;
	
	private final Reference from;
	
	private final Date publishTime;
	
	private final ApplicationReference application;
	
	private AchievementData data;
	
	private final String type;
	
	private final boolean noFeedStory;
	
	public Achievement(String id, Reference from, Date publishTime, ApplicationReference application, AchievementData data, String type, boolean noFeedStory) {
		this.id = id;
		this.from = from;
		this.publishTime = publishTime;
		this.application = application;
		this.data = data;
		this.type = type;
		this.noFeedStory = noFeedStory;
	}

	public String getId() {
		return id;
	}

	public Reference getFrom() {
		return from;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public ApplicationReference getApplication() {
		return application;
	}

	public AchievementType getAchievementType() {
		return data.achievement;
	}
	
	public int getImportance() {
		return data.importance;
	}
	
	public String getType() {
		return type;
	}

	public boolean isNoFeedStory() {
		return noFeedStory;
	}

	public static class AchievementData {
		private AchievementType achievement;
		private int importance;
		
		public AchievementType getAchievement() {
			return this.achievement;
		}
		
		public int getImportance() {
			return importance;
		}
	}
	
}
