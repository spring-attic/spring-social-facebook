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

import static org.springframework.social.facebook.api.impl.PagedListUtils.*;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.MediaOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Photo;
import org.springframework.social.facebook.api.Video;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class MediaTemplate implements MediaOperations {

	private final GraphApi graphApi;
	
	private final RestTemplate restTemplate;

	public MediaTemplate(GraphApi graphApi, RestTemplate restTemplate) {
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public PagedList<Album> getAlbums() {
		return getAlbums("me", new PagingParameters(25, 0, null, null));
	}

	public PagedList<Album> getAlbums(PagingParameters pagedListParameters) {
		return getAlbums("me", pagedListParameters);
	}

	public PagedList<Album> getAlbums(String userId) {
		return getAlbums(userId, new PagingParameters(25, 0, null, null));
	}
	
	public PagedList<Album> getAlbums(String userId, PagingParameters pagedListParameters) {
		return graphApi.fetchConnections(userId, "albums", Album.class, getPagingParameters(pagedListParameters));
	}

	public Album getAlbum(String albumId) {
		return graphApi.fetchObject(albumId, Album.class);
	}
	
	public String createAlbum(String name, String description) {
		return createAlbum("me", name, description);
	}
	
	public String createAlbum(String ownerId, String name, String description) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", name);
		data.set("message", description);
		return graphApi.publish(ownerId, "albums", data);
	}
	
	public byte[] getAlbumImage(String albumId) {
		return getAlbumImage(albumId, ImageType.ALBUM);
	}
	
	public byte[] getAlbumImage(String albumId, ImageType imageType) {
		return graphApi.fetchImage(albumId, "picture", imageType);
	}
	
	public PagedList<Photo> getPhotos(String objectId) {
		return getPhotos(objectId, new PagingParameters(25, 0, null, null));
	}
	
	public PagedList<Photo> getPhotos(String objectId, PagingParameters pagedListParameters) {
		return graphApi.fetchConnections(objectId, "photos", Photo.class, getPagingParameters(pagedListParameters), ALL_PHOTO_FIELDS);
	}
	
	public Photo getPhoto(String photoId) {
		return graphApi.fetchObject(photoId, Photo.class);
	}
	
	public byte[] getPhotoImage(String photoId) {
		return getPhotoImage(photoId, ImageType.NORMAL);
	}
	
	public byte[] getPhotoImage(String photoId, ImageType imageType) {
		return graphApi.fetchImage(photoId, "picture", imageType);
	}

	public String postPhoto(Resource photo) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish("me", "photos", parts);
	}
	
	public String postPhoto(Resource photo, String caption) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish("me", "photos", parts);
	}
	
	public String postPhoto(String albumId, Resource photo) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish(albumId, "photos", parts);
	}
	
	public String postPhoto(String albumId, Resource photo, String caption) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish(albumId, "photos", parts);
	}
	
	public PagedList<Video> getVideos() {
		return getVideos("me", new PagingParameters(25, 0, null, null));
	}

	public PagedList<Video> getVideos(PagingParameters pagedListParameters) {
		return getVideos("me", pagedListParameters);
	}

	public PagedList<Video> getVideos(String userId) {
		return getVideos(userId, new PagingParameters(25, 0, null, null));
	}
	
	public PagedList<Video> getVideos(String userId, PagingParameters pagedListParameters) {
		return graphApi.fetchConnections(userId, "videos", Video.class, getPagingParameters(pagedListParameters));
	}
	
	public Video getVideo(String videoId) {
		return graphApi.fetchObject(videoId, Video.class);
	}
	
	public byte[] getVideoImage(String videoId) {
		return graphApi.fetchImage(videoId, "picture", ImageType.SMALL);
	}
	
	@SuppressWarnings("unchecked")
	public String postVideo(Resource video) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}
	
	@SuppressWarnings("unchecked")
	public String postVideo(Resource video, String title, String description) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		parts.set("title", title);
		parts.set("description", description);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}
	
	public void tagVideo(String videoId, String userId) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("tag_uid", userId);
		graphApi.publish(videoId, "tags", data);
	}
	
	static final String[] ALL_PHOTO_FIELDS = {
			"id", "album", "backdated_time", "backdated_time_granularity", "created_time", "from", "height", "picture",
			"source", "link", "icon", "images", "name", "page_story_id", "place,updated_time", "tags"
	};
}
