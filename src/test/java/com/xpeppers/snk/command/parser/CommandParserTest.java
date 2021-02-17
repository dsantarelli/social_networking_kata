package com.xpeppers.snk.command.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.ICommand;
import com.xpeppers.snk.command.registry.ICommandRegistration;
import com.xpeppers.snk.command.registry.ICommandRegistry;

@ExtendWith(MockitoExtension.class)
class CommandParserTest {

    @Mock private ICommandRegistry commandRegistry;
    
    private ICommandParser parser;

    @BeforeEach
    void onBeforeEach() {
        parser = new CommandParser(commandRegistry);
    }

    @Test
    void command_found() throws UnknownCommandException {

        when(commandRegistry.getRegisteredCommands())
            .thenReturn(Arrays.asList(new TestCommandRegistration()));

        var command = parser.parse("test");

        assertNotNull(command);
        assertTrue(command instanceof TestCommand);
    }

    @Test
    void command_not_found() {

        when(commandRegistry.getRegisteredCommands())
            .thenReturn(Collections.emptyList());

        assertThrows(UnknownCommandException.class, 
                () -> parser.parse("test"));
    }

    public static class TestCommandRegistration implements ICommandRegistration {

        @Override
        public String getName() { return "test"; }

        @Override
        public String getSyntax() { return "test"; }

        @Override
        public Function<String, Boolean> getMatchLineFn() { 
            return (line) ->  true; 
        }

        @Override
        public Function<String, ICommand> getFactoryFn() {
            return (line) -> new TestCommand();
        }       
    }
    
    public static class TestCommand implements ICommand {

        @Override
        public void execute() { }
    }
}
