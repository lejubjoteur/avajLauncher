package src.Aircrafts;

import src.Coordinates;
import src.SystemLog;
import src.Tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.type = "JetPlane";
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
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": It's raining. Better watch out for lightings.");
	}

	private void fog() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": There is fog to cut with a knife.");
	}

	private void sun() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ": Under the sunlights of tropics.");
	}

	private void snow() {
		this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
		SystemLog.getWriteLog().addLog(this.getRegistration() + ":  OMG! Winter is coming!");
	}
}
