package avajlauncher.simulation;

import avajlauncher.exceptions.SimulationException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Simulation {
    private int iterations;
    private List<String[]> aircrafts = new ArrayList<>();

    public int getIterations() {
        return iterations;
    }

    public List<String[]> getAircrafts() {
        return aircrafts;
    }

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


    //Baloon B1 2 3 20
    private String[] checkString(String line, int num) throws SimulationException {
        String[] words = line.split("\\s+");
        try{
            if (words.length != 5)
                throw new SimulationException("Invalid number of arguments on line " + num + "!");
            if (!(words[0].equals("Baloon") || words[0].equals("Helicopter") || words[0].equals("JetPlane")))
                throw new SimulationException("Invalid aircraft on line " + num + "!");
            for (int i = 2; i < words.length; i++) {
                int coord = Integer.parseInt(words[i]);
                if (coord < 0)
                    throw new SimulationException("Invalid coordinates on line " + num + "!");
            }
        }
        catch (NumberFormatException e) {
            throw new SimulationException("Invalid coordinates on line " + num + "!");
        }
        return words;
    }
    private void readFile(String filename, Simulation simulation) throws SimulationException {
        int num = 1;
        try (Scanner scanner = new Scanner(new File("src/" + filename))) {
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
        try {
            simulation.checkArgs(args);
            simulation.readFile(args[0], simulation);
            for
        }
        catch (SimulationException e) {
            System.out.println(e.getMessage());
        }
    }
}



