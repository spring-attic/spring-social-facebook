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

public class TestUser {

	private String id;

	private String email;
	
	private String password;
	
	private String accessToken;
	
	private String loginUrl;

	public TestUser(String id, String email, String password, String accessToken, String loginUrl) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.accessToken = accessToken;
		this.loginUrl = loginUrl;
	}
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getLoginUrl() {
		return loginUrl;
	}
	
}
