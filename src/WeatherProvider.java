package src;

import java.util.Random;

public class WeatherProvider {
	
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private String[] weather;

	private WeatherProvider() {
		this.weather = new String[]{ "RAIN", "FOG", "SUN", "SNOW" };
	}

	public static WeatherProvider getProvider() {
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		Random r = new Random();
		int weatherMap = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + r.nextInt(100);
		if (weatherMap < 0)
			weatherMap *= -1;
		return this.weather[weatherMap % 4];
	}
}
