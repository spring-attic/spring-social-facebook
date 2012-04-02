/*
 * Copyright 2012 the original author or authors.
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

/**
 * Defines operations for working with Facebook OpenGraph actions.
 * @author habuma
 */
public interface OpenGraphOperations {

	/**
	 * Posts an action for an object specified by the given object URL.
	 * @param action The application specific action to post, without the application's namespace. (eg, "drink")
	 * @param objectType The application specific object type, without the application's namespace. (eg, "beverage")
	 * @param objectUrl The URL of the object that is the target of the action.
	 * @return the ID of the posted action.
	 */
	String publishAction(String action, String objectType, String objectUrl);
	
}
