package com.phone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.phone.model.GenerateResult;
import com.phone.model.GenerateStatus;
import com.phone.model.PhoneNumber;

@Service
public class NumberVariantBuilder {

	private static Map<String, GenerateResult> cache; 
	private static String keyMappings[][] = {
			{"0"},
	        {"1"}, 
	        {"2", "A", "B", "C"}, 
	        {"3", "D", "E", "F"}, 
	        {"4", "G", "H", "I"},
	        {"5", "J", "K", "L"}, 
	        {"6", "M", "N", "O"}, 
	        {"7", "P", "Q", "R", "S"}, 
	        {"8", "T", "U", "V"}, 
	        {"9", "W", "X", "Y", "Z"}
	    };
	
	NumberVariantBuilder() {
		cache = new HashMap<String, GenerateResult>();
	}
	
	public String BuildVariants(PhoneNumber number) {
		String num = Long.toString(number.getNumber());
		
		if (num.length() < 7 || num.length() > 11) {
			throw new IllegalArgumentException("Phone number must be between 7 and 11 digits.");
		}
		
		String refId = "ref" + num;
		Generate(refId, number.getNumber());
		
		return refId;
	}
	
	public GenerateStatus ReadyCheck(String referenceId) {
		GenerateStatus status = new GenerateStatus();
				
		if (!cache.containsKey(referenceId)) {
			status.setMissing(true);
			return status;
		}
		
		status.setReady(cache.get(referenceId) != null);
				
		if (status.isReady())	{
			status.setTotalNunbers(cache.get(referenceId).getTotalCount());
		}		
		return status;
	}
	
	public GenerateResult GetVariants(String referenceId, int start, int take) {
		if (!cache.containsKey(referenceId)) throw new IllegalArgumentException("Reference id not found: " + referenceId);
		if (start < 0) throw new IllegalArgumentException("Start index must be 0 or greater.");
		if (take < 1) throw new IllegalArgumentException("Take number must be 1 or greater.");
		
		return cache.get(referenceId).getSubset(start, take);
	}
	
	private void Generate(String referenceId, long number)	{	
		if (cache.containsKey(referenceId)) return;
		
		List<String> combos = new ArrayList<String>();
			
		cache.put(referenceId, null);
		
		Generate_recur(combos, Long.toString(number), "");
		GenerateResult genResults = new GenerateResult(combos);	
		
		cache.put(referenceId, genResults);
	}
	
	private void Generate_recur(List<String> combos, String endingChars, String beginningChars)	{
		
        int digit = Integer.parseInt(endingChars.substring(0, 1));
        
        if (endingChars.length() > 1) {
        	for (int i = 0; i < keyMappings[digit].length; i++) {
            	Generate_recur(combos, endingChars.substring(1), beginningChars + keyMappings[digit][i]);
            }
        }
        else {
        	for (int i = 0; i < keyMappings[digit].length; i++) {
                combos.add(beginningChars + keyMappings[digit][i]);
            }
        }
	}
}
