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
package org.springframework.social.facebook.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utilities for various <code>java.util.Collection</code> implementations
 *
 * @author Will Taylor
 *
 */
class Collections2 {

    private Collections2() {} // private to prevent instantiation

    static <T> List<T> immutable(List<T> list) {
        return list == null ? list : Collections.unmodifiableList(new ArrayList<T>(list));
    }

    static <T> List<T> copy(List<T> list) {
        return list == null ? list : new ArrayList<T>(list);
    }

    static <K, V> Map<K, V> immutable(Map<K, V> map) {
        return map == null ? null : Collections.unmodifiableMap(new HashMap<K, V>(map));
    }

    static <K, V> Map<K, V> copy(Map<K, V> map) {
        return map == null ? null : new HashMap<K, V>(map);
    }

}
