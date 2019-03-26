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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.Photo.TimeGranularity;
import org.springframework.util.StringUtils;

public class MediaTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void getAlbums() {
		mockServer.expect(requestTo(fbUrl("me/albums?offset=0&limit=25")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("albums"), MediaType.APPLICATION_JSON));
		List<Album> albums = facebook.mediaOperations().getAlbums();
		assertAlbums(albums);
	}

	@Test
	public void getAlbums_forSpecificUser() {
		mockServer.expect(requestTo(fbUrl("192837465/albums?offset=0&limit=25")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("albums"), MediaType.APPLICATION_JSON));
		List<Album> albums = facebook.mediaOperations().getAlbums("192837465");
		assertAlbums(albums);
	}

	@Test
	public void getAlbum() {
		mockServer.expect(requestTo(fbUrl("10151447271460580")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("album"), MediaType.APPLICATION_JSON));
		Album album = facebook.mediaOperations().getAlbum("10151447271460580");
		assertSingleAlbum(album);
	}

	@Test
	public void getAlbumWithUnknownPrivacy() {
		mockServer.expect(requestTo(fbUrl("10151447271460580")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("album-with-unknown-privacy"), MediaType.APPLICATION_JSON));
		Album album = facebook.mediaOperations().getAlbum("10151447271460580");
		assertEquals(Album.Privacy.CUSTOM, album.getPrivacy());
	}

	@Test
	public void getAlbumWithUnknownType() {
		mockServer.expect(requestTo(fbUrl("10151447271460580")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("album-with-unknown-type"), MediaType.APPLICATION_JSON));
		Album album = facebook.mediaOperations().getAlbum("10151447271460580");
		assertEquals(Album.Type.UNKNOWN, album.getType());
	}

	@Test
	public void getPhotos() {
		String allPhotoFields = StringUtils.arrayToCommaDelimitedString(ALL_PHOTO_FIELDS).replace(",", "%2C");
		mockServer.expect(requestTo(fbUrl("10151447271460580/photos?offset=0&limit=25&fields=" + allPhotoFields)))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("photos"), MediaType.APPLICATION_JSON));
	
		List<Photo> photos = facebook.mediaOperations().getPhotos("10151447271460580");
		assertPhotos(photos);
	}

	@Test
	public void getPhoto() {
		mockServer.expect(requestTo(fbUrl("10150447271355581")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("photo"), MediaType.APPLICATION_JSON));
		assertSinglePhoto(facebook.mediaOperations().getPhoto("10150447271355581"));		
	}
	
	@Test
	public void postPhoto_noCaption() {
		mockServer.expect(requestTo(fbUrl("me/photos")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.mediaOperations().postPhoto(photo);
		assertEquals("12345", photoId);
	}

	@Test
	public void postPhoto_withCaption() {
		mockServer.expect(requestTo(fbUrl("me/photos")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.mediaOperations().postPhoto(photo, "Some caption");
		assertEquals("12345", photoId);
	}

	@Test
	public void postPhoto_ToAlbumNoCaption() {
		mockServer.expect(requestTo(fbUrl("192837465/photos")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.mediaOperations().postPhoto("192837465", photo);
		assertEquals("12345", photoId);
	}

	@Test
	public void postPhoto_ToAlbumWithCaption() {
		mockServer.expect(requestTo(fbUrl("192837465/photos")))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
		String photoId = facebook.mediaOperations().postPhoto("192837465", photo, "Some caption");
		assertEquals("12345", photoId);
	}

	@Test
	public void getVideos() {
		mockServer.expect(requestTo(fbUrl("me/videos?offset=0&limit=25")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("videos"), MediaType.APPLICATION_JSON));
		List<Video> videos = facebook.mediaOperations().getVideos();
		assertVideos(videos);
	}

	@Test
	public void getVideos_forSpecificOwner() {
		mockServer.expect(requestTo(fbUrl("100001387295207/videos?offset=0&limit=25")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("videos"), MediaType.APPLICATION_JSON));
		List<Video> videos = facebook.mediaOperations().getVideos("100001387295207");
		assertVideos(videos);
	}

	@Test
	public void getVideo_preOctober2012() {
		mockServer.expect(requestTo(fbUrl("161500020572907")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("video_preOct2012"), MediaType.APPLICATION_JSON));
		Video video = facebook.mediaOperations().getVideo("161500020572907");
		assertSingleVideo(video);
	}

	@Test
	public void getVideo() {
		mockServer.expect(requestTo(fbUrl("161500020572907")))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("video"), MediaType.APPLICATION_JSON));
		Video video = facebook.mediaOperations().getVideo("161500020572907");
		assertSingleVideo(video);
	}

	@Test
	public void postVideo_noTitleOrDescription() {
		mockServer.expect(requestTo("https://graph-video.facebook.com/me/videos"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource video = getUploadResource("video.mov", "VIDEO DATA");
		String photoId = facebook.mediaOperations().postVideo(video);
		assertEquals("12345", photoId);
	}

	@Test
	public void postVideo_withTitleOrDescription() {
		mockServer.expect(requestTo("https://graph-video.facebook.com/me/videos"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"id\":\"12345\"}", MediaType.APPLICATION_JSON));
		// TODO: Match body content to ensure fields and photo are included
		Resource video = getUploadResource("video.mov", "VIDEO DATA");
		String photoId = facebook.mediaOperations().postVideo(video, "title", "description");
		assertEquals("12345", photoId);
	}

	@Test
	public void tagVideo() {
		String requestBody = "tag_uid=12345";

		mockServer.expect(requestTo(fbUrl("54321/tags")))
			.andExpect(method(POST))
			.andExpect(content().string(requestBody))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("{\"success\":\"true\"}", MediaType.APPLICATION_JSON));
		
		facebook.mediaOperations().tagVideo("54321", "12345");
		mockServer.verify();
	}

	private Resource getUploadResource(final String filename, String content) {
		Resource video = new ByteArrayResource(content.getBytes()) {
			public String getFilename() throws IllegalStateException {
				return filename;
			};
		};
		return video;
	}

	private void assertPhotos(List<Photo> photos) {
		assertEquals(2, photos.size());
		assertSinglePhoto(photos.get(1));
		assertEquals("10150447271355580", photos.get(0).getId());
		assertEquals("738140579", photos.get(0).getFrom().getId());
		assertEquals("Craig Walls", photos.get(0).getFrom().getName());
		assertNull(photos.get(0).getName());
		assertNull(photos.get(0).getTags());
		assertEquals(8, photos.get(0).getImages().size());
		assertEquals(toDate("2011-03-24T21:36:06+0000"), photos.get(0).getCreatedTime());
		assertEquals(toDate("2011-03-24T21:37:43+0000"), photos.get(0).getUpdatedTime());
	}

	private void assertSingleVideo(Video video) {
		assertEquals("161500020572907", video.getId());
		assertEquals("100001387295207", video.getFrom().getId());
		assertEquals("Art Names", video.getFrom().getName());
		assertEquals(1, video.getTags().size());
		assertEquals("100001387295207", video.getTags().get(0).getId());
		assertEquals("Art Names", video.getTags().get(0).getName());
		assertNull(video.getTags().get(0).getX());
		assertNull(video.getTags().get(0).getY());
		assertEquals("Just a test screen recording", video.getName());
		assertEquals("Nothing special...just for testing purposes.", video.getDescription());
		assertEquals("https://vthumb.ak.fbcdn.net/hvthumb-ak-ash2/158179_161500167239559_161500020572907_64114_872_t.jpg", video.getPicture());
		assertEquals("<object width=\"400\" height=\"250\" ><param name=\"allowfullscreen\" value=\"true\" /><param name=\"movie\" value=\"https://www.facebook.com/v/161500020572907\" /><embed src=\"https://www.facebook.com/v/161500020572907\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" width=\"400\" height=\"250\"></embed></object>", video.getEmbedHtml());
		assertEquals("https://b.static.ak.fbcdn.net/rsrc.php/v1/yD/r/DggDhA4z4tO.gif", video.getIcon());
		assertEquals("https://video.ak.fbcdn.net/cfs-ak-snc6/80396/785/161500020572907_43024.mp4?oh=2d01ac0ffce931fecb8987ae02837fc6&oe=4D94E600&__gda__=1301603840_718156f2f2c257ebd7714b3b0ba5164e", video.getSource());
		assertEquals(toDate("2011-03-29T20:25:55+0000"), video.getCreatedTime());
		assertEquals(toDate("2011-03-29T20:25:55+0000"), video.getUpdatedTime());
	}
	
	private void assertSinglePhoto(Photo photo) {
		assertEquals("10150447271355581", photo.getId());
		assertEquals("738140579", photo.getFrom().getId());
		assertEquals("Craig Walls", photo.getFrom().getName());
		assertEquals("Cool picture", photo.getName());
		assertEquals(1, photo.getTags().size());
		assertEquals("738140579", photo.getTags().get(0).getId());
		assertEquals("Craig Walls", photo.getTags().get(0).getName());
		assertEquals((Integer) 47, photo.getTags().get(0).getX());
		assertEquals((Integer) 24, photo.getTags().get(0).getY());
		assertEquals(8, photo.getImages().size());
		assertEquals("https://photos-e.ak.fbcdn.net/hphotos-ak-snc6/200110_10150447271355580_738140579_17698198_7684115_s.jpg", photo.getPicture());
		assertEquals("https://a5.sphotos.ak.fbcdn.net/hphotos-ak-snc6/200110_10150447271355580_738140579_17698198_7684115_n.jpg", photo.getSource());
		assertEquals("https://www.facebook.com/photo.php?pid=17698198&id=738140579", photo.getLink());
		assertEquals("https://static.ak.fbcdn.net/rsrc.php/v1/yz/r/StEh3RhPvjl.gif", photo.getIcon());
		assertEquals(toDate("2011-03-24T21:36:06+0000"), photo.getCreatedTime());
		assertEquals(toDate("2011-03-24T21:37:43+0000"), photo.getUpdatedTime());
		assertEquals(toDate("2011-03-24T21:36:06+0000"), photo.getBackdatedTime());
		assertEquals(TimeGranularity.MONTH, photo.getBackdatedTimeGranularity());
	}

	private void assertAlbums(List<Album> albums) {
		assertEquals(3, albums.size());
		assertSingleAlbum(albums.get(0));
		assertEquals("10150694228040580", albums.get(1).getId());
		assertEquals("738140579", albums.get(1).getFrom().getId());
		assertEquals("Craig Walls", albums.get(1).getFrom().getName());
		assertEquals("https://www.facebook.com/album.php?aid=526031&id=738140579", albums.get(1).getLink());
		assertEquals("Profile Pictures", albums.get(1).getName());
		assertNull(albums.get(1).getDescription());
		assertNull(albums.get(1).getLocation());
		assertEquals(Album.Privacy.FRIENDS_OF_FRIENDS, albums.get(1).getPrivacy());
		assertEquals(Album.Type.PROFILE, albums.get(1).getType());
		assertEquals(5, albums.get(1).getCount());
		assertEquals(toDate("2010-10-22T20:22:51+0000"), albums.get(1).getCreatedTime());
		assertNull(albums.get(1).getUpdatedTime());

		assertEquals("247501695549", albums.get(2).getId());
		assertEquals("738140579", albums.get(2).getFrom().getId());
		assertEquals("Craig Walls", albums.get(2).getFrom().getName());
		assertEquals("https://www.facebook.com/album.php?aid=290408&id=738140579", albums.get(2).getLink());
		assertEquals("Photos on the go", albums.get(2).getName());
		assertNull(albums.get(2).getDescription());
		assertNull(albums.get(2).getLocation());
		assertEquals(Album.Privacy.EVERYONE, albums.get(2).getPrivacy());
		assertEquals(Album.Type.MOBILE, albums.get(2).getType());
		assertEquals(3, albums.get(2).getCount());
		assertEquals(toDate("2009-08-08T19:28:46+0000"), albums.get(2).getCreatedTime());
		assertEquals(toDate("2010-08-25T02:03:43+0000"), albums.get(2).getUpdatedTime());
	}

	private void assertSingleAlbum(Album album) {
		assertEquals("10151447271460580", album.getId());
		assertEquals("738140579", album.getFrom().getId());
		assertEquals("Craig Walls", album.getFrom().getName());
		assertEquals("https://www.facebook.com/album.php?aid=620722&id=738140579", album.getLink());
		assertEquals("Early Broncos", album.getName());
		assertEquals("10151447371355580", album.getCoverPhoto());
		assertNull(album.getDescription());
		assertEquals("Somewhere", album.getLocation());
		assertEquals(Album.Privacy.CUSTOM, album.getPrivacy());
		assertEquals(Album.Type.NORMAL, album.getType());
		assertEquals(1, album.getCount());
		assertEquals(toDate("2011-03-24T21:36:04+0000"), album.getCreatedTime());
		assertEquals(toDate("2011-03-24T22:00:12+0000"), album.getUpdatedTime());
	}

	private void assertVideos(List<Video> videos) {
		assertEquals(2, videos.size());
		Video video = videos.get(0);
		assertEquals("161503963905846", video.getId());
		assertEquals("100001387295207", video.getFrom().getId());
		assertEquals("Art Names", video.getFrom().getName());
		assertEquals("https://vthumb.ak.fbcdn.net/hvthumb-ak-ash2/50903_161504077239168_161503963905846_21174_1003_t.jpg", video.getPicture());
		assertEquals("<object width=\"400\" height=\"250\" ><param name=\"allowfullscreen\" value=\"true\" /><param name=\"movie\" value=\"https://www.facebook.com/v/161503963905846\" /><embed src=\"https://www.facebook.com/v/161503963905846\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" width=\"400\" height=\"250\"></embed></object>", video.getEmbedHtml());
		assertEquals("https://b.static.ak.fbcdn.net/rsrc.php/v1/yD/r/DggDhA4z4tO.gif", video.getIcon());
		assertEquals("https://video.ak.fbcdn.net/cfs-ak-snc6/82226/704/161503963905846_41386.mp4?oh=131db79e0842f1c57940aa274b82d8fe&oe=4D95D900&__gda__=1301666048_11e66cf124ce537194b3f7b6ab86b579", video.getSource());
		assertEquals(toDate("2011-03-29T20:45:20+0000"), video.getCreatedTime());
		assertEquals(toDate("2011-03-29T20:45:20+0000"), video.getUpdatedTime());
		assertSingleVideo(videos.get(1));
	}

	static final String[] ALL_PHOTO_FIELDS = {
			"id", "album", "backdated_time", "backdated_time_granularity", "created_time", "from", "height", "picture",
			"source", "link", "icon", "images", "name", "page_story_id", "place,updated_time", "tags"
	};

}
