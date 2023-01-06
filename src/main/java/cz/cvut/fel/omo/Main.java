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
        System.out.println("You have two types of configurations. Type 1 to console to choose first. " +
                "2 to choose second.");
        // By default, we load always first configuration
        int name = 1;
        try {
            name = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException ex) {
            LOG.error("You can only choose 1 or 2 configuration.");
            System.exit(1);
        }

        Simulation simulation = new Simulation();
        simulation.startSimulation(name);
    }

}