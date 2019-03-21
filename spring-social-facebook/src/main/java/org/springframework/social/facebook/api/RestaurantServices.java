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

public class RestaurantServices {

	private boolean delivery;
	
	private boolean catering;
	
	private boolean groups;
	
	private boolean kids;
	
	private boolean outdoor;
	
	private boolean reservea;
	
	private boolean takeout;
	
	private boolean waiter;
	
	private boolean walkins;
	
	public boolean hasDelivery() {
		return delivery;
	}
	
	public boolean hasCatering() {
		return catering;
	}
	
	public boolean hasGroups() {
		return groups;
	}
	
	public boolean hasKids() {
		return kids;
	}
	
	public boolean hasOutdoor() {
		return outdoor;
	}
	
	public boolean hasReserve() {
		return reservea;
	}
	
	public boolean hasTakeout() {
		return takeout;
	}
	
	public boolean hasWaiter() {
		return waiter;
	}
	
	public boolean hasWalkins() {
		return walkins;
	}
}
