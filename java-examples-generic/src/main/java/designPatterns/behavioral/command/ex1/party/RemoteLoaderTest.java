package designPatterns.behavioral.command.ex1.party;

import designPatterns.behavioral.command.ex1.party.commands.*;
import designPatterns.behavioral.command.ex1.party.appliance.Hottub;
import designPatterns.behavioral.command.ex1.party.appliance.Stereo;

public class RemoteLoaderTest {

	public static void main(String[] args) {

		RemoteControl remoteControl = new RemoteControl();
		
		Stereo stereo = new Stereo("Living Room");
		Hottub hottub = new Hottub();
//		Light light = new Light("Living Room");
//		TV tv = new TV("Living Room");
 
		StereoOnCommand stereoOn = new StereoOnCommand(stereo);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		HottubOnCommand hottubOn = new HottubOnCommand(hottub);
		HottubOffCommand hottubOff = new HottubOffCommand(hottub);
//		LightOnCommand lightOn = new LightOnCommand(light);
//		TVOnCommand tvOn = new TVOnCommand(tv);
//		LightOffCommand lightOff = new LightOffCommand(light);
//		TVOffCommand tvOff = new TVOffCommand(tv);
	

		Command[] partyOn = { hottubOn, stereoOn }; // tvOn, lightOn};
		Command[] partyOff = { hottubOff , stereoOff};//, lightOff , tvOff};
  
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
 
		remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
  
		System.out.println(remoteControl);
		System.out.println("--- Pushing Macro On---");
		remoteControl.onButtonWasPushed(0);
		System.out.println("--- Pushing Macro Off---");
		remoteControl.offButtonWasPushed(0);
	}
}
