package com.xpeppers.snk.command;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.socialnetwork.ISocialNetwork;

@ExtendWith(MockitoExtension.class)
class FollowCommandTest {

    @Mock private ISocialNetwork socialNetwork;       
    
    @Test
    void should_call_follow_api() {
        
        new FollowCommand("follower", "followed", socialNetwork)
            .execute();
        
        verify(socialNetwork).follow("follower", "followed");
    }

}
