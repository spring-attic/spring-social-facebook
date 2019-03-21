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

import org.springframework.util.MultiValueMap;

/**
 * Defines low-level operations against Facebook's Graph API
 * @author Craig Walls
 */
public interface GraphApi {
	
	/**
	 * Fetches an object, extracting it into the given Java type
	 * Requires appropriate permission to fetch the object.
	 * @param objectId the Facebook object's ID
	 * @param type the Java type to fetch
	 * @param <T> The Java type to bind the Facebook object to
	 * @return an Java object representing the requested Facebook object.
	 */
	<T> T fetchObject(String objectId, Class<T> type);

	/**
	 * Fetches an object, extracting it into the given Java type
	 * Requires appropriate permission to fetch the object.
	 * @param objectId the Facebook object's ID
	 * @param type the Java type to fetch
	 * @param fields the fields to include in the response.
	 * @param <T> The Java type to bind the Facebook object to
	 * @return an Java object representing the requested Facebook object.
	 */
	<T> T fetchObject(String objectId, Class<T> type, String... fields);

	/**
	 * Fetches an object, extracting it into the given Java type
	 * Requires appropriate permission to fetch the object.
	 * @param objectId the Facebook object's ID
	 * @param type the Java type to fetch
	 * @param queryParameters query parameters to include in the request
	 * @param <T> The Java type to bind the Facebook object to
	 * @return an Java object representing the requested Facebook object.
	 */
	<T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParameters);

	/**
	 * Fetches connections, extracting them into a collection of the given Java type 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param fields the fields to include in the response.
	 * @param <T> The Java type to bind the Facebook object to
	 * @return a list of Java objects representing the Facebook objects in the connections.
	 */
	<T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, String... fields);
	
	/**
	 * Fetches connections, extracting them into a collection of the given Java type 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param queryParameters query parameters to include in the request
	 * @param <T> The Java type to bind the Facebook object to
	 * @return a list of Java objects representing the Facebook objects in the connections.
	 */
	<T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, MultiValueMap<String, String> queryParameters);

	/**
	 * Fetches connections, extracting them into a collection of the given Java type 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param queryParameters query parameters to include in the request
	 * @param fields the fields to include in the response.
	 * @param <T> The Java type to bind the Facebook object to
	 * @return a list of Java objects representing the Facebook objects in the connections.
	 */
	<T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, MultiValueMap<String, String> queryParameters, String... fields);

	/**
	 * Fetches an image as an array of bytes.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param imageType the type of image to retrieve (eg., small, normal, large, or square)
	 * @return an image as an array of bytes.
	 */
	byte[] fetchImage(String objectId, String connectionName, ImageType imageType);	

 	 /**
	 * Fetches an image as an array of bytes.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param width desired width of the image (optional)
	 * @param height desired height of the image (optional)
	 * @return an image as an array of bytes.
	 */
	byte[] fetchImage(String objectId, String connectionName, Integer width, Integer height);

	/**
	 * Publishes data to an object's connection.
	 * Requires appropriate permission to publish to the object connection.
	 * @param objectId the object ID to publish to.
	 * @param connectionName the connection name to publish to.
	 * @param data the data to publish to the connection.
	 * @return the ID of the newly published object.
	 */
	String publish(String objectId, String connectionName, MultiValueMap<String, Object> data);	

	/**
	 * Publishes data to an object. 
	 * Requires appropriate permission to publish to the object connection.
	 * This differs from publish() only in that it doesn't attempt to extract the ID from the response.
	 * This is because some publish operations do not return an ID in the response.
	 * @param objectId the object ID to publish to.
	 * @param data the data to publish to the object.
	 */
	void post(String objectId, MultiValueMap<String, Object> data);

	/**
	 * Publishes data to an object's connection. 
	 * Requires appropriate permission to publish to the object connection.
	 * This differs from publish() only in that it doesn't attempt to extract the ID from the response.
	 * This is because some publish operations do not return an ID in the response.
	 * @param objectId the object ID to publish to.
	 * @param connectionName the connection name to publish to.
	 * @param data the data to publish to the connection.
	 */
	void post(String objectId, String connectionName, MultiValueMap<String, Object> data);
	
	/**
	 * Deletes an object.
	 * Requires appropriate permission to delete the object.
	 * @param objectId the object ID
	 */
	void delete(String objectId);
	
	/**
	 * Deletes an object connection.
	 * Requires appropriate permission to delete the object connection.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 */
	void delete(String objectId, String connectionName);

	/**
	 * Deletes an object connection.
	 * Requires appropriate permission to delete the object connection.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param data parameters for the delete request
	 */
	void delete(String objectId, String connectionName, MultiValueMap<String, String> data);

	/**
	 * @return The application namespace associated with this GraphApi instance. Useful for interacting with Facebook's OpenGraph actions.
	 * 			May be null if no namespace was specified.
	 */
	String getApplicationNamespace();
	
	/**
	 * @return The base URL for the Graph API.
	 */
	String getBaseGraphApiUrl();
	
}
