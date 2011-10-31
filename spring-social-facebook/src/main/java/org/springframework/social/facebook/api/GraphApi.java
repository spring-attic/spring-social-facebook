/*
 * Copyright 2011 the original author or authors.
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

import java.io.File;
import java.util.List;

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
	 * @return an Java object representing the requested Facebook object.
	 */
	<T> T fetchObject(String objectId, Class<T> type);

	/**
	 * Fetches an object, extracting it into the given Java type
	 * Requires appropriate permission to fetch the object.
	 * @param objectId the Facebook object's ID
	 * @param type the Java type to fetch
	 * @param queryParameters query parameters to include in the request
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
	 * @return a list of Java objects representing the Facebook objects in the connections.
	 */
	<T> List<T> fetchConnections(String objectId, String connectionName, Class<T> type, String... fields);
	
	/**
	 * Fetches connections, extracting them into a collection of the given Java type 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param queryParameters query parameters to include in the request
	 * @return a list of Java objects representing the Facebook objects in the connections.
	 */
	<T> List<T> fetchConnections(String objectId, String connectionName, Class<T> type, MultiValueMap<String, String> queryParameters);
	
	/**
	 * Fetches an image as an array of bytes.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param imageType the type of image to retrieve (eg., small, normal, large, or square)
	 * @return an image as an array of bytes.
	 */
	byte[] fetchImage(String objectId, String connectionName, ImageType imageType);	

	/**
	 * Uploads an image file.
	 * Requires appropriate permission to upload to the object connection.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param imageFile the image file (maybe a zip of image files in some cases)
	 * @param responseType the type of the response (id, image hash/url, etc)
	 * @return an object of the responseType
	 */
	<T> T uploadImage(String objectId, String connectionName, File imageFile,
			Class<T> responseType);

	/**
	 * Uploads an image file.
	 * Requires appropriate permission to upload to the object connection.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @param bytes the in-memory byte array representation of the image
	 * @param name the logical name of the image file
	 * @param responseType the type of the response (id, image hash/url, etc)
	 * @return an object of the responseType
	 */
	<T> T uploadImage(String objectId, String connectionName,
			final byte[] bytes, final String name, Class<T> responseType);
	
	/**
	 * Adds a connection to a given object, extracting the data from the value the given Java type 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param queryParameters query parameters to include in the request
	 * @param connectionObject the connection object
	 * @return an indicator of success
	 */
	<T> String addConnection(String objectId, String connectionName,
			Class<T> type, MultiValueMap<String, String> queryParameters, T connectionObject);
	
	/**
	 * Removes a connection to a given object, based on the ID of the pair of objects 
	 * Requires appropriate permission to fetch the object connection.
	 * @param objectId the ID of the object to retrieve the connections for.
	 * @param connectionName the connection name.
	 * @param type the Java type of each connection.
	 * @param connectedObjectId the ID of the connection object to remove.
	 * @return an indicator of success
	 */
	 String deleteConnection(String objectId, String connectionName,
			Class<?> type, String connectedObjectId);
	
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
	 * Updates data of an object.
	 * Requires appropriate permission to update the object.
	 * @param objectId the object ID to publish to.
	 * @param data the data to publish to the connection.
	 * @return a boolean value indicating success of the update operation.
	 */
	boolean update(String objectId, MultiValueMap<String, Object> data);	

	/**
	 * Publishes data to an object's connection. 
	 * Requires appropriate permission to publish to the object connection.
	 * This differs from publish() only in that it doesn't attempt to extract the ID from the response.
	 * This is because some publish operations do not return an ID in the response.
	 * @param objectId the object ID to publish to.
	 * @param connectionName the connection name to publish to.
	 * @param data the data to publish to the connection.
	 */
	void post(String objectId, String connectionName, MultiValueMap<String, String> data);
	
	/**
	 * Deletes an object.
	 * Requires appropriate permission to delete the object.
	 * @param objectId the object ID
	 * @return an indicator of success
	 */
	String delete(String objectId);
	
	/**
	 * Deletes an object connection.
	 * Requires appropriate permission to delete the object connection.
	 * @param objectId the object ID
	 * @param connectionName the connection name
	 * @return an indicator of success
	 */
	String delete(String objectId, String connectionName);
	
	public static final String GRAPH_API_URL = "https://graph.facebook.com/";

}
