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

import org.springframework.social.ApiBinding;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.client.RestOperations;


/**
 * Interface specifying a basic set of operations for interacting with Facebook.
 * Implemented by {@link FacebookTemplate}.
 * @author Craig Walls
 */
public interface Facebook extends GraphApi, ApiBinding {
	
	/**
	 * API for reading and posting comments.
	 */
	CommentOperations commentOperations();

	/**
	 * API for performing operations on events.
	 */
	EventOperations eventOperations();
	
	/**
	 * API for performing operations on feeds.
	 */
	FeedOperations feedOperations();

	/**
	 * API for performing Facebook Query Language (FQL) queries.
	 */
	FqlOperations fqlOperations();
	
	/**
	 * API for performing operations with a user's set of friends.
	 */
	FriendOperations friendOperations();
	
	/**
	 * API for performing operations on groups.
	 */
	GroupOperations groupOperations();

	/**
	 * API for performing operations against user likes and interests.
	 */
	LikeOperations likeOperations();
	
	/**
	 * API for performing operations on albums, photos, and videos.
	 */
	MediaOperations mediaOperations();
	
	/**
	 * API for working with OpenGraph actions.
	 */
	OpenGraphOperations openGraphOperations();
	
	/**
	 * API for working with Facebook pages.
	 */
	PageOperations pageOperations();
	
	/**
	 * API for performing Facebook Places checkin operations.
	 */
	PlacesOperations placesOperations();
	
	/**
	 * API for performing question operations.
	 */
	QuestionOperations questionOperations();
	
	/**
	 * API for performing operations on Facebook user profiles.
	 */
	UserOperations userOperations();
	
	/**
	 * Returns the underlying {@link RestOperations} object allowing for consumption of Facebook endpoints that may not be otherwise covered by the API binding.
	 * The RestOperations object returned is configured to include an OAuth 2 "Authorization" header on all requests.
	 */
	RestOperations restOperations();
	
	/**
	 * @return the application namespace that this FacebookTemplate was created for. May be null if no namespace was given.
	 */
	String getApplicationNamespace();

}
