package com.vlab.androidexample;

import org.junit.Test;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalendarTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void main() {
        Calendar nowCalendar = Calendar.getInstance();
        int currentDayOfMonth = nowCalendar.get(Calendar.DAY_OF_MONTH);
        Calendar starCalendar = Calendar.getInstance();
        //starCalendar.set(Calendar.MONTH, 11);
        starCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int currentMonth = starCalendar.get(Calendar.MONTH);
        System.out.println("currentMonth " + currentMonth);
        System.out.println("getActualMaximum DAY_OF_MONTH " + starCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println("getDisplayName DAY_OF_WEEK " + starCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
        System.out.println("DAY_OF_YEAR " + starCalendar.get(Calendar.DAY_OF_YEAR));
        int startDayOfMonth = starCalendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("DAY_OF_MONTH " + startDayOfMonth);

        int dayOfWeek = starCalendar.get(Calendar.DAY_OF_WEEK) - 2;
        System.out.println("DAY_OF_WEEK " + dayOfWeek);

        int weekOfMonth = starCalendar.get(Calendar.WEEK_OF_MONTH);
        int actualWeekOfMonth = starCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println("WEEK_OF_MONTH " + weekOfMonth);
        System.out.println("actualWeekOfMonth " + actualWeekOfMonth);


        System.out.println("WEEK_OF_YEAR " + starCalendar.get(Calendar.WEEK_OF_YEAR));

        System.out.println("getMinimalDaysInFirstWeek " + starCalendar.getMinimalDaysInFirstWeek());

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

        // header
        for (int i = 0; i < weekdayValues.length; i++) {
            System.out.print(" " + weekdays[i]);
        }

        for (int j = 0; j < actualWeekOfMonth; j++) {
            System.out.println("\n-----------------------------");
            for (int i = 0; i < weekdayValues.length; i++) {
                // check to start begin of month
                // first week
                if (j == 0 && i < dayOfWeek) {
                    System.out.print(String.format(" %2s ", ""));
                    continue;
                }

                // check to stop end of month
                if(currentMonth == starCalendar.get(Calendar.MONTH)){
                    if(currentDayOfMonth == starCalendar.get(Calendar.DAY_OF_MONTH)){
                        System.out.print(String.format(" (%2s) " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                    } else {
                        System.out.print(String.format(" %2s " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                    }
                }
                starCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }


    }
}