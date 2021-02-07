package com.xpeppers.snk.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.io.ILineWriter;
import com.xpeppers.snk.socialnetwork.ISocialNetwork;
import com.xpeppers.snk.socialnetwork.Message;

@ExtendWith(MockitoExtension.class)
class WallCommandTest {

    @Mock private ISocialNetwork socialNetwork;
    @Mock private ILineWriter writer;

    @Test
    void should_call_wall_api() {

        wallCommand("username").execute();

        verify(socialNetwork).getWall("username");
    }

    @Test
    void should_sort_messages_by_descending_timestamp() {
        
        when(socialNetwork.getWall("username"))
            .thenReturn(Arrays.asList(
                    new Message("username", "message 1", Instant.now().minusSeconds(2)),
                    new Message("username", "message 2", Instant.now().minusSeconds(1)),
                    new Message("username", "message 3", Instant.now())
            ));

        wallCommand("username").execute();

        verify(socialNetwork).getWall("username");
        verify(writer).writeLine("username - message 3 (moments ago)");
        verify(writer).writeLine("username - message 2 (1 second ago)");
        verify(writer).writeLine("username - message 1 (2 seconds ago)");
    }
    
    @Test
    void should_write_no_message() {

        when(socialNetwork.getWall("username"))
            .thenReturn(Collections.emptyList());

        wallCommand("username").execute();

        verify(socialNetwork).getWall("username");
        verify(writer).writeLine("No messages found");
    }

    private ICommand wallCommand(String username) {
        return new WallCommand(username, socialNetwork, writer);
    }
}