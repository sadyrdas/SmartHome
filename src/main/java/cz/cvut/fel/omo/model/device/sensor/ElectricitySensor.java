package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.patterns.observer.Observer;

public class ElectricitySensor extends Sensor{
    public ElectricitySensor(int id, String sensorName, int baseEnergyConsumption) {
        super(id, sensorName, baseEnergyConsumption);
    }

    @Override
    public void addSubscriber(Observer observer) {

    }

    @Override
    public void notifySubscribers() {

    }
}
