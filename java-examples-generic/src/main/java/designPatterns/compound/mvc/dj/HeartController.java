package designPatterns.compound.mvc.dj;


//With our HeartAdapter in hand we should be ready to create a controller and get the
//view running with the HeartModel.
// Code reuse ..


//The HeartController implements the ControllerInterface, just like the BeatController did.
public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;


	//Like BeatController, the controllercreates the view and getseverything glued together.

	//There is one change - we are passed a	HeartModel, not a BeatModel...
	public HeartController(HeartModelInterface model) {
		this.model = model;

		//We need to wrap that model with an adapter before	we hand it to the view
		view = new DJView(this, new HeartAdapter(model));
        view.createView();
        view.createControls();

		//Finally, the HeartController disables the	menu items as they aren’t needed
		view.disableStopMenuItem();
		view.disableStartMenuItem();
	}


	//There’s not a lot to do here;	after all, we can’t really control hearts like we can beat machines.

	public void start() {}
 
	public void stop() {}
    
	public void increaseBPM() {}
    
	public void decreaseBPM() {}
  
 	public void setBPM(int bpm) {}
}



