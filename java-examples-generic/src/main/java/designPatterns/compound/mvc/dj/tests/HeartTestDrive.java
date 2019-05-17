package designPatterns.compound.mvc.dj.tests;

import designPatterns.compound.mvc.dj.ControllerInterface;
import designPatterns.compound.mvc.dj.HeartController;
import designPatterns.compound.mvc.dj.HeartModel;
  
public class HeartTestDrive {

    public static void main (String[] args) {
    	
		HeartModel heartModel = new HeartModel();

        //All we need to do is create  the controller and pass it a   heart monitor
        ControllerInterface controller = new HeartController(heartModel);
    }
}
