package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.api.device.Device;
import cz.cvut.fel.omo.api.device.Fridge;
import cz.cvut.fel.omo.api.device.TV;
import cz.cvut.fel.omo.api.room.Room;

import java.util.logging.Logger;

public class DeviceFactory {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    public Device createDevice(String deviceName, int baseEnergyConsumption) {
        if (deviceName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warning("Device name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (deviceName) {
            case "Fridge" -> new Fridge(deviceName, baseEnergyConsumption);
            case "TV" -> new TV(deviceName, baseEnergyConsumption);
            default -> throw new IllegalArgumentException("Unknown device: " + deviceName);
        };
    }
}