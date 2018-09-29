package com.phone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phone.model.GenerateResult;
import com.phone.model.GenerateStatus;
import com.phone.model.PhoneNumber;
import com.phone.model.ReferenceNumber;
import com.phone.model.SimpleMessage;
import com.phone.service.NumberVariantBuilder;

@RestController
public class PhoneController {

	@Autowired
	private NumberVariantBuilder numberVariantBuilder;
	
	@RequestMapping("/")
	public String home() {
		return "Welcome to the Alpha-Numeric Phone Number Generator";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/variants", produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> getVariants(@RequestBody PhoneNumber phoneNumber) {
		
		try {
			String refNum = numberVariantBuilder.BuildVariants(phoneNumber);
			ReferenceNumber reference = new ReferenceNumber(refNum);
			return new ResponseEntity<ReferenceNumber>(reference, HttpStatus.OK);
		}
		catch (IllegalArgumentException ex) {
			return new ResponseEntity<SimpleMessage>(new SimpleMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/variants/{referenceNumber}/status", produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> getStatus(@PathVariable String referenceNumber) {
		GenerateStatus status = numberVariantBuilder.ReadyCheck(referenceNumber);
		return new ResponseEntity<GenerateStatus>(status, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/variants/{referenceNumber}", params={"start", "take"}, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> getPhoneNumberVariants(@PathVariable String referenceNumber, @RequestParam int start, @RequestParam int take) {
		
		if (!numberVariantBuilder.ReadyCheck(referenceNumber).isReady()) {
			final String msg = "Your variants are not ready yet.";
			return new ResponseEntity<SimpleMessage>(new SimpleMessage(msg), HttpStatus.BAD_REQUEST);
		}
		
		try {
			GenerateResult results = numberVariantBuilder.GetVariants(referenceNumber, start, take);
			return new ResponseEntity<GenerateResult>(results, HttpStatus.OK);
		}
		catch (IllegalArgumentException ex) {			
			return new ResponseEntity<SimpleMessage>(new SimpleMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
		catch (Exception ex) {
			final String msg = "We're sorry, but an unexpected error occurred.";
			return new ResponseEntity<SimpleMessage>(new SimpleMessage(msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}