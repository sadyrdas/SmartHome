package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.model.device.*;import cz.cvut.fel.omo.model.room.Room;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceFactory {

    private static final Logger LOG = LogManager.getLogger(Device.class.getSimpleName());

    public Device createDevice(int id, String deviceName, int baseEnergyConsumption, Room room) {
        if (deviceName.isEmpty() || baseEnergyConsumption < 0 ) {
            LOG.warn("Device name is empty or consumptionEnergy is bellow zero!");
            return null;
        }

        return switch (deviceName) {
            case "Fridge" -> new Fridge(id,  deviceName,room, baseEnergyConsumption);
            case "TV" -> new TV(id, deviceName,room, baseEnergyConsumption);
            case "CoffeeMachine" -> new CoffeeMachine(id, deviceName, room, baseEnergyConsumption );
            case "AirConditioner" -> new AirConditioner(id, deviceName, room, baseEnergyConsumption);
            case "MusicCenter" -> new MusicCenter(id, deviceName, room, baseEnergyConsumption);
            case "PC" -> new PC(id, deviceName, room, baseEnergyConsumption);
            case "FeederForPet" -> new FeederForPet(id, deviceName, room, baseEnergyConsumption);
            case "Lamp" -> new Lamp(id, deviceName, room, baseEnergyConsumption);
            case "Shower" -> new Shower(id, deviceName, room, baseEnergyConsumption);
            default -> throw new IllegalArgumentException("Unknown device: " + deviceName);
        };
    }
}
