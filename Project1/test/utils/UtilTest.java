package utils;

import model.Mode;
import model.RepitionInterval;
import model.Status;
import org.junit.Test;
import static org.junit.Assert.*;


public class UtilTest {

    @Test
    public void getStatusPaidEnum() {
        Enum result = Util.getStatusEnum("Paid");
        assertEquals(result, Status.PAID);
    }

    @Test
    public void getStatusUnpaidEnum() {
        Enum status = Util.getStatusEnum("Unpaid");
        assertEquals(status, Status.UNPAID);
    }

    @Test(expected = Exception.class)
    public void getUnknownStatusEnum() {
        Enum status = Util.getStatusEnum("xxx");
    }

    @Test
    public void getModeCashEnum() {
        Enum paymentMethod = Util.getModeEnum("Cash");
        assertEquals(paymentMethod, Mode.CASH);
    }

    @Test
    public void getModeDebitEnum() {
        Enum paymentMethod = Util.getModeEnum("Debit");
        assertEquals(paymentMethod, Mode.DEBIT);
    }

    @Test
    public void getModeCreditEnum() {
        Enum paymentMethod = Util.getModeEnum("Credit");
        assertEquals(paymentMethod, Mode.CREDIT);
    }

    @Test(expected = Exception.class)
    public void getUnknownModeEnum() {
        Enum mode = Util.getModeEnum("xxx");
    }

    @Test
    public void getDailyRepetitionIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("Daily");
        assertEquals(interval, RepitionInterval.DAILY);
    }

    @Test
    public void getWeeklyRepetitionIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("Weekly");
        assertEquals(interval, RepitionInterval.WEEKLY);
    }

    @Test
    public void getBiweeklyRepetitionDailyIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("BiWeekly");
        assertEquals(interval, RepitionInterval.BIWEEKLY);
    }

    @Test
    public void getMonthlyRepetitionDailyIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("Monthly");
        assertEquals(interval, RepitionInterval.MONTHLY);
    }

    @Test
    public void getQuarterlyRepetitionDailyIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("Quarterly");
        assertEquals(interval, RepitionInterval.QUARTERLY);
    }

    @Test
    public void getYearlyRepetitionDailyIntervalEnum() {
        Enum interval = Util.getRepitionIntervalEnum("Yearly");
        assertEquals(interval, RepitionInterval.YEARLY);
    }

    @Test(expected = Exception.class)
    public void getUnknownRepetitionIntervalEnum() {
        Enum mode = Util.getModeEnum("xxx");
    }

}