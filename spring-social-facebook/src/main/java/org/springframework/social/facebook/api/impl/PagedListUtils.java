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
package org.springframework.social.facebook.api.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;

public class PagedListUtils {

	public static PagingParameters getPagedListParameters(JsonNode pagingNode, String pageKey) {
		if (pagingNode == null || pagingNode.get(pageKey) == null) {
			return null;
		}
		String pageNode = pagingNode.get(pageKey).textValue();
		String limitString = extractParameterValueFromUrl(pageNode, "limit");
		String sinceString = extractParameterValueFromUrl(pageNode, "since");
		String untilString = extractParameterValueFromUrl(pageNode, "until");
		String offsetString = extractParameterValueFromUrl(pageNode, "offset");
		String after = extractEncodedParameterValueFromUrl(pageNode, "after");
		String before = extractEncodedParameterValueFromUrl(pageNode, "before");
		String pagingToken = extractEncodedParameterValueFromUrl(pageNode, "__paging_token");
		
		return new PagingParameters(
				limitString != null ? Integer.valueOf(limitString) : null, 
				offsetString != null ? Integer.valueOf(offsetString) : null,
				sinceString != null ? Long.valueOf(sinceString) : null, 
				untilString != null ? Long.valueOf(untilString) : null,
				after, before, pagingToken, pageNode);
	}
	
	public static MultiValueMap<String, String> getPagingParameters(PagingParameters pagedListParameters) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		if (pagedListParameters.getOffset() != null) {
			parameters.add("offset", String.valueOf(pagedListParameters.getOffset()));
		}
		if (pagedListParameters.getLimit() != null) {
			parameters.add("limit", String.valueOf(pagedListParameters.getLimit()));
		}
		if (pagedListParameters.getSince() != null) {
			parameters.add("since", String.valueOf(pagedListParameters.getSince()));
		}
		if (pagedListParameters.getUntil() != null) {
			parameters.add("until", String.valueOf(pagedListParameters.getUntil()));
		}
		if (pagedListParameters.getBefore() != null) {
			parameters.add("before", String.valueOf(pagedListParameters.getBefore()));
		}
		if (pagedListParameters.getAfter() != null) {
			parameters.add("after", String.valueOf(pagedListParameters.getAfter()));
		}
		return parameters;
	}

	private static String extractEncodedParameterValueFromUrl(String url, String paramName) {
		try {
			String value = extractParameterValueFromUrl(url, paramName);
			return value != null ? URLDecoder.decode(value, "UTF-8") : null;
		} catch (UnsupportedEncodingException e) {
			// shouldn't happen
			return null;
		}
	}
	
	private static String extractParameterValueFromUrl(String url, String paramName) {
		int queryStart = url.indexOf('?') >= 0 ? url.indexOf('?') : 0;
		int startPos = url.indexOf(paramName + "=", queryStart);
		if (startPos == -1) {
			return null;
		}
		int ampPos = url.indexOf("&", startPos);
		if (ampPos >= 0) {
			return url.substring(startPos + paramName.length() + 1, ampPos);
		}
		return url.substring(startPos + paramName.length() + 1);
	}

}
