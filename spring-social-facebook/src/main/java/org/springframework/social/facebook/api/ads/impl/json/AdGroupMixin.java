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
package org.springframework.social.facebook.api.ads.impl.json;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.deser.std.DateDeserializer;
import org.springframework.social.facebook.api.ads.AdGroup;
import org.springframework.social.facebook.api.ads.BidInfo;
import org.springframework.social.facebook.api.ads.BidType;

/**
 * A Jackson mixin for the {@link AdGroup} object.
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdGroupMixin {

	@JsonCreator
	AdGroupMixin() {
	}

	@JsonProperty("ad_id")
	long adId;
	
	@JsonProperty("campaign_id")
	long campaignId;

	@JsonProperty("adgroup_status")
	int adGroupStatus;
	
	@JsonProperty("bid_type")
	@JsonDeserialize(using=BidTypeDeserializer.class)
	BidType bidType;
	
	@JsonProperty("max_bid")
	String maxBid;
	
	@JsonProperty("adgroup_id")
	long adGroupid;
	
	@JsonProperty("end_time")
	@JsonDeserialize(using=DateDeserializer.class)
	Date endTime;
	
	@JsonProperty("start_time")
	@JsonDeserialize(using=DateDeserializer.class)
	Date startTime;
	
	@JsonProperty("updated_time")
	@JsonDeserialize(using=DateDeserializer.class)
	Date updatedTime;
	
	@JsonProperty("bid_info")
	BidInfo bidInfo;
	
	@JsonProperty("disapprove_reason_descriptions")
	List<String> disapproveReasonDescriptions;
	
}
