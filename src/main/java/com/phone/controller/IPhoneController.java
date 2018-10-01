package com.phone.controller;

import org.springframework.http.ResponseEntity;
import com.phone.model.PhoneNumber;

public interface IPhoneController  {

	String home();
	ResponseEntity<?> generateVariants(PhoneNumber phoneNumber);
	ResponseEntity<?> getStatus(String referenceNumber);
	ResponseEntity<?> getPhoneNumberVariants(String referenceNumber, int start, int take);
}