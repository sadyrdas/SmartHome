package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.api.model.FridgeAPI;
import cz.cvut.fel.omo.model.device.*;import cz.cvut.fel.omo.model.room.Room;

import java.util.logging.Logger;

public class DeviceFactory {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    public Device createDevice(int id, String deviceName, int baseEnergyConsumption, Room room) {
        if (deviceName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warning("Device name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (deviceName) {
            case "Fridge" -> new Fridge(id,  deviceName,room, baseEnergyConsumption);
            case "TV" -> new TV(id, deviceName,room, baseEnergyConsumption);
            case "CoffeeMachine" -> new CoffeeMachine(id, deviceName, room, baseEnergyConsumption );
            case "AirConditioner" -> new AirConditioner(id, deviceName, room, baseEnergyConsumption);
            default -> throw new IllegalArgumentException("Unknown device: " + deviceName);
        };
    }
}
