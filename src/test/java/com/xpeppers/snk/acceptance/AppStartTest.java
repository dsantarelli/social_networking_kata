package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

class AppStartTest extends AcceptanceTest {

    @Test
    void app_should_start_with_command_prompt() {        
        test(
           // INPUT
           noLines()
           ,           
           // OUTPUT
           lines("> ")
        );       
    }
}
