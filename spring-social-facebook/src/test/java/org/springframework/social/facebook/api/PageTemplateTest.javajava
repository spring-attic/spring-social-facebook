/*
 * Copyright 2013 the original author or authors.
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
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;

/**
 * @author Craig Walls
 */
public class PageTemplateTest extends AbstractFacebookApiTest {
	
	@Test
	@SuppressWarnings("deprecation")
	public void getPage_organization() {
		mockServer.expect(requestTo("https://graph.facebook.com/140804655931206"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("organization-page"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("140804655931206");
		assertEquals("140804655931206", page.getId());
		assertEquals("SpringSource", page.getName());
		assertEquals("http://profile.ak.fbcdn.net/static-ak/rsrc.php/v1/yr/r/fwJFrO5KjAQ.png", page.getPicture());
		assertEquals("http://www.facebook.com/pages/SpringSource/140804655931206", page.getLink());
		assertEquals(33, page.getLikes());
		assertEquals("Organization", page.getCategory());
		assertEquals("<p><b>SpringSource</b> is a division of <a href=\"http://en.wikipedia.org/wiki/VMware\" class=\"wikipedia\">VMware</a> that provides...</p>", page.getDescription());
		assertEquals("SpringSource offers a product suite to build, run & manage enterprise Java applications.  Please join the SpringSource Group here: http://www.facebook.com/groups/10463298884/", page.getAbout());
		assertFalse(page.canPost());
		assertTrue(page.isPublished());
		assertFalse(page.isCommunityPage());
		assertFalse(page.hasAddedApp());
		assertEquals(19, page.getTalkingAboutCount());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void getPage_product() {
		mockServer.expect(requestTo("https://graph.facebook.com/21278871488"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("product-page"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("21278871488");
		assertEquals("21278871488", page.getId());
		assertEquals("Mountain Dew", page.getName());
		assertEquals("http://profile.ak.fbcdn.net/hprofile-ak-snc4/203494_21278871488_3106566_s.jpg", page.getPicture());
		assertEquals("http://www.facebook.com/mountaindew", page.getLink());
		assertEquals(5083988, page.getLikes());
		assertEquals("Food/beverages", page.getCategory());
		assertEquals("www.mountaindew.com\nwww.greenlabelsound.com\nwww.greenlabelart.com\nwww.honorthecode.com\nwww.dietdewchallenge.com\nwww.twitter.com/mtn_dew\nwww.youtube.com/mountaindew", page.getWebsite());
		assertEquals("This is How We DEW", page.getAbout());
		assertTrue(page.canPost());
		CoverPhoto coverPhoto = page.getCover();
		assertEquals("10151582481816489", coverPhoto.getId());
		assertEquals("http://sphotos-g.ak.fbcdn.net/hphotos-ak-ash3/s720x720/942964_10151582481816489_61037109_n.jpg", coverPhoto.getSource());
		assertEquals(20, coverPhoto.getOffsetX());
		assertEquals(10, coverPhoto.getOffsetY());
		assertTrue(page.isPublished());
		assertTrue(page.isCommunityPage());
		assertTrue(page.hasAddedApp());
		assertEquals(32597, page.getTalkingAboutCount());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void getPage_place() {
		mockServer.expect(requestTo("https://graph.facebook.com/150263434985489"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("place-page"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("150263434985489");
		assertEquals("150263434985489", page.getId());
		assertEquals("Denver International Airport", page.getName());
		assertEquals("http://profile.ak.fbcdn.net/static-ak/rsrc.php/v1/yZ/r/u3l2nEuXNsK.png", page.getPicture());
		assertEquals("http://www.facebook.com/pages/Denver-International-Airport/150263434985489", page.getLink());
		assertEquals(1052, page.getLikes());
		assertEquals("Local business", page.getCategory());
		assertEquals("http://flydenver.com", page.getWebsite());
		assertEquals("Denver", page.getLocation().getCity());
		assertEquals("CO", page.getLocation().getState());
		assertEquals("United States", page.getLocation().getCountry());
		assertEquals(39.851693483111, page.getLocation().getLatitude(), 0.0001);
		assertEquals(-104.67384947947, page.getLocation().getLongitude(), 0.0001);
		assertEquals("(303) 342-2000", page.getPhone());
		assertEquals(121661, page.getCheckins());
		assertFalse(page.canPost());
		assertFalse(page.isPublished());
		assertFalse(page.isCommunityPage());
		assertFalse(page.hasAddedApp());
		assertEquals(15062, page.getTalkingAboutCount());
		assertNull(page.getHours());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void getPage_place_with_hours() {
		mockServer.expect(requestTo("https://graph.facebook.com/220817147947513"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("place-with-hours-page"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("220817147947513");
		assertEquals("220817147947513", page.getId());
		assertEquals("Denton Square Donuts", page.getName());
		assertEquals("https://www.facebook.com/DentonSquareDonuts", page.getLink());
		assertEquals(3078, page.getLikes());
		assertEquals("Restaurant/cafe", page.getCategory());
		assertEquals("www.dsdonuts.com", page.getWebsite());
		assertEquals("Denton", page.getLocation().getCity());
		assertEquals("TX", page.getLocation().getState());
		assertEquals("United States", page.getLocation().getCountry());
		assertEquals(33.21556, page.getLocation().getLatitude(), 0.0001);
		assertEquals(-97.13414, page.getLocation().getLongitude(), 0.0001);
		assertEquals("940-220-9447", page.getPhone());
		assertEquals(959, page.getCheckins());
		assertFalse(page.canPost());
		assertTrue(page.isPublished());
		assertFalse(page.isCommunityPage());
		assertFalse(page.hasAddedApp());
		assertEquals(68, page.getTalkingAboutCount());
		assertNotNull(page.getHours());
		assertEquals("07:30", page.getHours().get("mon_1_open"));
		assertEquals("13:00", page.getHours().get("mon_1_close"));
		assertEquals("07:30", page.getHours().get("tue_1_open"));
		assertEquals("13:00", page.getHours().get("tue_1_close"));
		assertEquals("07:30", page.getHours().get("wed_1_open"));
		assertEquals("13:00", page.getHours().get("wed_1_close"));
		assertEquals("07:30", page.getHours().get("thu_1_open"));
		assertEquals("13:00", page.getHours().get("thu_1_close"));
		assertEquals("07:30", page.getHours().get("fri_1_open"));
		assertEquals("13:00", page.getHours().get("fri_1_close"));
		assertEquals("07:30", page.getHours().get("sat_1_open"));
		assertEquals("13:00", page.getHours().get("sat_1_close"));
		assertEquals("07:30", page.getHours().get("sun_1_open"));
		assertEquals("13:00", page.getHours().get("sun_1_close"));
	}

	@Test
	public void getPage_application() {
		mockServer.expect(requestTo("https://graph.facebook.com/140372495981006"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("application-page"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("140372495981006");
		assertEquals("140372495981006", page.getId());
		assertEquals("Greenhouse", page.getName());
		assertEquals("http://www.facebook.com/apps/application.php?id=140372495981006", page.getLink());
		assertEquals("The social destination for Spring application developers.", page.getDescription());
		assertTrue(page.canPost());
		assertEquals(0, page.getTalkingAboutCount());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getPage_withExtraData() {
		mockServer.expect(requestTo("https://graph.facebook.com/11803178355"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("page-with-extra-data"), MediaType.APPLICATION_JSON));

		Page page = facebook.pageOperations().getPage("11803178355");
		assertEquals("11803178355", page.getId());
		assertEquals("A Scanner Darkly", page.getName());
		assertEquals("https://www.facebook.com/pages/A-Scanner-Darkly/11803178355", page.getLink());
		assertNull(page.getDescription());
		Map<String, Object> extraData = page.getExtraData();
		assertEquals("Warner Independent Pictures", extraData.get("studio"));
		assertEquals(0, extraData.get("were_here_count"));
		assertEquals("Keanu Reeves, Robert Downey Jr., Woody Harrelson, Winona Ryder, Rory Cochrane", extraData.get("starring"));
		assertEquals("Richard Linklater based on Philip K. Dick's novel", extraData.get("screenplay_by"));
		assertEquals("2007", extraData.get("release_date"));
		assertEquals("Steven Soderbergh and George Clooney (Executive Producers)", extraData.get("produced_by"));
		assertTrue(extraData.get("plot_outline").toString().startsWith("In the future \"seven years from now\", America has lost the war on drugs. A highly addictive and debilitating illegal drug called Substance D, distilled from small blue flowers"));
		assertEquals("Science Fiction", extraData.get("genre"));
		assertEquals("Richard Linklater", extraData.get("directed_by"));
		assertEquals("Winner of Best Animation award OFCS Awards 2007", extraData.get("awards"));
		Map<String, Object> embedded = (Map<String, Object>) extraData.get("embedded");
		assertEquals("y", embedded.get("x"));
		assertEquals(2, embedded.get("a"));
		Map<String, Object> deeper = (Map<String, Object>) embedded.get("deeper");
		assertEquals("bar", deeper.get("foo"));
	}
	
	@Test
	public void isPageAdmin() {
		expectFetchAccounts();
		assertFalse(facebook.pageOperations().isPageAdmin("2468013579"));
		assertTrue(facebook.pageOperations().isPageAdmin("987654321"));
		assertTrue(facebook.pageOperations().isPageAdmin("1212121212"));
	}
	

	@Test(expected = NotAuthorizedException.class)
	public void isPageAdmin_unauthorized() {
		unauthorizedFacebook.pageOperations().isPageAdmin("2468013579");
	}
	
	@Test
	public void getAccounts() {
		expectFetchAccounts();
		List<Account> accounts = facebook.pageOperations().getAccounts();
		assertEquals(2, accounts.size());
		assertEquals("987654321", accounts.get(0).getId());
		assertEquals("Test Page", accounts.get(0).getName());
		assertEquals("Page", accounts.get(0).getCategory());
		assertEquals("pageAccessToken", accounts.get(0).getAccessToken());
		assertEquals("1212121212", accounts.get(1).getId());
		assertEquals("Test Page 2", accounts.get(1).getName());
		assertEquals("Page", accounts.get(1).getCategory());
		assertEquals("page2AccessToken", accounts.get(1).getAccessToken());
	}

	@Test
	public void post_message() throws Exception {
		expectFetchAccounts();
		String requestBody = "message=Hello+Facebook+World&access_token=pageAccessToken";
		mockServer.expect(requestTo("https://graph.facebook.com/987654321/feed"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		assertEquals("123456_78901234", facebook.pageOperations().post("987654321", "Hello Facebook World"));
		mockServer.verify();
	}

	@Test(expected = PageAdministrationException.class)
	public void postMessage_notAdmin() throws Exception {
		expectFetchAccounts();
		facebook.pageOperations().post("2468013579", "Hello Facebook World");
	}

	@Test(expected = NotAuthorizedException.class)
	public void postMessage_unauthorized() {
		unauthorizedFacebook.pageOperations().post("2468013579", "Hello Facebook World");
	}

	@Test
	public void postLink() throws Exception {
		expectFetchAccounts();
		String requestBody = "link=someLink&name=some+name&caption=some+caption&description=some+description&message=Hello+Facebook+World&access_token=pageAccessToken";
		mockServer.expect(requestTo("https://graph.facebook.com/987654321/feed")).andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\":\"123456_78901234\"}", MediaType.APPLICATION_JSON));
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		assertEquals("123456_78901234", facebook.pageOperations().post("987654321", "Hello Facebook World", link));
		mockServer.verify();
	}

	@Test(expected = PageAdministrationException.class)
	public void postLink_notAdmin() throws Exception {
		expectFetchAccounts();
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		facebook.pageOperations().post("2468013579", "Hello Facebook World", link);
	}

	@Test(expected = NotAuthorizedException.class)
	public void postLink_unauthorized() {
		FacebookLink link = new FacebookLink("someLink", "some name", "some caption", "some description");
		unauthorizedFacebook.pageOperations().post("2468013579", "Hello Facebook World", link);
	}

	@Test
	public void postPhoto_noCaption() {
		expectFetchAccounts();
		mockServer.expect(requestTo("https://graph.facebook.com/192837465/photos"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.pageOperations().postPhoto("987654321", "192837465", photo);
		assertEquals("12345", photoId);
	}

	@Test(expected = NotAuthorizedException.class)
	public void postPhoto_noCaption_unauthorized() {
		unauthorizedFacebook.pageOperations().postPhoto("987654321", "192837465", null);
	}
	
	@Test
	public void postPhoto_withCaption() {
		expectFetchAccounts();
		mockServer.expect(requestTo("https://graph.facebook.com/192837465/photos"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.pageOperations().postPhoto("987654321", "192837465", photo, "Some caption");
		assertEquals("12345", photoId);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void postPhoto_withCaption_unauthorized() {
		unauthorizedFacebook.pageOperations().postPhoto("987654321", "192837465", null, "Some caption");
	}

	// private helpers
	
	private void expectFetchAccounts() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/accounts"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("accounts"), MediaType.APPLICATION_JSON));
	}

	private Resource getUploadResource(final String filename, String content) {
		Resource video = new ByteArrayResource(content.getBytes()) {
			public String getFilename() throws IllegalStateException {
				return filename;
			};
		};
		return video;
	}

}
