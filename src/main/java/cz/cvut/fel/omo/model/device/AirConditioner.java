package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class AirConditioner extends Device implements Observer {

    public AirConditioner(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
    }

    public AirConditioner(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Hot_temperature, Turn_on_device-> this.setState(new ActiveState(this));
            case Cold_temperature, Smoky, Turn_off_device  -> this.setState(new StoppedState(this));
        }
    }

}
