package org.bgu.config;

import org.apache.logging.log4j.Level;

public class LoggerLevel {
	
	public static final Level SECURITY = Level.forName("SECURITY", 550);
	public static final Level AUTHENTICATION = Level.forName("AUTHENTICATION", 551);
	
	// Restrict Instantiation
	private LoggerLevel() {}
}
