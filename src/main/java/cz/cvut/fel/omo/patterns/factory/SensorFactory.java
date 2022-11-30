package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.api.device.Device;
import cz.cvut.fel.omo.api.device.sensor.ElectricitySensor;
import cz.cvut.fel.omo.api.device.sensor.Sensor;
import cz.cvut.fel.omo.api.device.sensor.SmokeSensor;
import cz.cvut.fel.omo.api.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.patterns.state.State;

import java.util.logging.Logger;

public class SensorFactory {
    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    public Sensor createSensor(int id, String sensorName, int baseEnergyConsumption, State state) {
        if (sensorName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warning("Sensor name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (sensorName) {
            case "Temperature" -> new TemperatureSensor(id, sensorName, baseEnergyConsumption,state);
            case "Electricity" -> new ElectricitySensor(id, sensorName, baseEnergyConsumption, state);
            case "Smoke" -> new SmokeSensor(id, sensorName, baseEnergyConsumption, state);
            default -> throw new IllegalArgumentException("Unknown device: " + sensorName);
        };
    }
}
