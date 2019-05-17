package designPatterns.compound.mvc.dj;

import org.jetbrains.annotations.NotNull;

public class BeatController implements ControllerInterface {
	//The controller is in the middle of the MVC so it is the object that
	//gets to hold on to the view and model and glues it all together.
	BeatModelInterface model;
	DJView view;

	//The controller is passed the model in the constructor and	then creates the view.
	public BeatController(@NotNull BeatModelInterface model) {
		this.model = model;
		view = new DJView(this, model);
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}

	//NOTE: the controller is	making the intelligent	decisions for the view.
	//The view just knows howto turn menu items on	and off;
	// it does not know	the situations in which it	should disable them

	//When you choose Start from the user interface menu, the controller turns the
	//	model on and then alters the user interface so that the start menu item is
	// disabled and	the stop menu item is enabled
	public void start() {
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}


	//When you choose Stop from the	menu, the controller turns the model off and alters the user interface
	// so thatthe stop menu item is disabled and the start menu item is enabled
	public void stop() {
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}

	//If the increase button is clicked, the controller gets the current BPM
	// from the model, adds one, and then sets a new BPM.

	public void increaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm + 1);
	}

	//Same thing here, only we subtract
	public void decreaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm - 1);
  	}

	//Finally, if the user interface is used to set an arbitrary BPM,
	//the controller instructs the model to set its BPM.
 	public void setBPM(int bpm)
	{
		model.setBPM(bpm);
	}
}
