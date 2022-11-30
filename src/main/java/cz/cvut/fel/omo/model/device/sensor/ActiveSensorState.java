package cz.cvut.fel.omo.model.device.sensor;

import java.util.logging.Logger;

public class ActiveSensorState implements SensorState {
    private static final Logger LOG = Logger.getLogger(Sensor.class.getSimpleName());

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
