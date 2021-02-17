package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.FollowCommand;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;

@ExtendWith(MockitoExtension.class)
class FollowCommandRegistrationTest {

    @Mock private ISocialNetwork socialNetwork;
    
    private ICommandRegistration commandRegistration;
    
    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new FollowCommandRegistration(socialNetwork);
    }
    
    @Test
    void postCommand_name() {
        assertEquals("following", commandRegistration.getName());
    }
    
    @Test
    void postCommand_syntax() {
        assertEquals("<user name> follows <another user>", commandRegistration.getSyntax());
    }
    
    @Test
    void postCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("Alice follows Bob"));
        assertTrue(matchLineFn.apply(" Alice follows Bob "));
        assertFalse(matchLineFn.apply("Alice follows"));        
        assertFalse(matchLineFn.apply("Alice"));
    }
    
    @Test
    void postCommand_factory() {
        var command = commandRegistration.getFactoryFn().apply("Alice follows Bob");
        
        assertTrue(command instanceof FollowCommand);
        assertEquals("Alice", ((FollowCommand)command).getFollower());
        assertEquals("Bob", ((FollowCommand)command).getFollowed());
    }
}
