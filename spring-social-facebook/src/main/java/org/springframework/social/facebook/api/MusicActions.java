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

/**
 * Defines operations for publishing OpenGraph actions pertaining to listening to music.
 * Many OpenGraph operations require approval from Facebook.
 * See https://developers.facebook.com/docs/opengraph/submission-process for details.
 * @author Craig Walls
 */
public interface MusicActions {

	/**
	 * Publishes a "music.listens" action for the OpenGraph song object at the given URL.
	 * @param songUrl The URL of the song that was listened to. Must reference an OpenGraph object of type "music.song".
	 * @return The ID for the action created.
	 */
	String listenToSong(String songUrl);

	/**
	 * Publishes a "music.listens" action for the OpenGraph song object at the given URL.
	 * @param songUrl The URL of the song that was listened to. Must reference an OpenGraph object of type "music.song".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listenToSong(String songUrl, ActionMetadata metadata);

	/**
	 * Publishes a "music.listens" action for the OpenGraph album object at the given URL.
	 * @param albumUrl The URL of the album that was listened to. Must reference an OpenGraph object of type "music.album".
	 * @return The ID for the action created.
	 */
	String listenToAlbum(String albumUrl);

	/**
	 * Publishes a "music.listens" action for the OpenGraph album object at the given URL.
	 * @param albumUrl The URL of the album that was listened to. Must reference an OpenGraph object of type "music.album".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listenToAlbum(String albumUrl, ActionMetadata metadata);

	/**
	 * Publishes a "music.listens" action for the OpenGraph musician object at the given URL.
	 * @param musicianUrl The profile URL of the musician that was listened to. Must reference an OpenGraph object of type "profile".
	 * @return The ID for the action created.
	 */
	String listenToMusician(String musicianUrl);

	/**
	 * Publishes a "music.listens" action for the OpenGraph musician object at the given URL.
	 * @param musicianUrl The profile URL of the musician that was listened to. Must reference an OpenGraph object of type "profile".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listenToMusician(String musicianUrl, ActionMetadata metadata);

	/**
	 * Publishes a "music.listens" action for the OpenGraph radio station object at the given URL.
	 * @param radioStationUrl The URL of the radio station that was listened to. Must reference an OpenGraph object of type "music.radio_station".
	 * @return The ID for the action created.
	 */
	String listenToRadioStation(String radioStationUrl);

	/**
	 * Publishes a "music.listens" action for the OpenGraph radio station object at the given URL.
	 * @param radioStationUrl The URL of the radio station that was listened to. Must reference an OpenGraph object of type "music.radio_station".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listenToRadioStation(String radioStationUrl, ActionMetadata metadata);

	/**
	 * Publishes a "music.listens" action for the OpenGraph playlist object at the given URL.
	 * @param playlistUrl The URL of the playlist that was listened to. Must reference an OpenGraph object of type "music.playlist".
	 * @return The ID for the action created.
	 */
	String listenToPlaylist(String playlistUrl);

	/**
	 * Publishes a "music.listens" action for the OpenGraph playlist object at the given URL.
	 * @param playlistUrl The URL of the playlist that was listened to. Must reference an OpenGraph object of type "music.playlist".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listenToPlaylist(String playlistUrl, ActionMetadata metadata);

	/**
	 * Publishes a "music.listens" action for the OpenGraph object at the given URL.
	 * This is a lower level and more generic method for creating "music.listens" actions that aren't covered by the more specific listenToXXX() methods.
	 * It allows for the possibility that Facebook may add additional types that may be listened to beyond those currently supported.
	 * @param objectType The type of object being listened to. E.g., "song", "album", "musician", "radio_station", or "playlist".
	 * @param objectUrl The URL of the object being listened to.
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String listen(String objectType, String objectUrl, ActionMetadata metadata);
	
	/**
	 * Publishes a "music.playlists" action (indicating that a playlist was created) for the OpenGraph playlist object at the given URL.
	 * @param playlistUrl The URL of the playlist that was created. Must reference an OpenGraph object of type "music.playlist".
	 * @return The ID for the action created.
	 */
	String createPlaylist(String playlistUrl);
	
	/**
	 * Publishes a "music.playlists" action (indicating that a playlist was created) for the OpenGraph playlist object at the given URL.
	 * @param playlistUrl The URL of the playlist that was created. Must reference an OpenGraph object of type "music.playlist".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String createPlaylist(String playlistUrl, ActionMetadata metadata);
	
}
