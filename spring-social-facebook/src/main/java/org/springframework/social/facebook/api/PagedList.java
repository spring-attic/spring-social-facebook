/*
 * Copyright 2015 the original author or authors.
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

import java.util.ArrayList;
import java.util.List;

public class PagedList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;

	private final PagingParameters previousPage;
	
	private final PagingParameters nextPage;

	private final Integer totalCount;

	public PagedList(List<T> unpagedList, PagingParameters previousPage, PagingParameters nextPage) {
		this(unpagedList, previousPage, nextPage, null);
	}
	
	public PagedList(List<T> unpagedList, PagingParameters previousPage, PagingParameters nextPage, Integer totalCount) {
		super(unpagedList);
		this.previousPage = previousPage;
		this.nextPage = nextPage;
		this.totalCount = totalCount;
	}

	public PagingParameters getPreviousPage() {
		return previousPage;
	}

	public PagingParameters getNextPage() {
		return nextPage;
	}

	/**
	 * The total number of objects. This number may be greater than the number of objects returned in a query.
	 * 
	 * For instance, when fetching a list of a user's friends, the list will only contain reference to those friends
	 * who are also using the querying application, but the total count will be the count of all of the user's friends.
	 * @return the total count. May be null if the count is unavailable.
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

}
