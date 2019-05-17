package designPatterns.structural.bridge.ex1;

/**
 * Created by DannyG on 05/01/2015.
 */
//Abstraction
//a remote control  abstraction to control the TV:
public abstract class RemoteControl
{
    private TV implementor;


    public void on()
    {
        implementor.on();
    }
    public void off()
    {
        implementor.off();
    }

    public void setChannel(int channel)
    {
        implementor.tuneChannel(channel);
    }
}
