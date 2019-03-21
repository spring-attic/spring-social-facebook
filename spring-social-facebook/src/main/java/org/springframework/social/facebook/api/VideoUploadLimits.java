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
 * Domain type describing limits on the time length and size of videos that can be uploaded.
 * @author Craig Walls
 */
public class VideoUploadLimits extends FacebookObject {

	private final long length;
	
	private final long size;

	public VideoUploadLimits(long length, long size) {
		this.length = length;
		this.size = size;
	}
	
	public long getLength() {
		return length;
	}
	
	public long getSize() {
		return size;
	}
	
}
