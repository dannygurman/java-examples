package designPatterns.compound.mvc.dj;
  
/*Okay, you know the model is responsible for maintaining all the data, state and any application logic.
 *   BeatModel main job is managing the beat,so it has state that maintains the current beats per minute and
  *   lots of code that generates MIDI events to create the beat that we hear.
 It also exposes an interface that lets the controller manipulate the beat 
 and lets the view and controller obtain the models state.
  
 Also, do not forget that the model uses the Observer Pattern,
 so we also need some methods to let objects register as observers
 and send out notifications.*/

public interface BeatModelInterface {
	void initialize(); //This gets called after the	BeatModel is instantiated.

	//These are the methods he controller will use todirect the model based on	user interaction
	void on();
  
	void off();
  
    void setBPM(int bpm);
  
	int getBPM();
  
	
//	We have split this into two kinds of 	observers:
//	observers that want to be notified on every beat,
//	and observers that just want to be notified with the beats per minute (BPM) change.
	
	void registerObserver(BeatObserver o);
  
	void removeObserver(BeatObserver o);
  
	void registerObserver(BPMObserver o);
  
	void removeObserver(BPMObserver o);
}
