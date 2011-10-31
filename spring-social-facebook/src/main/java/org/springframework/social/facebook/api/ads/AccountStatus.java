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
 * @author Karthick Sankarachary
 */
public enum AccountStatus {
	ACTIVE(1), DISABLED(2), UNSETTLED(3);

	private int value;

	AccountStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static AccountStatus findByValue(int value) {
		for (AccountStatus status : values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
