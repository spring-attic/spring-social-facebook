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

import java.util.List;

/**
 * Ad account object on Facebook {@link http://developers.facebook.com/docs/reference/ads-api/adaccount/}
 *
 * @author Will Taylor
 *
 */
public class AdAccount {

    private final String id;
    private final String accountId;
    private final String name;
    private final int status;
    private final String currency;
    private final int timezoneId;
    private final String timezoneName;
    private final int verticalId;
    private final String verticalName;
    private final int isPersonal;
    private final String businessName;
    private final String businessStreet;
    private final String businessStreet2;
    private final String businessCity;
    private final String businessState;
    private final String businessZip;
    private final String businessCountryCode;
    private final int vatStatus;
    private final int dailySpendLimit;
    private final List<AdAccountUser> users;
    private final List<AdAccountGroup> accountGroups;
    private final List<String> capabilities; // marked as "reserved for future use"

    public AdAccount(String id, String accountId, String name, int status, String currency, int timezoneId, String timezoneName, int verticalId, String verticalName, int isPersonal, String businessName, String businessStreet, String businessStreet2, String businessCity, String businessState, String businessZip, String businessCountryCode, int vatStatus, int dailySpendLimit, List<AdAccountUser> users, List<AdAccountGroup> accountGroups, List<String> capabilities) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.status = status;
        this.currency = currency;
        this.timezoneId = timezoneId;
        this.timezoneName = timezoneName;
        this.verticalId = verticalId;
        this.verticalName = verticalName;
        this.isPersonal = isPersonal;
        this.businessName = businessName;
        this.businessStreet = businessStreet;
        this.businessStreet2 = businessStreet2;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessZip = businessZip;
        this.businessCountryCode = businessCountryCode;
        this.vatStatus = vatStatus;
        this.dailySpendLimit = dailySpendLimit;
        this.users = Collections2.immutable(users);
        this.accountGroups = Collections2.immutable(accountGroups);
        this.capabilities = Collections2.immutable(capabilities);
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getCurrency() {
        return currency;
    }

    public int getTimezoneId() {
        return timezoneId;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public int getVerticalId() {
        return verticalId;
    }

    public String getVerticalName() {
        return verticalName;
    }

    public int getIsPersonal() {
        return isPersonal;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessStreet() {
        return businessStreet;
    }

    public String getBusinessStreet2() {
        return businessStreet2;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public String getBusinessZip() {
        return businessZip;
    }

    public String getBusinessCountryCode() {
        return businessCountryCode;
    }

    public int getVatStatus() {
        return vatStatus;
    }

    public int getDailySpendLimit() {
        return dailySpendLimit;
    }

    public List<AdAccountUser> getUsers() {
        return Collections2.copy(users);
    }

    public List<AdAccountGroup> getAccountGroups() {
        return Collections2.copy(accountGroups);
    }

    public List<String> getCapabilities() {
        return Collections2.copy(capabilities);
    }

}