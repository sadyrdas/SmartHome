package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class SmokeSensor extends Sensor {

    private static final Logger LOGGER = LogManager.getLogger(SmokeSensor.class.getName());

    private final Set<Observer> observers = new HashSet<>();

    public SmokeSensor(int id, String sensorName, int baseEnergyConsumption, Room room) {
        super(id, sensorName, baseEnergyConsumption, room, EnergyType.Electricity);
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType, SimulationFacade simulationFacade) {
        LOGGER.info("notify!");
        for(Observer observer : observers) {
            observer.update(eventsType, simulationFacade);
        }
    }
}
