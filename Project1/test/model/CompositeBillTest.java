package model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import utils.MyDate;

public class CompositeBillTest {
	@Mock Expense b1; @Mock Expense b2; @Mock Expense b3;
	@Mock ExpenseKey bk1; @Mock ExpenseKey bk2; @Mock ExpenseKey bk3;
	@Mock Expense b4; @Mock Expense b5; @Mock Expense b6;
	@Mock ExpenseKey bk4; @Mock ExpenseKey bk5; @Mock ExpenseKey bk6;
	
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
		CompositeBill test = new CompositeBill("test", ExpenseCategories.DAFAULT);
		comp_bill.add(b1); comp_bill.add(b2); 
		comp_bill.add(test);
		test.add(b3); test.add(b4);
		when(b1.getType()).thenReturn(ExpenseType.BILL);
		when(b2.getType()).thenReturn(ExpenseType.BILL);
		when(b3.getType()).thenReturn(ExpenseType.BILL);
		when(b4.getType()).thenReturn(ExpenseType.BILL);
		
		ArrayList<Expense> ae = comp_bill.getBillsList();		
		assertEquals(5, ae.size());
		ArrayList<Expense> tae = test.getBillsList();
		assertEquals(2, tae.size());
	}

	@Test
	public void testGetMatchedBill() {
		CompositeBill test1 = new CompositeBill("test1", ExpenseCategories.DAFAULT);
		CompositeBill test2 = new CompositeBill("test2", ExpenseCategories.DAFAULT);
		Expense fb1 = new Bill(63.14, "hydro", MyDate.getJustDate("2019-03-11"));
		Expense fb2 = new Bill(74.14, "cellphone", MyDate.getJustDate("2019-03-11"));
		Expense fb3 = new Bill(85.14, "cabel", MyDate.getJustDate("2019-03-11"));
		comp_bill.add(b1); comp_bill.add(b2); comp_bill.add(fb1);
		test2.add(b5);test2.add(fb3);
		test1.add(b3); test1.add(b4); test1.add(test2); test1.add(fb2);
		comp_bill.add(test1);
		when(b1.getType()).thenReturn(ExpenseType.BILL);
		when(b1.iseqal(any(Expense.class))).thenReturn(false);
		when(b2.getType()).thenReturn(ExpenseType.BILL);
		when(b2.iseqal(any(Expense.class))).thenReturn(false);
		when(b3.getType()).thenReturn(ExpenseType.BILL);
		when(b3.iseqal(any(Expense.class))).thenReturn(false);
		when(b4.getType()).thenReturn(ExpenseType.BILL);
		when(b4.iseqal(any(Expense.class))).thenReturn(false);
		when(b5.getType()).thenReturn(ExpenseType.BILL);
		when(b5.iseqal(any(Expense.class))).thenReturn(false);
		
		assertEquals(10, comp_bill.getBillsList().size());
		assertEquals(6, test1.getBillsList().size());
		assertEquals(2, test2.getBillsList().size());
		
		assert(fb1.iseqal(comp_bill.find(fb1)));
		assert(fb2.iseqal(comp_bill.find(fb2)));
		assert(test1.iseqal(comp_bill.find(test1)));
		assert(fb3.iseqal(comp_bill.find(fb3)));
		assertEquals(test2, comp_bill.find(test2));
	}

}
