package cz.cvut.fel.omo.reports;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Shower;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ConsumptionReport implements Reportable {

    private final House house;
    private final int numberOfConfig;
    private final SimulationFacade simulationFacade;



    public ConsumptionReport(House house, int numberOfConfig, SimulationFacade simulationFacade) {
        this.house = house;
        this.numberOfConfig = numberOfConfig;
        this.simulationFacade = simulationFacade;
    }

    @Override
    public void generateReport() {
        FileWriter writer;
        try {
            writer = new FileWriter("textReports/" + numberOfConfig + "/consumptionReport.txt");
            writer.write(numberOfConfig == 1 ? "First configuration:\n" : "Second configuration:\n");
            writer.write("\tDevices usage for the whole simulation\n");
            for (Map.Entry<Device, Integer> entry: simulationFacade.getDevicesPowerConsumptions().entrySet()) {
                writer.write("\t\t" + entry.getKey().getName() + " " + entry.getValue() + " W\n");
            }

            writer.write("\tShowers usage for the whole simulation\n");
            for (Map.Entry<Shower, Integer> entry: simulationFacade.getShowerWaterConsumptions().entrySet()) {
                writer.write("\t\t" + entry.getKey().getName() + " " + entry.getValue() + " l\n");
            }

            writer.flush();
            writer.close();
        }  catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
