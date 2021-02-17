package com.xpeppers.snk.command.registry;

import java.util.function.Function;

import com.xpeppers.snk.command.HelpCommand;
import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.io.ILineWriter;

public class HelpCommandRegistration implements ICommandRegistration {

    private final ICommandRegistry registry;
    private final ILineWriter writer;

    public HelpCommandRegistration(
            ILineWriter writer,
            ICommandRegistry registry) {
        
        this.writer = writer;
        this.registry = registry;
    }
    
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getSyntax() {
        return "help";
    }

    @Override
    public Function<String, Boolean> getMatchLineFn() {
        return (line) -> line.trim().equalsIgnoreCase("help");
    }

    @Override
    public Function<String, ICommand> getFactoryFn() {
        return (line) -> new HelpCommand(writer, registry);
    }
}
