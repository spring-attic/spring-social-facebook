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
 * The possible gender values used in a Facebook ads object.
 * 
 * @author Karthick Sankarachary
 */
public enum Gender implements Valuable {
    ALL(0), MALE(1), FEMALE(2);

    private int value;

    private Gender(int value) {
        this.value = value;
    }
    
    public int getValue() {
    	return value;
    }

    public static Gender findByValue(int value) {
        for (Gender type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

    public String toString() {
		return String.valueOf(value);
	}
}
