package view;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.ExpenseSubject;
import model.Bill;
import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;
import model.Purchase;

public class ExpenseObserverImplTest {
	@Mock private ExpenseSubject subject;
	@Mock List<Map<ExpenseKey , Expense>> data;
	@Mock Map<ExpenseKey , Expense> dataItem;
	@Mock Map<ExpenseKey , Expense> p;
	@Mock Map<ExpenseKey , Expense> b;
	@Mock Map<ExpenseKey , Expense> c_p;
	@Mock Map<ExpenseKey , Expense> c_b;

	
	private ExpenseObserver observer = ExpenseObserverImpl.getInstance();

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		observer.init(subject);
	}

	@After
	public void after() {
	}

	@Test
	public void testInit() {
		verify(subject, times(1)).register(observer);

	}

	@Test
	public void testUpdate() {
		when(data.get(0)).thenReturn(dataItem);
		when(data.get(0).size()).thenReturn(0);
		when(data.get(1)).thenReturn(dataItem);
		when(data.get(1).size()).thenReturn(0);
		when(data.get(2)).thenReturn(dataItem);
		when(data.get(2).size()).thenReturn(0);
		when(data.get(3)).thenReturn(dataItem);
		when(data.get(3).size()).thenReturn(0);
		observer.update(data);
		verify(subject, times(1)).resetStateChange();
	}

	@Test
	public void testGetData() {
		DisplayParameters params = new DisplayParameters();

		Expense p1=new Purchase();Expense p2=new Purchase();
		@SuppressWarnings("serial")
		Map<ExpenseKey , Expense> mp = new HashMap<ExpenseKey , Expense>() {{put(p1.getKey(),p1);put(p2.getKey(),p2);}};
		Collection<Expense> cp = mp.values();
		
		Expense cp1=new CompositePurchase(null, null, null, null);
		@SuppressWarnings("serial")
		Map<ExpenseKey , Expense> mcp = new HashMap<ExpenseKey , Expense>() {{put(cp1.getKey(),cp1);}};
		Collection<Expense> ccp = mcp.values();

		Expense b1=new Bill(1, "1",new Date());Expense b2=new Bill(2, "2",new Date());Expense b3=new Bill(3, "3",new Date());
		@SuppressWarnings("serial")
		Map<ExpenseKey , Expense> mb = new HashMap<ExpenseKey , Expense>() {{put(b1.getKey(),b1);put(b2.getKey(),b2);put(b3.getKey(),b3);}};
		Collection<Expense> cb = mb.values();
		
		Expense cb1=new CompositeBill(null, null);
		@SuppressWarnings("serial")
		Map<ExpenseKey , Expense> mcb = new HashMap<ExpenseKey , Expense>() {{put(cb1.getKey(),cb1);}};
		Collection<Expense> ccb = mcb.values();

		when(data.get(0)).thenReturn(p);
		when(data.get(0).size()).thenReturn(2);
		when(data.get(0).values()).thenReturn(cp);
		when(data.get(1)).thenReturn(b);
		when(data.get(1).size()).thenReturn(3);
		when(data.get(1).values()).thenReturn(cb);
		when(data.get(2)).thenReturn(c_p);
		when(data.get(2).size()).thenReturn(1);
		when(data.get(2).values()).thenReturn(ccp);
		when(data.get(3)).thenReturn(c_b);
		when(data.get(3).size()).thenReturn(1);
		when(data.get(3).values()).thenReturn(ccb);
		observer.update(data);

		params.type = ExpenseType.PURCHASE;
		assertEquals(3, observer.getData(params).size());
		params.type = ExpenseType.BILL;		
		assertEquals(4, observer.getData(params).size());
	}

}
