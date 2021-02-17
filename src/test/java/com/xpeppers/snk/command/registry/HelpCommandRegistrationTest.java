package com.xpeppers.snk.command.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpeppers.snk.command.HelpCommand;
import com.xpeppers.snk.io.ILineWriter;


@ExtendWith(MockitoExtension.class)
class HelpCommandRegistrationTest {

    @Mock private ILineWriter writer;
    @Mock private ICommandRegistry registry;

    private ICommandRegistration commandRegistration;

    @BeforeEach
    void onBeforeEach() {
        commandRegistration = new HelpCommandRegistration(writer, registry);
    }

    @Test
    void helpCommand_name() {
        assertEquals("help", commandRegistration.getName());
    }

    @Test
    void helpCommand_syntax() {
        assertEquals("help", commandRegistration.getSyntax());
    }

    @Test
    void helpCommand_match_line() {
        var matchLineFn = commandRegistration.getMatchLineFn();
        assertTrue(matchLineFn.apply("help"));
        assertTrue(matchLineFn.apply(" help "));
    }

    @Test
    void helpCommand_factory() {
        assertTrue(commandRegistration
                    .getFactoryFn()
                    .apply("help") 
                    instanceof HelpCommand);
    }
}
