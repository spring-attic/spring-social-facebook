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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.Achievement;
import org.springframework.social.facebook.api.AchievementOperations;
import org.springframework.social.facebook.api.AchievementType;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class AchievementTemplate implements AchievementOperations {

	private GraphApi graphApi;
	
	public AchievementTemplate(GraphApi graphApi) {
		this.graphApi = graphApi;
	}

	public Achievement getAchievement(String achievementId) {
		return graphApi.fetchObject(achievementId, Achievement.class);
	}

	public List<Achievement> getAchievements() {
		return graphApi.fetchConnections("me", "achievements", Achievement.class);
	}

	public String postAchievement(String achievementUrl) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("achievement", achievementUrl);
		return graphApi.publish("me", "achievements", data);
	}

	public void removeAchievement(String achievementUrl) {
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
		data.set("achievement", achievementUrl);
		graphApi.delete("me", "achievements", data);
	}

	
	// Application-level achievement type methods
	
	public List<AchievementType> getAchievementTypes() {
		return graphApi.fetchConnections("app", "achievements", AchievementType.class);
	}

	public AchievementType getAchievementType(String achievementTypeId) {
		return graphApi.fetchObject(achievementTypeId, AchievementType.class);
	}

	public void createAchievementType(String achievementTypeUrl, int displayOrder) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("achievement", achievementTypeUrl);
		data.set("display_order", String.valueOf(displayOrder));
		graphApi.post("app", "achievements", data);
	}

	public void removeAchievementType(String achievementTypeUrl) {
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
		data.set("achievement", achievementTypeUrl);
		graphApi.delete("app", "achievements", data);
	}

}
