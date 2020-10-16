# Othello Game Application
Othello GUI-based game application using Java 11 and OpenJFX.

## Design Patterns Used
### Observer/Observable
Used by the View to observe the Model.

### Singleton
- Multiple game timers may cause threading issues. This may put the application into an inconsistent state at no benefit (no point in having multiple timers).
- Therefore, GameTimer and GameTimerTask are singleton classes to prevent this. Additionally, having the GameTimer as a singleton provides a global point of access of the object for all classes.

### Observer/Observable
- GameTimer task observers the Othello game model to check if the current player's turn is changed.
- VTimerLabel observers the GameTimer model (controlled by GameTimerTask) to see if the clock has ticked down.
- VPlayerLabel observes the Othello model to check for current player, how many tokens each player has, and their PlayerVisitor type.
- GameModeObserver observes the Othello model to check if a player change has occurred, and sets the game mode menu to the correct player.
- ComputerVisitorFactory observes the Othello model to see if it is currently a computer's turn. If so, it creates the PlayerVisitor needed to visit Othello and make a move.
- VSpot observes the Othello model to see if there has been a change and updates the board accordingly.
- VWinner observes the Othello model to see if there is a winner and outputs the label accordingly.

### Command
- The command interface for specific commands to implement
- There are 3 main commands that implement the execute method on this, undo, redo, and pause
- Each of these commands are "hooked up" to a button on the board.
- Each button is hooked up to an action event that creates a corresponding command object, with a reference to a receiver
- This receiver has the implementation of the code
- The invoker object takes the command object, and executes the command
- Command Interface:
	- An interface to be implemented by specific commands
	- It has an Execute method which takes a receiver object to execute the command's implementation
  - UndoCommand: a command object to undo
  - RedoCommand: a command object to redo
  - PauseCommand: a command object to pause game
  - CommandInvoker: This class invokes the commands, calling their execute methods
  - CommandReceiver: This class receives the command to be executed, and performs the operations associated with it
	
### Factory
- SettingsMenuFactory - Creates the settings menu through the factory, hides the creation of the SettingsMenus in their respective factories.
  - Creator: SettingsMenuFactory
  - ConcreteCreatorA: StartSettingsMenuFactory
  - ConcreteCreatorB: InGameSettingsMenuFactory
  - ConcreteCreatorC: HelpSettingsMenuFactory
  - Product: SettingsMenu
- ComputerVisitorFactory - the creator class creates new PlayerVisitors, which then visit the Othello model to make a move.
  - Creator: ComputerVisitorFactory
  - Product: PlayerVisitor
  - ConcreteProductA: GreedyVisitor
  - ConcreteProductB: RandomVisitor
  - ConcreteProductC: ImprovedVisitor

### Visitor
- ComputerVisitorFactory - PlayerVisitors visit the board by having the Othello model accept them. After accepting, the PlayerVisitor makes a move on the board.
  - Element (Visitable): Othello
  - Visitor: PlayerVisitor
  - ConcreteVisitorA: HumanVisitor
  - ConcreteVisitorB: RandomVisitor
  - ConcreteVisitorC: GreedyVisitor
  - ConcreteVisitorD: ImprovedVisitor
