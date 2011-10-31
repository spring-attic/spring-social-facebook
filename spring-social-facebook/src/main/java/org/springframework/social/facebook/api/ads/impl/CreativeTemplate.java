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
package org.springframework.social.facebook.api.ads.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.Images;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * The <code>CreativeTemplate</code> implements the
 * {@link CreativeOperations} interface} in terms of the {@link #graphApi}.
 * 
 * @author Karthick Sankarachary
 */
class CreativeTemplate extends AbstractAdsOperations implements
		CreativeOperations {

	public CreativeTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adaccount", "adcreative" };
	}

	public List<AdCreative> getCreatives(String accountId) {
		requireAuthorization();
		return getConnections(getAccountId(accountId), AdCreative.class);
	}

	public AdCreative getCreative(String creativeId) {
		requireAuthorization();
		return graphApi.fetchObject(creativeId, AdCreative.class);
	}

	public Identifier createCreative(String accountId, AdCreative creative) {
		requireAuthorization();
		String id = graphApi.publish(getAccountId(accountId), "adcreatives",
				getCreativeData(creative));
		return new Identifier(id);
	}

	public Images uploadImages(String accountId, Map<String, InputStream> images) {
		requireAuthorization();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		try {
			byte[] data = new byte[2048];
			for (Map.Entry<String, InputStream> entry : images.entrySet()) {
				String imageName = entry.getKey();
				InputStream imageStream = entry.getValue();
				try {
					ZipEntry zipEntry = new ZipEntry(imageName);
					zos.putNextEntry(zipEntry);
					int count;
					while ((count = imageStream.read(data, 0, data.length)) != -1) {
						zos.write(data, 0, count);
					}
				} catch (Exception e) {
				} finally {
					imageStream.close();
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				zos.close();
			} catch (Exception e) {

			}
		}

		final byte[] bytes = baos.toByteArray();
		if (bytes.length == 0) {
			return null;
		}
		return graphApi.uploadImage(getAccountId(accountId), "adimages", bytes,
				"image.zip", Images.class);
	}

	public boolean updateCreative(String creativeId, AdCreative creative) {
		requireAuthorization();
		return graphApi.update(creativeId, getCreativeData(creative));
	}

	public boolean deleteCreative(String creativeId) {
		requireAuthorization();
		String status = graphApi.delete(creativeId);
		return Boolean.valueOf(status);
	}

	private MultiValueMap<String, Object> getCreativeData(AdCreative creative) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", creative.getName());
		data.set("type", String.valueOf(creative.getType().getValue()));
		data.set("object_id", String.valueOf(creative.getObjectId()));
		data.set("body", creative.getBody());
		data.set("image_hash", creative.getImageHash());
		data.set("image_url", creative.getImageUrl());
		data.set("count_current_adgroups", creative.getCountCurrentAdGroups());
		data.set("title", creative.getTitle());
		data.set("run_status", creative.getRunStatus());
		data.set("link_url", creative.getLinkUrl());
		data.set("preview_url", creative.getPreviewUrl());
		data.set("related_fan_page", creative.getRelatedFanPage());
		data.set("auto_update", String.valueOf(creative.isAutoUpdate()));
		data.set("story_id", creative.getStoryId());
		return data;
	}
}
