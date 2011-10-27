/*
 * Copyright 2011 the original author or authors.
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

import java.util.List;

import org.springframework.util.MultiValueMap;

/**
 * The <code>SearchOperations</code> interface lets you run search queries for a
 * particular type of object.
 * 
 * @author Karthick Sankarachary
 */
public interface SearchOperations {
	/**
	 * Search for a type of object using the given query.
	 * 
	 * @param type
	 *            the type of search object
	 * @param query
	 *            the search query (i.e., the "q" parameter)
	 * @param itemType
	 *            the type of the individual search result
	 * @return a list of search result items
	 */
	public <T> List<T> search(String type, String query, Class<T> itemType);

	/**
	 * 
	 * @param type
	 *            the type of search object
	 * @param query
	 *            the search query (i.e., the "q" parameter)
	 * @param vars
	 *            a map of query parameters
	 * @param itemType
	 *            the type of the individual search result
	 * @return a list of search result items
	 */
	public <T> List<T> search(String type, String query,
			MultiValueMap<String, String> vars, Class<T> itemType);
}
