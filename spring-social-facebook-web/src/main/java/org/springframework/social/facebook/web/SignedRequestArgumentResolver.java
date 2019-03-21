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

import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Web argument resolver for controller handler method arguments that are annotated with {@link SignedRequest}.
 * Binds JSON payload of the signed_request parameter to the parameter type.
 * JSON properties named with underbar (_) separates will be converted to camel-case when binding to field name. (e.g., "user_id" will bind to a property named "userId").
 * Any JSON properties without corresponding fields in the target type will be ignored.
 * @author Craig Walls
 */
public class SignedRequestArgumentResolver implements HandlerMethodArgumentResolver {

	private final SignedRequestDecoder signedRequestDecoder;

	public SignedRequestArgumentResolver(String appSecret) {
		this.signedRequestDecoder = new SignedRequestDecoder(appSecret);
	}
	
	public boolean supportsParameter(MethodParameter parameter) {
		SignedRequest annotation = parameter.getParameterAnnotation(SignedRequest.class);
		return annotation != null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
		SignedRequest annotation = parameter.getParameterAnnotation(SignedRequest.class);
		if (annotation == null) {
			return WebArgumentResolver.UNRESOLVED;
		}
		String signedRequest = request.getParameter("signed_request");
		if (signedRequest == null && annotation.required()) {
			throw new IllegalStateException("Required signed_request parameter is missing.");
		}
		if (signedRequest == null) {
			return null;
		}
		Class<?> parameterType = parameter.getParameterType();
		if (MultiValueMap.class.isAssignableFrom(parameterType)) {
			Map map = signedRequestDecoder.decodeSignedRequest(signedRequest, Map.class);
			LinkedMultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>(map.size());
			mvm.setAll((Map<String, Object>) map);
			return mvm;
		}
		return signedRequestDecoder.decodeSignedRequest(signedRequest, parameterType);
	}
}
