package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.patterns.state.State;

public class ElectricitySensor extends Sensor{
    public ElectricitySensor(int id, String sensorName, int baseEnergyConsumption) {
        super(id, sensorName, baseEnergyConsumption);
    }
}
