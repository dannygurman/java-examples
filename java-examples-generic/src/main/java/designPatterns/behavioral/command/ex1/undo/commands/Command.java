package designPatterns.behavioral.command.ex1.undo.commands;

public interface Command {
	public void execute();
	public void undo();
}
