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
package org.springframework.social.facebook.api.impl.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.facebook.api.AdGroup.AdGroupStatus;
import org.springframework.social.facebook.api.AdTargeting;

//@JsonIgnoreProperties(ignoreUnknown = true)
class AdGroupMixin {

    @JsonCreator
    AdGroupMixin(@JsonProperty("ad_id") String adId,
                 @JsonProperty("id") String id,
                 @JsonProperty("account_id") String accountId,
                 @JsonProperty("campaign_id") String campaignId,
                 @JsonProperty("name") String name,
                 @JsonProperty("ad_status") int status,
                 @JsonProperty("adgroup_status") AdGroupStatus adGroupStatus,
                 @JsonProperty("bid_type") int bidType,
                 @JsonProperty("max_bid") String maxBid,
                 @JsonProperty("targeting") AdTargeting targeting,
                 @JsonProperty("creative_ids") List<String> creativeIds,
                 @JsonProperty("adgroup_id") String adGroupId,
                 @JsonProperty("start_time") Date startTime,
                 @JsonProperty("end_time") Date endTime,
                 @JsonProperty("updated_time") Date updatedTime,
                 @JsonProperty("bid_info") Map<String, String> bidInfo,
                 @JsonProperty("disapprove_reason_descriptions") List<String> disapproveReasonDescriptions) {}

}
