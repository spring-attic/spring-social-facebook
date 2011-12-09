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
package org.springframework.social.facebook.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.social.facebook.api.AdTargeting.EducationStatus;

/**
 * Ad Group object on Facebook {@link http://developers.facebook.com/docs/reference/ads-api/adgroup/}
 *
 * @author Will Taylor
 *
 */
public class AdGroup {

    private final String adId;
    private final String id;
    private final String accountId;
    private final String campaignId;
    private final String name;
    private final int adStatus;
    private final AdGroupStatus adGroupStatus;
    private final int bidType;
    private final String maxBid; //?? String?
    private final AdTargeting targeting;
    private final List<String> creativeIds;
    private final String adGroupId;
    private final Date startTime;
    private final Date endTime;
    private final Date updatedTime;
    private final Map<String, String> bidInfo;
    private final List<String> disapproveReasonDescriptions;

    public AdGroup(String adId, String id, String accountId, String campaignId, String name, int adStatus, AdGroupStatus adGroupStatus, int bidType, String maxBid, AdTargeting targeting, List<String> creativeIds, String adGroupId, Date startTime, Date endTime, Date updatedTime, Map<String, String> bidInfo, List<String> disapproveReasonDescriptions) {
        this.adId = adId;
        this.id = id;
        this.accountId = accountId;
        this.campaignId = campaignId;
        this.name = name;
        this.adStatus = adStatus;
        this.adGroupStatus = adGroupStatus;
        this.bidType = bidType;
        this.maxBid = maxBid;
        this.targeting = targeting;
        this.creativeIds = Collections2.immutable(creativeIds);
        this.adGroupId = adGroupId;
        this.startTime = Dates.copy(startTime);
        this.endTime = Dates.copy(endTime);
        this.updatedTime = Dates.copy(updatedTime);
        this.bidInfo = Collections2.immutable(bidInfo);
        this.disapproveReasonDescriptions = Collections2.immutable(disapproveReasonDescriptions);
    }

    public String getAdId() {
        return adId;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public int getAdStatus() {
        return adStatus;
    }

    public AdGroupStatus getAdGroupStatus() {
        return adGroupStatus;
    }

    public int getBidType() {
        return bidType;
    }

    public String getMaxBid() {
        return maxBid;
    }

    public AdTargeting getTargeting() {
        return targeting;
    }

    public List<String> getCreativeIds() {
        return Collections2.copy(creativeIds);
    }

    public String getAdGroupId() {
        return adGroupId;
    }

    public Date getStartTime() {
        return Dates.copy(startTime);
    }

    public Date getEndTime() {
        return Dates.copy(endTime);
    }

    public Date getUpdatedTime() {
        return Dates.copy(updatedTime);
    }

    public Map<String, String> getBidInfo() {
        return Collections2.copy(bidInfo);
    }

    public List<String> getDisapproveReasonDescriptions() {
        return Collections2.copy(disapproveReasonDescriptions);
    }

    public enum AdGroupStatus {
        UNUSED_0(0),
        ACTIVE(1),
        UNUSED_2(2),
        DELETED(3),
        PENDING_REVIEW(4),
        DISAPPROVED(5),
        UNUSED_6(6),
        UNUSED_7(7),
        CAMPAIGN_PAUSED(8),
        ADGROUP_PAUSED(9);
        private final int adGroupStatus;
        AdGroupStatus(int adGroupStatus) { this.adGroupStatus = adGroupStatus; }
        public int getAdGroupStatus() { return adGroupStatus; }
        public static AdGroupStatus getValue(int adGroupStatus) {
            AdGroupStatus result = null;

            switch ( adGroupStatus ) {
            case 1:
                result = ACTIVE;
                break;
            case 3:
                result = DELETED;
                break;
            case 4:
                result = PENDING_REVIEW;
                break;
            case 5:
                result = DISAPPROVED;
                break;
            case 8:
                result = CAMPAIGN_PAUSED;
                break;
            case 9:
                result = ADGROUP_PAUSED;
                break;
            default:
                throw new EnumConstantNotPresentException(EducationStatus.class, String.valueOf(adGroupStatus));
            }

            return result;
        }
    }

    // The behavior of the API isn't lining up with the documentation...I'm getting back 6
    // From the API Doc: http://developers.facebook.com/docs/reference/ads-api/adgroup/
    // bid_type, int, Use 1 for CPC and 2 for CPM; required when creating ad groups; cannot be updated
    public enum BidType {
        UNUSED_0(0),
        CPC(1),
        CPM(2);
        private final int bidType;
        BidType(int bidType) { this.bidType = bidType; }
        public int getBidType() { return bidType; }
        public static BidType getValue(int bidType) {
            BidType result = null;

            switch ( bidType ) {
            case 1:
                result = CPC;
                break;
            case 2:
                result = CPM;
                break;
            default:
                throw new EnumConstantNotPresentException(EducationStatus.class, String.valueOf(bidType));
            }

            return result;
        }
    }

}
