package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.WallCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.ui.ICommandLineInterface;

@ExtendWith(MockitoExtension.class)
class WallCommandRegistrationTest {

    @Mock private ICommandLineInterface cli;
    @Mock private ISocialNetwork socialNetwork;
   
    private ICommandRegistration commandRegistration;
    
    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new WallCommandRegistration(cli, socialNetwork);
    }
    
    @Test
    void wallCommand_name() {
        assertEquals("wall", commandRegistration.getName());
    }
    
    @Test
    void wallCommand_syntax() {
        assertEquals("<user name> wall", commandRegistration.getSyntax());
    }
    
    @Test
    void wallCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("Alice wall"));        
        assertTrue(matchLineFn.apply("Alice WalL"));
        assertFalse(matchLineFn.apply("Alicewall"));
        assertFalse(matchLineFn.apply("Alice wall "));
    }
    
    @Test
    void wallCommand_factory() {
        var command = commandRegistration.getFactoryFn().apply("Alice wall");
        
        assertTrue(command instanceof WallCommand);
        assertEquals("Alice", ((WallCommand)command).getUsername());
    }
}
