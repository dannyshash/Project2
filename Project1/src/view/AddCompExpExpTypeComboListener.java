package view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import model.ExpenseType;

public class AddCompExpExpTypeComboListener extends AddExpExpTypeComboListener {
	private ExpenseType expToCreate;
	
	public AddCompExpExpTypeComboListener(ExpenseType expToCreate) {
		this.expToCreate = (expToCreate == ExpenseType.PURCHASE)? ExpenseType.COMPOSITE_PURCHASE:ExpenseType.COMPOSITE_BILL;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		final AddExpensePanel addPanel = (AddExpensePanel)SwingUtilities.windowForComponent((JComboBox<ExpenseType>)e.getSource());

		if (e.getStateChange() == ItemEvent.SELECTED) {
			ExpenseType selectedType = (ExpenseType)e.getItem();
						
			if(expToCreate != selectedType) {
				addPanel.lblTypeLabel.setForeground(Color.RED);
				addPanel.btnAddExpense1.setEnabled(false);
			} else {
				addPanel.lblTypeLabel.setForeground(new Color(0,0,0));
				addPanel.btnAddExpense1.setEnabled(true);
			}
	   }	
	}

}
