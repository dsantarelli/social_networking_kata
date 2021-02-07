package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

import com.ginsberg.junit.exit.ExpectSystemExit;

class ExitScenarioTest extends AcceptanceTest {

    @Test
    @ExpectSystemExit
    void user_can_exit() {        
        test(
            // INPUT
            lines(
              "exit"
            ),
            
            // OUTPUT
            lines(
             "> " + 
             "> "
            )
        );       
    }
}
