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
package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.Achievement;
import org.springframework.social.facebook.api.AchievementOperations;
import org.springframework.social.facebook.api.AchievementType;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class AchievementOperationsITests extends FacebookITest {
	
	private TestUser testUser1;
	private AchievementOperations achievementOps;
	
	public AchievementOperationsITests() {
		// Had to create a separate app to test achievements with, because achievements only work for game apps.
		super("248041811986157", "783e6b8d5239b22881c8cd925d91e623");
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream", "Alice Arensen");
		FacebookTemplate facebook1 = new FacebookTemplate(testUser1.getAccessToken());
		achievementOps = facebook1.achievementOperations();
	}
	

	@Test
	public  void achievementTests() {
		// TODO: Might want to host these somewhere other than my (Craig's) personal hosting provider
		clientFacebook.achievementOperations().createAchievementType("http://www.habuma.com/fb/foundwaldo.html", 1);
		clientFacebook.achievementOperations().createAchievementType("http://www.habuma.com/fb/tiedshoes.html", 2);

		List<AchievementType> achievementTypes = clientFacebook.achievementOperations().getAchievementTypes();
		assertEquals(2, achievementTypes.size());
		for (AchievementType achievementType : achievementTypes) {
			AchievementType fetched = achievementOps.getAchievementType(achievementType.getId());
			assertEquals(achievementType.getId(), fetched.getId());
			assertEquals(achievementType.getDescription(), fetched.getDescription());
			assertEquals(achievementType.getUrl(), fetched.getUrl());
			assertEquals(achievementType.getTitle(), fetched.getTitle());
			assertEquals(achievementType.getType(), fetched.getType());
		}
		
		
		List<Achievement> achievements = achievementOps.getAchievements();
		assertEquals(0, achievements.size());
		String achieveId = achievementOps.postAchievement("http://www.habuma.com/fb/foundwaldo.html");
		String achieveId2 = achievementOps.postAchievement("http://www.habuma.com/fb/tiedshoes.html");
		
		achievements = achievementOps.getAchievements();
		assertEquals(2, achievements.size());
		Achievement achievement = achievements.get(0);
		assertEquals(achieveId2, achievement.getId());
		assertEquals("games.achieves", achievement.getType());
		assertEquals(0, achievement.getImportance());
		assertEquals("248041811986157", achievement.getApplication().getId());
		assertEquals("Spring Social Game", achievement.getApplication().getName());
		assertEquals(testUser1.getId(), achievement.getFrom().getId());
		assertEquals("Alice Arensen", achievement.getFrom().getName());

		achievement = achievements.get(1);
		assertEquals(achieveId, achievement.getId());
		assertEquals("games.achieves", achievement.getType());
		assertEquals(0, achievement.getImportance());
		assertEquals("248041811986157", achievement.getApplication().getId());
		assertEquals("Spring Social Game", achievement.getApplication().getName());
		assertEquals(testUser1.getId(), achievement.getFrom().getId());
		assertEquals("Alice Arensen", achievement.getFrom().getName());

		
		achievement = achievementOps.getAchievement(achieveId);
		assertEquals(achieveId, achievement.getId());
		assertEquals("games.achieves", achievement.getType());
		assertEquals(0, achievement.getImportance());
		assertEquals("248041811986157", achievement.getApplication().getId());
		assertEquals("Spring Social Game", achievement.getApplication().getName());
		assertEquals(testUser1.getId(), achievement.getFrom().getId());
		assertEquals("Alice Arensen", achievement.getFrom().getName());
		
		achievementOps.removeAchievement("http://www.habuma.com/fb/foundwaldo.html");
		achievementOps.removeAchievement("http://www.habuma.com/fb/tiedshoes.html");
		achievements = achievementOps.getAchievements();
		assertEquals(0, achievements.size());
		
		clientFacebook.achievementOperations().removeAchievementType(achievementTypes.get(0).getUrl());
		clientFacebook.achievementOperations().removeAchievementType(achievementTypes.get(1).getUrl());
		achievementTypes = clientFacebook.achievementOperations().getAchievementTypes();
		assertEquals(0, achievementTypes.size());
	}
	
}
