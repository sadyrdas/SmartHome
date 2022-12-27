package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.room.Room;

public class Lamp extends Device {
    public Lamp(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    public Lamp(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }




}
