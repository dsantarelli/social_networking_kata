package com.xpeppers.snk.text;

import java.text.MessageFormat;

import com.xpeppers.snk.socialnetwork.Message;

public class WallMessageFormatter implements IMessageFormatter {
       
    @Override
    public String format(Message message) {              
        return MessageFormat.format("{0} - {1} ({2})", 
                message.getSender(), 
                message.getText(), 
                PrettyTimeFormatter.formatTimePassedSoFarStartingFrom(message.getTimestamp()));
    }  
}