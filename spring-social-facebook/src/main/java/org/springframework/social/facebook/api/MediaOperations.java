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

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;


/**
 * Defines operations for working with albums, photos, and videos.
 * @author Craig Walls
 */
public interface MediaOperations {

	/**
	 * Retrieves a list of albums belonging to the authenticated user.
	 * Requires "user_photos" permission.
	 * @return a list {@link Album}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Album> getAlbums();

	/**
	 * Retrieves a list of albums belonging to the authenticated user.
	 * Requires "user_photos" permission.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link Album}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Album> getAlbums(PagingParameters pagedListParameters);

	/**
	 * Retrieves a list of albums belonging to a specific owner (user, page, etc).
	 * Requires "user_photos" permission.
	 * @param ownerId the album owner's ID
	 * @return a list {@link Album}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 */
	PagedList<Album> getAlbums(String ownerId);

	/**
	 * Retrieves a list of albums belonging to a specific owner (user, page, etc).
	 * Requires "user_photos" permission.
	 * @param ownerId the album owner's ID
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list {@link Album}s for the user, or an empty list if not available.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 */
	PagedList<Album> getAlbums(String ownerId, PagingParameters pagedListParameters);

	/**
	 * Retrieves data for a specific album.
	 * Requires "user_photos" permission if the album is not public.
	 * @param albumId the album ID
	 * @return the requested {@link Album} object.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the album is not public and if the user has not granted "user_photos" permission.
	 */
	Album getAlbum(String albumId);
	
	/**
	 * Creates a new photo album.
	 * Requires "user_photos" permission.
	 * @param name the name of the album.
	 * @param description the album's description.
	 * @return the ID of the newly created album.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String createAlbum(String name, String description);

	/**
	 * Creates a new photo album.
	 * Requires "user_photos" permission.
	 * @param ownerId the owner of the album, possibly a page ID.
	 * @param name the name of the album.
	 * @param description the album's description.
	 * @return the ID of the newly created album.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_photos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String createAlbum(String ownerId, String name, String description);
	
	/**
	 * Retrieves an album's image as an array of bytes. Returns the image in Facebook's "normal" type.
	 * Requires "user_photos" permission if the album is not public.
	 * @param albumId the album ID
	 * @return an array of bytes containing the album's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the album is not public and if the user has not granted "user_photos" permission.
	 */
	byte[] getAlbumImage(String albumId);

	/**
	 * Retrieves an album's image as an array of bytes.
	 * Requires "user_photos" permission if the album is not public.
	 * @param albumId the album ID
	 * @param imageType the image type (eg., small, normal, large. square)
	 * @return an array of bytes containing the album's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the album is not public and if the user has not granted "user_photos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	byte[] getAlbumImage(String albumId, ImageType imageType);

	/**
	 * Retrieves data for up to 25 photos from a specific album or that a user is tagged in.
	 * If the objectId parameter is the ID of an album, the photos returned are the photos from that album.
	 * If the objectId parameter is the ID of a user, the photos returned are the photos that the user is tagged in.
	 * Requires "user_photos" permission if the album is not public.
	 * @param objectId either an album ID or a user ID
	 * @return a list of {@link Photo}s in the specified album.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the album is not public and if the user has not granted "user_photos" permission.
	 */
	PagedList<Photo> getPhotos(String objectId);

	/**
	 * Retrieves photo data from a specific album or that a user is tagged in.
	 * If the objectId parameter is the ID of an album, the photos returned are the photos from that album.
	 * If the objectId parameter is the ID of a user, the photos returned are the photos that the user is tagged in.
	 * Requires "user_photos" permission if the album is not public.
	 * @param objectId either an album ID or a user ID
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Photo}s in the specified album.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the album is not public and if the user has not granted "user_photos" permission.
	 */
	PagedList<Photo> getPhotos(String objectId, PagingParameters pagedListParameters);

	/**
	 * Retrieve data for a specified photo.
	 * Requires "user_photos" permission if the photo is not public.
	 * @param photoId the photo's ID
	 * @return the requested {@link Photo}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the photo is not public and if the user has not granted "user_photos" permission.
	 */
	Photo getPhoto(String photoId);
	
