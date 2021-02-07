package com.xpeppers.snk.command.parser;

import java.text.MessageFormat;

import com.xpeppers.snk.command.ICommand;

public class CommandParser implements ICommandParser {
    
    private final ICommandRegistry commandRegistry;

    public CommandParser(ICommandRegistry commandRegistry) {        
        this.commandRegistry = commandRegistry;
    }
    
    public ICommand parse(String line) throws UnknownCommandException {     
        return commandRegistry
                 .getRegisteredCommands()
                 .stream()
                 .filter(x -> x.getMatchLineFn().apply(line)) 
                 .map(x -> x.getCommandFactoryFn().apply(line))
                 .findFirst()
                 .orElseThrow(() -> new UnknownCommandException(MessageFormat.format("command not supported: \"{0}\"", line)));        
    }           
}
