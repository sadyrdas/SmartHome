package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.patterns.builder.TransportBuilder;
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
        LOGGER.info(":3-TickTack-:3");
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
