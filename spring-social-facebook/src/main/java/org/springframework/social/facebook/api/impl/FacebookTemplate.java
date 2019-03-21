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

import static org.springframework.social.facebook.api.impl.PagedListUtils.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.AchievementOperations;
import org.springframework.social.facebook.api.CommentOperations;
import org.springframework.social.facebook.api.EventOperations;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.GroupOperations;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.MediaOperations;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.SocialContextOperations;
import org.springframework.social.facebook.api.TestUserOperations;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.json.FacebookModule;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * <p>This is the central class for interacting with Facebook.</p>
 * <p>
 * There are some operations, such as searching, that do not require OAuth
 * authentication. In those cases, you may use a {@link FacebookTemplate} that is
 * created through the default constructor and without any OAuth details.
 * Attempts to perform secured operations through such an instance, however,
 * will result in {@link NotAuthorizedException} being thrown.
 * </p>
 * @author Craig Walls
 */
public class FacebookTemplate extends AbstractOAuth2ApiBinding implements Facebook {

	private String appId;
	
	private AchievementOperations achievementOperations;
	
	private UserOperations userOperations;
	
	private FriendOperations friendOperations;
	
	private FeedOperations feedOperations;
	
	private GroupOperations groupOperations;
	
	private CommentOperations commentOperations;
	
	private LikeOperations likeOperations;
	
	private EventOperations eventOperations;
	
	private MediaOperations mediaOperations;
	
	private PageOperations pageOperations;
	
	private OpenGraphOperations openGraphOperations;
	
	private SocialContextOperations socialContextOperations;
	
	private TestUserOperations testUserOperations;
	
	private ObjectMapper objectMapper;

	private String applicationNamespace;

	private String apiVersion = "2.8";
	
	/**
	 * Create a new instance of FacebookTemplate.
	 * This constructor creates the FacebookTemplate using a given access token.
	 * @param accessToken An access token given by Facebook after a successful OAuth 2 authentication (or through Facebook's JS library).
	 */
	public FacebookTemplate(String accessToken) {
		this(accessToken, null);
	}

	public FacebookTemplate(String accessToken, String applicationNamespace) {
		this(accessToken, applicationNamespace, null);
	}
	
	public FacebookTemplate(String accessToken, String applicationNamespace, String appId) {
		super(accessToken);
		this.applicationNamespace = applicationNamespace;
		this.appId = appId;
		initialize();
	}
	
