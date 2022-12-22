package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.CoffeeMachine;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CoffeeMachineApi implements Subject {
    private static final Logger LOG = Logger.getLogger(CoffeeMachineApi.class.getName());
    private final CoffeeMachine coffeeMachine;
    private Set<Observer> observers;

    public int getMlOfMilk() {
        return mlOfMilk;
    }

    public void setMlOfMilk(int mlOfMilk) {
        this.mlOfMilk = mlOfMilk;
    }

    public int getMlOfWater() {
        return mlOfWater;
    }

    public void setMlOfWater(int mlOfWater) {
        this.mlOfWater = mlOfWater;
    }

    public int getAmountOfBeans() {
        return amountOfBeans;
    }

    public void setAmountOfBeans(int amountOfBeans) {
        this.amountOfBeans = amountOfBeans;
    }

    private int mlOfMilk = 1000;
    private int mlOfWater = 2000;
    private int amountOfBeans = 500;



    public CoffeeMachineApi(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public void makeCoffee () {
        if (mlOfMilk - 100 < 0 || mlOfWater - 150 < 0  || amountOfBeans - 1 < 0){
            notifySubscribers(EventsType.Empty_CoffeeMachine);
            LOG.warning("Ingredients for coffee is below zero " + "Ask mother to full");
        }else {
            mlOfMilk -= 100;
            mlOfWater -= 100;
            amountOfBeans -=1;
            LOG.info("Your coffee is ready! " + "MlOfMilk " + mlOfMilk + "MlOfWater " + mlOfWater + "amountBeans " + amountOfBeans);
        }
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
