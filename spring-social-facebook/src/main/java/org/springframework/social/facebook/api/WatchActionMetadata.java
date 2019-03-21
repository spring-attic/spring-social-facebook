/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

import java.util.Date;

import org.springframework.util.MultiValueMap;

public class WatchActionMetadata extends ActionMetadata {

	private String airingId;
	
	private Date airingStartTime;
	
	private Date airingEndTime;
	
	public WatchActionMetadata() {
		super();
	}

	public WatchActionMetadata airingId(String airingId) {
		this.airingId = airingId;
		return this;
	}

	public WatchActionMetadata airingStartTime(Date airingStartTime) {
		this.airingStartTime = airingStartTime;
		return this;
	}

	public WatchActionMetadata airingEndTime(Date airingEndTime) {
		this.airingEndTime = airingEndTime;
		return this;
	}

	@Override
	public MultiValueMap<String, Object> toParameters() {
		MultiValueMap<String, Object> params = super.toParameters();
		setIfNotNull(params, "airing_id", airingId);
		setDateIfNotNull(params, "airing_start_time", airingStartTime);
		setDateIfNotNull(params, "airing_end_time", airingEndTime);
		return params;
	}
}
