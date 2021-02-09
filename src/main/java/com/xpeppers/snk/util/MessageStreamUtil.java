package com.xpeppers.snk.util;

import java.util.stream.Stream;

import com.xpeppers.snk.socialnetwork.Message;

public class MessageStreamUtil {

    public static Stream<Message> sortByTimestampDescending(Stream<Message> stream) {
        return stream.sorted((a,b) -> a.getTimestamp().compareTo(b.getTimestamp()) * -1);
    }  
}
