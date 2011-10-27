/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads;

import java.util.List;

import org.springframework.social.facebook.api.Identifier;

/**
 * The <code>SearchOperations</code> lets you search for an ad object by url,
 * and for keywords. It delegates the remote call to the generic
 * {@link org.springframework.social.facebook.api.SearchOperations} template.
 * 
 * @author Karthick Sankarachary
 * 
 */
public interface SearchOperations extends
		org.springframework.social.facebook.api.SearchOperations {
	/**
	 * Get the id of the object represented by the given url
	 * 
	 * @param url
	 *            the url of the object
	 * @return its identifier
	 */
	public Identifier getIdByUrl(String url);

	/**
	 * Get the auto-completions for the given keyword
	 * 
	 * @param keyword
	 *            the keyword
	 * @return the list of matching names
	 */
	public List<Identifier> getKeywordAutocomplete(String keyword);

	/**
	 * Get the suggestions for the given keyword(s)
	 * 
	 * @param keywords
	 *            one or more keywords
	 * @return the list of suggested names
	 */
	public List<Identifier> getKeywordSuggestions(String... keywords);

	/**
	 * Get the valid keywords for the given keywords
	 * 
	 * @param keywords
	 *            one or more keyword(s)
	 * @return the list of corresponding valid keyword objects
	 */
	public List<ValidKeyword> getValidKeywords(String... keywords);

}
