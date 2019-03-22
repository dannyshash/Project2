package misc;

import java.util.ArrayList;

import model.Expense;
import view.AddCompExpPanelAddBtnListener;
import view.UserActionsApi;

public class AddCompositeExpensePanel extends AddExpensePanel {

	public AddCompositeExpensePanel(UserActionsApi userActions, AddCompExpPanelAddBtnListener listener) {
		super(userActions, listener);
	}		
}
