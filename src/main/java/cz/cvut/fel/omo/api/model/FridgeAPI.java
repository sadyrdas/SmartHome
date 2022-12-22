package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Fridge;

import java.util.Map;
import java.util.logging.Logger;

public class FridgeAPI {
    private static final Logger LOG = Logger.getLogger(FridgeAPI.class.getSimpleName());

    private final Fridge fridge;


    public FridgeAPI(Fridge fridge) {
        this.fridge = fridge;
    }

    public Map<String, Integer> getAllFood(){
        return fridge.getFoodInFridge();
    }

    public void addFoodToFridge(String food, int amount) {
        fridge.getFoodInFridge().put(food, amount);
        LOG.info(food + " was inserted into the fridge with amout: " + amount);
    }





    public void takeFoodFromFridge(String food) {
        if (fridge.getFoodInFridge().get(food) < 0) {
            LOG.warning("Trying to get more food than exist in Fridge!");
            // update for human shtobi vlozil jedu
        }
        fridge.getFoodInFridge().put(food, fridge.getFoodInFridge().get(food) - 1);

    }
}
