package com.xpeppers.snk.command.registry;

import java.util.function.Function;

import com.xpeppers.snk.command.ICommand;


public interface ICommandRegistration {

    String getName();

    String getSyntax();

    Function<String, Boolean> getMatchLineFn();

    Function<String, ICommand> getFactoryFn();

}