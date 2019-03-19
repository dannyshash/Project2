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

}
