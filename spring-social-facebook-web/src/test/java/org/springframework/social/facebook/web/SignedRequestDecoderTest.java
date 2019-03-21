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

import java.util.Map;

import org.junit.Test;

public class SignedRequestDecoderTest {

	@Test
	public void decodeSignedRequest_simple() throws Exception {
		// Sample from Facebook's documentation
		SignedRequestDecoder decoder = new SignedRequestDecoder("secret");
		Map<String, ?> decoded = decoder.decodeSignedRequest("vlXgu64BQGFSQrY0ZcJBZASMvYvTHu9GQ0YM9rjPSso.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsIjAiOiJwYXlsb2FkIn0");
		assertEquals("payload", decoded.get("0"));
	}

	@Test(expected=SignedRequestException.class)
	public void decodeSignedRequest_jsonError() throws Exception {
		try {
			SignedRequestDecoder decoder = new SignedRequestDecoder("secret");
			decoder.decodeSignedRequest("vlXgu64BQGFSQrY0ZcJBZASMvYvTHu9GQ0YM9rjPSso.fyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsIjAiOiJwYXlsb2FkIn0");
		} catch (SignedRequestException e) {
			assertEquals("Error parsing payload.", e.getMessage());
			throw e;
		}		
	}

	@Test(expected=SignedRequestException.class)
	public void decodeSignedRequest_signatureError() throws Exception {
		try {
			SignedRequestDecoder decoder = new SignedRequestDecoder("secretx");
			decoder.decodeSignedRequest("vlXgu64BQGFSQrY0ZcJBZASMvYvTHu9GQ0YM9rjPSso.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsIjAiOiJwYXlsb2FkIn0");
		} catch (SignedRequestException e) {
			assertEquals("Invalid signature.", e.getMessage());
			throw e;
		}		
	}

	@Test(expected=SignedRequestException.class)
	public void decodeSignedRequest_unknownAlgorithm() throws Exception {
		try {
			SignedRequestDecoder decoder = new SignedRequestDecoder("secret");
			decoder.decodeSignedRequest("9uyqayaEe4bLYhw0CEDwjyzWUu_FCGYlSppT6OTodSo.eyJhbGdvcml0aG0iOiJCT0dVUyIsIjAiOiJwYXlsb2FkIn0");
		} catch (SignedRequestException e) {
			assertEquals("Unknown encryption algorithm: BOGUS", e.getMessage());
			throw e;
		}		
	}

	@Test
	public void decodeSignedRequest_deauthorizeRequest() throws Exception {
		SignedRequestDecoder decoder = new SignedRequestDecoder("888e92659dae96040216a257576b092a");
		Map<String, ?> decoded = decoder.decodeSignedRequest("T4PCp840PHnhgQwMgCSZODpDGqhLC4mFGaNG8oHW7WU.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTMzNTg5NDc5NiwidXNlciI6eyJjb3VudHJ5IjoidXMiLCJsb2NhbGUiOiJlbl9VUyJ9LCJ1c2VyX2lkIjoiNzM4MTQwNTc5In0");
		assertEquals("HMAC-SHA256", decoded.get("algorithm"));
		assertEquals(1335894796, decoded.get("issued_at"));
		assertEquals("738140579", decoded.get("user_id"));
		@SuppressWarnings("unchecked")
		Map<String, ?> userData = (Map<String, ?>) decoded.get("user");
		assertEquals("us", userData.get("country"));
		assertEquals("en_US", userData.get("locale"));
	}

	
	@Test
	public void decodeSignedRequest_deauthorizeRequest_toObject() throws Exception {
		SignedRequestDecoder decoder = new SignedRequestDecoder("888e92659dae96040216a257576b092a");
		DeauthorizationRequest deauth = decoder.decodeSignedRequest("T4PCp840PHnhgQwMgCSZODpDGqhLC4mFGaNG8oHW7WU.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTMzNTg5NDc5NiwidXNlciI6eyJjb3VudHJ5IjoidXMiLCJsb2NhbGUiOiJlbl9VUyJ9LCJ1c2VyX2lkIjoiNzM4MTQwNTc5In0", DeauthorizationRequest.class);
		assertEquals("HMAC-SHA256", deauth.getAlgorithm());
		assertEquals(1335894796, deauth.getIssuedAt());
		assertEquals("738140579", deauth.getUserId());
		assertEquals("us", deauth.getUser().getCountry());
		assertEquals("en_US", deauth.getUser().getLocale());
	}
	
}
