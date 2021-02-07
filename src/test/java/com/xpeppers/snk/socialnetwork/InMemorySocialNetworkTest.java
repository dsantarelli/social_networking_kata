package com.xpeppers.snk.socialnetwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemorySocialNetworkTest {

    private ISocialNetwork socialNetwork;
    
    @BeforeEach
    void onBeforeEach() {
        socialNetwork = new InMemorySocialNetwork(Arrays.asList(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie")
        ));
    }
 
    @Test
    void it_should_return_empty_timeline_for_a_given_user() {
        assertTrue(socialNetwork.getTimeline("Alice").isEmpty());    
    }    
    
    @Test
    void it_should_return_empty_wall_for_a_given_user() {
        assertTrue(socialNetwork.getWall("Alice").isEmpty());    
    }    
    
    @Test
    void it_should_return_timeline_for_a_given_user() {
        
        socialNetwork.postMessage("Alice", "Message by Alice 1"); sleep(10);
        socialNetwork.postMessage("Bob", "Message by Bob");        
        socialNetwork.postMessage("Alice", "Message by Alice 2");
        socialNetwork.follow("Alice", "Bob");
        
        var messages = socialNetwork.getTimeline("Alice");
        
        assertEquals(2, messages.size());

        assertEquals("Message by Alice 2", messages.get(0).getText());
        assertEquals("Alice", messages.get(0).getSender()); 
        
        assertEquals("Message by Alice 1", messages.get(1).getText());
        assertEquals("Alice", messages.get(1).getSender());
    }    
             
    @Test
    void it_should_return_wall_for_a_given_user() {      
        
        socialNetwork.postMessage("Bob", "Message by Bob"); sleep(10);
        socialNetwork.postMessage("Alice", "Message by Alice"); sleep(10);
        socialNetwork.postMessage("Charlie", "Message by Charlie");
        
        socialNetwork.follow("Alice", "Bob");
        socialNetwork.follow("Alice", "Charlie");
        
        var messages = socialNetwork.getWall("Alice");
        
        assertEquals(3, messages.size());               
        
        assertEquals("Message by Charlie", messages.get(0).getText());
        assertEquals("Charlie", messages.get(0).getSender());
        
        assertEquals("Message by Alice", messages.get(1).getText());
        assertEquals("Alice", messages.get(1).getSender());
        
        assertEquals("Message by Bob", messages.get(2).getText());
        assertEquals("Bob", messages.get(2).getSender());
    }
    
    @Test
    void a_not_existing_user_can_not_do_anything() {
        
        assertThrows(UserNotFoundException.class, 
                () -> socialNetwork.postMessage("Goku", "Message"));
        
        assertThrows(UserNotFoundException.class, 
                () -> socialNetwork.getTimeline("Goku"));
        
        assertThrows(UserNotFoundException.class, 
                () -> socialNetwork.follow("Goku", "Alice"));
        
        assertThrows(UserNotFoundException.class, 
                () -> socialNetwork.follow("Alice", "Goku"));
        
        assertThrows(UserNotFoundException.class, 
                () -> socialNetwork.getWall("Goku"));
    }
    
    private static void sleep(int millis) {
        try { Thread.sleep(millis); } 
        catch (Throwable e) { throw new RuntimeException(e); }
    }
}
