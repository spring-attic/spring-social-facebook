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

public class PageRestaurantSpecialties {
	
	private boolean breakfast;
	
	private boolean coffee;
	
	private boolean dinner;
	
	private boolean drinks;
	
	private boolean lunch;
	
	public boolean hasBreakfast() {
		return breakfast;
	}
	
	public boolean hasCoffee() {
		return coffee;
	}
	
	public boolean hasDinner() {
		return dinner;
	}
	
	public boolean hasDrinks() {
		return drinks;
	}
	
	public boolean hasLunch() {
		return lunch;
	}
	
}
