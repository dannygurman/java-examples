package designPatterns.structural.bridge.ex1;


//Implementor
//TV implementation interface:
public interface TV
{
    public void on();
    public void off();
    public void tuneChannel(int channel);
}