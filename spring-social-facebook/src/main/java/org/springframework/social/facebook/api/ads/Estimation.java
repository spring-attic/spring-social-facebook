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
 * The <code>Estimation</code> object captures the expected prices for a given
 * location.
 * 
 * @author Karthick Sankarachary
 * 
 */
public class Estimation {
	private int location;
	private int cpcMin;
	private int cpcMedian;
	private int cpcMax;
	private int cpmMin;
	private int cpmMedian;
	private int cpmMax;

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getCpcMin() {
		return cpcMin;
	}

	public void setCpcMin(int cpcMin) {
		this.cpcMin = cpcMin;
	}

	public int getCpcMedian() {
		return cpcMedian;
	}

	public void setCpcMedian(int cpcMedian) {
		this.cpcMedian = cpcMedian;
	}

	public int getCpcMax() {
		return cpcMax;
	}

	public void setCpcMax(int cpcMax) {
		this.cpcMax = cpcMax;
	}

	public int getCpmMin() {
		return cpmMin;
	}

	public void setCpmMin(int cpmMin) {
		this.cpmMin = cpmMin;
	}

	public int getCpmMedian() {
		return cpmMedian;
	}

	public void setCpmMedian(int cpmMedian) {
		this.cpmMedian = cpmMedian;
	}

	public int getCpmMax() {
		return cpmMax;
	}

	public void setCpmMax(int cpmMax) {
		this.cpmMax = cpmMax;
	}
}
