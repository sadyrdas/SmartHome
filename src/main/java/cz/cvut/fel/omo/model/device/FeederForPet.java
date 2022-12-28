package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.HashSet;
import java.util.Set;

public class FeederForPet extends Device implements Subject {
    private int countOfFood = 450;

    private final Set<Observer> observers = new HashSet<>();

    public FeederForPet(int id, String name, int baseEnergyConsumption) {
        super(id, name, baseEnergyConsumption, EnergyType.Electricity);
    }

    public FeederForPet(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
    }

    @Override
    public void update(EventsType events_type) {
        switch (events_type){
            case Smoky, Turn_off_device -> this.setState(new StoppedState(this));
            case Turn_on_device -> this.setState(new ActiveState(this));
            case Empty_FeederForFood -> this.setState(new IdleState(this));
        }
    }


    public int getCountOfFood() {
        return countOfFood;
    }

    public void setCountOfFood(int countOfFood) {
        this.countOfFood = countOfFood;
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType) {
        for (Observer observer : observers) observer.notify();
    }
}
