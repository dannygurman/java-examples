package designPatterns.behavioral.command.ex1.simpleremote.commands;

import designPatterns.behavioral.command.ex1.simpleremote.GarageDoor;

public class GarageDoorOpenCommand implements Command {
	GarageDoor garageDoor;

	public GarageDoorOpenCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.up();
	}
}
