package cz.cvut.fel.omo.patterns.observer;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

public interface Subject {
    void addSubscriber(Observer observer);

    void notifySubscribers(EventsType eventsType, SimulationFacade simulationFacade);
}
