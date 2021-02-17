package com.xpeppers.snk.command.registry;

import java.util.function.Function;

import com.xpeppers.snk.command.ExitCommand;
import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class ExitCommandRegistration implements ICommandRegistration {

    private final ICommandLineInterface cli;

    public ExitCommandRegistration(ICommandLineInterface cli) {
        this.cli = cli;
    }
    
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getSyntax() {
        return "exit";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {
        return (line) -> line.trim().equalsIgnoreCase("exit");
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> new ExitCommand(cli);
    }
}
