package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Fridge;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * <p>Provides API for fridge which includes access for fridge in house.</p>
 */

public class FridgeAPI {

    private static final Logger LOG = LogManager.getLogger(FridgeAPI.class.getName());

    private final Fridge fridge;
    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     * @param fridge - it is the one fridge in our house
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public FridgeAPI(Fridge fridge, SimulationFacade simulationFacade) {
        this.fridge = fridge;
        this.simulationFacade = simulationFacade;
    }

    /**
     * Get all food in fridge as Map
     * @return Map of foods in fridge: String as name of food and Integer as count of food
     */

    public Map<String, Integer> getAllFood(){
        return fridge.getFoodInFridge();
    }

    /**
     * Using of fridge
     * @param human - is the one user, who takes the food from fridge.
     * @param food - is the food, which is taken by one human
     */
    public void takeFoodFromFridge(Human human, String food) {
        if (fridge.getFoodInFridge().get(food) < 0) {
            LOG.warn("Trying to get more food than exist in Fridge!");
            fridge.notifySubscribers(EventsType.Empty_fridge, simulationFacade);
            simulationFacade.addDeviceEventsTypeToEventsHub(fridge, EventsType.Empty_fridge);
        }
        fridge.getFoodInFridge().put(food, fridge.getFoodInFridge().get(food) - 1);
        LOG.info("1 " + food + " was taken from fridge. " + food + " left: " + fridge.getFoodInFridge().get(food));
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.EAT);
        human.countDeviceUsage(fridge);
    }
}
