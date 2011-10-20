package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.ConnectionException;
import org.springframework.social.facebook.api.ConnectionOperations;
import org.springframework.social.facebook.api.GraphApi;

public abstract class ConnectionTemplate extends AbstractFacebookOperations
		implements ConnectionOperations {

	public ConnectionTemplate(GraphApi graphApi, boolean isAuthorized) {
		super(isAuthorized);
		this.graphApi = graphApi;
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
