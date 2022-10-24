package src.Tower;

import java.util.ArrayList;

import src.SystemLog;
import src.Aircrafts.Flyable;

public abstract class Tower {
	
	private ArrayList<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable flyable) {
		if (!observers.contains(flyable)) {
			observers.add(flyable);
			SystemLog.getWriteLog().addLog("Tower says: " + flyable.getRegistration() + " registered to weather tower.");
		}
	}

	public void unregister(Flyable flyable) {
		observers.remove(observers.indexOf(flyable));
		SystemLog.getWriteLog().addLog("Tower says: " + flyable.getRegistration() + " unregistered to weather tower.");
	}

	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}
}
