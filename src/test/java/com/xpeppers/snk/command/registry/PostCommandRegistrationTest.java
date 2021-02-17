package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.PostCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;

@ExtendWith(MockitoExtension.class)
class PostCommandRegistrationTest {
    
    @Mock private ISocialNetwork socialNetwork;
   
    private ICommandRegistration commandRegistration;
    
    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new PostCommandRegistration(socialNetwork);
    }
    
    @Test
    void postCommand_name() {
        assertEquals("posting", commandRegistration.getName());
    }
    
    @Test
    void postCommand_syntax() {
        assertEquals("<user name> -> <message>", commandRegistration.getSyntax());
    }
    
    @Test
    void postCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("Alice -> message"));
        assertTrue(matchLineFn.apply(" Alice -> message "));
        assertTrue(matchLineFn.apply("Alice -> "));        
        assertFalse(matchLineFn.apply("Alice"));
    }
    
    @Test
    void postCommand_factory() {
        var command = commandRegistration.getFactoryFn().apply("Alice -> message");
        
        assertTrue(command instanceof PostCommand);
        assertEquals("Alice", ((PostCommand)command).getUsername());
        assertEquals("message", ((PostCommand)command).getMessage());
    }
}
