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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for converting between raw and enum Facebook values.
 * 
 * @author Karthick Sankarachary
 * 
 */
public class EnumUtil {

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> List<T> getEnumList(String values,
			Class<T> enumClass) {
		List<T> list = new ArrayList<T>();
		if (values != null) {
			try {
				Method valuesMethod = enumClass.getMethod("values");
				T[] enumValues = (T[]) valuesMethod.invoke(null);

				for (String value : values.split(",")) {
					for (T enumValue : enumValues) {
						if (enumValue instanceof Valuable) {
							if (((Valuable) enumValue).getValue() == Integer
									.parseInt(value)) {
								list.add(enumValue);
							}
						}
					}
				}
			} catch (Exception e) {

			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(getEnumList("1,0", Gender.class));
		System.out.println(getEnumList("1,0", RelationshipStatus.class));
		System.out.println(getEnumList("1,0", UserEvent.class));
		System.out.println(getEnumList("1,0", EducationStatus.class));
	}

}
