package utils;

import model.Mode;
import model.RepitionInterval;
import model.Status;
import org.junit.Test;
import static org.junit.Assert.*;


public class UtilTest {

    @Test
    public void getStatusPaidEnum() {
        Status result = Util.getStatusEnum("Paid");
        assertEquals(result, Status.PAID);
    }

    @Test
    public void getStatusUnpaidEnum() {
        Status status = Util.getStatusEnum("Unpaid");
        assertEquals(status, Status.UNPAID);
    }

    @Test(expected = Exception.class)
    public void getUnknownStatusEnum() {
        Status status = Util.getStatusEnum("xxx");
    }

    @Test
    public void getModeCashEnum() {
        Mode paymentMethod = Util.getModeEnum("Cash");
        assertEquals(paymentMethod, Mode.CASH);
    }

    @Test
    public void getModeDebitEnum() {
        Mode paymentMethod = Util.getModeEnum("Debit");
        assertEquals(paymentMethod, Mode.DEBIT);
    }

    @Test
    public void getModeCreditEnum() {
        Mode paymentMethod = Util.getModeEnum("Credit");
        assertEquals(paymentMethod, Mode.CREDIT);
    }

    @Test(expected = Exception.class)
    public void getUnknownModeEnum() {
        Mode mode = Util.getModeEnum("xxx");
    }

    @Test
    public void getDailyRepetitionIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("Daily");
        assertEquals(interval, RepitionInterval.DAILY);
    }

    @Test
    public void getWeeklyRepetitionIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("Weekly");
        assertEquals(interval, RepitionInterval.WEEKLY);
    }

    @Test
    public void getBiweeklyRepetitionDailyIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("BiWeekly");
        assertEquals(interval, RepitionInterval.BIWEEKLY);
    }

    @Test
    public void getMonthlyRepetitionDailyIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("Monthly");
        assertEquals(interval, RepitionInterval.MONTHLY);
    }

    @Test
    public void getQuarterlyRepetitionDailyIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("Quarterly");
        assertEquals(interval, RepitionInterval.QUARTERLY);
    }

    @Test
    public void getYearlyRepetitionDailyIntervalEnum() {
        RepitionInterval interval = Util.getRepitionIntervalEnum("Yearly");
        assertEquals(interval, RepitionInterval.YEARLY);
    }

    @Test(expected = Exception.class)
    public void getUnknownRepetitionIntervalEnum() {

        Mode mode = Util.getModeEnum("xxx");
    }

}