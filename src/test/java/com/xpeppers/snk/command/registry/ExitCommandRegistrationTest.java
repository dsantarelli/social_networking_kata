package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.ExitCommand;
import com.xpeppers.snk.ui.ICommandLineInterface;

@ExtendWith(MockitoExtension.class)
class ExitCommandRegistrationTest {

    @Mock private ICommandLineInterface cli;
    
    private ICommandRegistration commandRegistration;
    
    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new ExitCommandRegistration(cli);
    }
    
    @Test
    void exitCommand_name() {
        assertEquals("exit", commandRegistration.getName());
    }

    @Test
    void exitCommand_syntax() {
        assertEquals("exit", commandRegistration.getSyntax());
    }

    @Test
    void exitCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("exit"));
        assertTrue(matchLineFn.apply(" exit "));
    }

    @Test
    void exitCommand_factory() {
        assertTrue(commandRegistration
                    .getFactoryFn()
                    .apply("exit") 
                    instanceof ExitCommand);
    }
}
