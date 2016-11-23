package com.vlab.androidexample;

import org.junit.Test;

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
        Calendar starCalendar = Calendar.getInstance();
        starCalendar.set(Calendar.MONTH, 4);
        starCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int currentMonth = starCalendar.get(Calendar.MONTH);
        System.out.println("currentMonth " + currentMonth);
        System.out.println("getActualMaximum DAY_OF_MONTH " + starCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println("getDisplayName DAY_OF_WEEK " + starCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
        System.out.println("DAY_OF_YEAR " + starCalendar.get(Calendar.DAY_OF_YEAR));

        int startDayOfMonth = starCalendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("DAY_OF_MONTH " + startDayOfMonth);

        int startDay = starCalendar.get(Calendar.DAY_OF_WEEK) + 5;
        System.out.println("DAY_OF_WEEK " + startDay);

        int weekOfMonth = starCalendar.get(Calendar.WEEK_OF_MONTH);
        int actualWeekOfMonth = starCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println("WEEK_OF_MONTH " + weekOfMonth);
        System.out.println("actualWeekOfMonth " + actualWeekOfMonth);


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

        for (int week = 0; week < 6; week++) {
            System.out.println("\n-----------------------------");
            Calendar nowCal = Calendar.getInstance();
            int selectMonth = starCalendar.get(Calendar.MONTH);
            for (int day = 0; day < 7; day++) {
                // check to start begin of month
                // first week
                if (week == 0 && day < startDay) {
                    System.out.print(String.format(" %2s ", " "));
                    continue;
                }

                // check to stop end of month
                if(selectMonth == starCalendar.get(Calendar.MONTH)){
                    if(nowCal.get(Calendar.DAY_OF_YEAR) == starCalendar.get(Calendar.DAY_OF_YEAR)){
                        System.out.print(String.format(" (%2s) " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                    } else {
                        System.out.print(String.format(" %2s " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                    }
                }
                starCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }


    }

    private void generateDayOfWeek(Calendar starCalendar, int startDay, int week) {

        Calendar nowCal = Calendar.getInstance();
        int selectMonth = starCalendar.get(Calendar.MONTH);
        for (int day = 0; day < 7; day++) {
            // check to start begin of month
            // first week
            if (week == 0 && day < startDay) {
                System.out.print(String.format(" %2s ", " "));
                continue;
            }

            // check to stop end of month
            if(selectMonth == starCalendar.get(Calendar.MONTH)){
                if(nowCal.get(Calendar.DAY_OF_YEAR) == starCalendar.get(Calendar.DAY_OF_YEAR)){
                    System.out.print(String.format(" (%2s) " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                } else {
                    System.out.print(String.format(" %2s " , starCalendar.get(Calendar.DAY_OF_MONTH)));
                }
            }
            starCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}