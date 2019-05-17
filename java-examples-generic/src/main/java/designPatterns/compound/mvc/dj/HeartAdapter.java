package designPatterns.compound.mvc.dj;


//We need to  adapt the HeartModel to a BeatModel.
// If we don’t, the view will not be able to work with the model,
// because the view only knows how to getBPM(), and the	equivalent heart model method is getHeartRate().
//
// HWe’re going to use the Adapter Pattern -This his is a common technique when working	with the MVC:
// use an adapter to adapt a model to work with existing controllers and views.
 //HeartAdapter adapt a HeartModel to a BeatModel:


//We implement the target interface, in this case, BeatModelInterface.
public class HeartAdapter implements BeatModelInterface {
	HeartModelInterface heart;
 
	public HeartAdapter(HeartModelInterface heart) {
		//we store a reference	to the heart model.
		this.heart = heart;
	}

    public void initialize() {}

	//We don’t know what these would do	to a heart, but it sounds scary.
	// So we’ll just leave them as “no ops.”
    public void on() {}
  
    public void off() {}


	//When getBPM() is called, we’ll just	translate it to a getHeartRate() callon the heart model.
	public int getBPM() {

		return heart.getHeartRate();
	}

	//We don’t want to do this on a heart!	Again, let’s leave it as a “no op”.
    public void setBPM(	int bpm) {}


	//Here are our observer methods.
	// We just delegate them to the	wrapped heart model.
	public void registerObserver(BeatObserver o)
	{
		heart.registerObserver(o);
	}
    
	public void removeObserver(BeatObserver o)
	{
		heart.removeObserver(o);
	}
     
	public void registerObserver(BPMObserver o)
	{
		heart.registerObserver(o);
	}
  
	public void removeObserver(BPMObserver o)
	{
		heart.removeObserver(o);
	}
}
