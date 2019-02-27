package view;

import model.Expense;

/**
 * UI input validation utilities
 * Here we do the system logic, in addition to simple validations after the viewer
 *
 */
public interface UserInputValidation {
	UIInputValidationError validate(Expense exp);
	UIInputValidationError validateToModify(Expense from, Expense to);
}
