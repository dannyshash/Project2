package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CompositePurchaseTest {
	@Mock Expense p1; @Mock Expense p2; @Mock Expense p3;
	@Mock ExpenseKey pk1; @Mock ExpenseKey pk2; @Mock ExpenseKey pk3;
	
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
		test.add(p1); test.add(p2);
		comp_purchase.add(p1); comp_purchase.add(p2); comp_purchase.add(test);
		//ArrayList<Expense> ae = comp_purchase.getPurchasesList();
		//assertEquals(3, ae.size());
	}

	@Test
	public void testGetMatchedPurchase() {
		//TODO
	}

}
