package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Fridge;

import java.util.logging.Logger;

public class FridgeAPI {
    private static final Logger LOG = Logger.getLogger(FridgeAPI.class.getSimpleName());
    private final Fridge fridge;

    public FridgeAPI(Fridge fridge) {
        this.fridge = fridge;
    }

    public void addFoodToFridge(String food, int amount) {
        fridge.getFoodInFridge().put(food, amount);
        LOG.info(food + " was inserted into the fridge with amout: " + amount);
    }

    public void takeFooFromFridge(String food, int amount) {
        if (fridge.getFoodInFridge().get(food) < amount) {
            LOG.warning("Trying to get more food than exist in Fridge! Actual amount: "
                    + fridge.getFoodInFridge().get(food) + ". asked: " + amount);
        }
        fridge.getFoodInFridge().put(food, fridge.getFoodInFridge().get(food) - amount);
        LOG.info("Took food from Fridge! Food: " + food + ". Amount: " + amount);
    }
}
