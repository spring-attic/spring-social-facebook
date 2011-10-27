/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads;

import java.util.Map;

/**
 * The <code>Images</code> object represents the list of image hash/url pairs
 * returned by Facebook when you upload an image (zip) file.
 * 
 * @author Karthick Sankarachary
 * 
 */
public class Images {
	private Map<String, Image> images;

	public Map<String, Image> getImages() {
		return images;
	}

	public void setImages(Map<String, Image> images) {
		this.images = images;
	}

	public static class Image {
		private String hash;
		private String url;

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
}
