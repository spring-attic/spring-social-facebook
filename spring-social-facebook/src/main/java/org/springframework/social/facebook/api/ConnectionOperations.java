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

import java.util.List;

/**
 * The <code>ConnectionOperations</code> lets you perform operations on the
 * connections of a given Facebook Graph API object. The
 * {@link #getConnectionTypes()} is sort of a SPI method, that specifies the
 * valid connections for the object type(s) that this interface represents.
 * 
 * @author Karthick Sankarachary
 * 
 */

public interface ConnectionOperations {
	/**
	 * Get the connection objects for the given object and connection type.
	 * 
	 * @param objectId
	 *            the object id
	 * @param objectType
	 *            the object type
	 * @return the list of related connection objects
	 */
	public <T> List<T> getConnectionObjects(String objectId, Class<T> objectType);

	/**
	 * Get the connections for the given object and connection type
	 * 
	 * @param objectId
	 *            the object id
	 * @param type
	 *            the connection type
	 * @return a list of connections for that object
	 */
	public <T> List<T> getConnections(String objectId, Class<T> type);

	/**
	 * Add a connection to the given object
	 * 
	 * @param objectId
	 *            the object id
	 * @param type
	 *            the connection type
	 * @param connectedObject
	 *            the connected object
	 * @return true if the connection was established
	 */
	public <T> boolean addConnection(String objectId, Class<T> type,
			T connectedObject);

	/**
	 * Delete a conection of the given object
	 * 
	 * @param objectId
	 *            the object id
	 * @param type
	 *            the connection type
	 * @param connectedObjectId
	 *            the id of the connected object
	 * @return true if the delete succeeded
	 */
	public boolean deleteConnection(String objectId, Class<?> type,
			String connectedObjectId);

	/**
	 * The list of valid connection types for the specific object type(s) this
	 * class represents.
	 * 
	 * @return the list of valid connection type names.
	 */
	public String[] getConnectionTypes();
}
