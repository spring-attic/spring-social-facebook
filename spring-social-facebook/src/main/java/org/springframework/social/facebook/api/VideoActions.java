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
public interface VideoActions {

	/**
	 * Publishes a "video.watches" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is being watched. Must reference an OpenGraph object of type "video.movie".
	 * @return The ID for the action created.
	 */
	String watchMovie(String movieUrl);

	/**
	 * Publishes a "video.watches" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is being watched. Must reference an OpenGraph object of type "video.movie".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchMovie(String movieUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.watches" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is being watched. Must reference an OpenGraph object of type "video.tv_show".
	 * @return The ID for the action created.
	 */
	String watchTvShow(String tvShowUrl);

	/**
	 * Publishes a "video.watches" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is being watched. Must reference an OpenGraph object of type "video.tv_show".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchTvShow(String tvShowUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.watches" action for the OpenGraph TV episode object at the given URL.
	 * @param tvEpisodeUrl The URL of the TV episode that is being watched. Must reference an OpenGraph object of type "video.episode".
	 * @return The ID for the action created.
	 */
	String watchTvEpisode(String tvEpisodeUrl);

	/**
	 * Publishes a "video.watches" action for the OpenGraph TV episode object at the given URL.
	 * @param tvEpisodeUrl The URL of the TV episode that is being watched. Must reference an OpenGraph object of type "video.episode".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchTvEpisode(String tvEpisodeUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.watches" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is being watched. Must reference an OpenGraph object of type "video.episode".
	 * @return The ID for the action created.
	 */
	String watchEpisode(String episodeUrl);

	/**
	 * Publishes a "video.watches" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is being watched. Must reference an OpenGraph object of type "video.episode".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchEpisode(String episodeUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.watches" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is being watched. Must reference an OpenGraph object of type "video.other".
	 * @return The ID for the action created.
	 */
	String watchVideo(String videoUrl);

