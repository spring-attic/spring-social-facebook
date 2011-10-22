package org.springframework.social.facebook.api.ads;

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
