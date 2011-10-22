package org.springframework.social.facebook.api.ads;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

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