	@Override
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
	}
	
	/**
	 * Set the Graph API version (e.g., "2.8"). If set to null, the version will be left out of the request URLs to the
	 * Graph API.
	 * @param apiVersion the API version. Default is "2.8".
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public AchievementOperations achievementOperations() {
		return achievementOperations;
	}
	
	public UserOperations userOperations() {
		return userOperations;
	}
	
	public LikeOperations likeOperations() {
		return likeOperations;
	}

	public FriendOperations friendOperations() {
		return friendOperations;
	}
	
	public FeedOperations feedOperations() {
		return feedOperations;
	}
	
	public GroupOperations groupOperations() {
		return groupOperations;
	}

	public CommentOperations commentOperations() {
		return commentOperations;
	}
	
	public EventOperations eventOperations() {
		return eventOperations;
	}
	
	public MediaOperations mediaOperations() {
		return mediaOperations;
	}
	
	public PageOperations pageOperations() {
		return pageOperations;
	}
	
	public RestOperations restOperations() {
		return getRestTemplate();
	}
	
	public OpenGraphOperations openGraphOperations() {
		return openGraphOperations;
	}
	
	public SocialContextOperations socialContextOperations() {
		return socialContextOperations;
	}
	
	public String getApplicationNamespace() {
		return applicationNamespace;
	}
	
	public TestUserOperations testUserOperations() {
		return testUserOperations;
	}
	
	// low-level Graph API operations
	public <T> T fetchObject(String objectId, Class<T> type) {
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId).build();
		return getRestTemplate().getForObject(uri, type);
	}

	public <T> T fetchObject(String objectId, Class<T> type, String... fields) {
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		if(fields.length > 0) {
			String joinedFields = join(fields);
			queryParameters.set("fields", joinedFields);
		}		
		return fetchObject(objectId, type, queryParameters);
	}

	public <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParameters) {
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId).queryParams(queryParameters).build();
		return getRestTemplate().getForObject(uri, type);
	}

	public <T> PagedList<T> fetchConnections(String objectId, String connectionType, Class<T> type, String... fields) {
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		if(fields.length > 0) {
			String joinedFields = join(fields);
			queryParameters.set("fields", joinedFields);
		}		
		return fetchConnections(objectId, connectionType, type, queryParameters);
	}

	public <T> PagedList<T> fetchConnections(String objectId, String connectionType, Class<T> type, MultiValueMap<String, String> queryParameters) {
		String connectionPath = connectionType != null && connectionType.length() > 0 ? "/" + connectionType : "";
		URIBuilder uriBuilder = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + connectionPath).queryParams(queryParameters);
		JsonNode jsonNode = getRestTemplate().getForObject(uriBuilder.build(), JsonNode.class);
		return pagify(type, jsonNode);
	}

	public <T> PagedList<T> fetchPagedConnections(String objectId, String connectionType, Class<T> type, MultiValueMap<String, String> queryParameters) {
		String connectionPath = connectionType != null && connectionType.length() > 0 ? "/" + connectionType : "";
		URIBuilder uriBuilder = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + connectionPath).queryParams(queryParameters);
		JsonNode jsonNode = getRestTemplate().getForObject(uriBuilder.build(), JsonNode.class);
		return pagify(type, jsonNode);
	}

	public <T> PagedList<T> fetchConnections(String objectId, String connectionType, Class<T> type, MultiValueMap<String, String> queryParameters, String... fields) {
		if(fields.length > 0) {
			String joinedFields = join(fields);
			queryParameters.set("fields", joinedFields);
		}
		return fetchPagedConnections(objectId, connectionType, type, queryParameters);
	}

	/**
	 * Fetches the next {@link org.springframework.social.facebook.api.PagedList PagedList} of the current one.
	 * @param page source {@link org.springframework.social.facebook.api.PagedList PagedList} to fetch the next one.
	 * @param type type of the source {@link org.springframework.social.facebook.api.PagedList PagedList} and the next one.
	 * @param <T> the type of the source
	 * @return the next {@link org.springframework.social.facebook.api.PagedList PagedList} of the given one.
	 * It returns <code>null</code> if the next {@link org.springframework.social.facebook.api.PagedList PagedList} doesn't exist.
	 */
	public <T> PagedList<T> fetchNextPagedConnections(PagedList<T> page, Class<T> type) {
		if (null != page && null != page.getNextPage() && !"".equals(page.getNextPage().getFullUrl().trim())) {
			URIBuilder uriBuilder = URIBuilder.fromUri(page.getNextPage().getFullUrl());
			JsonNode jsonNode = getRestTemplate().getForObject(uriBuilder.build(), JsonNode.class);
			return pagify(type, jsonNode);
		}
		return null;
	}

	/**
	 * Fetchs the previous {@link org.springframework.social.facebook.api.PagedList PagedList} of the current one.
	 * @param page source {@link org.springframework.social.facebook.api.PagedList PagedList} to fetch the previous one.
	 * @param type type of the source {@link org.springframework.social.facebook.api.PagedList PagedList} and the previous one.
	 * @return the previous {@link org.springframework.social.facebook.api.PagedList PagedList} of the given one.
	 * @param <T> the type of the source
	 * It returns <code>null</code> if the previous {@link org.springframework.social.facebook.api.PagedList PagedList} doesn't exist.
	 */
	public <T> PagedList<T> fetchPreviousPagedConnections(PagedList<T> page, Class<T> type) {
		if (null != page && null != page.getPreviousPage() && !"".equals(page.getPreviousPage().getFullUrl().trim())) {
			URIBuilder uriBuilder = URIBuilder.fromUri(page.getPreviousPage().getFullUrl());
			JsonNode jsonNode = getRestTemplate().getForObject(uriBuilder.build(), JsonNode.class);
			return pagify(type, jsonNode);
		}
		return null;
	}
	
	private <T> PagedList<T> pagify(Class<T> type, JsonNode jsonNode) {
		List<T> data = deserializeDataList(jsonNode.get("data"), type);
		if (!jsonNode.has("paging")) {
			return new PagedList<T>(data, null, null);
		}
		
		JsonNode pagingNode = jsonNode.get("paging");
		PagingParameters previousPage = getPagedListParameters(pagingNode, "previous");
		PagingParameters nextPage = getPagedListParameters(pagingNode, "next");
		
		Integer totalCount = null;
		if (jsonNode.has("summary")) {
			JsonNode summaryNode = jsonNode.get("summary");
			if (summaryNode.has("total_count")) {
				totalCount = summaryNode.get("total_count").intValue();
			}
		}
		
		return new PagedList<T>(data, previousPage, nextPage, totalCount);
	}
	
	public String getBaseGraphApiUrl() {
		if (apiVersion != null) {
			return "https://graph.facebook.com/v" + apiVersion + "/";
		}
		return "https://graph.facebook.com/";
	}

	public byte[] fetchImage(String objectId, String connectionType, ImageType type) {
		return fetchImage(objectId, connectionType, type, null, null);
	}

	public byte[] fetchImage(String objectId, String connectionType, Integer width, Integer height) {
		return fetchImage(objectId, connectionType, null, width, height);
	}

	private byte[] fetchImage(String objectId, String connectionType, ImageType type, Integer width, Integer height) {
		URIBuilder uriBuilder = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + "/" + connectionType);
		if (type != null) {
		  uriBuilder.queryParam("type", type.toString().toLowerCase());
		}
		if (width != null) {
			uriBuilder.queryParam("width", width.toString());
		}
		if (height != null) {
			uriBuilder.queryParam("height", height.toString());
		}
		URI uri = uriBuilder.build();
		ResponseEntity<byte[]> response = getRestTemplate().getForEntity(uri, byte[].class);
		if(response.getStatusCode() == HttpStatus.FOUND) {
			throw new UnsupportedOperationException("Attempt to fetch image resulted in a redirect which could not be followed. Add Apache HttpComponents HttpClient to the classpath " +
					"to be able to follow redirects.");
		}
		return response.getBody();
	}
	
	@SuppressWarnings("unchecked")
	public String publish(String objectId, String connectionType, MultiValueMap<String, Object> data) {
		MultiValueMap<String, Object> requestData = new LinkedMultiValueMap<String, Object>(data);
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + "/" + connectionType).build();
		Map<String, Object> response = getRestTemplate().postForObject(uri, requestData, Map.class);
		return (String) response.get("id");
	}
	
	public void post(String objectId, MultiValueMap<String, Object> data) {
		post(objectId, null, data);
	}
	
	public void post(String objectId, String connectionType, MultiValueMap<String, Object> data) {
		String connectionPath = connectionType != null ? "/" + connectionType : "";
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + connectionPath).build();
		getRestTemplate().postForObject(uri, new LinkedMultiValueMap<String, Object>(data), String.class);
	}
	
	public void delete(String objectId) {
		LinkedMultiValueMap<String, String> deleteRequest = new LinkedMultiValueMap<String, String>();
		deleteRequest.set("method", "delete");
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId).build();
		getRestTemplate().postForObject(uri, deleteRequest, String.class);
	}
	
	public void delete(String objectId, String connectionType) {
		LinkedMultiValueMap<String, String> deleteRequest = new LinkedMultiValueMap<String, String>();
		deleteRequest.set("method", "delete");
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + "/" + connectionType).build();
		getRestTemplate().postForObject(uri, deleteRequest, String.class);
	}
	
	public void delete(String objectId, String connectionType, MultiValueMap<String, String> data) {
		data.set("method", "delete");
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId + "/" + connectionType).build();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(data, new HttpHeaders());
		getRestTemplate().exchange(uri, HttpMethod.POST, entity, String.class);
	}
	
	// AbstractOAuth2ApiBinding hooks
	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new FacebookErrorHandler());
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		objectMapper = new ObjectMapper();				
		objectMapper.registerModule(new FacebookModule());
		converter.setObjectMapper(objectMapper);		
		return converter;
	}
	
	// private helpers
	private void initialize() {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis();
	}
		
	private void initSubApis() {
		achievementOperations = new AchievementTemplate(this);
		openGraphOperations = new OpenGraphTemplate(this);
		userOperations = new UserTemplate(this, getRestTemplate());
		friendOperations = new FriendTemplate(this, getRestTemplate());
		feedOperations = new FeedTemplate(this, getRestTemplate(), objectMapper);
		commentOperations = new CommentTemplate(this);
		likeOperations = new LikeTemplate(this);
		eventOperations = new EventTemplate(this);
		mediaOperations = new MediaTemplate(this, getRestTemplate());
		groupOperations = new GroupTemplate(this);
		pageOperations = new PageTemplate(this);
		testUserOperations = new TestUserTemplate(this, getRestTemplate(), appId);
		socialContextOperations = new SocialContextTemplate(this, getRestTemplate());
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
		try {
			CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
			return (List<T>) objectMapper.readerFor(listType).readValue(jsonNode.toString()); // TODO: EXTREMELY HACKY--TEMPORARY UNTIL I FIGURE OUT HOW JACKSON 2 DOES THIS
		} catch (IOException e) {
			throw new UncategorizedApiException("facebook", "Error deserializing data from Facebook: " + e.getMessage(), e);
		}
	}
	
	private String join(String[] strings) {
		StringBuilder builder = new StringBuilder();
		if(strings.length > 0) {
			builder.append(strings[0]);
			for (int i = 1; i < strings.length; i++) {
				builder.append("," + strings[i]);
			}
		}
		return builder.toString();
	}
	
}
