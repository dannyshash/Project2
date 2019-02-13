import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExpenseListTest {
    private ExpenseList expenseList;
    private Purchase purchase;

    @Before
    public void setUp() throws Exception {
        expenseList = new ExpenseList();
        purchase = new Purchase("Purchase",
                new Date(2019,1,1,0,0),"Coco Bun",5.5,
                "Paid","Montreal","credit card",
                new Date(2019,2,1));
        expenseList.add(purchase);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        assertEquals(purchase.getLocation(),"Montreal");
        assertEquals(purchase.getMethod(),"credit card");

    }

    @Test
    public void remove() {

        expenseList.remove(0);
        assertEquals(expenseList.getSize(), 0);
    }

    @Test
    public void markPaidUnpaid() {
        expenseList.markPaidUnpaid(0);
        assertEquals(expenseList.getList().get(0).getStatus(), "Unpaid");
    }

    @Test
    public void getSize() {
        assertEquals(expenseList.getSize(), 1);
        expenseList.getSize();
    }
}
