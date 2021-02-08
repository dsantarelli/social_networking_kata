package com.xpeppers.snk.command.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.ICommand;

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
            .thenReturn(Arrays.asList(
                    new CommandRegistration(
                        "testCommand", 
                        "test", 
                        (line) -> true, 
                        (line) -> new TestCommand())));

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

    public static class TestCommand implements ICommand {

        @Override
        public void execute() { }
    }
}
