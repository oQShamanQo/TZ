package org.example;

import java.sql.Date;
import java.util.Calendar;

public class InsuranceDateSender {

    public static Date getNearestSendingDate() {
        Calendar now = Calendar.getInstance();

        int[] sendingDays = {1, 10, 20};
        Date nearestDate = null;

        for (int day : sendingDays) {
            Calendar sendingDate = (Calendar) now.clone();
            sendingDate.set(Calendar.DAY_OF_MONTH, day);
            sendingDate.set(Calendar.HOUR_OF_DAY, 18);
            sendingDate.set(Calendar.MINUTE, 0);
            sendingDate.set(Calendar.SECOND, 0);
            sendingDate.set(Calendar.MILLISECOND, 0);

            if (sendingDate.compareTo(now) < 0) {
                sendingDate.add(Calendar.MONTH, 1);
            }
            Date sqlDate = new Date(sendingDate.getTimeInMillis());
            Date validDate = getVacCheck(sqlDate);
            if (nearestDate == null || validDate.compareTo(nearestDate) < 0) {
                nearestDate = validDate;
            }
        }

        return nearestDate;
    }

    public static Date getVacCheck(Date modDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(modDate);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -2);
        }

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        return new Date(calendar.getTimeInMillis());
    }

    public static void main(String[] args) {
        Date nearestDate = getNearestSendingDate();
        System.out.println("Ближайшая дата отправки: " + nearestDate);
    }
}

