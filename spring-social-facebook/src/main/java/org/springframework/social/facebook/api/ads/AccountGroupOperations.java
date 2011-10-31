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

import org.springframework.social.facebook.api.ConnectionOperations;
import org.springframework.social.facebook.api.Identifier;

/**
 * The <code>AccountGroupOperations</code> lets you perform operations on a
 * group for managing access to Facebook ad accounts, as represented in the
 * Graph API.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adaccountgroup/">Ad
 *      Account Group</a>
 * 
 * @author Karthick Sankarachary
 */
public interface AccountGroupOperations extends ConnectionOperations {
	/**
	 * Get the ad account group of the given id
	 * 
	 * @param accountGroupId
	 *            the ad group id
	 * @return the {@link AdAccountGroup} object
	 */
	public AdAccountGroup getAccountGroup(String accountGroupId);

	/**
	 * Create an ad account group
	 * 
	 * @param accountGroup
	 *            the ad account group
	 * @return the identified of the created ad account group
	 */
	public Identifier createAccountGroup(AdAccountGroup accountGroup);

	/**
	 * Update the given ad account group
	 * 
	 * @param accountGroupId
	 *            the account group id
	 * @param accountGroup
	 *            the account group
	 * @return true if the update succeeded
	 */
	public boolean updateAccountGroup(String accountGroupId,
			AdAccountGroup accountGroup);

	/**
	 * Delete the given ad group
	 * 
	 * @param accountGroupId
	 *            the ad group id
	 * @return true if the delete succeeded
	 */
	public boolean deleteAccountGroup(String accountGroupId);
}
