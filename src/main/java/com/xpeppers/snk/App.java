package com.xpeppers.snk;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Consumer;

import com.xpeppers.snk.command.parser.CommandParser;
import com.xpeppers.snk.command.parser.CommandRegistry;
import com.xpeppers.snk.command.parser.UnknownCommandException;
import com.xpeppers.snk.socialnetwork.InMemorySocialNetwork;
import com.xpeppers.snk.socialnetwork.User;
import com.xpeppers.snk.ui.CommandLineInterface;
import com.xpeppers.snk.ui.ICommandLineInterface;

public class App {

    private final ICommandLineInterface cli;

    public App(ICommandLineInterface cli) {
        this.cli = cli;
    }
    
    public static void main(String[] args) {
        new App(new CommandLineInterface(System.in, System.out)).start();
    }
          
    public void start() {
                
        var socialNetwork = new InMemorySocialNetwork(Arrays.asList(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie"))
        );
                
        var commandParser = new CommandParser(
            new CommandRegistry(cli, socialNetwork)
        );
                
        loop((line) -> {            
            try {
                var command = commandParser.parse(line);
                command.execute();
            } catch (UnknownCommandException e) {
                writeErrorMessage(e);
            }            
        });
    }

    private void loop(Consumer<String> lineConsumer) {
        while (true) {
            try {
                var line = cli.readLine();

                if (line == null)   break;
                if (line.isBlank()) continue;

                lineConsumer.accept(line);               
            }
            catch (SecurityException e) { } // thrown by System.exit(); -> Do nothing here
            catch (Throwable e) { writeErrorMessage(e); }
        }        
    }
    
    private void writeErrorMessage(Throwable e) {
        cli.writeLine(MessageFormat.format("ERROR: {0}", e.getMessage())); // I don't care about stack trace :/
    }
}

