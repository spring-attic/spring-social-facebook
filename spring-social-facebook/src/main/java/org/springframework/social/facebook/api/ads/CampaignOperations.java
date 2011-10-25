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
 * @author Karthick Sankarachary
 */
public interface CampaignOperations extends ConnectionOperations {
	public List<AdCampaign> getCampaigns(String accountId);

	public List<AdCampaign> getCampaigns(List<String> campaignIds,
			MultiValueMap<String, String> vars);

	public AdCampaign getCampaign(String campaignId);

	public List<Stats> getCampaignsStats(String accountId, long startTime,
			long endTime);

	public List<Stats> getCampaignsStats(List<String> campaignIds,
			long startTime, long endTime);

	public Stats getCampaignStats(String campaignId, long startTime,
			long endTime);

	public Identifier createCampaign(String accountId, AdCampaign campaign);

	public boolean updateCampaign(String campaignId, AdCampaign campaign);

	public boolean deleteCampaign(String campaignId);

}
