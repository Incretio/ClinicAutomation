package gb.internship.utils;

import org.joda.time.DateTime;

import java.util.Date;

public class TimeRangeHelper {

    private static DateTime startDateTime = new DateTime(2000, 1, 1, 0, 0);

    public static int toDaysPast(Date date) {
        DateTime dateTime = new DateTime(date.getTime() - startDateTime.getMillis());
        return dateTimeToDays(dateTime);
    }

    public static Date toDate(int daysAfter2000) {
        return new DateTime(startDateTime.getMillis()).plusDays(daysAfter2000).toDate();
    }

    private static int dateTimeToDays(DateTime dateTime) {
        return (int) (dateTime.getMillis() / 1000 / 60 / 60 / 24);
    }

    public static int takeMonday(int weekOffset) {
        int now = TimeRangeHelper.toDaysPast(new Date());
        int withOffset = now + (weekOffset * 7);
        return (withOffset - (new DateTime().getDayOfWeek() - 1));
    }

}
