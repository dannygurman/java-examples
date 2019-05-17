package designPatterns.structural.decorator.ex1;

public class HorizontalScrollBarDecorator extends WindowDecorator {

	public HorizontalScrollBarDecorator (Window decoratedWindow) {
		super(decoratedWindow);
	}

	public void draw() {
		drawHorizontalScrollBar();
		super.draw();
	}

	private void drawHorizontalScrollBar() {	
		System.out.println("Draw in orizontal Scroll Bar");
	}


	public String getDescription() {
		return decoratedWindow.getDescription() + ", including horizontal scrollbars";
	}
}
