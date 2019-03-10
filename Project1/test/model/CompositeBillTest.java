package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CompositeBillTest {
	@Mock Expense b1; @Mock Expense b2; @Mock Expense b3;
	@Mock ExpenseKey bk1; @Mock ExpenseKey bk2; @Mock ExpenseKey bk3;
	
	private CompositeBill comp_bill = new CompositeBill("cb", ExpenseCategories.DAFAULT);

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void after() {
	}

	@Test
	public void testAdd() {
		comp_bill.add(b1);
		assertEquals(comp_bill.getNoOfSubItems(), 1);
	}

	@Test
	public void testRemove() {
		comp_bill.add(b1);
		assertEquals(comp_bill.getNoOfSubItems(), 1);
		comp_bill.add(b2);
		assertEquals(comp_bill.getNoOfSubItems(), 2);
		comp_bill.remove(b1);
		assertEquals(1, comp_bill.getNoOfSubItems());
	}

	@Test
	public void testGet() {
		comp_bill.add(b1);
		comp_bill.add(b2);
		when(b1.getKey()).thenReturn(bk1);
		when(b2.getKey()).thenReturn(bk2);
		when(b3.getKey()).thenReturn(bk3);
		assertEquals(comp_bill.get(b1), b1);
		assertEquals(comp_bill.get(b2), b2);
		assertEquals(comp_bill.get(b3), null);	
	}

	@Test
	public void testIseqal() {
		final CompositeBill test = new CompositeBill("test", ExpenseCategories.DAFAULT);
		assertFalse(comp_bill.iseqal(test));	
	}

	@Test
	public void testGetBillsList() {
		//TODO
	}

	@Test
	public void testGetMatchedBill() {
		//TODO
	}

}
