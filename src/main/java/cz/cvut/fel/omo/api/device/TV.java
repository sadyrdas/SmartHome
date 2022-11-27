package cz.cvut.fel.omo.api.device;

import cz.cvut.fel.omo.api.room.Room;

public class TV extends Device{

    public TV(String name, Room room, int baseEnergyConsumption) {
        super(name, room, baseEnergyConsumption);
    }

    public TV(String name, int baseEnergyConsumption) {
        super(name, baseEnergyConsumption);
    }
}
