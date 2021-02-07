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
class ReadCommandTest {

    @Mock private ISocialNetwork socialNetwork;
    @Mock private ILineWriter writer;
    
    @Test
    void should_call_timeline_api() {

        readCommand("username").execute();

        verify(socialNetwork).getTimeline("username");
    }

    @Test
    void should_sort_messages_by_descending_timestamp() {
  
        when(socialNetwork.getTimeline("username"))
            .thenReturn(Arrays.asList(
                    new Message("username", "message 1", Instant.now().minusSeconds(2)),
                    new Message("username", "message 2", Instant.now().minusSeconds(1)),
                    new Message("username", "message 3", Instant.now())
            ));

        readCommand("username").execute();

        verify(socialNetwork).getTimeline("username");
        verify(writer).writeLine("message 3 (moments ago)");
        verify(writer).writeLine("message 2 (1 second ago)");
        verify(writer).writeLine("message 1 (2 seconds ago)");
    }

    @Test
    void should_write_no_message() {

        when(socialNetwork.getTimeline("username"))
            .thenReturn(Collections.emptyList());

        readCommand("username").execute();

        verify(socialNetwork).getTimeline("username");
        verify(writer).writeLine("No messages found");
    }

    private ICommand readCommand(String username) {
        return new ReadCommand(username, socialNetwork, writer);
    }
}
