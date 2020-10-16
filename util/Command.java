package ca.utoronto.utm.util;

/*
 * This command interface gives us a way to let classes implement and execute the methods defined here
 * 
 * NOTE:
 * 	- According to the Java Language Specification, explicitly declaring the interface abstract (Chapter 9.1.1.1), or the methods within (Chapter 9.4) is discouraged
 * 	- We do this regardless to demonstrate that we know these interfaces and methods are (implicitly) abstract
 * 
 */
abstract public interface Command {
	abstract void execute();
}
