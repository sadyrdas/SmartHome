package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.api.device.energy.Energy;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.state.IdleState;
import cz.cvut.fel.omo.patterns.state.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Sensor {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private int id;
    private String name;
    private State state;
    private int baseEnergyConsumption;
    private Energy energy;


    public Sensor(int id, String sensorName, int baseEnergyConsumption, State state) {
        this.id = id;
        this.name = sensorName;
        this.baseEnergyConsumption = baseEnergyConsumption;
        this.state = state;
        setEnergy(new Energy(baseEnergyConsumption));
    }

    private void setEnergy(Energy energy) {
        this.energy = energy;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getBaseEnergyConsumption() {
        return baseEnergyConsumption;
    }

    public void setBaseEnergyConsumption(int baseEnergyConsumption) {
        this.baseEnergyConsumption = baseEnergyConsumption;
    }
}
