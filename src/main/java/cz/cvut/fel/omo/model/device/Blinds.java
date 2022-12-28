package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;

import java.util.logging.Logger;

public class Blinds implements Observer {

    private final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());
    private final Room room;
    private final Window window;
    private Boolean isOpen = true;

    public Blinds(Room room, Window window) {
        this.room = room;
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