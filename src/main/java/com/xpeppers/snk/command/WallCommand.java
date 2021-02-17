package com.xpeppers.snk.command;
import static com.xpeppers.snk.util.MessageStreamUtil.sortByTimestampDescending;

import java.util.stream.Collectors;

import com.xpeppers.snk.io.ILineWriter;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.text.IMessageFormatter;
import com.xpeppers.snk.text.WallMessageFormatter;


public class WallCommand implements ICommand {

    private final String username;
    private final ILineWriter writer;
    private final ISocialNetwork socialNetwork;
    private final IMessageFormatter messageFormatter;
    
    public WallCommand(
            String username,
            ISocialNetwork socialNetwork,
            ILineWriter writer) {

        this.username = username;
        this.socialNetwork = socialNetwork;
        this.writer = writer;
        this.messageFormatter = new WallMessageFormatter();
    }
 
    public String getUsername() {
        return username;
    }
    
    @Override
    public void execute() {
        var messages = sortByTimestampDescending(
                socialNetwork.getWall(getUsername()).stream())
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
