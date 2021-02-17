package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.ReadCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

@ExtendWith(MockitoExtension.class)
class ReadCommandRegistrationTest {

    @Mock private ICommandLineInterface cli;
    @Mock private ISocialNetwork socialNetwork;
   
    private ICommandRegistration commandRegistration;
    
    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new ReadCommandRegistration(cli, socialNetwork);
    }

    @Test
    void readCommand_name() {
        assertEquals("reading", commandRegistration.getName());
    }
    
    @Test
    void readCommand_syntax() {
        assertEquals("<user name>", commandRegistration.getSyntax());
    }
    
    @Test
    void readCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("Alice"));
        assertTrue(matchLineFn.apply("alice"));
        assertTrue(matchLineFn.apply("Alice  "));
        assertTrue(matchLineFn.apply("  Alice"));     
    }
    
    @Test
    void readCommand_factory() {
        var command = commandRegistration.getFactoryFn().apply("Alice");
        
        assertTrue(command instanceof ReadCommand);
        assertEquals("Alice", ((ReadCommand)command).getUsername());
    }
}
