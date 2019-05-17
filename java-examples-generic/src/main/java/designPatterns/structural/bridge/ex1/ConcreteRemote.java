package designPatterns.structural.bridge.ex1;

/**
 * Created by DannyG on 05/01/2015.
 */
//Refined abstraction

//As the remote control holds a reference to the TV, it can delegates the methods through to the interface.
// //But what is we want a more specific remote control - one that has the + / - buttons for
// moving through the channels? All we need to do is extend our RemoteControl abstraction

// to contain these concepts:
public class ConcreteRemote extends RemoteControl
{
    private int currentChannel;

    public void nextChannel()
    {
        currentChannel++;
        setChannel(currentChannel);
    }

    public void prevChannel()
    {
        currentChannel--;
        setChannel(currentChannel);
    }


}