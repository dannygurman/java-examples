package designPatterns.behavioral.command.ex1.simpleremote;

import designPatterns.behavioral.command.ex1.simpleremote.commands.GarageDoorOpenCommand;
import designPatterns.behavioral.command.ex1.simpleremote.commands.LightOnCommand;

public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();
		Light light = new Light();
		GarageDoor garageDoor = new GarageDoor();
		LightOnCommand lightOn = new LightOnCommand(light);
		GarageDoorOpenCommand garageOpen =  new GarageDoorOpenCommand(garageDoor);
 
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		remote.setCommand(garageOpen);
		remote.buttonWasPressed();
    }
}
