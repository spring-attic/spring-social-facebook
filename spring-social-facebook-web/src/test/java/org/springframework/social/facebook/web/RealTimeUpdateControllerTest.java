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

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

public class RealTimeUpdateControllerTest {

	@Test
	public void verifySubscription() throws Exception {
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("foo", "yabbadabbadoo");
		List<UpdateHandler> updateHandlers = new ArrayList<UpdateHandler>();
		RealTimeUpdateController controller = new RealTimeUpdateController(tokens, updateHandlers, "signature");
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/realtime/facebook/foo")
							.param("hub.mode", "subscribe")
							.param("hub.verify_token", "yabbadabbadoo")
							.param("hub.challenge", "123456789")).andExpect(content().string("123456789"));
	}

	@Test
	public void verifySubscription_badToken() throws Exception {
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("foo", "yabbadabbadoo");
		List<UpdateHandler> updateHandlers = new ArrayList<UpdateHandler>();
		RealTimeUpdateController controller = new RealTimeUpdateController(tokens, updateHandlers, "shhhhh!!!!");
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/realtime/facebook/foo")
							.param("hub.mode", "subscribe")
							.param("hub.verify_token", "doh!")
							.param("hub.challenge", "123456789")).andExpect(content().string(""));
	}

	@Test
	public void receiveUpdate_simple() throws Exception {		
		TestUpdateHandler handler = new TestUpdateHandler();
		List<UpdateHandler> handlers = new ArrayList<UpdateHandler>();
		handlers.add(handler);
		RealTimeUpdateController controller = new RealTimeUpdateController(new HashMap<String, String>(), handlers, "shhhhh!!!!");
		MockMvc mockMvc = 
				standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/realtime/facebook/foo")
							.contentType(APPLICATION_JSON)
							.content(jsonFromFile("rtupdate-simple"))
							.header("X-Hub-Signature", "sha1=765aa709e93724268969ad0cd922d6e0acbb3f35"))
			.andExpect(content().string(""));
		
		MultiValueMap<String, RealTimeUpdate> updates = handler.getUpdates();
		assertEquals(1, updates.size());
		List<RealTimeUpdate> fooUpdates = updates.get("foo");
		assertEquals(1, fooUpdates.size());
		RealTimeUpdate update = fooUpdates.get(0);
		assertEquals("user", update.getObject());
		assertEquals(1, update.getEntries().size());
		assertEquals(183562555, update.getEntries().get(0).getId());
		assertEquals(1374559990, update.getEntries().get(0).getTime());
		assertEquals(asList("friends"), update.getEntries().get(0).getChangedFields());
	}
	
	@Test
	public void receiveUpdate_manyUpdatesSingleSubscription() throws Exception {
		TestUpdateHandler handler = new TestUpdateHandler();
		List<UpdateHandler> handlers = new ArrayList<UpdateHandler>();
		handlers.add(handler);
		RealTimeUpdateController controller = new RealTimeUpdateController(new HashMap<String, String>(), handlers, "shhhhh!!!!");
		MockMvc mockMvc = 
				standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/realtime/facebook/foo")
							.contentType(APPLICATION_JSON)
							.content(jsonFromFile("rtupdate-many"))
							.header("X-Hub-Signature", "sha1=816505e95f33287950e8992488637871085164c2"))
			.andExpect(content().string(""));
		MultiValueMap<String, RealTimeUpdate> updates = handler.getUpdates();
		assertEquals(1, updates.size());
		List<RealTimeUpdate> fooUpdates = updates.get("foo");
		assertEquals(1, fooUpdates.size());
		RealTimeUpdate update = fooUpdates.get(0);
		assertEquals("user", update.getObject());
		assertEquals(2, update.getEntries().size());
		assertEquals(424711, update.getEntries().get(0).getId());
		assertEquals(1373804766, update.getEntries().get(0).getTime());
		assertEquals(asList("friends"), update.getEntries().get(0).getChangedFields());
		assertEquals(620448186, update.getEntries().get(1).getId());
		assertEquals(1373804766, update.getEntries().get(1).getTime());
		assertEquals(asList("feed", "friends"), update.getEntries().get(1).getChangedFields());
	}

	@Test
	public void receiveUpdate_manySubscriptions() throws Exception {
		TestUpdateHandler handler = new TestUpdateHandler();
		List<UpdateHandler> handlers = new ArrayList<UpdateHandler>();
		handlers.add(handler);
		RealTimeUpdateController controller = new RealTimeUpdateController(new HashMap<String, String>(), handlers, "shhhhh!!!!");
		MockMvc mockMvc = 
				standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/realtime/facebook/foo")
							.contentType(APPLICATION_JSON)
							.content(jsonFromFile("rtupdate-simple"))
							.header("X-Hub-Signature", "sha1=765aa709e93724268969ad0cd922d6e0acbb3f35"))
			.andExpect(content().string(""));
		mockMvc.perform(post("/realtime/facebook/bar")
				.contentType(APPLICATION_JSON)
				.content(jsonFromFile("rtupdate-simple"))
				.header("X-Hub-Signature", "sha1=765aa709e93724268969ad0cd922d6e0acbb3f35"))
			.andExpect(content().string(""));
		
		MultiValueMap<String, RealTimeUpdate> updates = handler.getUpdates();
		assertEquals(2, updates.size());
		List<RealTimeUpdate> fooUpdates = updates.get("foo");
		assertEquals(1, fooUpdates.size());
		RealTimeUpdate update = fooUpdates.get(0);
		assertEquals("user", update.getObject());
		assertEquals(1, update.getEntries().size());
		assertEquals(183562555, update.getEntries().get(0).getId());
		assertEquals(1374559990, update.getEntries().get(0).getTime());
		assertEquals(asList("friends"), update.getEntries().get(0).getChangedFields());
		List<RealTimeUpdate> barUpdates = updates.get("bar");
		assertEquals(1, barUpdates.size());
		update = barUpdates.get(0);
		assertEquals("user", update.getObject());
		assertEquals(1, update.getEntries().size());
		assertEquals(183562555, update.getEntries().get(0).getId());
		assertEquals(1374559990, update.getEntries().get(0).getTime());
		assertEquals(asList("friends"), update.getEntries().get(0).getChangedFields());
	}

	@Test
	public void receiveUpdate_badSignature() throws Exception {		
		TestUpdateHandler handler = new TestUpdateHandler();
		List<UpdateHandler> handlers = new ArrayList<UpdateHandler>();
		handlers.add(handler);
		RealTimeUpdateController controller = new RealTimeUpdateController(new HashMap<String, String>(), handlers, "shhhhh!!!!");
		MockMvc mockMvc = 
				standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/realtime/facebook/foo")
							.contentType(APPLICATION_JSON)
							.content(jsonFromFile("rtupdate-simple"))
							.header("X-Hub-Signature", "sha1=765aa709e93724268969ad0cd922d6e0acbb3f36"))
			.andExpect(content().string(""));
		
		MultiValueMap<String, RealTimeUpdate> updates = handler.getUpdates();
		assertEquals(0, updates.size());
	}

	@Test
	public void receiveUpdate_missingSignature() throws Exception {		
		TestUpdateHandler handler = new TestUpdateHandler();
		List<UpdateHandler> handlers = new ArrayList<UpdateHandler>();
		handlers.add(handler);
		RealTimeUpdateController controller = new RealTimeUpdateController(new HashMap<String, String>(), handlers, "shhhhh!!!!");
		MockMvc mockMvc = 
				standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/realtime/facebook/foo")
							.contentType(APPLICATION_JSON)
							.content(jsonFromFile("rtupdate-simple")))
			.andExpect(content().string(""));
		
		MultiValueMap<String, RealTimeUpdate> updates = handler.getUpdates();
		assertEquals(0, updates.size());
	}

	private String jsonFromFile(String filename) throws IOException {
		ClassPathResource resource = new ClassPathResource(filename + ".json", getClass());
		return StreamUtils.copyToString(resource.getInputStream(), Charset.forName("UTF-8"));
	}

	private static class TestUpdateHandler implements UpdateHandler {
		private MultiValueMap<String, RealTimeUpdate> updates = new LinkedMultiValueMap<String, RealTimeUpdate>();
		
		public void handleUpdate(String subscription, RealTimeUpdate update) {
			updates.add(subscription, update);
		}

		public MultiValueMap<String, RealTimeUpdate> getUpdates() {
			return updates;
		}
	}
}
