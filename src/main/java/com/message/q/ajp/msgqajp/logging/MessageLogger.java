package com.message.q.ajp.msgqajp.logging;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MessageLogger {
    private final Logger mLogger; 
    private FileHandler fh;
    
    public MessageLogger() {
    	this.mLogger = Logger.getLogger(MessageLogger.class.getName());
    	try {
    		this.fh = new FileHandler("Log/MessageLog.log");
    	} catch (Exception excp) {
    		excp.printStackTrace();
    	}
    	fh.setFormatter(new SimpleFormatter());
    	mLogger.addHandler(fh);
    	mLogger.info("Starting...");
    }
    
    public void logMsg(String c) {
    	mLogger.info(c);
    }
}
