/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads;

/**
 * The possible permission values permitted in a Facebook ad object.
 * 
 * @author Karthick Sankarachary
 */
public enum Permission {
	ACCOUNT_ADMIN(1), ADMANAGER_READ(2), ADMANAGER_WRITE(3), BILLING_READ(4), BILLING_WRITE(
			5), REPORTS(6), TBD(7), TBD2(8);

	private int level;

	Permission(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	public static Permission findByValue(int value) {
		for (Permission status : values()) {
			if (status.getLevel() == value) {
				return status;
			}
		}
		return null;
	}
}
