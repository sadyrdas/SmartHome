package cz.cvut.fel.omo;

import cz.cvut.fel.omo.simulation.Simulation;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static final Scanner scan = new Scanner(System.in);
    private Simulation simulation;
    private static final Logger LOG = LogManager.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) throws IOException, ParseException {
        LOG.info("Trying to create simulation from file...");
        System.out.println("File name?");
        int name = Integer.parseInt(scan.nextLine());
        Simulation simulation = new Simulation();
        LOG.info("Simulation is created from prepared data set.");
        simulation.startSimulation(name);
    }


}