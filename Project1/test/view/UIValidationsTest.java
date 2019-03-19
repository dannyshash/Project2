package view;

import static org.junit.Assert.*;

import org.junit.Test;

public class UIValidationsTest {

	@Test
	public void testAmountValidation() {
		assertTrue(UIValidations.amountValidation("2.12"));
		assertTrue(UIValidations.amountValidation("0.12"));
		assertTrue(UIValidations.amountValidation("0.00"));
		assertTrue(UIValidations.amountValidation("2222"));
		assertTrue(UIValidations.amountValidation("2."));

		assertFalse(UIValidations.amountValidation("2.123"));
		assertFalse(UIValidations.amountValidation("-2.12"));
		assertFalse(UIValidations.amountValidation("-2"));
	}

	@Test
	public void testDateValidation() {
		assertTrue(UIValidations.dateValidation("2019-03-09"));
		assertTrue(UIValidations.dateValidation("2019-03-19"));
		assertTrue(UIValidations.dateValidation("2019-03-29"));
		assertTrue(UIValidations.dateValidation("2019-12-19"));
		assertTrue(UIValidations.dateValidation("2019-03-2"));
		
		assertFalse(UIValidations.dateValidation("blah"));		
		assertFalse(UIValidations.dateValidation("2019-03-32"));
		assertFalse(UIValidations.dateValidation("2019-13-19"));
		assertFalse(UIValidations.dateValidation("22019-03-19"));
		assertFalse(UIValidations.dateValidation("019-03-19"));
		assertFalse(UIValidations.dateValidation("2019-3-32"));
	}
}
