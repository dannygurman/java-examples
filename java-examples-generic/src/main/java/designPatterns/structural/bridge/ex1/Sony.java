package designPatterns.structural.bridge.ex1;


//Concrete Implementor
//specific implementations
//This class deal with the specific implementations of the TV from each vendor
public class Sony implements TV
{
    public void on()
    {
        //Sony specific on
    }


    public void off()
    {
        //Sony specific off
    }

    public void tuneChannel(int channel)
    {

        //Sony specific tuneChannel
    }
}

