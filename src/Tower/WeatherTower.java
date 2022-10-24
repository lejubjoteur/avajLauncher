package src.Tower;

import src.Coordinates;
import src.WeatherProvider;

public class WeatherTower extends Tower {
	
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	public void changeWeather() {
		this.conditionsChanged();
	}
}
