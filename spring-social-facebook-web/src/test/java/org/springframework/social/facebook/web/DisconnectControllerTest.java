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
package org.springframework.social.facebook.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.test.web.servlet.MockMvc;

public class DisconnectControllerTest {

	private static final String CLIENT_SECRET = "888e92659dae96040216a257576b092a";
	private static final String SIGNED_REQUEST = "T4PCp840PHnhgQwMgCSZODpDGqhLC4mFGaNG8oHW7WU.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTMzNTg5NDc5NiwidXNlciI6eyJjb3VudHJ5IjoidXMiLCJsb2NhbGUiOiJlbl9VUyJ9LCJ1c2VyX2lkIjoiNzM4MTQwNTc5In0";

	@Test
	public void disconnect() throws Exception {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("clientId", CLIENT_SECRET);
		Connection<Facebook> connection = connectionFactory.createConnection(new ConnectionData("facebook", "738140579", "", "", "", "", "", "", null));
		StubUsersConnectionRepository usersConnectionRepository = new StubUsersConnectionRepository();
		ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository("habuma");
		connectionRepository.addConnection(connection);
		assertEquals(1, connectionRepository.findAllConnections().size());
		DisconnectController controller = new DisconnectController(usersConnectionRepository, CLIENT_SECRET);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/disconnect/facebook").param("signed_request", SIGNED_REQUEST))
			.andExpect(status().isNoContent());		
		assertEquals(0, connectionRepository.findAllConnections().size());
	}
	
}
