package com.qa.utils;

public class BookingUtils {
    public static String createBookingPayload(String firstname, String lastname) {
        return "{\"firstname\": \"" + firstname + "\", " +
                "\"lastname\": \"" + lastname + "\", " +
                "\"totalprice\": 1500, " +
                "\"depositpaid\": true, " +
                "\"bookingdates\": {\"checkin\": \"2026-07-29\", \"checkout\": \"2026-08-05\"}, " +
                "\"additionalneeds\": \"Breakfast\"}";
    }
}
