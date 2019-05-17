package designPatterns.behavioral.observer.ex1.weather;

public interface Observer {
	public void update(float temp, float humidity, float pressure);
}
