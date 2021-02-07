package com.xpeppers.snk.command;

import com.xpeppers.snk.ui.ICommandLineInterface;

public class ExitCommand implements ICommand {
      
    private ICommandLineInterface cli;

    public ExitCommand(ICommandLineInterface cli) {
        this.cli = cli;     
    }
    
    @Override
    public void execute() {
        cli.exit();
    }
}
