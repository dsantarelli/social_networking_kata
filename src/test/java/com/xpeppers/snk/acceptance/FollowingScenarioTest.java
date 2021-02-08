package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

class FollowingScenarioTest extends AcceptanceTest {

    @Test
    void charlie_follows_alice_and_bob_and_he_should_see_their_messages_on_his_wall() {        
        test(

          // INPUT
          lines(
            "Alice -> Hello! I'm Alice.", 
            "Bob -> Hello! I'm Bob.", 
            "Charlie -> Hello! I'm Charlie.",
            "Charlie follows Alice",
            "Charlie follows Bob",
            "Charlie wall"
          ),
          
          // OUTPUT
          lines(
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> Charlie - Hello! I'm Charlie. (moments ago)",
            "> Bob - Hello! I'm Bob. (moments ago)",
            "> Alice - Hello! I'm Alice. (moments ago)",
            "> "
          ) 
        );
    }
    
    @Test
    void charlie_follows_alice_and_bob_and_he_should_not_see_their_messages_on_his_wall_if_nobody_posted_anything() {        
        test(
          
          // INPUT
          lines(
            "Charlie follows Alice",
            "Charlie follows Bob",
            "Charlie wall"
          ),
          
          // OUTPUT
          lines(
            "> " +
            "> " +
            "> " +
            "> No messages found",
            "> "
          ) 
        );
    }
    
    @Test
    void everybody_can_follow_everybody() {        
        test(
          
          // INPUT
          lines(
            "Alice -> Hello! I'm Alice.", 
            "Bob -> Hello! I'm Bob.", 
            "Charlie -> Hello! I'm Charlie.",
              
            "Alice follows Bob",
            "Alice follows Charlie",
            "Bob follows Alice",
            "Bob follows Charlie",
            "Charlie follows Alice",
            "Charlie follows Bob",

            "Alice wall",
            "Bob wall",
            "Charlie wall"
          ),
          
          // OUTPUT
          lines(
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> " +
            "> Charlie - Hello! I'm Charlie. (moments ago)",
            "> Bob - Hello! I'm Bob. (moments ago)",
            "> Alice - Hello! I'm Alice. (moments ago)",
            "> " +
            "> Charlie - Hello! I'm Charlie. (moments ago)",
            "> Bob - Hello! I'm Bob. (moments ago)",
            "> Alice - Hello! I'm Alice. (moments ago)",
            "> " +
            "> Charlie - Hello! I'm Charlie. (moments ago)",
            "> Bob - Hello! I'm Bob. (moments ago)",
            "> Alice - Hello! I'm Alice. (moments ago)",
            "> "
          ) 
        );
    }
}
