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
package org.springframework.social.facebook.api.ads.impl;

import java.util.Date;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.impl.ConnectionTemplate;

/**
 * @author Karthick Sankarachary
 */
public abstract class AbstractAdsOperations extends ConnectionTemplate {

	public AbstractAdsOperations(GraphApi graphApi, boolean isAuthorized) {
		super(graphApi, isAuthorized);
	}

	public String getAccountId(String accountId) {
		return "act_" + accountId;
	}

	public String getPath(String... subPaths) {
		StringBuffer path = new StringBuffer();
		for (String subPath : subPaths) {
			if (path.length() > 0) {
				path.append("/");
			}
			path.append(subPath);
		}
		return path.toString();
	}

	public static String getUnixTime(long time) {
		return getUnixTime(new Date(time));
	}

	public static String getUnixTime(Date date) {
		return date != null ? String.valueOf(date.getTime() / 1000L) : "";
	}
}
