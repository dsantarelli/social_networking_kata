package com.xpeppers.snk.ui;

import com.xpeppers.snk.io.ILineReader;
import com.xpeppers.snk.io.ILineWriter;

public interface ICommandLineInterface extends ILineReader, ILineWriter  {
    
    void exit();
}
