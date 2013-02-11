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
package org.springframework.social.facebook.api.impl;

import org.codehaus.jackson.JsonNode;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class PagedListUtils {

	public static PagingParameters getPagedListParameters(JsonNode pagingNode, String pageKey) {
		if (pagingNode == null || pagingNode.get(pageKey) == null) {
			return null;
		}
		String pageNode = pagingNode.get(pageKey).getTextValue();
		String limitString = extractParameterValueFromUrl(pageNode, "limit");
		String sinceString = extractParameterValueFromUrl(pageNode, "since");
		String untilString = extractParameterValueFromUrl(pageNode, "until");
		String offsetString = extractParameterValueFromUrl(pageNode, "offset");
		return new PagingParameters(
				offsetString != null ? Integer.valueOf(offsetString) : null,
				limitString != null ? Integer.valueOf(limitString) : null, 
				sinceString != null ? Long.valueOf(sinceString) : null, 
				untilString != null ? Long.valueOf(untilString) : null);
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
		return parameters;
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
