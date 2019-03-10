package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import utils.MyDate;

public class CompositePurchaseTest {
	@Mock Expense p1; @Mock Expense p2; @Mock Expense p3;
	@Mock Expense p4; @Mock Expense p5; @Mock Expense p6;
	@Mock ExpenseKey pk1; @Mock ExpenseKey pk2; @Mock ExpenseKey pk3;
	@Mock ExpenseKey pk4; @Mock ExpenseKey pk5; @Mock ExpenseKey pk6;
	
	private CompositePurchase comp_purchase = new CompositePurchase("cp", ExpenseCategories.DAFAULT, Mode.CASH, "comp_purchase");

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void after() {
	}

	@Test
	public void testAdd() {
		comp_purchase.add(p1);
		assertEquals(comp_purchase.getNoOfSubItems(), 1);
	}

	@Test
	public void testRemove() {
		comp_purchase.add(p1);
		assertEquals(comp_purchase.getNoOfSubItems(), 1);
		
		comp_purchase.add(p2);
		assertEquals(comp_purchase.getNoOfSubItems(), 2);
		
		comp_purchase.remove(p1);
		assertEquals(comp_purchase.getNoOfSubItems(), 1);
	}

	@Test
	public void testGet() {
		comp_purchase.add(p1);
		comp_purchase.add(p2);
		when(p1.getKey()).thenReturn(pk1);
		when(p2.getKey()).thenReturn(pk2);
		when(p3.getKey()).thenReturn(pk3);
		
		assertEquals(comp_purchase.get(p1), p1);
		assertEquals(comp_purchase.get(p2), p2);
		assertEquals(comp_purchase.get(p3), null);
	}

	@Test
	public void testIseqal() {
		final CompositePurchase test = new CompositePurchase("test", ExpenseCategories.DAFAULT, Mode.CASH, "test");
		assertFalse(comp_purchase.iseqal(test));
	}

	@Test
	public void testSetItems() {
		ArrayList<Expense> ae = new ArrayList<Expense>();
		ae.add(p1);ae.add(p2);
		comp_purchase.setItems(ae);
		
		assertEquals(2, comp_purchase.getNoOfSubItems());
	}

	@Test
	public void testGetPurchasesList() {
		CompositePurchase test = new CompositePurchase("test", ExpenseCategories.DAFAULT, Mode.CASH, "test");
		comp_purchase.add(p1); comp_purchase.add(p2); 
		comp_purchase.add(test);
		test.add(p3); test.add(p4);
		when(p1.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p2.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p3.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p4.getType()).thenReturn(ExpenseType.PURCHASE);
		
		ArrayList<Expense> ae = comp_purchase.getPurchasesList();
		assertEquals(5, ae.size());
		ArrayList<Expense> tae = test.getPurchasesList();
		assertEquals(2, tae.size());
	}

	@Test
	public void testGetMatchedPurchase() {
		CompositePurchase test1 = new CompositePurchase("test1", ExpenseCategories.DAFAULT, Mode.CASH, "test1");
		CompositePurchase test2 = new CompositePurchase("test2", ExpenseCategories.DAFAULT, Mode.CASH, "test2");
		Expense fp1 = new Purchase(3.14, "tea", MyDate.getJustDate("2019-03-11"));
		Expense fp2 = new Purchase(4.14, "coffee", MyDate.getJustDate("2019-03-11"));
		Expense fp3 = new Purchase(5.14, "bagel", MyDate.getJustDate("2019-03-11"));
		comp_purchase.add(p1); comp_purchase.add(p2); comp_purchase.add(fp1);
		test2.add(p5);test2.add(fp3);
		test1.add(p3); test1.add(p4); test1.add(test2); test1.add(fp2);
		comp_purchase.add(test1);
		when(p1.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p1.iseqal(any(Expense.class))).thenReturn(false);
		when(p2.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p2.iseqal(any(Expense.class))).thenReturn(false);
		when(p3.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p3.iseqal(any(Expense.class))).thenReturn(false);
		when(p4.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p4.iseqal(any(Expense.class))).thenReturn(false);
		when(p5.getType()).thenReturn(ExpenseType.PURCHASE);
		when(p5.iseqal(any(Expense.class))).thenReturn(false);
		
		assertEquals(10, comp_purchase.getPurchasesList().size());
		assertEquals(6, test1.getPurchasesList().size());
		assertEquals(2, test2.getPurchasesList().size());
		
		assert(fp1.iseqal(comp_purchase.find(fp1)));
		assert(fp2.iseqal(comp_purchase.find(fp2)));
		assert(test1.iseqal(comp_purchase.find(test1)));
		assert(fp3.iseqal(comp_purchase.find(fp3)));
		assertEquals(test2, comp_purchase.find(test2));
	}

}
