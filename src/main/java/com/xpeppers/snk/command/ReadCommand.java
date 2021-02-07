package com.xpeppers.snk.command;

import java.util.stream.Collectors;

import com.xpeppers.snk.io.ILineWriter;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.text.IMessageFormatter;
import com.xpeppers.snk.text.TimelineMessageFormatter;

public class ReadCommand implements ICommand {

    private final String username;
    private final ILineWriter writer;
    private final ISocialNetwork socialNetwork;
    private final IMessageFormatter messageFormatter;

    public ReadCommand(
            String username,
            ISocialNetwork socialNetwork,
            ILineWriter writer) {

        this.username = username;        
        this.socialNetwork = socialNetwork;
        this.writer = writer;
        this.messageFormatter = new TimelineMessageFormatter();
    }   

    @Override
    public void execute() {        
        var messages = socialNetwork
                .getTimeline(username)
                .stream()
                .sorted((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()) * -1)
                .collect(Collectors.toList());
        
        if (messages.isEmpty()) {
            writer.writeLine("No messages found");
        }
        else {
            messages.forEach(msg -> 
                writer.writeLine(messageFormatter.format(msg)));
        }
    }    
}
