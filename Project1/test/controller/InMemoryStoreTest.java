package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InMemoryStoreTest {

    private InMemoryStore inMemoryStore;

    @Before
    public void setUp() throws Exception {
        FileLoaderImpl dataLoader = new FileLoaderImpl("SampleExpensesData.csv");
        inMemoryStore = new InMemoryStore(dataLoader);
    }

    @Test
    public void put() throws IOException {
        Purchase purchase = new Purchase();
        Bill bill = new Bill();
        CompositeBill compositeBill = new CompositeBill("Internet", ExpenseCategories.INTERNET);
        CompositePurchase compositePurchase = new CompositePurchase("composite_purchase", ExpenseCategories.Food, Mode.CREDIT, "Downtown");

        inMemoryStore.put(purchase);
        inMemoryStore.put(bill);
        inMemoryStore.put(compositeBill);
        inMemoryStore.put(compositePurchase);
        List<Map<ExpenseKey, Expense>> list = inMemoryStore.getAll();

        Map<ExpenseKey, Expense> purchaseMap = list.get(0);
        Map<ExpenseKey, Expense> billMap = list.get(1);
        Map<ExpenseKey, Expense> compositePurchaseMap = list.get(2);
        Map<ExpenseKey, Expense> compositeBillMap = list.get(3);

        assertEquals(list.get(0).size(), purchaseMap.size());
        assertEquals(list.get(1).size(), billMap.size());
        assertEquals(list.get(2).size(), compositePurchaseMap.size());
        assertEquals(list.get(3).size(), compositeBillMap.size());

    }

    @Test
    public void remove() throws IOException {
        Purchase purchase = new Purchase(5.55, "cocobun", new Date(2019,12,12));
        inMemoryStore.remove(purchase);
        List<Map<ExpenseKey, Expense>> list = inMemoryStore.getAll();
        int purchaseMapSize = inMemoryStore.getAll().get(0).size();
        assertEquals(list.size() + 1, purchaseMapSize);
    }

    @Test
    public void modify() {
        Purchase purchase1 = new Purchase(2.28, "Coffee", new Date(2019,2,2));
        Purchase purchase2 = new Purchase(7.55, "cocobun", new Date(2019,12,12));

        inMemoryStore.modify(purchase1, purchase2);

        Map<ExpenseKey, Expense> purchaseMap = inMemoryStore.getAll().get(0);

        ExpenseKey expenseKey = new ExpenseKey(ExpenseType.PURCHASE, 2.28, "Coffee", new Date(2019,2,2));

        assertEquals(purchaseMap.get(expenseKey).getType(), ExpenseType.PURCHASE);
        assertEquals(purchaseMap.get(expenseKey).getName(), "cocobun");

    }

}