package cz.cvut.fel.omo.api.device;

import cz.cvut.fel.omo.api.room.Room;

public class TV extends Device{

    public TV(int id ,String name, Room room, int baseEnergyConsumption) {
        super(id,name, room, baseEnergyConsumption);
    }

    public TV(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }
}
