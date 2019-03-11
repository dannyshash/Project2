package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Expense;
import model.ExpenseKey;
import view.ExpenseObserver;

public class ExpenseContainerImplTest {

	@Mock Store mockStore; @Mock Store mockStore2;
	@Mock ExpenseObserver observer;
	@Mock List<Map<ExpenseKey,Expense>> data;
	
	private ExpenseContainerApi container = (ExpenseContainerApi)ExpenseContainerImpl.getInstance();
	private ExpenseSubject subject = (ExpenseSubject)ExpenseContainerImpl.getInstance();
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		//container.init(mockStore);
	}

	@After
	public void after() {
	}


	@Test
	public void testInit() {
		container.init(mockStore);
		assertEquals(mockStore, container.getStore());
	}

	@Test
	public void testStart() {
		container.init(mockStore);

		subject.register(observer);
		subject.start();
		when(mockStore.getAll()).thenReturn(data);
		verify(mockStore, times(1)).getAll();
		//verify(observer, times(1)).update(data);
	}

	@Test
	public void testRegister() {
		testStart();
	}

	@Test
	public void testResetStateChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPurchases() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompositePurchases() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBills() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompositeBills() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddExpense() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddExpenseIntoComposite() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddExpensesIntoComposite() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyExpense() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveExpense() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveExpenseFromComposite() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExpenses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMatching() {
		fail("Not yet implemented");
	}

}
