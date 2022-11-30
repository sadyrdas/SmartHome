package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.api.device.Device;

import java.util.logging.Logger;

public class StoppedSensorState implements SensorState {
    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

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
