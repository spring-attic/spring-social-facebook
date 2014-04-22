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

class MediaTemplate extends AbstractFacebookOperations implements MediaOperations {

	private final GraphApi graphApi;
	
	private final RestTemplate restTemplate;

	public MediaTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public PagedList<Album> getAlbums() {
		requireAuthorization();
		return getAlbums("me", 0, 25);
	}

	public PagedList<Album> getAlbums(int offset, int limit) {
		requireAuthorization();
		return getAlbums("me", offset, limit);
	}

	public PagedList<Album> getAlbums(PagingParameters pagedListParameters) {
		requireAuthorization();
		return getAlbums("me", pagedListParameters);
	}

	public PagedList<Album> getAlbums(String userId) {
		return getAlbums(userId, 0, 25);
	}
	
	public PagedList<Album> getAlbums(String userId, int offset, int limit) {
		return getAlbums(userId, new PagingParameters(limit, offset, null, null));
	}
	
	public PagedList<Album> getAlbums(String userId, PagingParameters pagedListParameters) {
		return graphApi.fetchConnections(userId, "albums", Album.class, getPagingParameters(pagedListParameters));
	}

	public Album getAlbum(String albumId) {
		return graphApi.fetchObject(albumId, Album.class);
	}
	
	public String createAlbum(String name, String description) {
		requireAuthorization();
		return createAlbum("me", name, description);
	}
	
	public String createAlbum(String ownerId, String name, String description) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", name);
		data.set("message", description);
		return graphApi.publish(ownerId, "albums", data);
	}
	
	public byte[] getAlbumImage(String albumId) {
		return getAlbumImage(albumId, ImageType.NORMAL);
	}
	
	public byte[] getAlbumImage(String albumId, ImageType imageType) {
		requireAuthorization();
		return graphApi.fetchImage(albumId, "picture", imageType);
	}
	
	public PagedList<Photo> getPhotos(String objectId) {
		return getPhotos(objectId, 0, 25);
	}
	
	public PagedList<Photo> getPhotos(String objectId, int offset, int limit) {
		return getPhotos(objectId, new PagingParameters(limit, offset, null, null));
	}
	
	public PagedList<Photo> getPhotos(String objectId, PagingParameters pagedListParameters) {
		return graphApi.fetchConnections(objectId, "photos", Photo.class, getPagingParameters(pagedListParameters));
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
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish("me", "photos", parts);
	}
	
	public String postPhoto(Resource photo, String caption) {
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish("me", "photos", parts);
	}
	
	public String postPhoto(String albumId, Resource photo) {
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish(albumId, "photos", parts);
	}
	
	public String postPhoto(String albumId, Resource photo, String caption) {
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish(albumId, "photos", parts);
	}
	
	public PagedList<Video> getVideos() {
		return getVideos("me", 0, 25);
	}

	public PagedList<Video> getVideos(int offset, int limit) {
		return getVideos("me", new PagingParameters(limit, offset, null, null));
	}

	public PagedList<Video> getVideos(PagingParameters pagedListParameters) {
		return getVideos("me", pagedListParameters);
	}

	public PagedList<Video> getVideos(String userId) {
		return getVideos(userId, 0, 25);
	}
	
	public PagedList<Video> getVideos(String userId, int offset, int limit) {
		return getVideos(userId, new PagingParameters(limit, offset, null, null));
	}

	public PagedList<Video> getVideos(String userId, PagingParameters pagedListParameters) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "videos", Video.class, getPagingParameters(pagedListParameters));
	}
	
	public Video getVideo(String videoId) {
		requireAuthorization();
		return graphApi.fetchObject(videoId, Video.class);
	}
	
	public byte[] getVideoImage(String videoId) {
		return getVideoImage(videoId, ImageType.NORMAL);
	}
	
	public byte[] getVideoImage(String videoId, ImageType imageType) {
		requireAuthorization();
		return graphApi.fetchImage(videoId, "picture", imageType);
	}
	
	@SuppressWarnings("unchecked")
	public String postVideo(Resource video) {
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}
	
	@SuppressWarnings("unchecked")
	public String postVideo(Resource video, String title, String description) {
		requireAuthorization();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		parts.set("title", title);
		parts.set("description", description);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}
}
