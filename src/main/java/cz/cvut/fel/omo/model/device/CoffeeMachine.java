package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>This class describes model CoffeeMachine.</p>
 */
public class CoffeeMachine extends Device implements Subject {

    private int mlOfMilk = 1000;
    private int mlOfWater = 2000;
    private int amountOfBeans = 500;

    private final Set<Observer> observers = new HashSet<>();

    public CoffeeMachine(int id, String name, Room room, int baseEnergyConsumption) {
        super(id, name, room, baseEnergyConsumption, EnergyType.Electricity);
    }


    @Override
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
        switch (events_type) {
            case Smoky, Turn_off_device -> this.setState(new StoppedState(this));
            case Turn_on_device -> this.setState(new ActiveState(this));
        }
    }

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

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType, SimulationFacade simulationFacade) {
        for (Observer observer : observers) {
            observer.update(eventsType, simulationFacade);
        }
    }
}
