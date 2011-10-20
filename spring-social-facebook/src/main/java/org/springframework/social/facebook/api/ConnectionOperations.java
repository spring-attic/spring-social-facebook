package org.springframework.social.facebook.api;

import java.util.List;

public interface ConnectionOperations {
	public <T> List<T> getConnections(String objectId, Class<T> type);

	public <T> boolean addConnection(String objectId, Class<T> type,
			T connectedObject);

	public boolean deleteConnection(String objectId, Class<?> type,
			String connectedObjectId);

	public String[] getConnectionTypes();
}
