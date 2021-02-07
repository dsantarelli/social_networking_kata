package com.xpeppers.snk.acceptance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.xpeppers.snk.App;
import com.xpeppers.snk.ui.ClockingCommandLineInterface;
import com.xpeppers.snk.ui.CommandLineInterface;

abstract class AcceptanceTest {
        
    protected static void test(String[] inputLines, String[] expectedOutputLines) {
        
        var inputStream = new ByteArrayInputStream(String.join(newLine(), inputLines).getBytes());        
        var outputStream = new ByteArrayOutputStream();
        var printStream = new PrintStream(outputStream);

        try {
            // Decorator for simulating a little latency between commands.
            var cli = new ClockingCommandLineInterface(new CommandLineInterface(inputStream, printStream), 10);
            new App(cli).start();
            var actualOutputLines = new String(outputStream.toByteArray()).split(newLine());
            
            assertArrayEquals(expectedOutputLines, actualOutputLines);
            
        } finally {
            try {
                inputStream.close();
                printStream.close();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected static String[] noLines() {
        return new String[0];
    }
    
    protected static String[] lines(String... lines) {
        return lines; // (-_-;)
    }
    
    protected static String newLine() {
        return System.lineSeparator();
    }
}
