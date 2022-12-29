package cz.cvut.fel.omo.reports;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.user.ActivityPet;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class EventReport implements Reportable {

    private final House house;
    private final int numberOfConfig;
    private final SimulationFacade simulationFacade;

    public EventReport(House house, int numberOfConfig, SimulationFacade simulationFacade) {
        this.house = house;
        this.numberOfConfig = numberOfConfig;
        this.simulationFacade = simulationFacade;
    }

    @Override
    public void generateReport() {
        FileWriter writer;
        try {
            writer = new FileWriter("textReports/" + numberOfConfig + "/eventsReport.txt");
            writer.write(numberOfConfig == 1 ? "First configuration:\n" : "Second configuration:\n");
            System.out.println();
            writer.write("\t Users events:\n");
            for (Map<Human, ActivityUser> map : simulationFacade.getHumanActivityUserMap()) {
                for (Map.Entry<Human, ActivityUser> entry : map.entrySet()) {
                    writer.write("\t\tType of Event: " + entry.getValue() + "\n");
                    writer.write("\t\tTarget: " + entry.getKey().getName() + "\n\n");
                }
            }

            writer.write("================================================================\n");
            writer.write("\t Device events:\n");
            for (Map<Device, EventsType> map : simulationFacade.getDeviceEventsTypeMap()) {
                for (Map.Entry<Device, EventsType> entry : map.entrySet()) {
                    writer.write("\t\tType of Event: " + entry.getValue() + "\n");
                    writer.write("\t\tTarget: " + entry.getKey().getName() + "\n\n");
                }
            }

            writer.write("=================================================================\n");
            writer.write("\t Pet events:\n");
            for (Map<Pet, ActivityPet> map : simulationFacade.getPetActivityPetMap()) {
                for (Map.Entry<Pet, ActivityPet> entry : map.entrySet()) {
                    writer.write("\t\tType of Event: " + entry.getValue() + "\n");
                    writer.write("\t\tTarget: " + entry.getKey().getName() + "\n\n");
                }
            }

            writer.flush();
            writer.close();
        }  catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
