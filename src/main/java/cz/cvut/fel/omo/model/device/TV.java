package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.HashSet;
import java.util.Set;

public class TV extends Device implements Subject {

    private final Set<Observer> observers = new HashSet<>();

    public TV(int id ,String name, Room room, int baseEnergyConsumption) {
        super(id,name, room, baseEnergyConsumption, EnergyType.Electricity);
    }


    @Override
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
        switch (events_type) {
            case Smoky, Repair_device -> setState(new StoppedState(this));
        }
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType, SimulationFacade simulationFacade) {
        for (Observer observer : observers) {
            observer.update(eventsType, simulationFacade);
        }
    }
}
