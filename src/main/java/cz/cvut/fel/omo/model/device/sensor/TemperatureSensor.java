package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.simulation.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class TemperatureSensor extends Sensor {
    private static final Logger LOGGER = LogManager.getLogger(TemperatureSensor.class.getName());

    private final Set<Observer> observers = new HashSet<>();
    private int baseRoomTemperature = 21;


    public TemperatureSensor(int id ,String sensorName, int baseEnergyConsumption, Room room) {
        super(id, sensorName, baseEnergyConsumption, room, EnergyType.Electricity);
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void notifySubscribers(EventsType eventsType) {
        LOGGER.info("Notifying all subscribers");
        LOGGER.info(observers.size());
        for(Observer observer : observers) {
            observer.update(eventsType);
//            LOGGER.debug();
        }

    }

    public int getBaseRoomTemperature() {
        return baseRoomTemperature;
    }

    public void setBaseRoomTemperature(int baseRoomTemperature) {
        this.baseRoomTemperature = baseRoomTemperature;
    }
}
