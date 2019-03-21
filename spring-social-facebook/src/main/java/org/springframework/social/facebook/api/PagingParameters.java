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

import java.io.Serializable;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Carries parameters to describe a paged set of results.
 * @author Craig Walls
 */
@SuppressWarnings("serial")
public class PagingParameters implements Serializable {
	
	private final Integer limit;

	private final Integer offset;

	private final Long since;
	
	private final Long until;

	private final String after;

	private final String before;
	
	private final String pagingToken;

	private final String fullUrl;

	/**
	 * Constructs a PagedListParameters.
	 * @param limit The number of items to limit the list to.
	 * @param offset The offset into the full result list to start this list at.
	 * @param since The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until) {
		this(limit, offset, since, until, null, null, null, null);
	}

	/**
	 * Constructs a PagedListParameters.
	 * @param limit The number of items to limit the list to.
	 * @param offset The offset into the full result list to start this list at.
	 * @param since The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param after A cursor token that points to the end of the page being returned. 
	 * @param before A cursor token that points to the start of the page being returned. 
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until, String after, String before) {
		this(limit, offset, since, until, after, before, null, null);
	}
	
	/**
	 * Constructs a PagedListParameters.
	 * @param limit The number of items to limit the list to.
	 * @param offset The offset into the full result list to start this list at.
	 * @param since The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param after A cursor token that points to the end of the page being returned. 
	 * @param before A cursor token that points to the start of the page being returned. 
	 * @param pagingToken A page token. This is undocumented by Facebook and its purpose is unclear, 
	 *                    but if it's available, it helps prevent the last item in a page of results 
	 *                    from appearing as the first item on the next page.
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until, String after, String before, String pagingToken) {
		this(limit, offset, since, until, after, before, pagingToken, null);
	}

	/**
	 * Constructs a PagedListParameters.
	 * @param limit       The number of items to limit the list to.
	 * @param offset      The offset into the full result list to start this list at.
	 * @param since       The beginning timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param until       The ending timestamp bound for time-sensitive content (e.g., posts, comments, etc).
	 * @param after       A cursor token that points to the end of the page being returned.
	 * @param before      A cursor token that points to the start of the page being returned.
	 * @param pagingToken A page token. This is undocumented by Facebook and its purpose is unclear,
	 *                    but if it's available, it helps prevent the last item in a page of results
	 *                    from appearing as the first item on the next page.
	 * @param fullUrl     The full url to the next or previous page.
	 */
	public PagingParameters(Integer limit, Integer offset, Long since, Long until, String after, String before, String pagingToken, String fullUrl) {
		this.limit = limit;
		this.offset = offset;
		this.since = since;
		this.until = until;
		this.after = after;
		this.before = before;
		this.pagingToken = pagingToken;
		this.fullUrl = fullUrl;
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
	
	public String getPagingToken() {
		return pagingToken;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (limit != null) { map.set("limit", String.valueOf(limit)); }
		if (offset != null) { map.set("offset", String.valueOf(offset)); }
		if (since != null) { map.set("since", String.valueOf(since)); }
		if (until != null) { map.set("until", String.valueOf(until)); }
		if (after != null) { map.set("after", after); }
		if (before != null) { map.set("before", before); }
		if (pagingToken != null) { map.set("__paging_token", pagingToken); }
		return map;
	}
	
}
