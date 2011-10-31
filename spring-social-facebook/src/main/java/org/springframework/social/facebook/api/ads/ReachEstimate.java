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

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The <code>ReachEstimate</code> not only tells you how many potential users a
 * certain {@link Targeting} object can reach, but also the estimated prices for
 * bids and impressions.
 * 
 * @author Karthick Sankarachary
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReachEstimate {
	private long users;
	private List<Estimation> bidEstimations;
	private List<Estimation> impEstimates;

	public long getUsers() {
		return users;
	}

	public void setUsers(long users) {
		this.users = users;
	}

	public List<Estimation> getBidEstimations() {
		return bidEstimations;
	}

	public void setBidEstimations(List<Estimation> bidEstimations) {
		this.bidEstimations = bidEstimations;
	}

	public List<Estimation> getImpEstimates() {
		return impEstimates;
	}

	public void setImpEstimates(List<Estimation> impEstimates) {
		this.impEstimates = impEstimates;
	}

}
