package designPatterns.structural.decorator.ex1;

//Both WindowDecorator and Simple Window implement Window
//This class is abstract  -doesn't implement getDescription
abstract class WindowDecorator implements Window {
	protected Window decoratedWindow; // the Window being decorated

	public WindowDecorator (Window decoratedWindow) {
		this.decoratedWindow = decoratedWindow;
	}
	public void draw() {
		decoratedWindow.draw();
	}
}

