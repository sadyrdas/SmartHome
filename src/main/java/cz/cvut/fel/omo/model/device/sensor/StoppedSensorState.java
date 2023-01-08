package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.device.Device;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>Class implements StoppedState of sensors.</p>
 */
public class StoppedSensorState implements SensorState {
    private static final Logger LOG = LogManager.getLogger(Device.class.getSimpleName());

    private final Sensor sensor;

    public StoppedSensorState(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void setPower() {
        sensor.getEnergy().setPower(sensor.getBaseEnergyConsumption());
        LOG.info("Device's state was changed to Active. Consumption Energy is: " + sensor.getEnergy().getPower());
    }

    @Override
    public String toString() {
        return "STOPPED";
    }
}