	/**
	 * Retrieves a photo's image as an array of bytes. Returns the image in Facebook's "normal" type.
	 * Requires "user_photos" permission if the photo is not public.
	 * @param photoId the photo ID
	 * @return an array of bytes containing the photo's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the photo is not public and if the user has not granted "user_photos" permission.
	 */
	byte[] getPhotoImage(String photoId);

	/**
	 * Retrieves a photo's image as an array of bytes.
	 * Requires "user_photos" permission if the photo is not public.
	 * @param photoId the photo ID
	 * @param imageType the image type (eg., small, normal, large. square)
	 * @return an array of bytes containing the photo's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the photo is not public and if the user has not granted "user_photos" permission.
	 */
	byte[] getPhotoImage(String photoId, ImageType imageType);
	
	/**
	 * Uploads a photo to an album created specifically for the application.
	 * Requires "publish_actions" permission.
	 * If no album exists for the application, it will be created.
	 * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @return the ID of the photo.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postPhoto(Resource photo);
	
	/**
	 * Uploads a photo to an album created specifically for the application.
	 * If no album exists for the application, it will be created.
	 * Requires "publish_actions" permission.
	 * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @param caption A caption describing the photo.
	 * @return the ID of the photo.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postPhoto(Resource photo, String caption);
	
	/**
	 * Uploads a photo to a specific album.
	 * Requires "publish_actions" permission.
	 * @param albumId the ID of the album to upload the photo to.
	 * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @return the ID of the photo.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postPhoto(String albumId, Resource photo);
	
	/**
	 * Uploads a photo to a specific album.
	 * Requires "publish_actions" permission.
	 * @param albumId the ID of the album to upload the photo to.
	 * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @param caption A caption describing the photo.
	 * @return the ID of the photo.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postPhoto(String albumId, Resource photo, String caption);
	
	/**
	 * Retrieves a list of up to 25 videos that the authenticated user is tagged in.
	 * Requires "user_videos" permission.
	 * @return a list of {@link Video} belonging to the authenticated user.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Video> getVideos();

	/**
	 * Retrieves a list of videos that the authenticated user is tagged in.
	 * Requires "user_videos" permission.
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Video} belonging to the authenticated user.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Video> getVideos(PagingParameters pagedListParameters);	
	
	/**
	 * Retrieves a list of up to 25 videos that a specified user is tagged in.
	 * Requires "user_videos" permission.
	 * @param userId the ID of the user who is tagged in the videos
	 * @return a list of {@link Video} which the specified user is tagged in.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Video> getVideos(String userId);

	/**
	 * Retrieves a list of videos that a specified user is tagged in.
	 * Requires "user_videos" permission.
	 * @param userId the ID of the user who is tagged in the videos
	 * @param pagedListParameters the parameters defining the bounds of the list to return.
	 * @return a list of {@link Video} which the specified user is tagged in.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	PagedList<Video> getVideos(String userId, PagingParameters pagedListParameters);

	/**
	 * Retrieves data for a specific video.
	 * Requires "user_videos" permission.
	 * @param videoId the ID of the video.
	 * @return the requested {@link Video} data.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	Video getVideo(String videoId);
	
	/**
	 * Retrieves a video's image as an array of bytes. Returns the image in Facebook's "normal" type.
	 * Requires "user_videos" permission.
	 * @param videoId the video ID
	 * @return an array of bytes containing the video's image.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "user_videos" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	byte[] getVideoImage(String videoId);

	/**
	 * Uploads a video for the authenticated user.
	 * Requires "publish_actions" permission.
	 * Note that the video will not be immediately available after uploading, as Facebook performs some post-upload processing on the video.
	 * @param video A {@link Resource} for the video data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @return the ID of the video.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postVideo(Resource video);
	
	/**
	 * Uploads a video for the authenticated user.
	 * Note that the video will not be immediately available after uploading, as Facebook performs some post-upload processing on the video.
	 * Requires "publish_actions" permission.
	 * @param video A {@link Resource} for the video data. The given Resource must implement the getFilename() method (such as {@link FileSystemResource} or {@link ClassPathResource}).
	 * @param title the video title
	 * @param description the video description
	 * @return the ID of the video.
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_actions" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String postVideo(Resource video, String title, String description);
	
	/**
	 * Tags a video with the given user ID.
	 * @param videoId the video ID
	 * @param userId the user ID
	 */
	void tagVideo(String videoId, String userId);

}
