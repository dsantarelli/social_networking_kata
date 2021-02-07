package com.xpeppers.snk.command.parser;

import java.util.List;

public interface ICommandRegistry {
    
    List<CommandRegistration> getRegisteredCommands();
}