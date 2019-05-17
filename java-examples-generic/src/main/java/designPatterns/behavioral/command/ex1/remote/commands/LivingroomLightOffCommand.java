package designPatterns.behavioral.command.ex1.remote.commands;

import designPatterns.behavioral.command.ex1.remote.Light;

public class LivingroomLightOffCommand implements Command {
	Light light;

	public LivingroomLightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.off();
	}
}
