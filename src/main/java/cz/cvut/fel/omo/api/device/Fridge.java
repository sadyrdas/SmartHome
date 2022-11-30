package cz.cvut.fel.omo.api.device;

import cz.cvut.fel.omo.api.device.energy.Energy;
import cz.cvut.fel.omo.api.room.Room;
import cz.cvut.fel.omo.patterns.state.State;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Fridge extends Device {
    private static final Logger LOG = Logger.getLogger(Fridge.class.getSimpleName());
    Map<String, Integer> foodInFridge = new HashMap<>();


    public Fridge(String name, Room room, int baseEnergyConsumption) {
        super(name, room, baseEnergyConsumption);
    }

    public Fridge(String name, int baseEnergyConsumption) {
        super(name, baseEnergyConsumption);
    }

    public void addFoodToFridge(String food, int amount) {
        foodInFridge.put(food, amount);
        LOG.info(food + " was inserted into the fridge with amout: " + amount);
    }
}
