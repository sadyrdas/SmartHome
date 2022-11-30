package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.room.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Fridge extends Device {
    private static final Logger LOG = Logger.getLogger(Fridge.class.getSimpleName());
    Map<String, Integer> foodInFridge = new HashMap<>();


    public Fridge(int id , String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
    }

    public Fridge(int id ,String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    public void addFoodToFridge(String food, int amount) {
        foodInFridge.put(food, amount);
        LOG.info(food + " was inserted into the fridge with amout: " + amount);
    }
}
