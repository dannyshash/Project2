package model;

import java.util.Date;

public class ExpenseKey {
	public ExpenseType type;
	public double amount;
	public String expName;
	public Date expDate;
	
	public ExpenseKey(ExpenseType type, double amount, String name, Date date) {
		this.type = type;
		this.amount = amount;
		this.expName = name;
		this.expDate = date;
	}
	
	@Override   
	public boolean equals(Object obj) {
		if (!(obj instanceof ExpenseKey))
			return false;
		ExpenseKey ref = (ExpenseKey) obj;
		return (this.type == ref.type &&
					(this.amount == ref.amount) && 
					this.expName.equals(ref.expName) &&
					this.expDate.equals(ref.expDate));
   }

    @Override
    public int hashCode() {
        return new Double(amount).hashCode() ^ expName.hashCode() ^ expDate.hashCode();
    }
    
    @Override
    public String toString() {
    	System.out.println();
    	return "Expense Key: type="+type+",name="+expName+",amount="+amount+",date="+expDate;
    }
}
