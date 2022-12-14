package src.Aircrafts;

import src.Coordinates;

public abstract class Aircraft {

	protected long id;
	protected String name;
	protected String type;
	protected Coordinates coordinates;
	private static long idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private long nextId() {
		return ++idCounter;
	}

	public String getRegistration() {
		return this.type + "#" + this.name + "(" + this.id + ")";
	}
}
