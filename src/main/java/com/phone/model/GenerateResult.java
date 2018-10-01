package com.phone.model;

import java.util.List;

public class GenerateResult {
	
	long totalCount;
	List<String> numberVariants;
	
	public GenerateResult() {}
	public GenerateResult(List<String> variants) {
		this.totalCount = variants.size();
		this.numberVariants = variants;
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public List<String> getNumberVariants() {
		return numberVariants;
	}
	public void setNumberVariants(List<String> numberVariants) {
		this.numberVariants = numberVariants;
	}	
	
	public GenerateResult getSubset(int start, int take) {
		
		int end = start + take;
		if (end > numberVariants.size() - 1) {
			end = numberVariants.size() - 1;
		}
		GenerateResult subset = new GenerateResult(this.numberVariants.subList(start, end));
		subset.setTotalCount(this.getTotalCount());
		
		return subset;		
	}
}
