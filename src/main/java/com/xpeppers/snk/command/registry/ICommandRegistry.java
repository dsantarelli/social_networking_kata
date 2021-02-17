package com.xpeppers.snk.command.registry;

import java.util.List;

public interface ICommandRegistry {
    
    List<ICommandRegistration> getRegisteredCommands();
}