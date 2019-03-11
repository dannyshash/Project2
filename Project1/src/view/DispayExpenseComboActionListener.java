package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import misc.ExpenseListTableModel;
import model.ExpenseType;

public class DispayExpenseComboActionListener implements ActionListener{
	private JComboBox<ExpenseType> dispayExpenseCombo;
	private ExpenseContentApi contentUpdator;
	ExpenseListTableModel tableModel;
	public DispayExpenseComboActionListener(JComboBox<ExpenseType> comboBox, ExpenseListTableModel tableModel) {
		this.dispayExpenseCombo = comboBox;
		this.tableModel=tableModel;
	}
    public void actionPerformed(ActionEvent event) {
    	if(dispayExpenseCombo.getSelectedItem() ==ExpenseType.PURCHASE ||
    			dispayExpenseCombo.getSelectedItem() ==ExpenseType.COMPOSITE_PURCHASE) {

    		tableModel.refresh(ExpenseType.PURCHASE);
		}
    	else if(dispayExpenseCombo.getSelectedItem() ==ExpenseType.BILL ||
    			dispayExpenseCombo.getSelectedItem() ==ExpenseType.COMPOSITE_BILL) {
    		tableModel.refresh(ExpenseType.BILL);
		} 
		else {
			throw new RuntimeException("Data error"); 
		}		
    }
}
