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

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.facebook.api.ads.AccountStatus;
import org.springframework.social.facebook.api.ads.AdAccount;
import org.springframework.social.facebook.api.ads.AdAccountGroup;
import org.springframework.social.facebook.api.ads.User;

/**
 * A Jackson mixin for the {@link AdAccount} object.
 * 
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdAccountMixin {

	@JsonCreator
	AdAccountMixin() {
	}

	@JsonProperty("account_id")
	long accountId;

	@JsonProperty("account_status")
	@JsonDeserialize(using = AccountStatusDeserializer.class)
	AccountStatus accountStatus;

	@JsonProperty("daily_spend_limit")
	int dailySpendLimit;

	@JsonProperty("timezone_id")
	int timezoneId;

	@JsonProperty("timezone_name")
	String timezoneName;

	@JsonIgnore
	abstract List<Object> getCapabilities();

	@JsonProperty("account_groups")
	List<AdAccountGroup> accountGroups;

	@JsonProperty("users")
	@JsonDeserialize(using = UserListDeserializer.class)
	List<User> users;
}
