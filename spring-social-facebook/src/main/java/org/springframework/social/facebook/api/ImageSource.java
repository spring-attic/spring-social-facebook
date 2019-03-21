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
 * A domain type representing an image source.
 * 
 * @author Craig Walls
 * @since 2.0
 */
public class ImageSource {

	private final String source;
	
	private final int height;
	
	private final int width;
	
	public ImageSource(String source, int height, int width) {
		this.source = source;
		this.height = height;
		this.width = width;
	}
	
	/**
	 * @return a URL to the image source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the image height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return the image width
	 */
	public int getWidth() {
		return width;
	}
	
}
