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

/**
 * Represents an error returned from a bad Graph API request.
 * @author Craig Walls
 */
public class FacebookError {

	private final Integer code;
	
	private final String type;
	
	private final String message;
	
	private final Integer subcode;
	
	private final String userMessage;
	
	private final String userTitle;
	
	public FacebookError(Integer code, String type, String message, Integer subcode, String userMessage, String userTitle) {
		this.code = code != null && code != 0 ? code : null;
		this.type = type;
		this.message = message;
		this.subcode = subcode;
		this.userMessage = userMessage;
		this.userTitle = userTitle;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Integer getSubcode() {
		return subcode;
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	
	public String getUserTitle() {
		return userTitle;
	}
	
}
