package com.xpeppers.snk.text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.xpeppers.snk.socialnetwork.Message;

class TimelineMessageFormatterTest {
    
    private final IMessageFormatter formatter = new TimelineMessageFormatter();
    
    @Test
    void should_format_moments_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now()));        
        assertEquals("text (moments ago)", string);
    }
    
    @Test
    void should_format_seconds_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(1, ChronoUnit.SECONDS)));   
        assertEquals("text (1 second ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(2, ChronoUnit.SECONDS)));   
        assertEquals("text (2 seconds ago)", string);
    }
    
    @Test
    void should_format_minutes_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(1, ChronoUnit.MINUTES)));   
        assertEquals("text (1 minute ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(2, ChronoUnit.MINUTES)));   
        assertEquals("text (2 minutes ago)", string);
    }
    
    @Test
    void should_format_hours_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(1, ChronoUnit.HOURS)));
        assertEquals("text (1 hour ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(2, ChronoUnit.HOURS)));
        assertEquals("text (2 hours ago)", string);
    }
    
    @Test
    void should_format_days_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(1, ChronoUnit.DAYS)));        
        assertEquals("text (1 day ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(2, ChronoUnit.DAYS)));        
        assertEquals("text (2 days ago)", string);
    }
    
    @Test
    void should_format_months_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(32, ChronoUnit.DAYS)));        
        assertEquals("text (1 month ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(64, ChronoUnit.DAYS)));        
        assertEquals("text (2 months ago)", string);
    }
    
    @Test
    void should_format_years_ago() {
        var string = formatter.format(new Message("sender", "text", Instant.now().minus(367, ChronoUnit.DAYS)));        
        assertEquals("text (1 year ago)", string);
        
        string = formatter.format(new Message("sender", "text", Instant.now().minus(734, ChronoUnit.DAYS)));        
        assertEquals("text (2 years ago)", string);
    }
}