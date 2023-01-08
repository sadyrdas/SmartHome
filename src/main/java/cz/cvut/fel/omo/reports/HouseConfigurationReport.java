package cz.cvut.fel.omo.reports;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.house.Floor;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>This is class for reports for configuration of House.</p>
 */
public class HouseConfigurationReport implements Reportable {

    private final House house;
    private final int numberOfConfig;

    public HouseConfigurationReport(House house, int numberOfConfig) {
        this.house = house;
        this.numberOfConfig = numberOfConfig;
    }

    @Override
    public void generateReport() {
        FileWriter writer;

        try {
            writer = new FileWriter("textReports/" + numberOfConfig + "/houseConfigurationReport.txt");
            writer.write(numberOfConfig == 1 ? "First configuration:\n" : "Second configuration:\n");

            for (Floor f : house.getFloors()) {
                writer.write("\t" + "Floor " + f.getId() + ": \n");
                for (Room r : f.getRooms()) {
                    writer.write("\t\t" + r.getRoomName() + ":\n");
                    writer.write("\t\t\tDevices:\n");
                    for (Device d : house.getDevices().stream().filter(d -> d.getRoom().getId() == r.getId()).toList()) {
                        writer.write("\t\t\t\t" + d.getName() + "\n");
                    }
                    writer.write("\t\t\tSensors:\n");
                    for (Sensor s : house.getSensors().stream().filter(s -> s.getRoom().getId() == r.getId()).toList()) {
                        writer.write("\t\t\t\t" + s.getName() + "\n");
                    }
                    writer.write("\t\t\tWindows: " + r.getWindows().size() + "\n");

                }
            }
            writer.write("Humans:\n");
            for (Human h : house.getHumans()) {
                writer.write("\t" + h.getName() + " - " + h.getPermissions() + "\n");
            }

            writer.write("Pets:\n");
            for (Pet p : house.getPets()) {
                writer.write("\t" + p.getName() + "\n");

            }
            writer.write("Transport:\n");
            for (Transport t : house.getTransports()) {
                writer.write("\t" + t.getName() + "\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}

