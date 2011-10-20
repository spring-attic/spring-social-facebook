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

/**
 * @author Karthick Sankarachary
 */
public interface AdGroupOperations {

	public List<AdGroup> getAdGroups(String accountId);

	public AdGroup getAdGroup(String adGroupId);

	public List<AdGroup> getCampaignAdGroups(String campaignId);

	public Id createAdGroup(String accountId, AdGroup adGroup);

	public boolean updateAdGroup(String adGroupId, AdGroup adGroup);

	public boolean deleteAdGroup(String adGroupId);

}
