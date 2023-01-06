package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class TimeSimulation {
    private static final Logger LOGGER = LogManager.getLogger(TimeSimulation.class.getName());
    private LocalDateTime dateTime;

    public TimeSimulation(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void tick(){

        dateTime = dateTime.plusHours(1);
    }

    public void printCurrentTime() {
        LOGGER.info("=====================================");
        LOGGER.info("= Current time is: " + getDateTime().toString() + " =");
        LOGGER.info("=====================================");
    }

    public void updateBlindsStatusByCurrentTime(House house, SimulationFacade simulationFacade) {
        if (dateTime.getHour() > 8 && dateTime.getHour() < 19) {
            for (Room room : house.getRooms()) {
                for (Window window : room.getWindows()) {
                    LOGGER.info("It is day! Open blinds!");
                    window.update(EventsType.Day, simulationFacade);
                }
            }
        } else {
            for (Room room : house.getRooms()) {
                for (Window window : room.getWindows()) {
                    LOGGER.info("It is day! Open blinds!");
                    window.update(EventsType.Night, simulationFacade);
                }
            }
        }
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
