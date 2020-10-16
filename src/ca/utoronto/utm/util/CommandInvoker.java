package ca.utoronto.utm.util;

/*
 * A class to act as the "invoker" class in the command design pattern
 * 
 * This asks the command to carry out its operation, through it's execute method
 * 
 * This acts as a "middle man" controlling the way commands get executed
 * 
 */
public class CommandInvoker {
	
	/*
	 * Empty Constructor
	 */
	public CommandInvoker() {
	}
	
	/*
	 * Executes the command passed in, in a specific way
	 * While this method is basic, the design pattern's strength comes from its ability to control the way commands are executed
	 * It does so through the usage of this class
	 */
	public void ExecuteCommand(Command c) {
		c.execute();
	}
}
