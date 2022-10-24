package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.Aircrafts.Airbus;
import src.Aircrafts.Flyable;
import src.Tower.WeatherTower;

public class App {

	private static ArrayList<String> data = new ArrayList<String>();
	private static ArrayList<Flyable> flyables = new ArrayList<Flyable>();

	public static boolean readData(String arg) throws MyFileException {
		try {
			File file = new File(arg);
			Scanner readFile = new Scanner(file);
			while (readFile.hasNextLine()) {
				data.add(readFile.nextLine());
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			throw new MyFileException("An error occured: " + e);
		}
		return true;
	}

	public static boolean parser(ArrayList<String> data) throws MyFileException, NumberFormatException {
		if (data.size() < 2)
			throw new MyFileException("Incomplete file.");
		for (int i = 0; i < data.size(); i++) {
			String[] str = data.get(i).split(" ");
			if (i == 0) {
				try {
					if (str.length != 1 || Integer.parseInt(str[0]) <= 0)
						throw new MyFileException("First line require only one positive int.");
				} catch (NumberFormatException e) {
					throw new MyFileException("First line require only one positive int.");
				}
			}
			else {
				if (str.length != 5)
					throw new MyFileException("Line " + (i+1) + " require 5 elements.");
				if (!str[0].equals("Helicopter") && !str[0].equals("JetPlane") && !str[0].equals("Baloon")) {
					throw new MyFileException("Line " + (i+1) + " need one of these types: Helicopter, JetPlane, Baloon.");
				}
				for (int j = 2; j < str.length; j++) {
					try {
						if (Integer.parseInt(str[j]) <= 0)
							throw new MyFileException("Line " + (i+1) + ", argument [" + str[j] + "] should be positive int.");
					} catch (NumberFormatException e) {
						throw new MyFileException("Line " + (i+1) + ", argument [" + str[j] + "] should be positive int.");
					}
				}
			}
		}
		return true;
	}

	public static void run() {
		int nbSimulations = Integer.parseInt(data.get(0));
		WeatherTower weatherTower = new WeatherTower();
		Airbus airbus = new Airbus();
		for (String str: data) {
			String[] arg = str.split(" ");
			if (arg.length == 1)
				continue;
			flyables.add(airbus.newAircraft(arg[0], arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3]), Integer.parseInt(arg[4])));
			flyables.get(flyables.size()-1).registerTower(weatherTower);
		}
		for (int i = 0; i < nbSimulations; i++) {
			weatherTower.changeWeather();
		}
	}

	public static void main(String[] args) throws NumberFormatException, MyFileException {
		if (args.length != 1)
			System.out.println("Argument is missing.");
		else {
			if (!readData(args[0]))
				return;
			if (!parser(data))
				return;
			run();
			SystemLog.getWriteLog().writeLog();
		}
	}
}
