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
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.social.facebook.api.VideoActions;
import org.springframework.util.MultiValueMap;

public class VideoActionsTemplate implements VideoActions {

	private OpenGraphOperations openGraphOperations;

	public VideoActionsTemplate(OpenGraphOperations openGraphOperations) {
		this.openGraphOperations = openGraphOperations;
	}
	
	public String watchMovie(String movieUrl) {
		return watchMovie(movieUrl, EMPTY_ACTION_METADATA);
	};
	
	public String watchMovie(String movieUrl, ActionMetadata metadata) {
		return watchVideo("movie", movieUrl, metadata);
	}

	public String watchTvShow(String tvShowUrl) {
		return watchTvShow(tvShowUrl, EMPTY_ACTION_METADATA);
	};
	
	public String watchTvShow(String tvShowUrl, ActionMetadata metadata) {
		return watchVideo("tv_show", tvShowUrl, metadata);
	}

	public String watchTvEpisode(String tvEpisodeUrl) {
		return watchTvEpisode(tvEpisodeUrl, EMPTY_ACTION_METADATA);
	};
	
	public String watchTvEpisode(String tvShowUrl, ActionMetadata metadata) {
		return watchVideo("tv_episode", tvShowUrl, metadata);
	}

	public String watchEpisode(String episodeUrl) {
		return watchEpisode(episodeUrl, EMPTY_ACTION_METADATA);
	};
	
	public String watchEpisode(String episodeUrl, ActionMetadata metadata) {
		return watchVideo("episode", episodeUrl, metadata);
	}

	public String watchVideo(String videoUrl) {
		return watchVideo(videoUrl, EMPTY_ACTION_METADATA);
	};
	
	public String watchVideo(String videoUrl, ActionMetadata metadata) {
		return watchVideo("video", videoUrl, metadata);
	}

	public String watchVideo(String videoType, String videoUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set(videoType, videoUrl);
		return openGraphOperations.publishAction("video.watches", parameters, true);
	}
	
	public String rateMovie(String movieUrl, float rating, int scale) {
		return rateMovie(movieUrl, rating, scale, EMPTY_ACTION_METADATA);
	}
	
	public String rateMovie(String movieUrl, float rating, int scale, ActionMetadata metadata) {
		return rateVideo("movie", movieUrl, rating, scale, metadata);
	}
	
	public String rateEpisode(String episodeUrl, float rating, int scale) {
		return rateEpisode(episodeUrl, rating, scale, EMPTY_ACTION_METADATA);
	}
	
	public String rateEpisode(String episodeUrl, float rating, int scale, ActionMetadata metadata) {
		return rateVideo("episode", episodeUrl, rating, scale, metadata);
	}
	
	public String rateTvShow(String tvShowUrl, float rating, int scale) {
		return rateTvShow(tvShowUrl, rating, scale, EMPTY_ACTION_METADATA);
	}
	
	public String rateTvShow(String tvShowUrl, float rating, int scale, ActionMetadata metadata) {
		return rateVideo("tv_show", tvShowUrl, rating, scale, metadata);
	}
	
	public String rateVideo(String otherUrl, float rating, int scale) {
		return rateVideo(otherUrl, rating, scale, EMPTY_ACTION_METADATA);
	}
	
	public String rateVideo(String otherUrl, float rating, int scale, ActionMetadata metadata) {
		return rateVideo("other", otherUrl, rating, scale, metadata);
	}
	
	public String rateVideo(String videoType, String videoUrl, float rating, int scale, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set(videoType, videoUrl);
		parameters.set("rating:value", String.valueOf(rating));
		parameters.set("rating:scale", String.valueOf(scale));
		return openGraphOperations.publishAction("video.rates", parameters, true);
	}
	
	public String wantsToWatchMovie(String movieUrl) {
		return wantsToWatchMovie(movieUrl, EMPTY_ACTION_METADATA);
	}
	
	public String wantsToWatchMovie(String movieUrl, ActionMetadata metadata) {
		return wantsToWatch("movie", movieUrl, metadata);
	}
	
	public String wantsToWatchTvShow(String tvShowUrl) {
		return wantsToWatchTvShow(tvShowUrl, EMPTY_ACTION_METADATA);
	}
	
	public String wantsToWatchTvShow(String tvShowUrl, ActionMetadata metadata) {
		return wantsToWatch("tv_show", tvShowUrl, metadata);
	}
	
	public String wantsToWatchEpisode(String episodeUrl) {
		return wantsToWatchEpisode(episodeUrl, EMPTY_ACTION_METADATA);
	}
	
	public String wantsToWatchEpisode(String episodeUrl, ActionMetadata metadata) {
		return wantsToWatch("episode", episodeUrl, metadata);
	}
	
	public String wantsToWatchVideo(String otherUrl) {
		return wantsToWatchVideo(otherUrl, EMPTY_ACTION_METADATA);
	}
	
	public String wantsToWatchVideo(String otherUrl, ActionMetadata metadata) {
		return wantsToWatch("other", otherUrl, metadata);
	}
	
	public String wantsToWatch(String videoType, String videoUrl, ActionMetadata metadata) {
		MultiValueMap<String, Object> parameters = metadata.toParameters();
		parameters.set(videoType, videoUrl);
		return openGraphOperations.publishAction("video.wants_to_watch", parameters, true);
	}

}
