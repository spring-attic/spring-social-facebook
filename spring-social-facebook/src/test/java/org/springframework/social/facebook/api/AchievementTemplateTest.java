/*
 * Copyright 2015 the original author or authors.
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

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @author Craig Walls
 */
public class AchievementTemplateTest extends AbstractFacebookApiTest {

	//
	// User-level achievement operations
	//

	@Test
	public void getAchievement() throws Exception {
		mockServer.expect(requestTo(fbUrl("1403783649909361")))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("achievement"), MediaType.APPLICATION_JSON));
		
		Achievement achievement = facebook.achievementOperations().getAchievement("1403783649909361");
		assertFoundWaldoAchievement(achievement);
	}
	
	@Test
	public void getAchievements() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/achievements")))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("achievements"), MediaType.APPLICATION_JSON));
		
		List<Achievement> achievements = facebook.achievementOperations().getAchievements();
		assertEquals(2, achievements.size());
		assertFoundWaldoAchievement(achievements.get(0));
		assertTiedShoesAchievement(achievements.get(1));
	}

	@Test
	public void postAchievement() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/achievements")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth " + ACCESS_TOKEN))
			.andExpect(content().string("achievement=http%3A%2F%2Fexample.com%2Fachievement"))
			.andRespond(withSuccess(jsonResource("id-only"), MediaType.APPLICATION_JSON));
		
		String achievementId = facebook.achievementOperations().postAchievement("https://example.com/achievement");
		assertEquals("297875170268724", achievementId);
		mockServer.verify();
	}

	@Test
	public void deleteAchievement() throws Exception {
		mockServer.expect(requestTo(fbUrl("me/achievements")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth " + ACCESS_TOKEN))
			.andExpect(content().string("achievement=http%3A%2F%2Fexample.com%2Fachievement&method=delete"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.achievementOperations().removeAchievement("https://example.com/achievement");
		mockServer.verify();
	}

	
	//
	// Application-level achievement type operations
	//
	
	@Test
	public void getAchievementTypes() throws Exception {
		appFacebookMockServer.expect(requestTo(fbUrl("app/achievements")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth " + APP_ACCESS_TOKEN))
			.andRespond(withSuccess(jsonResource("achievement-types"), MediaType.APPLICATION_JSON));
	
		List<AchievementType> achievementTypes = appFacebook.achievementOperations().getAchievementTypes();
		assertEquals(2, achievementTypes.size());
		assertWaldoAchievement(achievementTypes.get(0), null);
		assertTiedShoesAchievement(achievementTypes.get(1));
	}

	@Test
	public void getAchievementType() throws Exception {
		appFacebookMockServer.expect(requestTo(fbUrl("651053301631017")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth " + APP_ACCESS_TOKEN))
			.andRespond(withSuccess(jsonResource("achievement-type"), MediaType.APPLICATION_JSON));
	
		AchievementType achievementType = appFacebook.achievementOperations().getAchievementType("651053301631017");
		assertWaldoAchievement(achievementType, "2014-05-29T17:23:36+0000");
	}
	
	@Test
	public void createAchievementType() throws Exception {
		appFacebookMockServer.expect(requestTo(fbUrl("app/achievements")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth " + APP_ACCESS_TOKEN))
			.andExpect(content().string("achievement=http%3A%2F%2Fexample.com%2Fachievement&display_order=2"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		
		appFacebook.achievementOperations().createAchievementType("https://example.com/achievement", 2);
		appFacebookMockServer.verify();
	}

	@Test
	public void removeAchievementType() throws Exception {
		appFacebookMockServer.expect(requestTo(fbUrl("app/achievements")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth " + APP_ACCESS_TOKEN))
			.andExpect(content().string("achievement=http%3A%2F%2Fexample.com%2Fachievement&method=delete"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		appFacebook.achievementOperations().removeAchievementType("https://example.com/achievement");
		appFacebookMockServer.verify();
	}
	
	
	
	// private helpers
	
	private void assertFoundWaldoAchievement(Achievement achievement) {
		assertEquals("1403833783237681", achievement.getId());
		assertEquals("248041811986157", achievement.getApplication().getId());
		assertEquals("Spring Social Game", achievement.getApplication().getName());
//		assertEquals("springgame", achievement.getApplication().getExtraData().get("namespace"));
		assertEquals("651053301631017", achievement.getAchievementType().getId());
		assertEquals("Found Waldo", achievement.getAchievementType().getTitle());
		assertEquals("game.achievement", achievement.getAchievementType().getType());
		assertEquals("http://www.habuma.com/fb/foundwaldo.html", achievement.getAchievementType().getUrl());
		assertEquals(0, achievement.getImportance());
		assertEquals("1401275846826808", achievement.getFrom().getId());
		assertEquals("Carol Amhccbahibha Zuckersky", achievement.getFrom().getName());
		assertEquals(toDate("2014-06-02T15:56:00+0000"), achievement.getPublishTime());
		assertEquals("games.achieves", achievement.getType());
	}

	private void assertTiedShoesAchievement(Achievement achievement) {
		assertEquals("1403783649909361", achievement.getId());
		assertEquals("248041811986157", achievement.getApplication().getId());
		assertEquals("Spring Social Game", achievement.getApplication().getName());
//		assertEquals("springgame", achievement.getApplication().getExtraData().get("namespace"));
		assertEquals("891536160872086", achievement.getAchievementType().getId());
		assertEquals("Tied shoes", achievement.getAchievementType().getTitle());
		assertEquals("game.achievement", achievement.getAchievementType().getType());
		assertEquals("http://www.habuma.com/fb/tiedshoes.html", achievement.getAchievementType().getUrl());
		assertEquals(1, achievement.getImportance());
		assertEquals("1401275846826808", achievement.getFrom().getId());
		assertEquals("Carol Amhccbahibha Zuckersky", achievement.getFrom().getName());
		assertEquals(toDate("2014-06-02T14:35:45+0000"), achievement.getPublishTime());
		assertEquals("games.achieves", achievement.getType());
	}
	
	private void assertWaldoAchievement(AchievementType achievementType, String createdTime) {
		assertEquals("248041811986157", achievementType.getApplication().getId());
		assertEquals("Spring Social Game", achievementType.getApplication().getName());
		assertEquals("https://www.facebook.com/apps/application.php?id=248041811986157", achievementType.getApplication().getUrl());
		if (createdTime != null) {
			assertEquals(toDate(createdTime), achievementType.getCreatedTime());
		} else {
			assertNull(achievementType.getCreatedTime());
		}
		assertEquals("He's sneaky in red and white stripes and hiding in crowds, but you found him!", achievementType.getDescription());
		assertEquals("651053301631017", achievementType.getId());
		assertEquals("http://www.habuma.com/fb/foundwaldo.jpg", achievementType.getImage().getUrl());
		assertEquals(1517, achievementType.getImage().getHeight());
		assertEquals(827, achievementType.getImage().getWidth());
		assertEquals(50, achievementType.getPoints());
		assertEquals("Found Waldo", achievementType.getTitle());
		assertEquals("game.achievement", achievementType.getType());
		assertEquals(toDate("2014-05-29T17:23:36+0000"), achievementType.getUpdatedTime());
		assertEquals("http://www.habuma.com/fb/foundwaldo.html", achievementType.getUrl());
	}
	
	private void assertTiedShoesAchievement(AchievementType achievementType) {
		assertEquals("248041811986157", achievementType.getApplication().getId());
		assertEquals("Spring Social Game", achievementType.getApplication().getName());
		assertEquals("https://www.facebook.com/apps/application.php?id=248041811986157", achievementType.getApplication().getUrl());
		assertNull(achievementType.getCreatedTime());
		assertEquals("You're not losing your sneakers. Not again!", achievementType.getDescription());
		assertEquals("891536160872086", achievementType.getId());
		assertEquals("http://www.habuma.com/fb/tiedshoes.jpg", achievementType.getImage().getUrl());
		assertEquals(1517, achievementType.getImage().getHeight());
		assertEquals(827, achievementType.getImage().getWidth());
		assertEquals(10, achievementType.getPoints());
		assertEquals("Tied shoes", achievementType.getTitle());
		assertEquals("game.achievement", achievementType.getType());
		assertEquals(toDate("2014-05-29T17:30:47+0000"), achievementType.getUpdatedTime());
		assertEquals("http://www.habuma.com/fb/tiedshoes.html", achievementType.getUrl());
	}
}
