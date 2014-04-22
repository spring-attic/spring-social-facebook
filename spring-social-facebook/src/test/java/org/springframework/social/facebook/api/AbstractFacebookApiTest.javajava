/*
 * Copyright 2013 the original author or authors.
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

public class AbstractFacebookApiTest {
	protected static final String ACCESS_TOKEN = "someAccessToken";

	protected FacebookTemplate facebook;
	protected FacebookTemplate unauthorizedFacebook;
	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer unauthorizedMockServer;

	@Before
	public void setup() {
		facebook = createFacebookTemplate();
		mockServer = MockRestServiceServer.createServer(facebook.getRestTemplate());
		
		unauthorizedFacebook = new FacebookTemplate();
		unauthorizedMockServer = MockRestServiceServer.createServer(unauthorizedFacebook.getRestTemplate());
	}

	protected FacebookTemplate createFacebookTemplate() {
		return new FacebookTemplate(ACCESS_TOKEN);
	}

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

	protected Date toDate(String dateString) {
		try {
			return FB_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	private static final DateFormat FB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);

}
