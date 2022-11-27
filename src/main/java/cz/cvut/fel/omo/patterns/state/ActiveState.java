package cz.cvut.fel.omo.patterns.state;

import cz.cvut.fel.omo.api.device.Device;

import java.util.logging.Logger;

public class ActiveState implements State {
    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    private final Device device;

    public ActiveState(Device device) {
        this.device = device;
    }

    @Override
    public void setPower() {
        device.getEnergy().setPower(device.getBaseEnergyConsumption());
        LOG.info("Device's state was changed to Active. Consumption Energy is: " + device.getEnergy().getPower());
    }

    @Override
    public String toString() {
        return "ACTIVE";
    }
}
