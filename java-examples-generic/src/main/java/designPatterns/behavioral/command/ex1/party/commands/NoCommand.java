package designPatterns.behavioral.command.ex1.party.commands;

import designPatterns.behavioral.command.ex1.party.commands.Command;

public class NoCommand implements Command {
	public void execute() { }
	public void undo() { }
}
