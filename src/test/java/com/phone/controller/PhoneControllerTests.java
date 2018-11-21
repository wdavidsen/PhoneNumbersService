package com.phone.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.phone.service.INumberVariantBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.phone.model.PhoneNumber;
import com.phone.model.ReferenceNumber;
import com.phone.service.NumberVariantBuilder;

public class PhoneControllerTests {

	@InjectMocks
	private PhoneController phoneController;
	
	@Mock
	private NumberVariantBuilder numberVariantBuilder;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void homeTest() {
		INumberVariantBuilder variantBuilder = new NumberVariantBuilder();
		IPhoneController controller = new PhoneController(variantBuilder);
		String result = controller.home();
		
		assertEquals(result, "Welcome to the Alpha-Numeric Phone Number Generator");
	}
	
	@Test
	public void generateVariants() {
		PhoneNumber phoneNum = new PhoneNumber(3015553789L);
		//ReferenceNumber refNum = new ReferenceNumber("ref3015553789");
		//ResponseEntity<ReferenceNumber> reference = new ResponseEntity<ReferenceNumber>(refNum, HttpStatus.OK);
		
		when(numberVariantBuilder.buildVariants(phoneNum)).thenReturn("ref3015553789");
		
		ResponseEntity<?> result = phoneController.generateVariants(new PhoneNumber(3015553789L));
				
		ReferenceNumber entity = (ReferenceNumber)result.getBody();

		assert entity != null;
		assertEquals(entity.getNumber(), "ref3015553789");
	}
}
