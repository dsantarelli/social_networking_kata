package com.xpeppers.snk.text;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

class PrettyTimeFormatter {
        
    private static final long SECONDS_IN_A_MINUTE = 60L;
    private static final long SECONDS_IN_AN_HOUR  = 60L * 60L;
    private static final long SECONDS_IN_A_DAY    = 60L * 60L * 24L;
    private static final long SECONDS_IN_A_WEEK   = 60L * 60L * 24L * 7L;
    private static final long SECONDS_IN_A_MONTH  = 60L * 60L * 24L * 30L;
    private static final long SECONDS_IN_A_YEAR   = 60L * 60L * 24L * 365;

    static String formatTimePassedSoFarStartingFrom(Instant instant) {
        
        var duration  = Duration.between(instant, Instant.now());        
        var seconds = duration.getSeconds();                
        var minutes = seconds / SECONDS_IN_A_MINUTE;
        var hours   = seconds / SECONDS_IN_AN_HOUR;
        var days    = seconds / SECONDS_IN_A_DAY;
        var weeks   = seconds / SECONDS_IN_A_WEEK;
        var months  = seconds / SECONDS_IN_A_MONTH;
        var years   = seconds / SECONDS_IN_A_YEAR;
                
        if (years   != 0L) return format(years,   "year",   "years");
        if (months  != 0L) return format(months,  "month",  "months");
        if (weeks   != 0L) return format(weeks,   "week",   "weeks");
        if (days    != 0L) return format(days,    "day",    "days");
        if (hours   != 0L) return format(hours,   "hour",   "hours");
        if (minutes != 0L) return format(minutes, "minute", "minutes");
        if (seconds != 0L) return format(seconds, "second", "seconds");
        
        return "moments ago";
    }
    
    private static String format(long value, String singular, String plural) {
        return MessageFormat.format("{0} {1} ago", value, (value == 1L) ? singular : plural);
    }
}