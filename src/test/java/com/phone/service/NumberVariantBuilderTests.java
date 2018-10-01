package com.phone.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.phone.model.PhoneNumber;

public class NumberVariantBuilderTests {

	@Test
	public void buildVariantsTest() {
		INumberVariantBuilder service = new NumberVariantBuilder();
		String result = service.buildVariants(new PhoneNumber(3015553789L));
		
		assertEquals(result, "ref3015553789");
	}
}
