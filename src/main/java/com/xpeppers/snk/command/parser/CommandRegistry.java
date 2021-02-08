package com.xpeppers.snk.command.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import com.xpeppers.snk.command.ExitCommand;
import com.xpeppers.snk.command.FollowCommand;
import com.xpeppers.snk.command.HelpCommand;
import com.xpeppers.snk.command.PostCommand;
import com.xpeppers.snk.command.ReadCommand;
import com.xpeppers.snk.command.WallCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class CommandRegistry implements ICommandRegistry {

    private static final Pattern POST_PATTERN = Pattern.compile("(.*)\\s->\\s(.*)");
    private static final Pattern FOLLOW_PATTERN = Pattern.compile("(.*)\\sfollows\\s(.*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern WALL_PATTERN = Pattern.compile("(.*)\\swall", Pattern.CASE_INSENSITIVE);
    
    private final ICommandLineInterface cli;
    private final ISocialNetwork socialNetwork;    
    private final List<CommandRegistration> commandRegistrations;
    
    public CommandRegistry(ICommandLineInterface cli, ISocialNetwork socialNetwork) {
        this.cli = cli;
        this.socialNetwork = socialNetwork;
        this.commandRegistrations = new ArrayList<>();
        registerCommands();
    }
    
    @Override
    public List<CommandRegistration> getRegisteredCommands() {
        return Collections.unmodifiableList(commandRegistrations);
    }
    
    private void registerCommands() {
        commandRegistrations.addAll(Arrays.asList(
                
            // Help command (BONUS)
            new CommandRegistration(
                "help", 
                "help",
                (line) -> line.trim().equalsIgnoreCase("help"),
                (line) -> new HelpCommand(cli, this)),
                
            // Exit command (BONUS)
            new CommandRegistration(
                "exit", 
                "exit",
                (line) -> line.trim().equalsIgnoreCase("exit"),
                (line) -> new ExitCommand(cli)),
                
            // Post command
            new CommandRegistration(
                "posting", 
                "<user name> -> <message>",
                (line) -> POST_PATTERN.matcher(line).matches(),
                (line) -> {
                    var matcher = POST_PATTERN.matcher(line); 
                    matcher.find();
                    return new PostCommand(matcher.group(1).trim(), matcher.group(2).trim(), socialNetwork); 
                }),
                
            // Follow command
            new CommandRegistration(
                "following", 
                "<user name> follows <another user>",
                (line) -> FOLLOW_PATTERN.matcher(line).matches(),
                (line) -> {
                    var matcher = FOLLOW_PATTERN.matcher(line); 
                    matcher.find();
                    return new FollowCommand(matcher.group(1).trim(), matcher.group(2).trim(), socialNetwork);
                }),
                
            // Wall command
            new CommandRegistration(
                "wall", 
                "<user name> wall",
                (line) -> WALL_PATTERN.matcher(line).matches(),
                (line) -> {
                    var matcher = WALL_PATTERN.matcher(line); 
                    matcher.find();
                    return new WallCommand(matcher.group(1).trim(), socialNetwork, cli);
                }),
                
            // Read command (fallback)
            new CommandRegistration(
                "reading", 
                "<user name>",
                (line) -> true,
                (line) -> new ReadCommand(line.trim(), socialNetwork, cli))
        ));                
    }
}