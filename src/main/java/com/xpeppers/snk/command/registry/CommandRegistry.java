package com.xpeppers.snk.command.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class CommandRegistry implements ICommandRegistry {
  
    private final ICommandLineInterface cli;
    private final ISocialNetwork socialNetwork;    
    private final List<ICommandRegistration> commandRegistrations;
    
    public CommandRegistry(ICommandLineInterface cli, ISocialNetwork socialNetwork) {
        this.cli = cli;
        this.socialNetwork = socialNetwork;
        this.commandRegistrations = new ArrayList<>();
        registerCommands();
    }
    
    @Override
    public List<ICommandRegistration> getRegisteredCommands() {
        return Collections.unmodifiableList(commandRegistrations);
    }
    
    private void registerCommands() {
        commandRegistrations.addAll(Arrays.asList(
            new HelpCommandRegistration(cli, this),
            new ExitCommandRegistration(cli),
            new PostCommandRegistration(socialNetwork),
            new FollowCommandRegistration(socialNetwork),
            new WallCommandRegistration(cli, socialNetwork),
            new ReadCommandRegistration(cli, socialNetwork)
        ));                
    }
}
