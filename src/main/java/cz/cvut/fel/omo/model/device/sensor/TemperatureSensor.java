package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.patterns.observer.Observer;
import java.util.HashSet;
import java.util.Set;

public class TemperatureSensor extends Sensor {
    private final Set<Observer> observers = new HashSet<>();


    public TemperatureSensor(int id ,String sensorName, int baseEnergyConsumption) {
        super(id, sensorName, baseEnergyConsumption);
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void notifySubscribers() {

    }
}
