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

public class GroupMembership extends FacebookObject {

	private final String id;
	
	private final String name;
	
	private final int bookmarkOrder;
	
	private final boolean administrator;
	
	private int unread;

	public GroupMembership(String id, String name, int bookmarkOrder, boolean administrator) {
		this.id = id;
		this.name = name;
		this.bookmarkOrder = bookmarkOrder;
		this.administrator = administrator;
	}
	
	/**
	 * @return the group ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the group name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the group version (always 1)
	 * @deprecated No longer maintained in the Graph API. Will always be 1
	 */
	public int getVersion() {
		return 1;
	}

	/**
	 * @return the position of the group in the user's group bookmarks (or 999999999 if not positioned)
	 */
	public int getBookmarkOrder() {
		return bookmarkOrder;
	}

	/**
	 * @return true if the user is an administrator of the group.
	 */
	public boolean isAdministrator() {
		return administrator;
	}
	
	/**
	 * @return the count of group updates that the user has not yet read.
	 */
	public int getUnread() {
		return unread;
	}
}
