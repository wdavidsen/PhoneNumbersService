package com.phone.model;

public class GenerateStatus {

	private int totalNumbers;
	private boolean ready;
	private boolean missing;
	
	public boolean isMissing() {
		return missing;
	}
	public void setMissing(boolean missing) {
		this.missing = missing;
	}
	public int getTotalNunbers() {
		return totalNumbers;
	}
	public void setTotalNunbers(int totalNunbers) {
		this.totalNumbers = totalNunbers;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
