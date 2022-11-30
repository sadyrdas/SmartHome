package cz.cvut.fel.omo.patterns.state;

import cz.cvut.fel.omo.model.device.Device;

import java.util.logging.Logger;

public class StoppedState implements State {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    private final Device device;

    public StoppedState(Device device) {
        this.device = device;
    }

    @Override
    public void setPower() {
        device.getEnergy().setPower(0);
        LOG.info("Device was turned off. Device's current consumption is: " + device.getEnergy().getPower());
    }

    @Override
    public String toString() {
        return "STOPPED";
    }
}
