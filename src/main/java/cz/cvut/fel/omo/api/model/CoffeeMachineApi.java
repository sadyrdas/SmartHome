package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.CoffeeMachine;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoffeeMachineApi implements Subject {
    private static final Logger LOG = LogManager.getLogger(CoffeeMachineApi.class.getName());
    private final CoffeeMachine coffeeMachine;
    private Set<Observer> observers;

    public int getMlOfMilk() {
        return coffeeMachine.getMlOfMilk();
    }

    public void setMlOfMilk(int mlOfMilk) {
        coffeeMachine.setMlOfMilk(mlOfMilk);
    }

    public int getMlOfWater() {
        return coffeeMachine.getMlOfWater();
    }

    public void setMlOfWater(int mlOfWater) {
        coffeeMachine.setMlOfWater(mlOfWater);
    }

    public int getAmountOfBeans() {
        return coffeeMachine.getAmountOfBeans();
    }

    public void setAmountOfBeans(int amountOfBeans) {
        coffeeMachine.setAmountOfBeans(amountOfBeans);
    }

    public CoffeeMachineApi(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public void makeCoffee () {
        if (coffeeMachine.getMlOfMilk() - 100 < 0 ||
                coffeeMachine.getMlOfWater() - 150 < 0  ||
                coffeeMachine.getAmountOfBeans() - 1 < 0) {
            notifySubscribers(EventsType.Empty_CoffeeMachine);
            LOG.warn("Ingredients for coffee is below zero " + "Ask mother to full");
        } else {
            coffeeMachine.setMlOfMilk(coffeeMachine.getMlOfMilk() - 100);
            coffeeMachine.setMlOfWater(coffeeMachine.getMlOfWater() - 100);
            coffeeMachine.setAmountOfBeans(coffeeMachine.getAmountOfBeans() - 1);
            LOG.info("Your coffee is ready! Remaining amount of milk: " + coffeeMachine.getMlOfMilk() +
                    " ml., water: " + coffeeMachine.getMlOfWater() + " ml., " +
                    coffeeMachine.getAmountOfBeans() + " beans");
        }
    }

    public void fillCoffeeMachine() {
        coffeeMachine.setAmountOfBeans(100);
        coffeeMachine.setMlOfMilk(100);
        coffeeMachine.setMlOfWater(100);
        LOG.info("Coffee machine was filled. Current amount of milk: " + coffeeMachine.getMlOfMilk() +
                " ml., " + coffeeMachine.getMlOfWater() + " ml., beans: " + coffeeMachine.getAmountOfBeans() + ".");
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
