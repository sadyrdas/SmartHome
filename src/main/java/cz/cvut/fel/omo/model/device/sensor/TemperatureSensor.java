package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import java.util.HashSet;
import java.util.Set;

public class TemperatureSensor extends Sensor {
    private final Set<Observer> observers = new HashSet<>();
    private int baseRoomTemperature = 21;


    public TemperatureSensor(int id ,String sensorName, int baseEnergyConsumption, Room room) {
        super(id, sensorName, baseEnergyConsumption, room);
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void notifySubscribers() {

    }

    public int getBaseRoomTemperature() {
        return baseRoomTemperature;
    }

    public void setBaseRoomTemperature(int baseRoomTemperature) {
        this.baseRoomTemperature = baseRoomTemperature;
    }
}
