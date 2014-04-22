/*
 * Copyright 2013 the original author or authors.
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
import java.util.List;


/**
 * Model class representing a photo.
 * @author Craig Walls
 */
public class Photo extends FacebookObject {
	private String id;
	
	private String name;
	
	private Reference from;
	
	private String picture;
	
	private String source;
	
	private String link;
	
	private String icon;
	
	private int position;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private List<Tag> tags;
	
	private List<Image> images;
	
	private Photo(String id, Reference from, String picture, String source, String link, String icon, Date createdTime, List<Image> images) {
		this.id = id;
		this.from = from;
		this.picture = picture;
		this.source = source;
		this.link = link;
		this.icon = icon;
		this.createdTime = createdTime;
		this.images = images;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Reference getFrom() {
		return from;
	}

	public String getPicture() {
		return picture;
	}

	public String getSource() {
		return source;
	}

	public String getLink() {
		return link;
	}

	public String getIcon() {
		return icon;
	}

	/**
	 * The position of the photo in a list of photos.
	 * @deprecated On September 5, 2012, Facebook will either stop returning a position property on Photo objects or will only return 0. This method will be removed in Spring Social Facebook 1.1.0.
	 */
	@Deprecated
	public Integer getPosition() {
		return position;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	/**
	 * @return A list of all Image objects for this photo, ordered largest to smallest.
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * <p>The Image object for the oversized image.</p>
	 * 
	 * <p>Note that as of the July 2013 breaking changes in Facebook's API, Facebook will no longer return images larger than the originally uploaded image. Therefore,
	 * there is no way of knowing which index the oversized image is at, if it exists at all. Consequently, this method has been deprecated and will be removed in
	 * Spring Social Facebook 1.1.0. Until then, it will return the image at index 0 in the images list, which may or may not be the source image.</p>
	 * 
	 * @return the 0th image in the images list, previously assumed to be the tiny image. null if there is no 0th image. 
	 * @deprecated With July 2013 breaking changes, Facebook will no longer return images larger than the original image. Therefore, there's no way of knowing what sized images will or won't be in the image list. 
	 */
	@Deprecated
	public Image getOversizedImage() {
		return images.size() > 0 ? images.get(0) : null;
	}
	
	/**
	 * <p>The Image object for the source image.</p>
	 * 
	 * <p>Note that as of the July 2013 breaking changes in Facebook's API, Facebook will no longer return images larger than the originally uploaded image. Therefore,
	 * there is no way of knowing which index the source image is at, if it exists at all. Consequently, this method has been deprecated and will be removed in
	 * Spring Social Facebook 1.1.0. Until then, it will return the image at index 1 in the images list, which may or may not be the source image.</p>
	 * 
	 * @return the 1th image in the images list, previously assumed to be the tiny image. null if there is no 1th image. 
	 * @deprecated With July 2013 breaking changes, Facebook will no longer return images larger than the original image. Therefore, there's no way of knowing what sized images will or won't be in the image list.
	 */
	@Deprecated
	public Image getSourceImage() {
		return images.size() > 1 ? images.get(1) : null;
	}
	
	/**
	 * <p>The Image object for the small image.</p>
	 * 
	 * <p>Note that as of the July 2013 breaking changes in Facebook's API, Facebook will no longer return images larger than the originally uploaded image. Therefore,
	 * there is no way of knowing which index the small image is at, if it exists at all. Consequently, this method has been deprecated and will be removed in
	 * Spring Social Facebook 1.1.0. Until then, it will return the image at index 6 in the images list, which may or may not be the small image.</p>
	 * 
	 * @return the 6th image in the images list, previously assumed to be the tiny image. null if there is no 6th image. 
	 * @deprecated With July 2013 breaking changes, Facebook will no longer return images larger than the original image. Therefore, there's no way of knowing what sized images will or won't be in the image list.
	 */
	@Deprecated
	public Image getSmallImage() {
		return images.size() > 6 ? images.get(6) : null;
	}
	
	/**
	 * <p>The Image object for the album image.</p>
	 * 
	 * <p>Note that as of the July 2013 breaking changes in Facebook's API, Facebook will no longer return images larger than the originally uploaded image. Therefore,
	 * there is no way of knowing which index the album image is at, if it exists at all. Consequently, this method has been deprecated and will be removed in
	 * Spring Social Facebook 1.1.0. Until then, it will return the image at index 5 in the images list, which may or may not be the album image.</p>
	 * 
	 * @return the 5th image in the images list, previously assumed to be the tiny image. null if there is no 5th image. 
	 * @deprecated With July 2013 breaking changes, Facebook will no longer return images larger than the original image. Therefore, there's no way of knowing what sized images will or won't be in the image list.
	 */
	@Deprecated
	public Image getAlbumImage() {
		return images.size() > 5 ? images.get(5) : null;
	}
	
	/**
	 * <p>The Image object for the tiny image.</p>
	 * 
	 * <p>Note that as of the July 2013 breaking changes in Facebook's API, Facebook will no longer return images larger than the originally uploaded image. Therefore,
	 * there is no way of knowing which index the tiny image is at, if it exists at all. Consequently, this method has been deprecated and will be removed in
	 * Spring Social Facebook 1.1.0. Until then, it will return the image at index 7 in the images list, which may or may not be the tiny image.</p>
	 * 
	 * @return the 7th image in the images list, previously assumed to be the tiny image. null if there is no 7th image. 
	 * @deprecated With July 2013 breaking changes, Facebook will no longer return images larger than the original image. Therefore, there's no way of knowing what sized images will or won't be in the image list.
	 */
	@Deprecated
	public Image getTinyImage() {
		
		return images.size() > 7 ? images.get(7) : null;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public static class Image {
		
		private final int width;
		
		private final int height;
		
		private final String source;
		
		public Image(String source, int width, int height) {
			this.source = source;
			this.width = width;
			this.height = height;
			
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public String getSource() {
			return source;
		}
	}

}
