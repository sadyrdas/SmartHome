package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.room.Room;

public class Shower extends Device{
    public Shower(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Water);
    }

    public Shower(int id, String name, int baseEnergyConsumption, EnergyType energyType) {
        super(id, name, baseEnergyConsumption, EnergyType.Water);
    }
}
