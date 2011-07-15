/*
 * Copyright 2011 the original author or authors.
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

import java.util.Date;


/**
 * Model class representing a Post with an embedded music file (e.g., MP3).
 * @author Craig Walls
 */
public class MusicPost extends Post {

	private String source;

	public MusicPost(String id, Reference from, Date createdTime, Date updatedTime) {
		super(id, from, createdTime, updatedTime);
	}
	
	public String getSource() {
		return source;
	}
}
