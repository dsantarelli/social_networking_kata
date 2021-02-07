package com.xpeppers.snk.command;

import java.text.MessageFormat;
import java.util.stream.Collectors;

import com.xpeppers.snk.command.parser.ICommandRegistry;
import com.xpeppers.snk.io.ILineWriter;

public class HelpCommand implements ICommand {

    private static final String HELP_HEADER = 
            System.lineSeparator() +
            "********************" + System.lineSeparator() +
            "******* HELP *******" + System.lineSeparator() + 
            "********************" + System.lineSeparator() +
            "<COMMAND>: <syntax>"  + System.lineSeparator() +
            System.lineSeparator();
    
    
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
                .collect(Collectors.joining(System.lineSeparator()))
            +
            System.lineSeparator()
        );
    }
}
