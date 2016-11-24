package com.vlab.androidexample;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalendarTest {
    String[] weekdays = {"Mon", "Tue" , "Wed", "Thu", "Fri", "Sat", "Sun"};
    int[] weekdayValues = {
            Calendar.MONDAY,
            Calendar.TUESDAY ,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.SATURDAY,
            Calendar.SUNDAY
    };
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testWeekCalendar() {
        Calendar starCalendar = Calendar.getInstance();
        starCalendar.set(Calendar.MONTH, 4);
        starCalendar.set(Calendar.DAY_OF_MONTH, 1);
        starCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        System.out.println("getDisplayName DAY_OF_WEEK " + starCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));

        int actualWeekOfMonth = starCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println("actualWeekOfMonth " + actualWeekOfMonth);

        Calendar nowCal = Calendar.getInstance();
        int selectMonth = starCalendar.get(Calendar.MONTH);

        // header
        for (int i = 0; i < weekdayValues.length; i++) {
            System.out.print(" " + weekdays[i]);
        }

        for (int week = 1; week <= actualWeekOfMonth; week++) {
            System.out.println("\n-----------------------------");
            generateWeek(starCalendar, nowCal, selectMonth, week);
        }
    }

    private void generateWeek(Calendar starCalendar, Calendar nowCal, int selectMonth, int week) {
        for (int day = 1; day <= 7; day++) {
            // check to start begin of month
            // first week
            if (week == 1 && weekdayValues[day - 1] != starCalendar.get(Calendar.DAY_OF_WEEK)) {
                System.out.print(String.format(" %2s ", " "));
                continue;
            }

            if(starCalendar.get(Calendar.MONTH) > selectMonth){
                break;
            }

            // check to stop end of month
            if (nowCal.get(Calendar.DAY_OF_YEAR) == starCalendar.get(Calendar.DAY_OF_YEAR)) {
                System.out.print(String.format(" (%2s) ", starCalendar.get(Calendar.DAY_OF_MONTH)));
            } else {
                System.out.print(String.format(" %2s ", starCalendar.get(Calendar.DAY_OF_MONTH)));
            }
            // increase day
            starCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}