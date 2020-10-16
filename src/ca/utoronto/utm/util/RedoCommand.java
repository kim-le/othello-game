package ca.utoronto.utm.util;

public class RedoCommand implements Command {
	
	// Store access to the receiver
	CommandReceiver receiver; // remember not to make a new receiver object, just store reference
	
	/*
	 * Constructor
	 */
	public RedoCommand(CommandReceiver r) {
		this.receiver = r; // Hook up reference to receiver
	}

	/*
	 * This execute method simply calls the respective command that is received by the receiver class
	 */
	@Override
	public void execute() {
		this.receiver.RedoMove();
	}

}
