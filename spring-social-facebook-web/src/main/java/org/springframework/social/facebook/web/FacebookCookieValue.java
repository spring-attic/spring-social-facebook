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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that indicates to {@link FacebookWebArgumentResolver} that a controller method parameter should be bound
 * to a value from the Facebook cookie written when the user signed in with Facebook.
 * @author Craig Walls
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FacebookCookieValue {
	
	/**
	 * The specific element of the cookie to be bound (e.g., "uid", "expires", etc)
	 * @return the cookie element name
	 */
	public String value() default "";

	/**
	 * Whether the Facebook cookie value is required.
	 * Default is true, leading to an exception being thrown in case the Facebook cookie is missing or if the value can't be found in the cookie. 
	 * Switch this to false if you prefer a null in case of the missing cookie/value.
	 * @return true if the cookie value is required
	 */
	boolean required() default true;
}
