package com.xpeppers.snk.command.parser;

import com.xpeppers.snk.command.ICommand;

public interface ICommandParser {
    
    ICommand parse(String line) throws UnknownCommandException;
}