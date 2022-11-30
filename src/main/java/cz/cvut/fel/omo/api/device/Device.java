package cz.cvut.fel.omo.api.device;

import cz.cvut.fel.omo.api.device.energy.Energy;
import cz.cvut.fel.omo.api.room.Room;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.State;

import java.util.logging.Logger;

public abstract class Device {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    private int id;
    private String name;
    private Room room;
    private State state;
    private Energy energy;
    private int baseEnergyConsumption;

    public Device(int id, String name, Room room, int baseEnergyConsumption) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.baseEnergyConsumption = baseEnergyConsumption;
        setEnergy(new Energy(baseEnergyConsumption));
        setState(new IdleState(this));
    }

    public Device(int id ,String name, int baseEnergyConsumption) {
        this.id = id;
        this.name = name;
        this.baseEnergyConsumption = baseEnergyConsumption;
        setEnergy(new Energy(baseEnergyConsumption));
        setState(new IdleState(this));
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
