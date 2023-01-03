package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;

import java.util.logging.Logger;

/**
 * <p>This class describes Blind on the Window.</p>
 */
public class Blinds {

    private final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());
    private final Window window;
    private Boolean isOpen = true;

    public Blinds(Window window) {
        this.window = window;
    }

    public void closeBlinds() {
        this.isOpen = false;
    }

    public void openBlinds() {
        this.isOpen = true;
    }

    @Override
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
        switch (events_type) {
            case Day -> closeBlinds();
            case Night -> openBlinds();
        }
    }
}