package com.xpeppers.snk.socialnetwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void user_initial_state() {
        
        var user = new User("Alice");
        
        assertEquals("Alice", user.getUsername());
        assertTrue(user.getMessages().isEmpty());
        assertTrue(user.getFollowing().isEmpty());
        assertTrue(user.getFollowers().isEmpty());
    }

    @Test
    void user_posts_and_gets_messages() {
        
        var user = new User("Alice");
        user.postMessage("A");
        user.postMessage("B");
        user.postMessage("C");
        
        var messages = user.getMessages();
        
        assertEquals(3, messages.size());
        
        assertEquals("Alice", messages.get(0).getSender());
        assertEquals("A", messages.get(0).getText());
        
        assertEquals("Alice", messages.get(1).getSender());
        assertEquals("B", messages.get(1).getText());
        
        assertEquals("Alice", messages.get(2).getSender());
        assertEquals("C", messages.get(2).getText());
    }
    
    @Test
    void user_follows_user() {
        
        var alice = new User("Alice");
        var bob = new User("Bob");
        var charlie = new User("Charlie");
        
        alice.followUser(bob);
        alice.followUser(charlie);
        bob.followUser(charlie);
        
        // ALICE
        assertEquals(1L, alice.getFollowing().stream().filter(x -> x.getUsername().equals(bob.getUsername())).count());
        assertEquals(1L, alice.getFollowing().stream().filter(x -> x.getUsername().equals(charlie.getUsername())).count());
        assertTrue(alice.getFollowers().isEmpty());

        // BOB
        assertEquals(1, bob.getFollowers().size());
        assertEquals(alice.getUsername(), bob.getFollowers().get(0).getUsername());
        assertEquals(1, bob.getFollowing().size());
        assertEquals(charlie.getUsername(), bob.getFollowing().get(0).getUsername());
        
        // CHARLIE
        assertEquals(1L, charlie.getFollowers().stream().filter(x -> x.getUsername().equals(alice.getUsername())).count());
        assertEquals(1L, charlie.getFollowers().stream().filter(x -> x.getUsername().equals(bob.getUsername())).count());
        assertTrue(charlie.getFollowing().isEmpty());
    }
    
    @Test
    void user_can_not_follow_itself() {
        
        var alice1 = new User("Alice");
        var alice2 = new User("Alice");
        
        assertThrows(IllegalArgumentException.class, () -> alice1.followUser(alice1));
        assertThrows(IllegalArgumentException.class, () -> alice1.followUser(alice2));
    }
}
