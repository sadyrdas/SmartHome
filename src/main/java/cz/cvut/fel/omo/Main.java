package cz.cvut.fel.omo;

import cz.cvut.fel.omo.simulation.Simulation;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}