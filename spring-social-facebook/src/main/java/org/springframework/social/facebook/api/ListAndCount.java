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

import java.util.List;

/**
 * Contains a list of comments and a count of the total number of comments for a post or checkin.
 * This type is not intended for direct use and is not exposed by either the Post or Checkin type.
 * Instead, the comments list and count are available through Comment.getComments(), Post.getComments(), and Post.getCommentCount().
 * @author habuma
 */
public class ListAndCount<T> {
	
	private final List<T> list;
	
	private final int count;
	
	public ListAndCount(List<T> list, int count) {
		this.list = list;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public List<T> getList() {
		return list;
	}

}
