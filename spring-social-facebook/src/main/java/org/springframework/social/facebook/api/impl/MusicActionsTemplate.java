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

import static org.springframework.social.facebook.api.OpenGraphOperations.*;

import org.springframework.social.facebook.api.ActionMetadata;
import org.springframework.social.facebook.api.MusicActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.util.MultiValueMap;

public class MusicActionsTemplate implements MusicActions {

	private OpenGraphOperations openGraphOperations;

	public MusicActionsTemplate(OpenGraphOperations openGraphOperations) {
		this.openGraphOperations = openGraphOperations;
	}
	
	public String listenToSong(String songUrl) {
		return listen("song", songUrl, EMPTY_ACTION_METADATA);
	}

	public String listenToSong(String songUrl, ActionMetadata metadata) {
		return listen("song", songUrl, metadata);
	}

	public String listenToAlbum(String albumUrl) {
		return listen("album", albumUrl, EMPTY_ACTION_METADATA);
	}

	public String listenToAlbum(String albumUrl, ActionMetadata metadata) {
		return listen("album", albumUrl, metadata);
	}

	public String listenToMusician(String musicianUrl) {
		return listen("musician", musicianUrl, EMPTY_ACTION_METADATA);
	}

	public String listenToMusician(String musicianUrl, ActionMetadata metadata) {
		return listen("musician", musicianUrl, metadata);
	}

	public String listenToRadioStation(String radioStationUrl) {
		return listen("radio_station", radioStationUrl, EMPTY_ACTION_METADATA);
	}

	public String listenToRadioStation(String radioStationUrl, ActionMetadata metadata) {
		return listen("radio_station", radioStationUrl, metadata);
	}

	public String listenToPlaylist(String playlistUrl) {
		return listen("playlist", playlistUrl, EMPTY_ACTION_METADATA);
	}

	public String listenToPlaylist(String playlistUrl, ActionMetadata metadata) {
		return listen("playlist", playlistUrl, metadata);
	}

	public String listen(String objectType, String url, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set(objectType, url);
		return openGraphOperations.publishAction("music.listens", parameters, true);
	}
	
	public String createPlaylist(String playlistUrl) {
		return createPlaylist(playlistUrl, EMPTY_ACTION_METADATA);
	}
	
	public String createPlaylist(String playlistUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set("playlist", playlistUrl);
		return openGraphOperations.publishAction("music.playlists", parameters, true);
	}

}
