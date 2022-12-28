package cz.cvut.fel.omo.patterns.observer;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

public interface Observer {
    void update(EventsType events_type, SimulationFacade simulationFacade);
}
