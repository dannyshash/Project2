package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FileLoaderImplTest {


    private List<Map<ExpenseKey, Expense>> expenseData;
    private FileLoaderImpl fileLoader;

    @Before
    public void setUp() throws Exception {
        ExpenseKey expenseKey = new ExpenseKey(ExpenseType.PURCHASE, 5.55, "cocobun", new Date(2019,1,1));
        Expense expense1 = new Purchase();

        ExpenseKey expenseKey2 = new ExpenseKey(ExpenseType.BILL, 8.5, "Internet", new Date(2018,12,1));
        Expense expense2 = new Bill();

        ExpenseKey expenseKey3 = new ExpenseKey(ExpenseType.COMPOSITE_BILL, 300, "Internet", new Date(2018,12,12));
        Expense expense3 = new CompositeBill("composite_bill", ExpenseCategories.INTERNET);

        ExpenseKey expenseKey4 = new ExpenseKey(ExpenseType.COMPOSITE_PURCHASE, 200, "Restaurant", new Date(2018,12,1));
        Expense expense4 = new CompositePurchase("composite_purchase", ExpenseCategories.Food, Mode.CREDIT, "Downtown");

        fileLoader = new FileLoaderImpl("SampleExpensesData.csv");
        expenseData = new ArrayList<>();
        Map<ExpenseKey, Expense> purchaseMap = new HashMap<>();
        Map<ExpenseKey, Expense> billMap = new HashMap<>();
        Map<ExpenseKey, Expense> compositeBillMap = new HashMap<>();
        Map<ExpenseKey, Expense> compositePurchaseMap = new HashMap<>();

        purchaseMap.put(expenseKey, expense1);
        billMap.put(expenseKey2, expense2);
        compositeBillMap.put(expenseKey3, expense3);
        compositePurchaseMap.put(expenseKey4, expense4);

        expenseData.add(purchaseMap);
        expenseData.add(billMap);
        expenseData.add(compositeBillMap);
        expenseData.add(compositePurchaseMap);


    }

    @Test
    public void dataLoad() {
        fileLoader.dataLoad(expenseData);
    }
}