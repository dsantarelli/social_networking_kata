package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

class ReadingScenarioTest extends AcceptanceTest {

    @Test
    void bob_can_see_alice_timeline() {        
        test(
                
            // INPUT
            lines(
              "Alice -> Hello! I'm Alice.", 
              "Bob -> Hello! I'm Bob.", 
              "Alice",
              "Bob"
            ),
            
            // OUTPUT
            lines(
               "> " +
               "> " +
               "> " +
               "> Hello! I'm Alice. (moments ago)",
               "> " +
               "> Hello! I'm Bob. (moments ago)",
               "> "
            )
        );
    }
}
