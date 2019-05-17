package designPatterns.creational.factory.abstractFactory.ex2;

//Client
public class GUIBuilder
{
 public void buildWindow(AbstractWidgetFactory widgetFactory)
 {
    Window window = widgetFactory.createWindow();   
    window.setTitle("New Window");
 }
}