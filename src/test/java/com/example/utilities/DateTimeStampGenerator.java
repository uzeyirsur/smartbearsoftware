package com.example.utilities;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeStampGenerator {

    public static String getCurrentDateYYYYMMDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getCurrentDateDDMMYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getCurrentDateMMDDYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }



    public static String getCurrentZonedDateTime(String timeZone) {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(timeZone));
        return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z"));
    }
}
