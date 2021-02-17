package com.xpeppers.snk.command.registry;

import java.util.function.Function;
import java.util.regex.Pattern;

import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.command.WallCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class WallCommandRegistration implements ICommandRegistration {

    private static final Pattern WALL_PATTERN = Pattern.compile("(.*)\\swall", Pattern.CASE_INSENSITIVE);
    
    private final ICommandLineInterface cli;
    private final ISocialNetwork socialNetwork;
        
    public WallCommandRegistration(ICommandLineInterface cli, ISocialNetwork socialNetwork) {
        this.cli = cli;
        this.socialNetwork = socialNetwork;
    }
    
    @Override
    public String getName() {
        return "wall";
    }

    @Override
    public String getSyntax() {
        return "<user name> wall";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {
        return (line) -> WALL_PATTERN.matcher(line).matches();
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> {
            var matcher = WALL_PATTERN.matcher(line); 
            matcher.find();
            return new WallCommand(matcher.group(1).trim(), socialNetwork, cli);
        };
    }
}