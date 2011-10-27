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
 * The <code>AdGroupOperations</code> lets you perform operations on an ad on
 * Facebook , as represented in the Graph API.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adgroup/">Ad
 *      Group</a>
 * 
 * @author Karthick Sankarachary
 */
public interface AdGroupOperations {
	/**
	 * Get all the ad groups in the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @return a list of {@link AdGroup} objects
	 */
	public List<AdGroup> getAdGroups(String accountId);

	/**
	 * Get the ad group of the given id
	 * 
	 * @param adGroupId
	 *            the ad group id
	 * @return the {@link AdGroup} object
	 */
	public AdGroup getAdGroup(String adGroupId);

	/**
	 * Get all the ad groups of the given ids
	 * 
	 * @param adGroupIds
	 *            a list of ad group ids
	 * @return a list of {@link AdGroup} objects
	 */
	public List<AdGroup> getAdGroups(List<String> adGroupIds);

	/**
	 * Get the ad groups for the given campaign
	 * 
	 * @param campaignId
	 *            the campaign id
	 * @return a list of {@link AdGroup} objects
	 */
	public List<AdGroup> getCampaignAdGroups(String campaignId);

	/**
	 * Create an ad group in the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @param adGroup
	 *            the ad group
	 * @return the identifier of the created ad group
	 */
	public Identifier createAdGroup(String accountId, AdGroup adGroup);

	/**
	 * Update the given ad group
	 * 
	 * @param adGroupId
	 *            the ad group id
	 * @param adGroup
	 *            the ad group
	 * @return true if the update succeeded
	 */
	public boolean updateAdGroup(String adGroupId, AdGroup adGroup);

	/**
	 * Delete the ad group of the given id
	 * 
	 * @param adGroupId
	 *            the ad group id
	 * @return true if the delete succeeded
	 */
	public boolean deleteAdGroup(String adGroupId);

	/**
	 * Get the stats for the given ad group
	 * 
	 * @param adGroupId
	 *            the ad group id
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return the {@link Stats} object
	 */
	public Stats getAdGroupStats(String adGroupId, long startTime, long endTime);

	/**
	 * Get the stats for the given set of ad group ids
	 * 
	 * @param adGroupIds
	 *            the list of ad group ids
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return the list of {@link Stats} objects
	 */
	public List<Stats> getAdGroupsStats(List<String> adGroupIds,
			long startTime, long endTime);

	/**
	 * Get stats for all the ad groups in the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return the list of {@link Stats} objects
	 */
	public List<Stats> getAdGroupsStats(String accountId, long startTime,
			long endTime);

	/**
	 * Get the keyword stats for the given ad group
	 * 
	 * @param adGroupId
	 *            the ad group id
	 * @return a {@link Stats} object
	 */
	public Stats getAdGroupKeywordStats(String adGroupId);
}
