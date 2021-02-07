package com.xpeppers.snk.ui;

public class ClockingCommandLineInterface implements ICommandLineInterface {

    private final ICommandLineInterface cli;
    private final int clockTime;
    
    public ClockingCommandLineInterface(ICommandLineInterface cli, int clockTime) {
        this.cli = cli;
        this.clockTime = clockTime;     
    }
    
    @Override
    public String readLine() {
        sleep(clockTime);
        return cli.readLine();
    }
    
    @Override
    public void writeLine(String line) {        
        cli.writeLine(line);        
    }       
    
    @Override
    public void exit() {
        cli.exit();
    }
    
    private static void sleep(int millis) {
        try { Thread.sleep(millis); } 
        catch (Throwable e) { throw new RuntimeException(e); }
    }
}
