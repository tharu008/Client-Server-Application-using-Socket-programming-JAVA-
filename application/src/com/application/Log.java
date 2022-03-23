package com.application;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public Logger logger;
    FileHandler fh;

    public Log(String server_log) throws SecurityException, IOException {

        File f = new File(server_log);
        if(!f.exists()) {
            f.createNewFile();
        }
        fh = new FileHandler(server_log, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        //Print a brief summary of the LogRecord
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
