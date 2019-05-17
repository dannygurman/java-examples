package designPatterns.creational.factory.abstractFactory.ex2;

public class Main{
	
	   public static void main(String[] args)   
	   {
	     GUIBuilder builder = new GUIBuilder();
	     AbstractWidgetFactory widgetFactory = null;
	     //check what platform we're on 
	     if(Platform.currentPlatform()=="MACOSX")
	     {
	        widgetFactory  = new MacOSXWidgetFactory();
	     }
	     else
	     {
	           widgetFactory  = new MsWindowsWidgetFactory();
	     }
	     builder.buildWindow(widgetFactory); 
	    }
	}
