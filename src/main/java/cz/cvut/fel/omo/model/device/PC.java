package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class PC extends Device implements Subject {
    private static final Logger LOG = LogManager.getLogger(Fridge.class.getSimpleName());

    private final Set<Observer> observers = new HashSet<>();

    public PC(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
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

    @Override
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
        switch (events_type){
            case Repair_device -> {
                setState(new StoppedState(this));
                notifySubscribers(EventsType.Repair_device,simulationFacade );
                LOG.info("PC is under repairment!");
            }
            case Smoky -> setState(new StoppedState(this));
        }
    }
}
