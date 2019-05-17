package designPatterns.behavioral.observer.ex1.weatherobservable;

public class TestStation2 {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

		System.out.println("1");
		weatherData.setMeasurements(80, 65, 30.4f);
		System.out.println("2");
		weatherData.setMeasurements(82, 70, 29.2f);
		System.out.println("3");
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
