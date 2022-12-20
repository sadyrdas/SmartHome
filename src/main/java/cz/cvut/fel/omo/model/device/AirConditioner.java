package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.events.Events_Type;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class AirConditioner extends Device implements Observer {

    public AirConditioner(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    public AirConditioner(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    @Override
    public void update(Events_Type events_type) {
        switch (events_type){
            case Hot_temperature -> this.setState(new ActiveState(this));
            case Cold_temperature -> this.setState(new StoppedState(this));
        }
    }

}