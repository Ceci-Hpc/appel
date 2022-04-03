package com.example.appel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePlanning {

    private static final SimpleDateFormat SDP = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static final SimpleDateFormat SDP_HOUR_MINUTE = new SimpleDateFormat("HH:mm:ss");
    private Date firstDayOfWeek;
    private Date lastDayOfWeek;

    public DatePlanning() {
    }

    public DatePlanning(Date date) {
        loadDayOfWeek(date);
    }


    private void loadDayOfWeek(Date date) {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        // Print dates of the current week starting on Monday
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.firstDayOfWeek = c.getTime();
        for (int i = 0; i <6; i++) {
            c.add(Calendar.DATE, 1);
        }
        this.lastDayOfWeek = c.getTime();
    }

    public static boolean isWithinRange(Date date, Date startDate, Date endDate) {
        return !(date.before(startDate) || date.after(endDate));
    }

    public static int getWeektoMillis(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Dimanche";
                break;
            case 2:
                day = "Lundi";
                break;
            case 3:
                day = "Mardi";
                break;
            case 4:
                day = "Mercredi";
                break;
            case 5:
                day = "Jeudi";
                break;
            case 6:
                day = "Vendredi";
                break;
            case 7:
                day = "Samedi";
                break;
        }
        return day;
    }

    public static String getStrFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String getStringToLong(Long date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);

    }
    public static void main(String[] args) throws ParseException {

        Date date = new Date();
        String dateStr = SDP_HOUR_MINUTE.format(date);
        System.out.println(dateStr);
    }

    public Date getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(Date firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public Date getLastDayOfWeek() {
        return lastDayOfWeek;
    }

    public void setLastDayOfWeek(Date lastDayOfWeek) {
        this.lastDayOfWeek = lastDayOfWeek;
    }
}
