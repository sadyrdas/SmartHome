package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.sensor.ElectricitySensor;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.device.sensor.SmokeSensor;
import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.room.Room;

import java.util.logging.Logger;

public class SensorFactory {
    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    public Sensor createSensor(int id, String sensorName, int baseEnergyConsumption, Room room) {
        if (sensorName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warning("Sensor name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (sensorName) {
            case "TemperatureSensor" -> new TemperatureSensor(id, sensorName, baseEnergyConsumption,room);
            case "ElectricitySensor" -> new ElectricitySensor(id, sensorName, baseEnergyConsumption,room);
            case "SmokeSensor" -> new SmokeSensor(id, sensorName, baseEnergyConsumption,room);
            default -> throw new IllegalArgumentException("Unknown device: " + sensorName);
        };
    }
}
