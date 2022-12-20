package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.model.device.*;

import java.util.logging.Logger;

public class DeviceFactory {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    public Device createDevice(int id, String deviceName, int baseEnergyConsumption) {
        if (deviceName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warning("Device name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (deviceName) {
            case "Fridge" -> new Fridge(id,  deviceName, baseEnergyConsumption);
            case "TV" -> new TV(id, deviceName, baseEnergyConsumption);
            case "CoffeeMachine" -> new CoffeeMachine(id, deviceName, baseEnergyConsumption);
            case "AirConditioner" -> new AirConditioner(id, deviceName, baseEnergyConsumption);
            default -> throw new IllegalArgumentException("Unknown device: " + deviceName);
        };
    }
}
