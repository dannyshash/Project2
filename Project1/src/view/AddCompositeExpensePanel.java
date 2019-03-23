package view;

import model.ExpenseType;

public class AddCompositeExpensePanel extends AddExpensePanel {
	private AddExpPanelAddBtnListener listener;

	public AddCompositeExpensePanel(UserActionsApi userActions, AddCompExpPanelAddBtnListener listener, AddCompExpExpTypeComboListener expTypeListener) {
		super(userActions, listener, expTypeListener);
		this.listener = listener;
		ExpenseType expToCreate = (listener.getType() == ExpenseType.PURCHASE)? ExpenseType.COMPOSITE_PURCHASE:ExpenseType.COMPOSITE_BILL;
		
		setTitle("Add Composite Expense Panel");
		
		expTypeCombo.setSelectedIndex(expToCreate.ordinal());
		expTypeCombo.addItemListener(expTypeListener);
	}		
}
