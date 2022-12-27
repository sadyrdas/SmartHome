package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class FeederForPet extends Device {
    private int countOfFood = 450;


    public FeederForPet(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    public FeederForPet(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Smoky, Turn_off_device -> this.setState(new StoppedState(this));
            case Turn_on_device -> this.setState(new ActiveState(this));
            case Empty_FeederForFood -> this.setState(new IdleState(this));
        }
    }


    public int getCountOfFood() {
        return countOfFood;
    }

    public void setCountOfFood(int countOfFood) {
        this.countOfFood = countOfFood;
    }
}
