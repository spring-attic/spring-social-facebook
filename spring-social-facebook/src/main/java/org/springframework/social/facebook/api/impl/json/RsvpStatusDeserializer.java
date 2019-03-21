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
package org.springframework.social.facebook.api.impl.json;

import java.io.IOException;

import org.springframework.social.facebook.api.RsvpStatus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserializer to convert an Invitation's or EventInvitee's "rsvp_status" value to an RsvpStatus. 
 * @author Craig Walls
 */
class RsvpStatusDeserializer extends JsonDeserializer<RsvpStatus> {

	@Override
	public RsvpStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String normalizedStatus = jp.getText().toUpperCase();
		if (normalizedStatus.equals("UNSURE")) {
			normalizedStatus = "MAYBE";
		}
		return RsvpStatus.valueOf(normalizedStatus);
	}
	
}
