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
import org.springframework.social.facebook.api.Identifier;
import org.springframework.util.MultiValueMap;

/**
 * The <code>CampaignOperations</code> lets you perform operations on a campaign
 * for managing ads, as represented in the Graph API.
 * 
 * @see <a
 *      href="http://developers.facebook.com/docs/reference/ads-api/adcampaign/">Ad
 *      Campaign</a>
 * 
 * @author Karthick Sankarachary
 */
public interface CampaignOperations extends ConnectionOperations {
	/**
	 * Get all the campaigns of the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @return the set of {@link AdCampaign} objects
	 */
	public List<AdCampaign> getCampaigns(String accountId);

	/**
	 * Get all the campaigns of the specified ids
	 * 
	 * @param campaignIds
	 *            the list of campaign ids
	 * @param vars
	 *            the query parameters
	 * @return the set of {@link AdCampaign} objects
	 */
	public List<AdCampaign> getCampaigns(List<String> campaignIds,
			MultiValueMap<String, String> vars);

	/**
	 * Get the campaign of the specified id
	 * 
	 * @param campaignId
	 *            the campaing id
	 * @return an {@link AdCampaign} object
	 */
	public AdCampaign getCampaign(String campaignId);

	/**
	 * Get the stats for all the campaigns in the given account
	 * 
	 * @param accountId
	 *            the account id
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return a list of {@link Stats} objects
	 */
	public List<Stats> getCampaignsStats(String accountId, long startTime,
			long endTime);

	/**
	 * Get the stats for given campaign ids
	 * 
	 * @param campaignIds
	 *            the list of campaign ids
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return a list of {@link Stats} objects
	 */
	public List<Stats> getCampaignsStats(List<String> campaignIds,
			long startTime, long endTime);

	/**
	 * Get the stats for the given campaign
	 * 
	 * @param campaignId
	 *            the campaign id
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return a {@link Stats} object
	 */
	public Stats getCampaignStats(String campaignId, long startTime,
			long endTime);

	public Identifier createCampaign(String accountId, AdCampaign campaign);

	/**
	 * Update the given campaign
	 * 
	 * @param campaignId
	 *            the campaign id
	 * @param campaign
	 *            the campaign object
	 * @return true if the update succeeded
	 */
	public boolean updateCampaign(String campaignId, AdCampaign campaign);

	/**
	 * Delete the given campaign
	 * 
	 * @param campaignId
	 *            the campaign id
	 * @return true if the delete succeeded
	 */
	public boolean deleteCampaign(String campaignId);

}
