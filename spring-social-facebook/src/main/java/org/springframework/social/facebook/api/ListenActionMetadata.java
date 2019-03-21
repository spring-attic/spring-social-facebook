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

import org.springframework.util.MultiValueMap;

public class ListenActionMetadata extends ActionMetadata {
	
	private Boolean paused;
	
	private String viaUser;
	
	public ListenActionMetadata() {
		super();
	}

	public ListenActionMetadata paused(boolean paused) {
		this.paused = paused;
		return this;
	}
	
	public ListenActionMetadata via(String userId) {
		this.viaUser = userId;
		return this;
	}
	
	@Override
	public MultiValueMap<String, Object> toParameters() {
		MultiValueMap<String, Object> params = super.toParameters();
		setIfNotNull(params, "via_user", viaUser);
		setIfBooleanNotNull(params, "paused", paused);
		return params;
	}
}
