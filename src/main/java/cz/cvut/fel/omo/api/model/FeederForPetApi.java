package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.FeederForPet;
import cz.cvut.fel.omo.model.events.EventsType;

import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeederForPetApi {
    private static final Logger LOG = LogManager.getLogger(FeederForPetApi.class.getName());
    private final FeederForPet feederForPet;
    private final SimulationFacade simulationFacade;

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

    public void timeForDinner(){
        if (getFood() - 75 <= 0){
            LOG.warn("Food in feeder is below zero");
            feederForPet.notifySubscribers(EventsType.Empty_FeederForFood, simulationFacade);
        }else {
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 75);
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 50);
            feederForPet.setCountOfFood(feederForPet.getCountOfFood() - 20);
            LOG.info("Time for dinner,  " + feederForPet.getCountOfFood());
        }
    }

    public void fillFeeder(){
        feederForPet.setCountOfFood(435);
    }

}
