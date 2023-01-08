package cz.cvut.fel.omo.reports;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * <p>This is class for reports ActivityAndUsage.</p>
 */
public class ActivityAndUsageReport implements Reportable {
    private final House house;
    private final int numberOfConfig;

    public ActivityAndUsageReport(House house, int numberOfConfig) {
        this.house = house;
        this.numberOfConfig = numberOfConfig;
    }

    @Override
    public void generateReport() {
        FileWriter writer;
        try {
            writer = new FileWriter("textReports/" + numberOfConfig + "/activityAndUsageReport.txt");
            writer.write(numberOfConfig == 1 ? "First configuration:\n" : "Second configuration:\n");
            writer.write("\tHumans:\n");
            for (Human h : house.getHumans()) {
                writer.write("\t\t" + h.getName() + ":\n");
                for (Map.Entry<Device, Integer> entry : h.getDeviceUsageCount().entrySet()) {
                    writer.write("\t\t\t" + entry.getKey().getName() + ": " + entry.getValue() + "\n");
                }
            }

            writer.write("\tPets:\n");
            for (Pet p : house.getPets()) {
                writer.write("\t\t" + p.getName() + ":\n");
                for (Map.Entry<Device, Integer> entry : p.getDeviceUsageCount().entrySet()) {
                    writer.write("\t\t\t" + entry.getKey().getName() + ": " + entry.getValue() + "\n");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
