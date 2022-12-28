package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class PC extends Device {

    public PC(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
    }

    public PC(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
    }
}
