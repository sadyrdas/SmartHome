package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class CoffeeMachine extends Device {

    private int mlOfMilk = 1000;
    private int mlOfWater = 2000;
    private int amountOfBeans = 500;

    public CoffeeMachine(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    public CoffeeMachine(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Smoky, Turn_off_device -> this.setState(new StoppedState(this));
            case Turn_on_device -> this.setState(new ActiveState(this));
        }
    }

    public int getMlOfMilk() {
        return mlOfMilk;
    }

    public void setMlOfMilk(int mlOfMilk) {
        this.mlOfMilk = mlOfMilk;
    }

    public int getMlOfWater() {
        return mlOfWater;
    }

    public void setMlOfWater(int mlOfWater) {
        this.mlOfWater = mlOfWater;
    }

    public int getAmountOfBeans() {
        return amountOfBeans;
    }

    public void setAmountOfBeans(int amountOfBeans) {
        this.amountOfBeans = amountOfBeans;
    }
}
