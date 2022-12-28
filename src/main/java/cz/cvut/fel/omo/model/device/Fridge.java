package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.api.model.FridgeAPI;
import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Fridge extends Device implements Subject {

    private static final Logger LOG = LogManager.getLogger(Fridge.class.getSimpleName());
    private  FridgeAPI fridgeAPI;
    Map<String, Integer> foodInFridge = new HashMap<>();
    private final Set<Observer> observers = new HashSet<>();

    public Fridge(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
        addFoodToFridge();
    }

    public Fridge(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
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
            case Smoky -> setState(new StoppedState(this));
        }
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType) {
        for (Observer observer : observers) {
            observer.update(eventsType);
        }
    }
}
