package com.xpeppers.snk.acceptance;

import org.junit.jupiter.api.Test;

public class HelpScenarioTest extends AcceptanceTest {

    @Test
    void user_can_get_help() {
        test(
          // INPUT
          lines("help")
          ,
          // OUTPUT
          lines(
            "> " +
            "> ",
            "***************************",
            "**** HELP ¯\\_(°_o)_/¯ ****",
            "***************************",
            "<COMMAND>: <syntax>",
            "",
            "* EXIT: exit",
            "* FOLLOWING: <user name> follows <another user>",
            "* HELP: help",
            "* POSTING: <user name> -> <message>",
            "* READING: <user name>",
            "* WALL: <user name> wall",
            "",
            "> "
          )
        );
    }
}
