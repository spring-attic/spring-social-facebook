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

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.facebook.api.AdAccountGroup;
import org.springframework.social.facebook.api.AdAccountUser;

//@JsonIgnoreProperties(ignoreUnknown = true)
class AdAccountMixin {

    @JsonCreator
    AdAccountMixin(@JsonProperty("id") String id,
                   @JsonProperty("account_id") String accountId,
                   @JsonProperty("name") String name,
                   @JsonProperty("account_status") int status,
                   @JsonProperty("currency") String currency,
                   @JsonProperty("timezone_id") int timezoneId,
                   @JsonProperty("timezone_name") String timezoneName,
                   @JsonProperty("vertical_id") int verticalId,
                   @JsonProperty("vertical_name") String verticalName,
                   @JsonProperty("is_personal") int isPersonal,
                   @JsonProperty("business_name") String businessName,
                   @JsonProperty("business_street") String businessStreet,
                   @JsonProperty("business_street2") String businessStreet2,
                   @JsonProperty("business_city") String businessCity,
                   @JsonProperty("business_state") String businessState,
                   @JsonProperty("business_zip") String businessZip,
                   @JsonProperty("business_country_code") String businessCountryCode,
                   @JsonProperty("vat_status") int vatStatus,
                   @JsonProperty("daily_spend_limit") int dailySpendLimit,
                   @JsonProperty("users") List<AdAccountUser> users,
                   @JsonProperty("account_groups") List<AdAccountGroup> accountGroups,
                   @JsonProperty("capabilities") List<String> capabilities) {}

}
