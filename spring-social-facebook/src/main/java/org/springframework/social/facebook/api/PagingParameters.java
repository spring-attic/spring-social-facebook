/*
 * Copyright 2013 the original author or authors.
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

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Carries parameters to describe a paged set of results.
 * @author Craig Walls
 */
public class PagingParameters {
	
	private final Integer limit;

	private final Integer offset;

	private final Long since;
	
	private final Long until;

	private final String after;

	private final String before;

	/**
	 * Constructs a PagedListParameters.
	 * @param limit The number of items to limit the list to.
	 * @param offset The offset into the full result list to start this list at.
	 * @param since The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until) {
		this(limit, offset, since, until, null, null);
	}
	
	public PagingParameters(Integer limit, Integer offset, Long since, Long until, String after, String before) {
		this.limit = limit;
		this.offset = offset;
		this.since = since;
		this.until = until;
		this.after = after;
		this.before = before;
	}
	
	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}
	
	public Long getSince() {
		return since;
	}

	public Long getUntil() {
		return until;
	}
	
	public String getAfter() {
		return after;
	}
	
	public String getBefore() {
		return before;
	}
	
	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (limit != null) { map.set("limit", String.valueOf(limit)); }
		if (offset != null) { map.set("offset", String.valueOf(offset)); }
		if (since != null) { map.set("since", String.valueOf(since)); }
		if (until != null) { map.set("until", String.valueOf(until)); }
		if (after != null) { map.set("after", after); }
		if (before != null) { map.set("before", before); }
		return map;
	}
	
}
