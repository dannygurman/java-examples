package designPatterns.creational.factory.abstractFactory.ex2;

//ConcreteFactory2
public class MacOSXWidgetFactory implements AbstractWidgetFactory {

 //create a MacOSXWindow
 public Window createWindow()
 {
    MacOSXWindow window = new MacOSXWindow();   
    return window;
 }
}
