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
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.MediaOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Photo;
import org.springframework.social.facebook.api.Tag;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.Video;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class MediaOperationsITests extends FacebookITest implements ITestCredentials {

	private static final Logger logger = Logger.getLogger(MediaOperationsITests.class.getName());
	
	private TestUser testUser1;
	private MediaOperations mediaOps1;
	private FacebookTemplate facebook1;

	private TestUser testUser2;
	private MediaOperations mediaOps2;
	private FacebookTemplate facebook2;

	public MediaOperationsITests() {
		super(APP_ID, APP_SECRET);
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_photos,user_videos", "Alice Arensen");
		testUser2 = createTestUser(true, "publish_actions,read_stream,user_posts,user_photos,user_videos", "Bob Beeswax");

		facebook1 = new FacebookTemplate(testUser1.getAccessToken());
		facebook2 = new FacebookTemplate(testUser2.getAccessToken());
		facebook1.testUserOperations().sendConfirmFriends(testUser1, testUser2);
		facebook2.testUserOperations().sendConfirmFriends(testUser2, testUser1);
		
		mediaOps1 = facebook1.mediaOperations();
		mediaOps2 = facebook2.mediaOperations();
		
		logger.info("CREATED TEST USERS: " + testUser1.getId() + " , " + testUser2.getId());
		
	}

	@Test
	public void photoAndAlbumTests() {
	
		// NOTE: These tests are surface tests only. In other words, they only test to make sure that the endpoints
		//       won't fail. Although it's possible to create a group for testing purposes, it seems impossible to
		//       delete it because you can't remove all members from the group. Instead, using a fixed group for
		//       surface testing purposes.
		//
		// TODO: Figure out how to create and delete a group.
		
		assertEquals(0, mediaOps1.getAlbums().size());
		String albumId = mediaOps1.createAlbum("TEST ALBUM", "An album for testing only");
		assertNotNull(albumId);
		PagedList<Album> albums = mediaOps1.getAlbums();
		assertEquals(1, albums.size());
		assertEquals("TEST ALBUM", albums.get(0).getName());
		assertEquals("An album for testing only", albums.get(0).getDescription());
		
		ClassPathResource photoResource = new ClassPathResource("GrizzlyPeak.jpg");
		String photoId = mediaOps1.postPhoto(albumId, photoResource, "Grizzly Peak");
		assertNotNull(photoId);
		Photo photo = mediaOps1.getPhoto(photoId);
		assertEquals(photoId, photo.getId());
		assertEquals("Grizzly Peak", photo.getName());
		assertEquals(720, photo.getWidth());
		assertEquals(482, photo.getHeight());
		assertEquals(testUser1.getId(), photo.getFrom().getId());
		assertEquals("Alice Arensen", photo.getFrom().getName());
		
		PagedList<Photo> photos = mediaOps1.getPhotos(albumId);
		assertEquals(1, photos.size());
		photo = photos.get(0);
		assertEquals(photoId, photo.getId());
		assertEquals("Grizzly Peak", photo.getName());
		assertEquals(720, photo.getWidth());
		assertEquals(482, photo.getHeight());
		assertEquals(testUser1.getId(), photo.getFrom().getId());
		assertEquals("Alice Arensen", photo.getFrom().getName());
		
		assertEquals(76962, mediaOps1.getPhotoImage(photoId).length);
	}
	
	@Test
	public void videoTests() throws Exception {
		assertEquals(0, mediaOps1.getVideos().size());

		ClassPathResource videoResource = new ClassPathResource("hamlet.mov");
		String videoId = mediaOps1.postVideo(videoResource, "TEST VIDEO", "A video for testing");

		Video video = mediaOps1.getVideo(videoId);
		assertEquals(videoId, video.getId());
		assertEquals("TEST VIDEO", video.getName());
		assertEquals("A video for testing", video.getDescription());
		
		assertEquals(0, mediaOps1.getVideos().size());
		mediaOps1.tagVideo(videoId, testUser2.getId());
		
		Thread.sleep(10000); // Give the video some time to get loaded and the tags to be applied or else the next set of assertions will fail.
		
		PagedList<Video> videos = mediaOps2.getVideos();
		assertEquals(1, videos.size());
		video = videos.get(0);
		assertEquals(videoId, video.getId());
		assertEquals("TEST VIDEO", video.getName());
		assertEquals("A video for testing", video.getDescription());
		List<Tag> tags = video.getTags();
		assertEquals(1, tags.size());
		assertEquals(testUser2.getId(), tags.get(0).getId());
		assertEquals("Bob Beeswax", tags.get(0).getName());
	}

}
