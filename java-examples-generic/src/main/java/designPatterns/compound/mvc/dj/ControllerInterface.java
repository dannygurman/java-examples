package designPatterns.compound.mvc.dj;


//The controller is	the strategy that we plug into the view to give it some smarts.
//Because we are implementing the Strategy Pattern, we need to start with an interface
// for any Strategy that might be plugged into the DJ View.
// We’re going to call it ControllerInterface.
public interface ControllerInterface {
	void start();
	void stop();
	void increaseBPM();
	void decreaseBPM();
 	void setBPM(int bpm);
}
