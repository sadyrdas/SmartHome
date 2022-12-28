package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.room.Room;

public class MusicCenter extends Device{
    public MusicCenter(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
    }

    public MusicCenter(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
    }
}
