package src.Aircrafts;

import src.Coordinates;
import src.SystemLog;
import src.Tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.type = "Helicopter";
	}

	public void updateConditions() {
		String currentWeather = weatherTower.getWeather(this.coordinates);

		switch (currentWeather) {
			case "RAIN":
				rain();
			case "FOG":
				fog();
			case "SUN":
				sun();
			case "SNOW":
				snow();
		}
		if (this.coordinates.getHeight() <= 0) {
			SystemLog.getWriteLog().addLog(this.getRegistration() + " landing.");
			this.weatherTower.unregister(this);
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}

	private void rain() {
		this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": I'm not an umbrella.");
	}

	private void fog() {
		this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": We can't chase away the fog with a fan!");
	}

	private void sun() {
		this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": This is hot.");
	}

	private void snow() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": My rotor is going to freeze!");
	}
}
