package designPatterns.structural.bridge.ex1;

/**
 * Created by DannyG on 05/01/2015.
 */
//Concrete Implementor
//specific implementations
//This class deal with the specific implementations of the TV from each vendor

public class Philips implements TV
{
    public void on()
    {
        //Philips specific on
    }


    public void off()
    {
        //Philips specific off
    }

    public void tuneChannel(int channel)
    {
        //Philips specific tuneChannel
    }
}