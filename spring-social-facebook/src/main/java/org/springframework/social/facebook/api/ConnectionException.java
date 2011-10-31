package org.springframework.social.facebook.api;

import org.springframework.social.OperationNotPermittedException;

@SuppressWarnings("serial")
public class ConnectionException extends OperationNotPermittedException {

	public ConnectionException(String message) {
		super(message);
	}

}
