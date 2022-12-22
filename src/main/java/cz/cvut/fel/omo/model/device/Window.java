package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Window implements Observer {
    private static final Logger LOGGER = LogManager.getLogger(TemperatureSensor.class.getName());

    private boolean isOpen;

    public Window(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type) {
            case Hot_temperature -> {
                if (!isOpen) {
                    open();
                    LOGGER.info("Window is open");
                }
            }
            case Cold_temperature -> {
                if (isOpen) {
                    close();
                    LOGGER.info("Window is closed");
                }
            }
        }

    }
}
