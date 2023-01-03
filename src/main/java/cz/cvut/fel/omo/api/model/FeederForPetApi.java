package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.FeederForPet;
import cz.cvut.fel.omo.model.events.EventsType;

import cz.cvut.fel.omo.model.user.ActivityPet;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>Provides API for feeder for pet which includes access for feeder for pet in house.</p>
 */
public class FeederForPetApi {
    private static final Logger LOG = LogManager.getLogger(FeederForPetApi.class.getName());
    private final FeederForPet feederForPet;
    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     * @param feederForPet - includes our feeder for pet in the house
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public FeederForPetApi(FeederForPet feederForPet, SimulationFacade simulationFacade){
        this.feederForPet = feederForPet;
        this.simulationFacade = simulationFacade;
    }

    public int getFood(){
        return feederForPet.getCountOfFood();
    }

    public void setFood(int food){
        feederForPet.setCountOfFood(food);
    }


    /**
     * Makes a dinner for three our pets.
     * @param pet - this action is happening for pets.
     */
    public void timeForDinner(Pet pet){
        if (getFood() - 75 <= 0){
            LOG.warn("Food in feeder is below zero");
            feederForPet.notifySubscribers(EventsType.Empty_FeederForFood, simulationFacade);
            simulationFacade.addHumanEventToEventsHub(simulationFacade.getHouse()
                    .getHumanByPermission(ResidentPermission.ADULT), ActivityUser.FILL_FEEDER);
            feederForPet.setState(new StoppedState(feederForPet));
        }else {
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 75);
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 50);
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 20);
            LOG.info("Time for dinner,  " + feederForPet.getCountOfFood());
            simulationFacade.addActivityPetEventToEventsHub(pet, ActivityPet.EAT);
            simulationFacade.addDeviceEventsTypeToEventsHub(feederForPet, EventsType.Turn_on_device);
            feederForPet.setState(new ActiveState(feederForPet));
            pet.countDeviceUsage(feederForPet);
        }
    }

    /**
     * Fill the feeder.
     */

    public void fillFeeder(){
        feederForPet.setCountOfFood(435);
    }

}
