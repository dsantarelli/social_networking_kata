package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

public class ErrorScenarioTest extends AcceptanceTest {
    
    @Test
    void alice_submits_an_invalid_command() {        
        test(                
            // INPUT
            lines("Alice XXX Hello!")
            ,            
            // OUTPUT
            lines(
              "> " +
              "> ERROR: user \"Alice XXX Hello!\" not found",
              "> "
            )
        );
    }
    
    @Test
    void a_not_existing_user_submits_a_command() {        
        test(                
            // INPUT
            lines("Dario -> Hello!")
            ,            
            // OUTPUT
            lines(
              "> " +
              "> ERROR: user \"Dario\" not found",
              "> "
            )
        );
    }
    
    @Test
    void a_not_existing_user_cant_follow_an_existing_user_and_viceversa() {        
        test(                
            // INPUT
            lines("Dario follows Alice")
            ,            
            // OUTPUT
            lines(
              "> " +
              "> ERROR: user \"Dario\" not found",
              "> "
            )
        );
        
        test(                
            // INPUT
            lines("Alice follows Dario")
            ,            
            // OUTPUT
            lines(
              "> " +
              "> ERROR: user \"Dario\" not found",
              "> "
            )
        );
    }
    
    @Test
    void an_existing_user_cant_follow_itself() {        
        test(                
            // INPUT
            lines("Bob follows Bob")
            ,            
            // OUTPUT
            lines(
              "> " +
              "> ERROR: user \"Bob\" can't follow itself",
              "> "
            )
        );
    }
}

