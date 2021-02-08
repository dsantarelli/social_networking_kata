package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

class PostingScenarioTest extends AcceptanceTest {

    @Test
    void alice_can_post_messages_to_her_personal_timeline() {
        test(  
                
           // INPUT
           lines(
             "Alice -> Hello! I'm Alice", 
             "Alice -> I love the weather today", 
             "Alice"
           )
           ,  
           // OUTPUT
           lines(
             "> " +
             "> " +
             "> " +
             "> I love the weather today (moments ago)",
             "> Hello! I'm Alice (moments ago)",
             "> "
           )
        );
    }
    
    @Test
    void alice_can_see_her_empty_timeline() {        
        test(
                
           // INPUT
           lines("Alice")
           ,
           // OUTPUT
           lines(
            "> " +
            "> No messages found",
            "> "
          )
       );
    }
}
