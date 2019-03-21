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

import java.util.List;

/**
 * Domain type carrying lists payment price points.
 * Currently only includes payment price points for mobile.
 * 
 * @author Craig Walls
 */
public class PaymentPricePoints extends FacebookObject {

	private List<PaymentPricePoint> mobile;
	
	/**
	 * @return a list of {@link PaymentPricePoint} for mobile.
	 */
	public List<PaymentPricePoint> getMobile() {
		return mobile;
	}
	
}
