package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.Energy;
import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.State;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Device implements Observer {


    private static final Logger LOG = LogManager.getLogger(Device.class.getSimpleName());

    private int id;
    private String name;
    private Room room;
    private State state;
    private Energy energy;
    private int baseEnergyConsumption;

    public Device(int id, String name, Room room, int baseEnergyConsumption, EnergyType energyType) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.baseEnergyConsumption = baseEnergyConsumption;
        setEnergy(new Energy(baseEnergyConsumption, energyType));
        setState(new IdleState(this));
    }

    public Device(int id ,String name, int baseEnergyConsumption, EnergyType energyType) {
        this.id = id;
        this.name = name;
        this.baseEnergyConsumption = baseEnergyConsumption;
        setEnergy(new Energy(baseEnergyConsumption, energyType));
        setState(new IdleState(this));
    }

    @Override
    public void update(EventsType eventsType){
        switch (eventsType) {
            case Smoky, Turn_off_device -> {
                setState(new StoppedState(this));
            }
            case Turn_on_device -> {
                setState(new ActiveState(this));
                LOG.info("Device was turned on");}
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public int getBaseEnergyConsumption() {
        return baseEnergyConsumption;
    }

    public void setBaseEnergyConsumption(int baseEnergyConsumption) {
        this.baseEnergyConsumption = baseEnergyConsumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
