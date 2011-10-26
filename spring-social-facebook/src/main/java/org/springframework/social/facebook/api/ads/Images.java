package org.springframework.social.facebook.api.ads;

import java.util.Map;

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
