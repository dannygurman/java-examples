package designPatterns.behavioral.observer.ex1.weather;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
