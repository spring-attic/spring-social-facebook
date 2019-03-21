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

/**
 * Model class representing a user's or a page's cover photo.
 * @author Craig Walls
 */
public class CoverPhoto extends FacebookObject {
	
	private String id;
	
	private int offsetX;
	
	private int offsetY;

	private String source;
	
	/**
	 * @return The ID of the cover photo's Photo object.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return A link to the cover photo's image.
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return The percentage of offset from left (0-100).
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * @return The percentage of offset from top (0-100).
	 */
	public int getOffsetY() {
		return offsetY;
	}

}
