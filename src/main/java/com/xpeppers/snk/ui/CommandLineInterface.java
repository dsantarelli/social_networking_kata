package com.xpeppers.snk.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class CommandLineInterface implements ICommandLineInterface {

    private static final String PROMPT = "> ";
    
    private final Scanner input;
    private final PrintStream output;

    public CommandLineInterface(InputStream input, PrintStream output) {        
        this.input = new Scanner(input);
        this.output = output;
    }
    
    @Override
    public String readLine() {
        output.print(PROMPT);
        if (input.hasNextLine()) {
            return input.nextLine();
        }
        else return null;
    }

    @Override
    public void writeLine(String line) {
        output.println(PROMPT + line);
    }

    @Override
    public void exit() {
       System.exit(0);
    }
}