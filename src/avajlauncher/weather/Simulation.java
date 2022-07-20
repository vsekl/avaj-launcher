package avajlauncher.weather;

import avajlauncher.aircrafts.AircraftFactory;
import avajlauncher.exceptions.SimulationException;
import avajlauncher.utils.OutputWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Simulation {
    private int iterations;
    private List<String[]> aircrafts = new ArrayList<>();

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void addAircrafts(String[] aircraft) {
        this.aircrafts.add(aircraft);
    }

    private void checkArgs(String[] args) throws SimulationException{
        if (args.length != 1)
            throw new SimulationException("Wrong number of arguments!");
        if (!args[0].endsWith("scenario.txt"))
            throw new SimulationException("File should ends with \"scenario.txt\"");
    }
    private int checkIterations(String str) throws  SimulationException{
        int iterations = 0;

        try {
            iterations = Integer.parseInt(str);
            if (iterations < 0)
                throw new SimulationException("Number of iterations should be a positive integer!");
            return iterations;
        }
        catch (NumberFormatException e) {
            throw new SimulationException("Number of iterations should be a positive integer!");
        }
    }

    private String[] checkString(String line, int num) throws SimulationException {
        String[] words = line.split("\\s+");
        try{
            if (words.length != 5)
                throw new SimulationException("Invalid number of arguments on line " + num + "!");
            if (!(words[0].equals("Balloon") || words[0].equals("Helicopter") || words[0].equals("JetPlane")))
                throw new SimulationException("Invalid aircraft on line " + num +1  + "!");
            for (int i = 2; i < words.length; i++) {
                int coord = Integer.parseInt(words[i]);
                if (coord < 0)
                    throw new SimulationException("Invalid coordinates on line " + num + 1 + "!");
            }
        }
        catch (NumberFormatException e) {
            throw new SimulationException("Invalid coordinates on line " + num + 1 + "!");
        }
        return words;
    }
    private void readFile(String filename, Simulation simulation) throws SimulationException {
        int num = 1;
        try (Scanner scanner = new Scanner(new File("src/avajlauncher/scenarios/" + filename))) {
            if (!scanner.hasNext())
                throw new SimulationException("This file is empty!");
            simulation.setIterations(checkIterations(scanner.nextLine()));
            while (scanner.hasNext())
                simulation.addAircrafts(checkString(scanner.nextLine(), num++));
        }
        catch (FileNotFoundException e) {
            throw new SimulationException("File does not exist!");
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        WeatherTower weatherTower;

        try {
            simulation.checkArgs(args);
            simulation.readFile(args[0], simulation);
            weatherTower = new WeatherTower();
            OutputWriter.create();
            for(String[] aircraft: simulation.aircrafts)
                AircraftFactory.newAircraft(aircraft[0], aircraft[1], Integer.parseInt(aircraft[2]),
                        Integer.parseInt(aircraft[3]), Integer.parseInt(aircraft[4])).registerTower(weatherTower);
            for (int i=0; i < simulation.iterations; i++)
                weatherTower.changeWeather();
        }
        catch (SimulationException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}



