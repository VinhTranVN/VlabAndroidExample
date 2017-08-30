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
        Calendar selectCal = Calendar.getInstance();
        selectCal.set(Calendar.YEAR, 2016);
        selectCal.set(Calendar.MONTH, 10);
        selectCal.set(Calendar.DAY_OF_MONTH, 1);

        System.out.println("getDisplayName DAY_OF_WEEK " + selectCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));

        int actualWeekOfMonth = selectCal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println("actualWeekOfMonth " + actualWeekOfMonth);

        // header
        for (int i = 0; i < weekdayValues.length; i++) {
            System.out.print(" " + weekdays[i]);
        }

        for (int week = 1; week <= actualWeekOfMonth; week++) {
            System.out.println("\n-----------------------------");
            generateWeek(selectCal, week);
        }
    }

    private void generateWeek(Calendar starCalendar, int week) {

        Calendar weekCal = Calendar.getInstance();
        weekCal.set(Calendar.MONTH, starCalendar.get(Calendar.MONTH));
        weekCal.set(Calendar.DAY_OF_MONTH, 1); // start day
        weekCal.setFirstDayOfWeek(Calendar.MONDAY);
        if(week >  1){
            weekCal.add(Calendar.WEEK_OF_YEAR, week - 1);
            weekCal.set(Calendar.DAY_OF_WEEK, weekCal.getFirstDayOfWeek());
            System.out.println("weekCal.getFirstDayOfWeek() " + weekCal.getTime().toString());
        }


        for (int day = 1; day <= 7; day++) {
            // check to start begin of month
            // first week
            if (week == 1 && weekdayValues[day - 1] != weekCal.get(Calendar.DAY_OF_WEEK)) {
                System.out.print(String.format(" %2s ", " "));
                continue;
            }

            if(weekCal.get(Calendar.MONTH) > starCalendar.get(Calendar.MONTH)){
                break;
            }

            // check to stop end of month
            if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == starCalendar.get(Calendar.DAY_OF_YEAR)) {
                System.out.print(String.format(" (%2s) ", weekCal.get(Calendar.DAY_OF_MONTH)));
            } else {
                System.out.print(String.format(" %2s ", weekCal.get(Calendar.DAY_OF_MONTH)));
            }
            // increase day
            weekCal.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Test
    public void testCalendar() {
        Calendar selectCal = Calendar.getInstance();
        selectCal.set(Calendar.YEAR, 2017);
        selectCal.set(Calendar.MONTH, 7);
        selectCal.set(Calendar.DAY_OF_MONTH, 1);
        selectCal.set(Calendar.HOUR, 0);
        selectCal.set(Calendar.MINUTE, 0);
        selectCal.set(Calendar.SECOND, 0);

        int numberDay = convertDateToCombineDay(selectCal.get(Calendar.YEAR),
                selectCal.get(Calendar.MONTH),
                selectCal.get(Calendar.DAY_OF_MONTH));

        System.out.println(numberDay);
    }

    public static int convertDateToCombineDay(int year, int month, int dayOfMonth) {
        return year * 416 + month * 32 + dayOfMonth;
    }


}