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

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * An object that captures data used to update a Page.
 * @author Craig Walls
 */
public class PageUpdate {

	private final String pageId;
	
	private String about;
	
	private String cover;
	
	private String name;
	
	private Integer offsetX;
	
	private Integer offsetY;

	private Float zoomScaleX;

	private Float zoomScaleY;

	private Float focusX;

	private Float focusY;
	
	/**
	 * Creates a new {@link PageUpdate}.
	 * @param pageId The ID of the page to update.
	 */
	public PageUpdate(String pageId) {
		this.pageId = pageId;
	}
	
	public String getPageId() {
		return pageId;
	}
	
	public PageUpdate about(String about) {
		this.about = about;
		return this;
	}
	
	public PageUpdate cover(String cover, Integer offsetX, Integer offsetY, Float zoomScaleX, Float zoomScaleY, Float focusX, Float focusY) {
		this.cover = cover;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.zoomScaleX = zoomScaleX;
		this.zoomScaleY = zoomScaleY;
		this.focusX = focusX;
		this.focusY = focusY;
		return this;
	}
	
	public PageUpdate name(String name) {
		this.name = name;
		return this;
	}
	
	public MultiValueMap<String, Object> toRequestParameters() {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		if (about != null) { parameters.add("about", about); }
		if (cover != null) { parameters.add("cover", cover); }
		if (name != null) { parameters.add("name", name); }
		if (offsetX != null) { parameters.add("offset_x", offsetX.toString()); }
		if (offsetY != null) { parameters.add("offset_y", offsetY.toString()); }
		if (zoomScaleX != null) { parameters.add("zoom_scale_x", zoomScaleX.toString()); }
		if (zoomScaleY != null) { parameters.add("zoom_scale_y", zoomScaleY.toString()); }
		if (focusX != null) { parameters.add("focus_x", focusX.toString()); }
		if (focusY != null) { parameters.add("focus_y", focusY.toString()); }
		return parameters;
	}

}
