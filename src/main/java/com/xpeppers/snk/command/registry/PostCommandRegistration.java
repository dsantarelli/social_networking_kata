package com.xpeppers.snk.command.registry;

import java.util.function.Function;
import java.util.regex.Pattern;

import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.command.PostCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;

public class PostCommandRegistration implements ICommandRegistration {

    private static final Pattern POST_PATTERN = Pattern.compile("(.*)\\s->\\s(.*)");
    private final ISocialNetwork socialNetwork;
        
    public PostCommandRegistration(ISocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }
    
    @Override
    public String getName() {
        return "posting";
    }

    @Override
    public String getSyntax() {
        return "<user name> -> <message>";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {       
        return (line) -> POST_PATTERN.matcher(line).matches();
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> {
            var matcher = POST_PATTERN.matcher(line); 
            matcher.find();
            return new PostCommand(matcher.group(1).trim(), matcher.group(2).trim(), socialNetwork); 
        };
    }    
}