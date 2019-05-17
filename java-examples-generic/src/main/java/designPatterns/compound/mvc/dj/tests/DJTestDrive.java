package designPatterns.compound.mvc.dj.tests;

import designPatterns.compound.mvc.dj.BeatController;
import designPatterns.compound.mvc.dj.BeatModel;
import designPatterns.compound.mvc.dj.BeatModelInterface;
import designPatterns.compound.mvc.dj.ControllerInterface;

public class DJTestDrive {

	public static void main (String[] args) {
// First create a model...
		BeatModelInterface model = new BeatModel();

//then create a controller and pass it the model.
// Remember, the controller creates the view, so we don’t have to do that.
		ControllerInterface controller = new BeatController(model);

	}
}



