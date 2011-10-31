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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.ConnectionException;
import org.springframework.social.facebook.api.ConnectionOperations;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ResultSet;

/**
 * @author Karthick Sankarachary
 * 
 */

public abstract class ConnectionTemplate extends AbstractFacebookOperations
		implements ConnectionOperations {

	public ConnectionTemplate(GraphApi graphApi, boolean isAuthorized) {
		super(isAuthorized);
		this.graphApi = graphApi;
	}

	public <T> List<T> getConnectionObjects(String objectId, Class<T> objectType) {
		requireAuthorization();
		@SuppressWarnings("unchecked")
		ResultSet<T> resultSet = graphApi.fetchObject(objectId
				+ "/connectionobjects", ResultSet.class);
		return resultSet.getData();
	}

	public <T> List<T> getConnections(String objectId, Class<T> type) {
		requireAuthorization();
		List<T> connections = graphApi.fetchConnections(objectId,
				getConnectionType(type), type);
		return connections;
	}

	public <T> boolean addConnection(String objectId, Class<T> type,
			T connectedObject) {
		requireAuthorization();
		String result = graphApi.addConnection(objectId,
				getConnectionType(type), type, null, connectedObject);
		return Boolean.valueOf(result);
	}

	public boolean deleteConnection(String objectId, Class<?> type,
			String connectedObjectId) {
		requireAuthorization();
		String result = graphApi.deleteConnection(objectId,
				getConnectionType(type), type, connectedObjectId);
		return Boolean.valueOf(result);
	}

	public abstract String[] getConnectionTypes();

	protected String getConnectionType(Class<?> type) {
		// derive the connection name from the type, and pluralize it
		String typeName = type.getSimpleName().toLowerCase();
		boolean validConnectionType = false;
		for (String connectionType : getConnectionTypes()) {
			if (typeName.equals(connectionType)) {
				validConnectionType = true;
				break;
			}
		}
		if (!validConnectionType) {
			throw new ConnectionException(typeName
					+ " is not a valid connection type for "
					+ getClass().getSimpleName());
		}
		return typeName + "s";
	}

	protected final GraphApi graphApi;

}
