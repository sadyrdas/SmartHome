package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class CoffeeMachine extends Device {

    private int beans;
    private int milk;
    private int water;
    private int coupleOfCoffee;

    public CoffeeMachine(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    public CoffeeMachine(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Smoky -> this.setState(new StoppedState(this));
            case Turn_on_device -> this.setState(new ActiveState(this));
            case Turn_off_device -> this.setState(new StoppedState(this));
        }
    }

}
