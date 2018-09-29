package com.phone.model;

import java.util.ArrayList;
import java.util.List;

public class GenerateResult {
	
	int totalCount;
	List<String> numberVariants;
	
	public GenerateResult() {}
	public GenerateResult(List<String> variants) {
		this.totalCount = variants.size();
		this.numberVariants = variants;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<String> getNumberVariants() {
		return numberVariants;
	}
	public void setNumberVariants(List<String> numberVariants) {
		this.numberVariants = numberVariants;
	}	
	
	public GenerateResult getSubset(int start, int take) {
		List<String> subset = new ArrayList<String>();
		
		int end = start + take;
		if (end > numberVariants.size() - 1) {
			end = numberVariants.size() - 1;
		}
		subset = this.numberVariants.subList(start, end);
		return new GenerateResult(subset);		
	}
}
