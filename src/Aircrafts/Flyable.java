package src.Aircrafts;

import src.Tower.WeatherTower;

public interface Flyable {

	public void updateConditions();

	public void registerTower(WeatherTower WeatherTower);

	public String getRegistration();

}
