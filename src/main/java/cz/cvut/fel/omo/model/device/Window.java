package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>This class describes Window in house</p>
 */
public class Window implements Observer {
    private static final Logger LOGGER = LogManager.getLogger(TemperatureSensor.class.getName());

    private boolean isOpen;



    private Blinds blinds;

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
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
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
            case Day -> {
                this.blinds.openBlinds();
            }
            case Night -> {
                this.blinds.closeBlinds();
            }
        }
    }


    public Blinds getBlinds() {
        return blinds;
    }

    public void setBlinds(Blinds blinds) {
        this.blinds = blinds;
    }
}
