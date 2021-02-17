package com.xpeppers.snk.command.registry;

import java.util.function.Function;
import java.util.regex.Pattern;

import com.xpeppers.snk.command.FollowCommand;
import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;

public class FollowCommandRegistration implements ICommandRegistration {

    private static final Pattern FOLLOW_PATTERN = Pattern.compile("(.*)\\sfollows\\s(.*)", Pattern.CASE_INSENSITIVE);
    
    private final ISocialNetwork socialNetwork;
        
    public FollowCommandRegistration(ISocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }
    
    @Override
    public String getName() {
        return "following";
    }

    @Override
    public String getSyntax() {
        return "<user name> follows <another user>";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {
        return  (line) -> FOLLOW_PATTERN.matcher(line).matches();
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> {
            var matcher = FOLLOW_PATTERN.matcher(line); 
            matcher.find();
            return new FollowCommand(matcher.group(1).trim(), matcher.group(2).trim(), socialNetwork);
        };
    }
    
}