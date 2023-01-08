package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.sensor.ElectricitySensor;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.device.sensor.SmokeSensor;
import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.room.Room;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>This class implements Desing Patter Factory.</p>
 */
public class SensorFactory {
    private static final Logger LOG = LogManager.getLogger(Device.class.getSimpleName());

    /**
     * This class is creating the sensor
     *
     * @param id                    unique specific of sensor
     * @param sensorName            name of sensor
     * @param baseEnergyConsumption base energy consumption
     * @param room                  room where is sensor
     * @return object as new depends on name
     */
    public Sensor createSensor(int id, String sensorName, int baseEnergyConsumption, Room room) {
        if (sensorName.isEmpty() || baseEnergyConsumption < 0) {
            LOG.warn("Sensor name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (sensorName) {
            case "TemperatureSensor" -> new TemperatureSensor(id, sensorName, baseEnergyConsumption, room);
            case "ElectricitySensor" -> new ElectricitySensor(id, sensorName, baseEnergyConsumption, room);
            case "SmokeSensor" -> new SmokeSensor(id, sensorName, baseEnergyConsumption, room);
            default -> throw new IllegalArgumentException("Unknown device: " + sensorName);
        };
    }
}
