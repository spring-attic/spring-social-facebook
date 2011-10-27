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

import org.springframework.social.facebook.api.ConnectionOperations;

/**
 * The <code>AccountOperations</code> lets you perform operations on an account
 * for managing ads on Facebook , as represented in the Graph API.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adaccount/">Ad
 *      Account</a>
 * 
 * @author Karthick Sankarachary
 */
public interface AccountOperations extends ConnectionOperations {
	/**
	 * Get all the accounts for the given user
	 * 
	 * @param userId
	 *            the user id
	 * @return the list of {@link AdAccount} objects
	 */
	public List<AdAccount> getAccounts(String userId);

	/**
	 * Get the account of the given id
	 * 
	 * @param accountId
	 *            the account id
	 * @return the {@link AdAccount} object
	 */
	public AdAccount getAccount(String accountId);

	/**
	 * Get the users for the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @return the list of {@link User} objects
	 */
	public List<User> getAccountUsers(String accountId);

	/**
	 * Get the stats for the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @return the {@link Stats} object
	 */
	public AccountStats getAccountStats(String accountId);

	/**
	 * Get the connection stats for the given account and connection type
	 * 
	 * @param accountId
	 *            the account id
	 * @param connectionType
	 *            the connection type
	 * @return the list of {@link Stats} objects
	 */
	public <T> List<Stats> getAccountConnectionStats(String accountId,
			Class<T> connectionType);

	/**
	 * Get the reach estimate for the given account and targeting spec
	 * 
	 * @param accountId
	 *            the account id
	 * @param currency
	 *            the currency format to use
	 * @param targetingSpec
	 *            the {@link Targeting} object
	 * @return the reach estimate
	 */
	public ReachEstimate getReachEstimate(String accountId, String currency,
			Targeting targetingSpec);
}
