package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.api.model.FridgeAPI;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Fridge extends Device {
    private static final Logger LOG = Logger.getLogger(Fridge.class.getSimpleName());
    private  FridgeAPI fridgeAPI;
    Map<String, Integer> foodInFridge = new HashMap<>();


    public Fridge(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption);
        addFoodToFridge();
    }

    public Fridge(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption);
    }

    public Map<String, Integer> getFoodInFridge() {
        return foodInFridge;
    }

    public void setFoodInFridge(Map<String, Integer> foodInFridge) {
        this.foodInFridge = foodInFridge;
    }


    public void addFoodToFridge(){
        foodInFridge.put("Meat", 3);
        foodInFridge.put("Bread", 1);
        foodInFridge.put("Milk", 1);
        foodInFridge.put("Fish",2);
        foodInFridge.put("Cheese", 3);
        foodInFridge.put("Beer", 3);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Empty_fridge -> addFoodToFridge();

        }
    }

}
