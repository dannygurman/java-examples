package designPatterns.behavioral.command.ex1.simpleremote;

import designPatterns.behavioral.command.ex1.simpleremote.commands.Command;

//
// This is the invoker
//
public class SimpleRemoteControl {
	Command slot;
 
	public SimpleRemoteControl() {}
 
	public void setCommand(Command command) {
		slot = command;
	}
 
	public void buttonWasPressed() {
		slot.execute();
	}
}
