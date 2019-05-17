package designPatterns.behavioral.command.ex1.remote.commands;

import designPatterns.behavioral.command.ex1.remote.Light;

public class LightOffCommand implements Command {
	Light light;
 
	public LightOffCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.off();
	}
}
