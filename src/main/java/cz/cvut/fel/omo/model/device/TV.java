package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.room.Room;

public class TV extends Device{

    public TV(int id ,String name, Room room, int baseEnergyConsumption) {
        super(id,name, room, baseEnergyConsumption, EnergyType.Electricity);
    }

    public TV(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
    }
}
