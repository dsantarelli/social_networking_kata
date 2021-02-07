package com.xpeppers.snk.text;

import java.text.MessageFormat;

import com.xpeppers.snk.socialnetwork.Message;

public class TimelineMessageFormatter implements IMessageFormatter {
     
    @Override
    public String format(Message message) {
        return MessageFormat.format("{0} ({1})", 
                message.getText(), 
                PrettyTimeFormatter.formatTimePassedSoFarStartingFrom(message.getTimestamp()));
    }    
}