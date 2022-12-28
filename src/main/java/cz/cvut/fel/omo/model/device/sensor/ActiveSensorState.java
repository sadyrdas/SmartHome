package cz.cvut.fel.omo.model.device.sensor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActiveSensorState implements SensorState {
    private static final Logger LOG = LogManager.getLogger(Sensor.class.getSimpleName());

    private final Sensor sensor;

    public ActiveSensorState(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void setPower() {
        sensor.getEnergy().setPower(sensor.getBaseEnergyConsumption());
        LOG.info("Sensor's state was changed to Active. Consumption Energy is: " + sensor.getEnergy().getPower());
    }

    @Override
    public String toString() {
        return "ACTIVE";
    }
}
