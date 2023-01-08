package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.device.energy.Energy;
import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.State;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * <p>This class describes (model) the abstract class of devices : Device and this class implements Design Pattern Observer.</p>
 */
public abstract class Device implements Observer {


    private static final Logger LOG = LogManager.getLogger(Device.class.getSimpleName());

    private int id;
    private String name;
    private Room room;
    private State state;
    private Energy energy;
    private int baseEnergyConsumption;

    /**
     * Main constructor
     *
     * @param id                    - unique specific of device
     * @param name                  - name of device
     * @param room                  - room, where is device
     * @param baseEnergyConsumption - base energy consumption during day
     * @param energyType            - type of energy(Electricity, except Shower(Water)
     */
    public Device(int id, String name, Room room, int baseEnergyConsumption, EnergyType energyType) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.baseEnergyConsumption = baseEnergyConsumption;
        setEnergy(new Energy(baseEnergyConsumption, energyType));
        setState(new IdleState(this));
    }

    /**
     * Part of Design Patter Observer
     *
     * @param eventsType       - type of event, which effect on the states of device
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    @Override
    public void update(EventsType eventsType, SimulationFacade simulationFacade) {
        switch (eventsType) {
            case Smoky, Turn_off_device -> {
                setState(new StoppedState(this));
            }
            case Turn_on_device -> {
                setState(new ActiveState(this));
                LOG.info("Device was turned on");
            }
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
        Objects.requireNonNull(state);
        this.state = state;

        if (state instanceof ActiveState) {
            this.energy.setPower(baseEnergyConsumption);
        } else if (state instanceof IdleState) {
            if (!(this instanceof Fridge)) {
                this.energy.setPower((int) (baseEnergyConsumption * 0.10));
            }
        } else {
            this.energy.setPower(0);
        }
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
