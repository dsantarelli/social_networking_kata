package com.xpeppers.snk.command.parser;

import java.util.function.Function;

import com.xpeppers.snk.command.ICommand;

public class CommandRegistration {

    private final String name;
    private final String syntax;
    private final Function<String, Boolean> matchLineFn;
    private final Function<String, ICommand> createCommandFn;

    public CommandRegistration(
            String name, 
            String syntax, 
            Function<String, Boolean> matchLineFn,
            Function<String, ICommand> createCommandFn) {
        
        this.name = name;
        this.syntax = syntax;
        this.matchLineFn = matchLineFn;
        this.createCommandFn = createCommandFn;
    }

    public String getName() {
        return name;
    }

    public String getSyntax() {
        return syntax;
    }

    public Function<String, Boolean> getMatchLineFn() {
        return matchLineFn;
    }

    public Function<String, ICommand> getCommandFactoryFn() {
        return createCommandFn;
    }
}