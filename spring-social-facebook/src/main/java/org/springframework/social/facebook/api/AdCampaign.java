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

/**
 * Ad Campaign User object on Facebook {@link http://developers.facebook.com/docs/reference/ads-api/adcampaign/}
 * 
 * @author Will Taylor
 *
 */
public class AdCampaign {
    
    private final String accountId;
    private final String campaignId;
    private final String name;
    private final int dailyBudget;
    private final CampaignStatus campaignStatus;
    private final int dailyImpressions;
    private final String id;
    private final Date startTime;
    private final Date endTime;
    private final Date updatedTime;

    public AdCampaign(String accountId, String campaignId, String name, int dailyBudget, CampaignStatus campaignStatus, int dailyImpressions, String id, Date startTime, Date endTime, Date updatedTime) {
        this.accountId = accountId;
        this.campaignId = campaignId;
        this.name = name;
        this.dailyBudget = dailyBudget;
        this.campaignStatus = campaignStatus;
        this.dailyImpressions = dailyImpressions;
        this.id = id;
        this.startTime = Dates.copy(startTime);
        this.endTime = Dates.copy(endTime);
        this.updatedTime = Dates.copy(updatedTime);
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

    public int getDailyBudget() {
        return dailyBudget;
    }

    public CampaignStatus getCampaignStatus() {
        return campaignStatus;
    }

    public int getDailyImpressions() {
        return dailyImpressions;
    }

    public String getId() {
        return id;
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

    public enum CampaignStatus {
        UNUSED_0(0), ACTIVE(1), PAUSED(2), DELETED(3);
        private final int campaignStatus;
        private CampaignStatus(int campaignStatus){ this.campaignStatus = campaignStatus; }
        public int getCampaignStatus() { return campaignStatus; }
        public static CampaignStatus getValue(int campaignStatus) {
            CampaignStatus result = null;
            
            switch ( campaignStatus ) {
            case 1:
                result = ACTIVE;
                break;
            case 2:
                result = PAUSED;
                break;
            case 3:
                result = DELETED;
                break;
            default:
                throw new EnumConstantNotPresentException(CampaignStatus.class, String.valueOf(campaignStatus));
            }
            
            return result;
        }
    }

}