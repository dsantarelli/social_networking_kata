package com.xpeppers.snk.command;

import java.text.MessageFormat;
import java.util.stream.Collectors;

import com.xpeppers.snk.command.registry.ICommandRegistry;
import com.xpeppers.snk.io.ILineWriter;

public class HelpCommand implements ICommand {

    private static final String NEW_LINE = System.lineSeparator();    
    private static final String HELP_HEADER = 
            NEW_LINE +
            "********************" + NEW_LINE +
            "******* HELP *******" + NEW_LINE + 
            "********************" + NEW_LINE +
            "<COMMAND>: <syntax>"  + NEW_LINE +
            NEW_LINE;
    
    
    private final ILineWriter writer;
    private final ICommandRegistry commandRegistry;

    public HelpCommand(ILineWriter writer, ICommandRegistry commandRegistry) {
        this.writer = writer;
        this.commandRegistry = commandRegistry;
    } 
    
    @Override
    public void execute() {
        writer.writeLine(
            HELP_HEADER 
            +
            commandRegistry
                .getRegisteredCommands()
                .stream()
                .sorted((a,b) -> a.getName().compareTo(b.getName()))
                .map(x -> MessageFormat.format("* {0}: {1}", x.getName().toUpperCase(), x.getSyntax()))
                .collect(Collectors.joining(NEW_LINE))
            +
            NEW_LINE
        );
    }    
}
