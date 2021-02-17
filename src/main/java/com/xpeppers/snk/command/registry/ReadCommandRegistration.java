package com.xpeppers.snk.command.registry;

import java.util.function.Function;

import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.command.ReadCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class ReadCommandRegistration implements ICommandRegistration {

    private final ICommandLineInterface cli;
    private final ISocialNetwork socialNetwork;

    public ReadCommandRegistration(ICommandLineInterface cli, ISocialNetwork socialNetwork) {
        this.cli = cli;
        this.socialNetwork = socialNetwork;
    }
    
    @Override
    public String getName() {
        return "reading";
    }

    @Override
    public String getSyntax() {
        return "<user name>";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {
        return (line) -> true;
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> new ReadCommand(line.trim(), socialNetwork, cli);
    }
}