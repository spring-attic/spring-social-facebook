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

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.Identifier;

/**
 * The <code>CreativeOperations</code> lets you perform operations on a creative
 * that can be used in ads, as represented in the Graph API.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adcreative/">Ad
 *      Creative</a>
 * 
 * @author Karthick Sankarachary
 */
public interface CreativeOperations {
	/**
	 * Get all the creatives for the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @return a list of {@link AdCreative}s
	 */
	public List<AdCreative> getCreatives(String accountId);

	/**
	 * Get the creative of the specified id
	 * 
	 * @param creativeId
	 *            the creative id
	 * @return an {@link AdCreative} object
	 */
	public AdCreative getCreative(String creativeId);

	/**
	 * Create a creative in the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @param creative
	 *            the creative object
	 * @return the identifer of the created creative
	 */
	public Identifier createCreative(String accountId, AdCreative creative);

	/**
	 * Upload a set of images to the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @param images
	 *            a map of file name to input stream
	 * @return the hash/url combinations for each file uploaded
	 */
	public Images uploadImages(String accountId, Map<String, InputStream> images);

	/**
	 * Update the given creative
	 * 
	 * @param creativeId
	 *            the creative id
	 * @param creative
	 *            the creative object
	 * @return true if the update succeeded
	 */
	public boolean updateCreative(String creativeId, AdCreative creative);

	/**
	 * Delete the given creative
	 * 
	 * @param creativeId
	 *            the creative id
	 * @return true if the delete succeeded
	 */
	public boolean deleteCreative(String creativeId);

}
