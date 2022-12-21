package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;

import java.util.HashSet;
import java.util.Set;

public class SmokeSensor extends Sensor {
    private final Set<Observer> observers = new HashSet<>();

    public SmokeSensor(int id, String sensorName, int baseEnergyConsumption, Room room) {
        super(id, sensorName, baseEnergyConsumption, room);
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers() {

    }
}
