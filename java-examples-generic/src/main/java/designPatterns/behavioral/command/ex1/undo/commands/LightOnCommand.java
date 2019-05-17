package designPatterns.behavioral.command.ex1.undo.commands;

import designPatterns.behavioral.command.ex1.undo.Light;

public class LightOnCommand implements Command {
	Light light;
	int level;
	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
        level = light.getLevel();
		light.on();
	}
 
	public void undo() {
		light.dim(level);
	}
}
