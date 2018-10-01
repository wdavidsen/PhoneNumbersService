package com.phone.service;

import com.phone.model.GenerateResult;
import com.phone.model.GenerateStatus;
import com.phone.model.PhoneNumber;

public interface INumberVariantBuilder {

	String buildVariants(PhoneNumber number);
	GenerateStatus readyCheck(String referenceId);
	GenerateResult getVariants(String referenceId, int start, int take);
}