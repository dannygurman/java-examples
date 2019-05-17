package designPatterns.structural.decorator.ex1;

public class DecoratedWindowTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a decorated Window with horizontal and vertical scrollbars
		Window decoratedWindow = new HorizontalScrollBarDecorator (
				new VerticalScrollBarDecorator(new SimpleWindow()));

		// print the Window's description
		System.out.println(decoratedWindow.getDescription());
		
		//Draw window
		decoratedWindow.draw();
		
	}



}
