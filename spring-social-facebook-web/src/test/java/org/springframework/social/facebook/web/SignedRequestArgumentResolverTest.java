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

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

public class SignedRequestArgumentResolverTest {
	
	private static final String APP_SECRET = "888e92659dae96040216a257576b092a";

	private static final String DEAUTH_CALLBACK = "T4PCp840PHnhgQwMgCSZODpDGqhLC4mFGaNG8oHW7WU.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTMzNTg5NDc5NiwidXNlciI6eyJjb3VudHJ5IjoidXMiLCJsb2NhbGUiOiJlbl9VUyJ9LCJ1c2VyX2lkIjoiNzM4MTQwNTc5In0";

	private SignedRequestArgumentResolver resolver;

	@Before
	public void setup() {
		resolver = new SignedRequestArgumentResolver(APP_SECRET);
	}
	
	@Test
	public void resolveArgument_dauthorizationRequest_object() throws Exception {
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.setParameter("signed_request", DEAUTH_CALLBACK);
		NativeWebRequest request = new ServletWebRequest(httpServletRequest); 
		Method method = SignedRequestArgumentResolverTest.class.getDeclaredMethod("annotatedMethod", DeauthorizationRequest.class, Map.class, MultiValueMap.class, String.class);
		MethodParameter deauthParameter = new MethodParameter(method, 0);
		DeauthorizationRequest resolved = (DeauthorizationRequest) resolver.resolveArgument(deauthParameter, null, request, null);
		assertEquals("HMAC-SHA256", resolved.getAlgorithm());
		assertEquals(1335894796, resolved.getIssuedAt());
		assertEquals("738140579", resolved.getUserId());
		assertEquals("us", resolved.getUser().getCountry());
		assertEquals("en_US", resolved.getUser().getLocale());
	}
	
	@Test
	@SuppressWarnings({ "unused", "unchecked" })
	public void resolveArgument_dauthorizationRequest_map() throws Exception {
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.setParameter("signed_request", DEAUTH_CALLBACK);
		NativeWebRequest request = new ServletWebRequest(httpServletRequest);
		Method method = SignedRequestArgumentResolverTest.class.getDeclaredMethod("annotatedMethod", DeauthorizationRequest.class, Map.class, MultiValueMap.class, String.class);
		MethodParameter deauthParameter = new MethodParameter(method, 1);
		Class<?> parameterType = deauthParameter.getParameterType();
		Map<String, Object> resolved = (Map<String, Object>) resolver.resolveArgument(deauthParameter, null, request, null);
		assertEquals("HMAC-SHA256", resolved.get("algorithm"));
		assertEquals(1335894796, resolved.get("issued_at"));
		assertEquals("738140579", resolved.get("user_id"));
		Map<String, ?> userData = (Map<String, ?>) resolved.get("user");
		assertEquals("us", userData.get("country"));
		assertEquals("en_US", userData.get("locale"));
	}
	
	@Test
	@SuppressWarnings({ "unused", "unchecked" })
	public void resolveArgument_dauthorizationRequest_multiValueMap() throws Exception {
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.setParameter("signed_request", DEAUTH_CALLBACK);
		NativeWebRequest request = new ServletWebRequest(httpServletRequest);
		Method method = SignedRequestArgumentResolverTest.class.getDeclaredMethod("annotatedMethod", DeauthorizationRequest.class, Map.class, MultiValueMap.class, String.class);
		MethodParameter deauthParameter = new MethodParameter(method, 2);
		Class<?> parameterType = deauthParameter.getParameterType();
		MultiValueMap<String, Object> resolved = (MultiValueMap<String, Object>) resolver.resolveArgument(deauthParameter, null, request, null);
		assertEquals("HMAC-SHA256", resolved.getFirst("algorithm"));
		assertEquals(1335894796, resolved.getFirst("issued_at"));
		assertEquals("738140579", resolved.getFirst("user_id"));
		Map<String, ?> userData = (Map<String, ?>) resolved.getFirst("user");
		assertEquals("us", userData.get("country"));
		assertEquals("en_US", userData.get("locale"));
	}
	
	@SuppressWarnings("unused")
	private void annotatedMethod(@SignedRequest DeauthorizationRequest deauth, @SignedRequest Map<String, ?> deauthMap, @SignedRequest MultiValueMap<String, ?> dauthMVM, String someOtherParameter) {		
	}

}
