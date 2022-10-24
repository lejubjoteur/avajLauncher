package src.Aircrafts;

import src.Coordinates;
import src.SystemLog;
import src.Tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.type = "Baloon";
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
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": Damn you rain! You messed up my baloon.");
	}

	private void fog() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": I can't see you bro!");
	}

	private void sun() {
		this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": Let's enjoy the good weather and take some pics.");
	}

	private void snow() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": It's snowing. We're gonna crash.");
	}
}
