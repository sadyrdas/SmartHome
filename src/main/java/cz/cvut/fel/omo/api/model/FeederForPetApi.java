package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.FeederForPet;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeederForPetApi implements Subject {
    private static final Logger LOG = LogManager.getLogger(FeederForPetApi.class.getName());
    private final FeederForPet feederForPet;
    private Set<Observer> observers;

    public FeederForPetApi(FeederForPet feederForPet){
        this.feederForPet = feederForPet;
    }

    public int getFood(){
        return feederForPet.getCountOfFood();
    }

    public void setFood(int food){
        feederForPet.setCountOfFood(food);
    }

    public void timeForDinner(){
        if (getFood() - 75 < 0){
//           notifySubscribers(EventsType.Empty_FeederForFood);
            LOG.warn("Food in feeder is below zero");
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


    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType) {
        for (Observer observer : observers){
            observer.update(eventsType);
        }
    }
}
