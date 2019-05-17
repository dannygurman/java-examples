package designPatterns.structural.decorator.ex1;

public class VerticalScrollBarDecorator extends WindowDecorator {

	public VerticalScrollBarDecorator (Window decoratedWindow) {
		super(decoratedWindow);
	}

	public void draw() {
		super.draw();
		drawVerticalScrollBar();
	}

	private void drawVerticalScrollBar() {
		System.out.println("Draw in Vertical Scroll Bar");
	}

	public String getDescription() {
		return decoratedWindow.getDescription() + ", including vertical scrollbars";
	}
}
