package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.room.Room;

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


}
