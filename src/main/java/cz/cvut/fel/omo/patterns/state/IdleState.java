package cz.cvut.fel.omo.patterns.state;

import cz.cvut.fel.omo.model.device.Device;

public class IdleState implements State {

    private final Device device;

    public IdleState(Device device) {
        this.device = device;
    }

    @Override
    public void setPower() {
        device.getEnergy().setPower((int) (device.getEnergy().getPower() * 0.10));
        LOG.info("Device's state was changed to Idle. Consumption Energy is: " + device.getEnergy().getPower());
    }

    @Override public String toString() {
        return "IDLE";
    }
}
