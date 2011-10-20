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

/**
 * @author Karthick Sankarachary
 */
public interface AccountOperations extends ConnectionOperations {
	public AdAccount getAccount(String accountId);

	public List<User> getAccountUsers(String accountId);

	public Stats getAccountStats(String accountId);

	public <T> List<Stats> getAccountConnectionStats(String accountId,
			Class<T> connectionType);

	public long getReachEstimate(String accountId, String currency,
			Targeting targetingSpec);
}
