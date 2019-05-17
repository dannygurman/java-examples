package designPatterns.behavioral.command.ex1.remote.commands;

import designPatterns.behavioral.command.ex1.remote.Light;

public class LightOnCommand implements Command {
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.on();
	}
}