	/**
	 * Publishes a "video.watches" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is being watched. Must reference an OpenGraph object of type "video.other".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchVideo(String videoUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.watches" action for the OpenGraph video object at the given URL.
	 * Generic method for creating a "video.watches" action, allowing for future addition of video types not currently supported by Facebook.
	 * @param videoType The type of video being watched. E.g., "movie", "tv_show", "tv_episode", "episode", "other", etc.
	 * @param videoUrl The URL of the video that is being watched. Must reference an OpenGraph object of type "video.other", "video.movie", "video.tv_show", or "video.episode".
	 * @param metadata Action metadata to be applied to the action.
	 * @return The ID for the action created.
	 */
	String watchVideo(String videoType, String videoUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.rates" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is being rated. Must reference an OpenGraph object of type "video.movie".
	 * @param rating The rating given to the movie, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @return The ID for the action created.
	 */
	String rateMovie(String movieUrl, float rating, int scale);

	/**
	 * Publishes a "video.rates" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is being rated. Must reference an OpenGraph object of type "video.movie".
	 * @param rating The rating given to the movie, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateMovie(String movieUrl, float rating, int scale, ActionMetadata metadata);

	/**
	 * Publishes a "video.rates" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is being rated. Must reference an OpenGraph object of type "video.tv_show".
	 * @param rating The rating given to the movie, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @return The ID for the action created.
	 */
	String rateTvShow(String tvShowUrl, float rating, int scale);

	/**
	 * Publishes a "video.rates" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is being rated. Must reference an OpenGraph object of type "video.tv_show".
	 * @param rating The rating given to the TV show, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateTvShow(String tvShowUrl, float rating, int scale, ActionMetadata metadata);

	/**
	 * Publishes a "video.rates" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is being rated. Must reference an OpenGraph object of type "video.episode".
	 * @param rating The rating given to the movie, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @return The ID for the action created.
	 */
	String rateEpisode(String episodeUrl, float rating, int scale);

	/**
	 * Publishes a "video.rates" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is being rated. Must reference an OpenGraph object of type "video.episode".
	 * @param rating The rating given to the episode, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateEpisode(String episodeUrl, float rating, int scale, ActionMetadata metadata);

	/**
	 * Publishes a "video.rates" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is being rated. Must reference an OpenGraph object of type "video.other".
	 * @param rating The rating given to the video, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @return The ID for the action created.
	 */
	String rateVideo(String videoUrl, float rating, int scale);

	/**
	 * Publishes a "video.rates" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is being rated. Must reference an OpenGraph object of type "video.other".
	 * @param rating The rating given to the video, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateVideo(String videoUrl, float rating, int scale, ActionMetadata metadata);

	/**
	 * Publishes a "video.rates" action for the OpenGraph video object at the given URL.
	 * Generic method for creating "video.rates" action allowing for future addition of video types that aren't currently supported by Facebook.
	 * @param videoType The type of video being rated. E.g., "movie", "tv_show", "tv_episode", "episode", "other", etc.
	 * @param videoUrl The URL of the video that is being rated. Must reference an OpenGraph object of type "video.other", "video.movie", "video.tv_show", or "video.episode".
	 * @param rating The rating given to the video, relative to the scale attribute. (e.g., "{rating} out of {scale} stars")
	 * @param scale The maximum rating possible.
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link RatingActionMetadata} to apply review text and/or a review link to the rating.
	 * @return The ID for the action created.
	 */
	String rateVideo(String videoType, String videoUrl, float rating, int scale, ActionMetadata metadata);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is wanted to be watched. Must reference an OpenGraph object of type "video.movie".
	 * @return The ID for the action created.
	 */
	String wantsToWatchMovie(String movieUrl);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph movie object at the given URL.
	 * @param movieUrl The URL of the movie that is wanted to be watched. Must reference an OpenGraph object of type "video.movie".
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link WatchActionMetadata}.
	 * @return The ID for the action created.
	 */
	String wantsToWatchMovie(String movieUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is wanted to be watched. Must reference an OpenGraph object of type "video.tv_show".
	 * @return The ID for the action created.
	 */
	String wantsToWatchTvShow(String tvShowUrl);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph TV show object at the given URL.
	 * @param tvShowUrl The URL of the TV show that is wanted to be watched. Must reference an OpenGraph object of type "video.tv_show".
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link WatchActionMetadata}.
	 * @return The ID for the action created.
	 */
	String wantsToWatchTvShow(String tvShowUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is wanted to be watched. Must reference an OpenGraph object of type "video.episode".
	 * @return The ID for the action created.
	 */
	String wantsToWatchEpisode(String episodeUrl);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph episode object at the given URL.
	 * @param episodeUrl The URL of the episode that is wanted to be watched. Must reference an OpenGraph object of type "video.episode".
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link WatchActionMetadata}.
	 * @return The ID for the action created.
	 */
	String wantsToWatchEpisode(String episodeUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is wanted to be watched. Must reference an OpenGraph object of type "video.other".
	 * @return The ID for the action created.
	 */
	String wantsToWatchVideo(String videoUrl);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph video object at the given URL.
	 * @param videoUrl The URL of the video that is wanted to be watched. Must reference an OpenGraph object of type "video.other".
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link WatchActionMetadata}.
	 * @return The ID for the action created.
	 */
	String wantsToWatchVideo(String videoUrl, ActionMetadata metadata);

	/**
	 * Publishes a "video.wants_to_watch" action for the OpenGraph video object at the given URL.
	 * Generic method that allows for future addition of video types that are currently unsupported by Facebook.
	 * @param videoType The type of video that is wanted to be watched. E.g., "movie", "tv_show", "tv_episode", "episode", "other", etc.
	 * @param videoUrl The URL of the video that is wanted to be watched. Must reference an OpenGraph object of type "video.other".
	 * @param metadata Action metadata to be applied to the action. May be an instance of {@link WatchActionMetadata}.
	 * @return The ID for the action created.
	 */
	String wantsToWatch(String videoType, String videoUrl, ActionMetadata metadata);

}
