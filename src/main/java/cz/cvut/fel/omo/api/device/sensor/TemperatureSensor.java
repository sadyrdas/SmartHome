package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.patterns.state.State;

public class TemperatureSensor extends Sensor{


    public TemperatureSensor(int id ,String sensorName, int baseEnergyConsumption, State state) {
        super(id, sensorName, baseEnergyConsumption, state);
    }
}
