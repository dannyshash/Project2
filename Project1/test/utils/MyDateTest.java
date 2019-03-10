package utils;

import org.junit.Test;
import java.time.*;
import java.util.Date;
import static org.junit.Assert.*;


public class MyDateTest {

    @Test
    public void getDateString() {

        LocalDate localDate = LocalDate.of(2018,10,19);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String expectedDate = "2018-10-19";
        String actualDate = MyDate.getDateString(date);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getDateTimeString() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 10, 19, 17, 30, 23);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        String actualDate = MyDate.getDateTimeString(date);
        String expectedDate = "2018-10-19 17:30:23";
        assertEquals(actualDate, expectedDate);

    }

    @Test
    public void getJustDate() {
        String d1 = "2019-10-19";
        String actualDate = MyDate.getJustDate(d1).toString();
        String expectedDate = "Sat Oct 19 00:00:00 EDT 2019";

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getDateAndTime() {
        String d1 = "2019-10-19 17:30:23";
        String actualDate = MyDate.getDateAndTime(d1).toString();
        String expectedDate = "Sat Oct 19 17:30:23 EDT 2019";

        assertEquals(actualDate, expectedDate);
    }

    @Test
    public void getRandomDateStr() {
        String d1 = MyDate.getRandomDateStr();
        int day = Integer.parseInt(d1.split("-")[2]);

        assertTrue(day>= 1 && day <= 31);
    }

    @Test
    public void getRandomDate() {
        String d1 = MyDate.getRandomDateStr();
        int day = Integer.parseInt(d1.split("-")[2]);

        assertTrue(day>= 1 && day <= 31);
    }

    @Test
    public void getRandomTimeStr() {
        String d1 = MyDate.getRandomTimeStr();
        String[] time = d1.split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        int second = Integer.parseInt(time[2]);

        assertTrue(hour>= 0  && hour <= 23);
        assertTrue(minute>= 0  && minute <= 59);
        assertTrue(second>= 0  && second <= 59);

    }

}
